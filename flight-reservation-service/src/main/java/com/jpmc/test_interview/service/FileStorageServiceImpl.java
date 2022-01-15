package com.jpmc.test_interview.service;

import com.jpmc.test_interview.config.FlightConfig;
import com.jpmc.test_interview.exception.DownloadTicketException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
@AllArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    private final FlightConfig flightConfig;

    @SneakyThrows
    @Override
    public Resource getFile(String fileName) {
        String absoluteFilePath = flightConfig.getPath()+ "/" + fileName;
        log.info("Absolute Path {} ",absoluteFilePath);
        Path file = Paths.get(absoluteFilePath);
        Resource resource = new UrlResource(file.toUri());

        if (resource.exists() || resource.isReadable()) {
            log.info("File is available to download.. {} ", absoluteFilePath);
            return resource;
        }
        else throw new DownloadTicketException("Could not load the file");
    }
}
