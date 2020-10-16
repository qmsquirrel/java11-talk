package de.qsmq.java11introduction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

class StringTest {


    String[] lines;

    @BeforeEach
    void readFile() {
        Resource resource = new ClassPathResource("string.txt");
        String content = "";
        try {
            content = Files.readString(resource.getFile().toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines = content.split("\n");
    }

    @Test
    void lengthOfAString() {
        String string = lines[0];
        int length = string.strip().length();
        assertThat(length).isEqualTo(3);

        String longString = "ABC".repeat(100);
        assertThat(longString.strip()).hasSize(300);
    }


}