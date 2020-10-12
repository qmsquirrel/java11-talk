package de.qsmq.java11introduction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class StringTest {


    String[] lines;

    @BeforeEach
    void readFile() {
        Resource resource = new ClassPathResource("users.txt");
        String content = "";
        try {
            content =
                    readFileAsString(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines = content.split("\n");
    }

    //@Test
    void lengthOfAString() {
        String string = lines[0];
        int length = string.trim().length();
        assertThat(length).isEqualTo(3);
    }


    private String readFileAsString(String filePath) {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            return "";
        }
        return new String(encoded, StandardCharsets.UTF_8);
    }


}