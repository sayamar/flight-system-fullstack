package com.jpmc.test_interview.service;

import org.springframework.core.io.Resource;

public interface FileStorageService {
    Resource getFile(String filePath);
}
