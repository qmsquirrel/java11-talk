package de.qsmq.java11introduction.repository;

import de.qsmq.java11introduction.entity.Competences;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ListCompetencesRepository {

    private List<Competences> competences;

    public Optional<Competences> findById(Integer id) {
        listSources();
        return competences.stream()
                .filter(competences -> competences.getId() == id)
                .findFirst();
    }

    void listSources() {
        competences = new ArrayList<>();
        competences.add(new Competences(100, "jimbo", "Basic"));
        competences.add(new Competences(200, "jimbo", "Fortran"));
        competences.add(new Competences(300, "jimbo", "PL/1"));
    }
}
