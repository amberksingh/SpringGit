package com.example.SpringGit.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryService {

    public boolean checkProductAvailability(int productId) {
        log.info("Checking product availability");
        return true;
    }
}

