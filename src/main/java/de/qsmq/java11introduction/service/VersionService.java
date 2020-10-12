package de.qsmq.java11introduction.service;

import de.qsmq.java11introduction.model.JVersion;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VersionService {

    private Stream<JVersion> javaStream() {
        return Stream.iterate(
                new JVersion(11, LocalDate.of(2018, 9, 1), 11 ),
                a -> a.nextVersion());
    }

    public JVersion getVersion(int version) {
        return javaStream()
                // remove all versions lower than version
                .filter(x -> ! (x.getVersion() < version))
                .findFirst()
                .get();
    }

    public List<JVersion> getCycle(int version) {
        JVersion version1 = getVersion(version);
        return javaStream()
                // remove all versions with lover lts
                .filter(x -> x.getLastLtsVersion() >= version1.getLastLtsVersion())
                .limit(10)
                // only keep version until next lts
                .filter(x -> x.getLastLtsVersion() == version1.getLastLtsVersion())
                .collect(Collectors.toList());
    }

}
