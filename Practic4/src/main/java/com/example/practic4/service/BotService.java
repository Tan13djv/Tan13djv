package com.example.practic4.service;

import com.example.practic4.dto.ValuteCursOnDate;
import com.example.practic4.dto.CentralRussianBankService;
import com.example.practic4.entity.ActiveChat;
import com.example.practic4.repository.ActiveChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.practic4.service.FinanceService.*;

@Service //Данный класс является сервисом
@Slf4j //Подключаем логирование из Lombok'a
@RequiredArgsConstructor
public class BotService extends TelegramLongPollingBot {

    private final CentralRussianBankService centralBankRussianService;
    private final ActiveChatRepository activeChatRepository;
    private final FinanceService financeService;
    private Map<Long, List<String>> previousCommands = new ConcurrentHashMap<>();
    @Value("${bot.api.key}") //Сюда будет вставлено значение из application.properties, в котором будет указан api key, полученный от BotFather
    private String apiKey;

    @Value("${bot.name}") //Как будут звать нашего бота
    private String name;
    //Это основной метод, который связан с обработкой сообщений
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        try {
            SendMessage response = new SendMessage();
            Long chatId = message.getChatId();
            response.setChatId(String.valueOf(chatId));
            if (CURRENT_RATES.equalsIgnoreCase(message.getText())) {
                for (ValuteCursOnDate valuteCursOnDate : centralBankRussianService.getCurrenciesFromCbr()) {
                    response.setText(StringUtils.defaultIfBlank(response.getText(), "") + valuteCursOnDate.getName() + " - " + valuteCursOnDate.getCourse() + "\n");
                }
            } else if (ADD_INCOME.equalsIgnoreCase(message.getText())) {
                response.setText("Отправьте мне сумму полученного дохода");
            } else if (ADD_SPEND.equalsIgnoreCase(message.getText())) {
                response.setText("Отправьте мне сумму расходов");
            } else {
                response.setText(financeService.addFinanceOperation(getPreviousCommand(message.getChatId()), message.getText(), message.getChatId()));
            }

            putPreviousCommand(message.getChatId(), message.getText());
            execute(response);
            if (activeChatRepository.findActiveChatByChatId(chatId).isEmpty()) {
                ActiveChat activeChat = new ActiveChat();
                activeChat.setChatId(chatId);
                activeChatRepository.save(activeChat);
            }
        } catch (TelegramApiException e) {
            log.error("Возникла проблема при получении данных от сервисов ЦБ РФ", e);
        } catch (Exception e) {
            log.error("Возникла проблема при получении данных от сервисов ЦБ РФ", e);
        }
    }

    //Данный метод будет вызван сразу после того, как данный бин будет создан - это обеспечено аннотацией Spring PostConstruct
    @PostConstruct
    public void start() {
        log.info("username: {}, token: {}", name, apiKey);
    }

    //Данный метод просто возвращает данные о имени бота и его необходимо переопределять
    @Override
    public String getBotUsername() {
        return name;
    }
    //Данный метод возвращает API ключ для взаимодействия с Telegram
    @Override
    public String getBotToken() {
        return apiKey;
    }
    public void sendNotificationToAllActiveChats(String message, Set<Long> chatIds) {
        for (Long id : chatIds) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(id));
            sendMessage.setText(message);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                log.error("Возникла проблема при получении данных от сервисов ЦБ РФ", e);
            }
        }
    }
    private void putPreviousCommand(Long chatId, String command) {
        if (previousCommands.get(chatId) == null) {
            List<String> commands = new ArrayList<>();
            commands.add(command);
            previousCommands.put(chatId, commands);
        } else {
            previousCommands.get(chatId).add(command);
        }
    }

    private String getPreviousCommand(Long chatId) {
        return previousCommands.get(chatId)
                .get(previousCommands.get(chatId).size() - 1);
    }
}
