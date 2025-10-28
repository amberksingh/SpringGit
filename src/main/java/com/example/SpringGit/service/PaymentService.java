package com.example.SpringGit.service;

import com.example.SpringGit.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    public void processPayment(Order order) throws InterruptedException {
        log.info("initiate payment for order " + order.getProductId());
        //System.out.println("initiate payment for order " + order.getProductId());
        //call actual payment gateway
        Thread.sleep(2000L);
        log.info("completed payment for order " + order.getProductId());
        //System.out.println("completed payment for order " + order.getProductId());
    }
}
