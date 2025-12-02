package com.example.SpringGit.controller;

import com.example.SpringGit.dto.Order;
import com.example.SpringGit.service.OrderFulfillmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/async")
public class OrderFulfillmentController {

    @Autowired
    private OrderFulfillmentService service;

    @PostMapping("/order")
    public ResponseEntity<Order> processOrder(@RequestBody Order order) throws InterruptedException {

        service.processOrder(order); // SYNCHRONOUS
//        // asynchronous
//        service.notifyUser(order);
//        service.assignVendor(order);
//        service.packaging(order);
//        service.assignDeliveryPartner(order);
//        service.assignTrailerAndDispatch(order);
//        return ResponseEntity.ok(order);

        // ASYNCHRONOUS
        CompletableFuture<Void> chain =
                service.notifyUser(order)
                        .thenCompose(v -> {
                            try {
                                return service.assignVendor(order);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .thenCompose(v -> {
                            try {
                                return service.packaging(order);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .thenCompose(v -> {
                            try {
                                return service.assignDeliveryPartner(order);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .thenCompose(v -> {
                            try {
                                return service.assignTrailerAndDispatch(order);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        });

        //chain.join();//commented so that main threads returns the response and not blocked by these background threads calls
        return ResponseEntity.ok(order);
        //return ResponseEntity.status(HttpStatus.OK).body(order);
    }
}
