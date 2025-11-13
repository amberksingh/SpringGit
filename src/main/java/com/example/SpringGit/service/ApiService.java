package com.example.SpringGit.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class ApiService {


    int paymentAttempt = 1;
    int refundAttempt = 1;
    int genericAttempt = 1;
    int arithmeticAttempt = 1;

    @Retryable(retryFor = {RuntimeException.class}, maxAttempts = 2, backoff = @Backoff(value = 2000))
    public void retryService() {

        System.out.println("Attempt : " + genericAttempt);
        genericAttempt++;
        throw new RuntimeException("retryService() simulating retry..");
    }

    @Retryable(retryFor = {RuntimeException.class}, maxAttempts = 3, backoff = @Backoff(value = 3000))
    public void retryPayment(String paymentId) {

        System.out.println("Retry Payment for paymentId :" + paymentId + " attempt " + paymentAttempt);
        paymentAttempt++;
        throw new RuntimeException("retryPayment exception..");
    }

    @Retryable(retryFor = {RuntimeException.class}, maxAttempts = 4, backoff = @Backoff(value = 2000))
    public void retryRefund(Long refundId) {

        System.out.println("Retry Refund for refundId : " + refundId + " attempt " + refundAttempt);
        refundAttempt++;
        throw new RuntimeException("retryRefund exception..");
    }

    @Retryable(retryFor = {ArithmeticException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public ResponseEntity<String> simulateArithmeticException(String value) {

        System.out.println("Inside simulateArithmeticException attempt : " + arithmeticAttempt);
        arithmeticAttempt++;
        if (Integer.parseInt(value) < 10) {
            throw new ArithmeticException("value less than 10");
        } else {
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
    }

    @Recover
    public void recoverPayment(RuntimeException e, String paymentId) {

        System.out.println("Inside recoverPayment().." + paymentId);
        System.out.println("Exception : " + e.getMessage());
    }

    @Recover
    public void recoverRefund(RuntimeException e, Long refundId) {

        System.out.println("Inside recoverRefund().." + refundId);
        System.out.println("Exception : " + e.getMessage());
    }

    @Recover
    public void recover(RuntimeException e) {

        System.out.println("Generic recover for unhandled retryable methods");
        System.out.println("Exception : " + e.getMessage());
    }


    @Recover
    public ResponseEntity<String> recoverArithmeticSimulate(ArithmeticException e) {

        System.out.println("ArithmeticException simulate recover method");
        System.out.println("Exception : " + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Recovery: Invalid arithmetic value. " + e.getMessage());
    }
}
