package com.example.SpringGit.interview2;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

class OrderEntity {

    private final String orderId;

    OrderEntity(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

}

class OrderService {

    public static String processOrder(OrderEntity order) {
        System.out.println(
                "Processing " + order.getOrderId() + " by " + Thread.currentThread().getName());

        try {
            Thread.sleep(3000); // simulate work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Processed " + order.getOrderId();
    }
}


public class CompletableFutureOrderDemo {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<OrderEntity> orders = List.of(
                new OrderEntity("ORDER-1"),
                new OrderEntity("ORDER-2"),
                new OrderEntity("ORDER-3")
        );

        //✅ The list is fully created immediately and its size is fixed (3).
        //❌ The work inside the futures is NOT finished at that time.
        //The list is a container of futures, not a container of results.
        //Creating a list of CompletableFutures is a synchronous operation that fixes the list size immediately,
        //while the asynchronous computations represented by those futures may still be running or incomplete.
        List<CompletableFuture<String>> futures = orders.stream()
                        .map(order ->
                                CompletableFuture.supplyAsync(
                                        () -> OrderService.processOrder(order),
                                        executor
                                )
                        )
                        .toList();

        // wait for ALL orders to complete
        System.out.println("wait for ALL orders to complete");

        IntFunction<CompletableFuture<?>[]> intFunction = (size) -> new CompletableFuture<?>[size];
        IntFunction<CompletableFuture<String>[]> intFunction2 = (size) -> new CompletableFuture[size];
        IntFunction<CompletableFuture[]> intFunction1 = CompletableFuture[]::new;

        //Important clarification (this fixes the confusion)
        //
        //allOf() does not wait for futures
        //
        //Futures do not wait for allOf()
        //
        //join() waits for all to complete
        //
        //all completes only after all futures complete
        //
        //So the dependency is:
        //
        //futures complete  →  all completes  →  join() unblocks

        //❌ allOf(...) does NOT wait
        //✅ all.join() is the ONLY place where waiting (blocking) happens
        //allOf() is created immediately, regardless of whether futures are finished or not.
        System.out.println("just before CompletableFuture.allOf() call...");

        //This line( .allOf() call ) executes instantly.
        CompletableFuture<Void> all =
                CompletableFuture.allOf(
                        futures.toArray(new CompletableFuture[0])
                        //futures.toArray(intFunction2)
                );

        all.join(); // blocks main until all done

        // collect results
        futures.forEach(f ->
                System.out.println(f.join())
        );

        executor.shutdown();

    }
}
