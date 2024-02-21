package com.czareg.school.util;

import lombok.experimental.UtilityClass;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;

@UtilityClass
public final class FileUtils {

    public static String readFileAsString(String fileName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        return new String(Files.readAllBytes(classPathResource.getFile().toPath()));
    }
}
