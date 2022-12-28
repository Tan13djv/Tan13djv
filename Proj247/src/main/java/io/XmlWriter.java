package io;
import org.w3c.dom.Node;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;


public class XmlWriter {

    public static Logger log;

    public static void MakeFile(byte[] outputStream, String filename) throws IOException {

        log = Logger.getLogger(XmlWriter.class.getName());

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(filename);
            log.info("Файл xml создан");
        }
        catch (Exception e){
            log.severe("Файл xml не создан: "+ e.toString());
        }

        fileOutputStream.write(outputStream);


    }

}