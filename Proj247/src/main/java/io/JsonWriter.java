package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class JsonWriter {
    public static Logger log;

    public static void MakeFile(byte[] outputStream, String filename) throws IOException {

        log = Logger.getLogger(JsonWriter.class.getName());

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(filename);
            log.info("Файл json создан");
        }
        catch (Exception e){
            log.severe("Файл json не создан: "+ e.toString());
        }

        fileOutputStream.write(outputStream);


    }
}
