package com.example.practic4.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.PostConstruct;

@Service //Данный класс является сервисом
@Slf4j //Подключаем логирование из Lombok'a
@RequiredArgsConstructor
public class BotService extends TelegramLongPollingBot {


    @Value("${bot.api.key}") //Сюда будет вставлено значение из application.properties, в котором будет указан api key, полученный от BotFather
    private String apiKey;

    @Value("${bot.name}") //Как будут звать нашего бота
    private String name;
    //Это основной метод, который связан с обработкой сообщений
    @Override
    public void onUpdateReceived(Update update) {
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
}
