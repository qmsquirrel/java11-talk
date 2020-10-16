package de.qsmq.java11introduction.service;

import de.qsmq.java11introduction.entity.Competences;
import de.qsmq.java11introduction.repository.MapCompetencesRepository;
import de.qsmq.java11introduction.repository.ListCompetencesRepository;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CompetencesService {

    private final MapCompetencesRepository mainCompetencesRepository;
    private final ListCompetencesRepository additionalCompetencesRepository;

    public CompetencesService(MapCompetencesRepository mainTokenRepository, ListCompetencesRepository additionalTokenRepository) {
        this.mainCompetencesRepository = mainTokenRepository;
        this.additionalCompetencesRepository = additionalTokenRepository;
    }

    public Competences getCompetences(int i) {
        return mainCompetencesRepository.findById(i).orElseThrow();
    }


    public List<Integer> getIdsByIdentifier(String identifier) {
        List<Competences> competences = mainCompetencesRepository.findByName(identifier).orElseThrow();
        return competences.stream()
                .map( t -> t.getId())
                .collect(Collectors.toList());
    }

    public Competences getCompetencesInAnyRepository(int i) {
        Optional<Competences> competences = mainCompetencesRepository.findById(i)
                .or(() -> additionalCompetencesRepository.findById(i));
        return competences.get();
    }

}
