package de.qsmq.java11introduction.restcontroller;

import de.qsmq.java11introduction.entity.Competences;
import de.qsmq.java11introduction.service.CompetencesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/competences")
public class CompetencesController {

    private final CompetencesService competencesService;

    public CompetencesController(CompetencesService competencesService) {
        this.competencesService = competencesService;
    }

    @GetMapping
    public Competences getToken(@RequestParam("id") int i) {
        return competencesService.getCompetences(i);
    }


    @GetMapping("/name")
    public List<Integer> getIds(@RequestParam("name") String identifier) {
        return competencesService.getIdsByIdentifier(identifier);
    }

    @GetMapping("/everywhere")
    public Competences getTokenEverywhere(@RequestParam("id") int i) {
        return competencesService.getCompetencesInAnyRepository(i);
    }

}
