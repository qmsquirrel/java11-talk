package de.qsmq.java11introduction.restcontroller;

import de.qsmq.java11introduction.model.JVersion;
import de.qsmq.java11introduction.service.VersionService;
import de.qsmq.java11introduction.service.XMLService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.util.List;

@RestController
@RequestMapping("/java")
public class JavaController {

    private final VersionService versionService;
    private final XMLService xmlService;

    public JavaController(VersionService versionService, XMLService xmlService) {
        this.versionService = versionService;
        this.xmlService = xmlService;
    }

    @GetMapping("/version")
    public JVersion getVersion(@RequestParam("version") int versionNumber) {
        if (versionNumber < 11) {
            throw new IllegalArgumentException();
        }
        return versionService.getVersion(versionNumber);
    }

    @GetMapping("/version/xml")
    public String getXmlVersion(@RequestParam("version") int versionNumber) throws JAXBException {
        if (versionNumber < 11) {
            throw new IllegalArgumentException();
        }
        return xmlService.convertVersionToXml(versionService.getVersion(versionNumber));
    }

    @GetMapping("/cycle")
    public List<JVersion> getCycle(@RequestParam("version") int versionNumber) {
        if (versionNumber < 11) {
            throw new IllegalArgumentException();
        }
        return versionService.getCycle(versionNumber);
    }
}
