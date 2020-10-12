package de.qsmq.java11introduction.repository;

import de.qsmq.java11introduction.entity.Competences;

import java.util.*;

import org.springframework.stereotype.Component;

@Component
public class MapCompetencesRepository {

    private Map<Integer, Competences> competencesMap = new HashMap<>();



    public Optional<Competences> findById(Integer id) {
         Competences competences = competencesMap.get(id);
         if (competences != null) {
             return Optional.of(competences);
         } else {
             return  Optional.empty();
         }
    }
    public Optional<List<Competences>> findByName(String identifier) {
        List<Competences> competences = new ArrayList<>();
        competencesMap.forEach((id, competence) -> {
            if (competence.getName().equals(identifier)) {
                competences.add(competence);
            }
        });
        return Optional.of(competences);
    }

}
