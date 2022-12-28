package util;

import io.ImportData;
import model.DataStructure;
import model.Student;
import model.University;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.logging.Logger;


public class XmlUtil {
    public static Logger log;

    public byte[] marshal(DataStructure struct) throws JAXBException {
        log = Logger.getLogger(XmlUtil.class.getName());
        log.severe("in marshal");
        JAXBContext context = JAXBContext.newInstance(struct.getClass());
        log.severe("create context in marshal");
        Marshaller marshaller = context.createMarshaller();
        log.severe("create marshaller in marshal");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        log.severe("property was set in marshal");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        log.severe("new stream was created in marshal");
        marshaller.marshal(struct, outputStream);
        log.severe("marshal was called in marshal");
        return outputStream.toByteArray();
    }


}