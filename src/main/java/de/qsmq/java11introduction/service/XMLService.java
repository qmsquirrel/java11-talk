package de.qsmq.java11introduction.service;

import de.qsmq.java11introduction.model.JVersion;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@Service
public class XMLService {

    public String convertVersionToXml(JVersion version) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(JVersion.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();

        jaxbMarshaller.marshal(version, sw);

        return sw.toString();
    }


}
