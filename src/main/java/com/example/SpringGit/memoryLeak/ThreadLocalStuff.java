package com.example.SpringGit.memoryLeak;

public class ThreadLocalStuff {

//    private static final ThreadLocal<byte[]> local = new ThreadLocal<>();
//    try {
//        local.set(new byte[10 * 1024 * 1024]); // heavy object
//        // use it here
//    } finally{
//        local.remove();   // REQUIRED to avoid leak
//    }

    private static ThreadLocal<String> userId = new ThreadLocal<>();

    public static void set(String id) {
        userId.set(id);   // put into thread's private storage
    }

    public static String get() {
        return userId.get();  // get it later
    }

    public static void clear() {
        userId.remove(); // remove to avoid memory leak
    }
}
//🔰 1. What is ThreadLocal? (VERY SIMPLE)
//
//Imagine you have multiple threads working in your application:
//
//Thread A
//Thread B
//Thread C
//
//
//Each thread wants to store its own private data, NOT shared with others.
//
//ThreadLocal provides a small storage box for each thread.
//
//Think of it as:
//
//ThreadLocal = a private locker attached to each thread.
//
//So Thread A has its own locker, Thread B has its own locker, etc.
//
//🌰 2. Beginner Example: Store userId for each request
//
//Let’s say your web app receives 3 requests:
//
//Request 1 → user = "A"
//Request 2 → user = "B"
//Request 3 → user = "C"
//
//
//We want each thread to know which user it is handling —
//WITHOUT passing userId in every method.
//
//ThreadLocal makes it easy:
//
//public class UserContext {
//    private static ThreadLocal<String> userId = new ThreadLocal<>();
//
//    public static void set(String id) {
//        userId.set(id);   // put into thread's private storage
//    }
//
//    public static String get() {
//        return userId.get();  // get it later
//    }
//
//    public static void clear() {
//        userId.remove(); // remove to avoid memory leak
//    }
//}
//
//🧵 3. How it works internally (simple diagram)
//
//Imagine 3 threads:
//
//Thread-1 locker → "A"
//Thread-2 locker → "B"
//Thread-3 locker → "C"
//
//
//Each locker is separate:
//
//ThreadLocal
//    |
//    |-- Thread-1 → "A"
//    |-- Thread-2 → "B"
//    |-- Thread-3 → "C"
//
//⚠️ 4. HOW MEMORY LEAK HAPPENS (THE REAL PROBLEM)
//
//This is the most important part.
//
//In a real web server like Tomcat, Jetty, Undertow:
//
//Threads are reused
//
//They do NOT die after request
//
//ThreadLocal values stay attached to the thread
//
//Let’s simulate:
//
//🧵 Thread-1 handles Request A:
//UserContext.set("A");   // put "A" into ThreadLocal
//
//
//👉 Now Thread-1's locker contains "A".
//
//✔ Request ends — but we FORGOT to call remove()
//
//Thread-1 STILL holds "A".
//
//Now Thread-1 is reused for Request X.
//
//Thread-1 STILL carries "A" internally.
//
//After 10,000 requests → each thread accumulates lots of data
//
//That data NEVER gets garbage-collected because:
//
//The thread still exists
//
//ThreadLocal is still attached
//
//GC thinks it's "in use"
//
//Eventually → memory leak → OutOfMemoryError.
//
//📦 5. Real Leaky Example
//private static final ThreadLocal<byte[]> local = new ThreadLocal<>();
//
//public void process() {
//    // allocate 10MB array for each request!
//    local.set(new byte[10 * 1024 * 1024]);
//}
//
//
//If a thread handles 100 requests:
//
//100 × 10 MB = 1 GB leak
//
//And there are 200 threads → 200GB leak 🤯
//(Your app will crash LONG before that.)
//
//🔥 6. Why ThreadLocal leaks are COMMON
//
//Because developers FORGET to remove:
//
//local.set(value);
/// / no remove()
//
//
//Thread pools keep threads alive → memory stays FOREVER.
//
//🛠️ 7. The FIX (VERY SIMPLE)
//⭐ Always remove inside finally block
//try {
//    local.set(new byte[10 * 1024 * 1024]); // heavy object
//    // use it here
//} finally {
//    local.remove();   // REQUIRED to avoid leak
//}
//
//Why inside finally?
//
//Because:
//
//Even if code throws exception
//
//Or logic returns early
//
//→ The cleanup STILL runs.
//
//🟢 8. Beginner Real Use Case (Proper and Safe)
//Use Case: Store userId per request (like a request context)
//public class UserContext {
//
//    private static final ThreadLocal<String> user = new ThreadLocal<>();
//
//    public static void startRequest(String userId) {
//        user.set(userId);
//    }
//
//    public static String getCurrentUser() {
//        return user.get();
//    }
//
//    public static void endRequest() {
//        user.remove();
//    }
//}
//
//How it works in a web request:
//public void handleRequest() {
//    UserContext.startRequest("amber");
//
//    try {
//        System.out.println(UserContext.getCurrentUser()); // "amber"
//    } finally {
//        UserContext.endRequest();  // avoid leak!
//    }
//}
//
//🧠 9. Interview-ready explanation
//
//“ThreadLocal creates thread-specific storage.
//In application servers with thread pools, if ThreadLocal.set() is called but remove() is not called,
//the data stays attached to the long-lived thread and becomes a memory leak.
//To fix this, always call ThreadLocal.remove() in a finally block.”
//
//📝 10. Diagram Summary
//Before remove():
//Thread-1 locker → bigObject(10MB)
//Thread-2 locker → bigObject(10MB)
//Thread-3 locker → bigObject(10MB)
//
//After remove():
//Thread-1 locker → empty
//Thread-2 locker → empty
//Thread-3 locker → empty
//
//
//GC can now free the objects → no leak.
//
//🚀 Ready for Cache Map memory leak explanation?
//
//If yes, say “Explain cache map leak next.”
//
//You said:
//but how it wcomes into picture in rest get endpoint lets say, the user passes @PathVariable with evry req. how this solves in that situation..ThreadLocal what is it achieving in real life..understood the issuebut how it comes in springboot microservices apis call i dont get it..userId is utilised how here?
//ChatGPT said:
//
//Excellent — now let’s connect ThreadLocal to real-world Spring Boot REST APIs,
//because THAT is the part most beginners miss.
//
//You already understand:
//
//What ThreadLocal is
//
//How it leaks
//
//Now let's explain:
//
//✅ Why ThreadLocal is used in real Spring Boot microservices
//✅ How it appears in APIs
//✅ What problem it solves
//✅ When you actually need it
//🧩 1. In a REST API, everything is stateless
//
//Example:
//
//@GetMapping("/orders/{userId}")
//public List<Order> getOrders(@PathVariable String userId) {
//   ...
//}
//
//
//Here the userId is explicitly passed.
//
//So why ThreadLocal?
//
//Because not every method has access to that userId.
//
//⭐ REAL LIFE USE CASE:
//ThreadLocal solves the problem of passing context through many layers
//
//Look at a real microservice structure:
//
//Controller → Service → Repository → KafkaPublisher → AuditLogger
//
//
//Only the controller knows the userId.
//
//❌ Without ThreadLocal
//
//You must pass userId manually into EVERY method:
//
//controller calls service(userId)
//service calls repo(userId)
//repo calls kafkaPublisher(userId)
//kafkaPublisher calls auditLogger(userId)
//
//
//This becomes horrible.
//
//⭐ ThreadLocal solves this:
//✔ Controller sets userId ONCE
//✔ Any deep-layer component can read it WITHOUT parameters
//
//Like this ↓
//
//@GetMapping("/orders/{userId}")
//public List<Order> getOrders(@PathVariable String userId) {
//    UserContext.set(userId);     // store in ThreadLocal
//    try {
//        return orderService.getOrders();
//    } finally {
//        UserContext.clear();     // avoid leak
//    }
//}
//
//In Service Layer:
//public List<Order> getOrders() {
//    String user = UserContext.get(); // no need to pass userId
//    System.out.println("Processing for: " + user);
//    return repo.findOrders();
//}
//
//In Repository Layer:
//public List<Order> findOrders() {
//    logger.info("DB query executed by user: " + UserContext.get());
//}
//
//In Kafka Producer:
//kafkaTemplate.send("audit", "User " + UserContext.get() + " fetched orders");
//
//💡 MAIN ADVANTAGE: No need to pass userId everywhere
//
//ThreadLocal = global variable, but private to the thread.
//
//🧠 4. Real-world Spring Boot use cases
//🟢 Use Case 1 — Logging user identity
//
//You want every log line to automatically include:
//
//userId=amber123
//
//
//Instead of adding userId in EVERY log statement:
//
//Use ThreadLocal:
//
//MDC.put("userId", userId);  // MDC uses ThreadLocal internally
//
//
//This is EXACTLY how logging frameworks (Log4j, Logback) add:
//
//requestId
//
//userId
//
//correlationId
//
//They all use ThreadLocal internally.
//
//🟢 Use Case 2 — Correlation ID (microservices tracing)
//
//Every incoming request has a header:
//
//X-Correlation-Id: 98765
//
//
//Instead of passing it through every method, store in ThreadLocal:
//
//CorrelationIdHolder.set(id);
//
//
//Then any component can log:
//
//Starting payment for correlationId=98765
//
//
//This is how distributed tracing works.
//
//🟢 Use Case 3 — Transaction context
//
//JPA Transaction Manager uses ThreadLocal to bind:
//
//EntityManager
//
//Transaction state
//
//So each thread has its own transaction.
//
//🟢 Use Case 4 — SecurityContextHolder (Spring Security)
//
//This is the BIGGEST example:
//
//When a user authenticates, Spring stores user info in a ThreadLocal:
//
//SecurityContextHolder.getContext().getAuthentication()
//
//
//Spring Security runs:
//
//ThreadLocal<SecurityContext> contextHolder
//
//
//Without ThreadLocal, EVERY method would need:
//
//username
//
//roles
//
//permissions
//
//Instead Spring injects them magically — because ThreadLocal carries the authentication.
//
//🟥 So what ThreadLocal is actually achieving in APIs?
//✔ It carries "per request" data across layers
//✔ Without changing method signatures
//✔ Without passing parameters manually
//✔ Automatically available anywhere during that request
//✔ And automatically removed after request
//🧵 ThreadLocal Lifecycle in a REST API
//Step 1 → Request enters
//
//Thread-1 starts processing.
//
//Step 2 → Filter/Controller puts userId in ThreadLocal
//UserContext.set(userId);
//
//Step 3 → Service/Repo/Kafka/Logger fetch it
//UserContext.get();
//
//Step 4 → Request ends → remove()
//UserContext.clear();
//
//Now Thread is clean. No leak.
//🎯 FINAL BEGINNER SUMMARY
//
//ThreadLocal is used to store data that belongs to a SINGLE HTTP request,
//but needs to be accessed DEEP inside the system without passing it manually.
//
//It solves:
//
//logging user identity automatically
//
//passing correlationId
//
//storing authentication context
//
//transaction management
//
//It appears in real microservice APIs because:
//
//Only controller gets the header/path data
//
//Other layers need it
//
//ThreadLocal carries it without modifying every method signature
//so this part executes per request and is only belonging to that thread for only this request ? for new request one more UserContext will come into picture for that new thread?
//ChatGPT said:
//
//Great — now you’re asking the EXACT right question.
//Let’s explain it slowly, clearly, visually.
//
//✅ 1. Does UserContext.set(userId) run per request?
//
//👉 YES — one time per HTTP request.
//
//When a request arrives:
//
//Server picks a thread from thread-pool (let’s say Thread-12)
//
//Controller/Filter runs:
//
//UserContext.set(userId);
//
//
//That userId now belongs ONLY to Thread-12.
//
//So Thread-12 = userId of THIS request only.
//
//✅ 2. Is a NEW UserContext created for every request?
//
//👉 NO — UserContext is not recreated.
//UserContext is just a class containing:
//
//private static ThreadLocal<String> user = new ThreadLocal<>();
//
//
//ThreadLocal creates per-thread storage, not per-request storage.
//
//So what gets created per request?
//
//✔ Only a value inside that Thread’s private locker.
//❌ NOT a new ThreadLocal
//❌ NOT a new UserContext object
//
//🧵 3. Then how do different requests get different userIds?
//
//Let’s visualize using 3 requests:
//
//Request A → userId=A
//Request B → userId=B
//Request C → userId=C
//
//
//Assume thread pool has 3 threads:
//
//Thread-1
//Thread-2
//Thread-3
//
//When Request A uses Thread-1:
//UserContext.set("A");
//
//
//Thread-1 locker → "A"
//
//When Request B uses Thread-3:
//UserContext.set("B");
//
//
//Thread-3 locker → "B"
//
//When Request C uses Thread-2:
//UserContext.set("C");
//
//
//Thread-2 locker → "C"
//
//📌 4. Do threads get reused for the next request?
//
//👉 YES
//
//Thread pools reuse threads to avoid creating new ones.
//
//Example:
//
//Request A uses Thread-1
//
//Request B uses Thread-2
//
//Request C uses Thread-1 again
//
//This is NORMAL behavior.
//
//❗ BUT THIS IS WHERE LEAK HAPPENS (if you don’t remove)
//
//If Request A sets:
//
//UserContext.set("A");
//
//
//and you DO NOT call:
//
//UserContext.remove();
//
//
//Then Thread-1 STILL holds "A" when request ends.
//
//If next request (Request D) also uses Thread-1:
//
//UserContext.get()  → WRONG: value is still "A"
//
//
//And Thread-1 memory keeps growing → memory leak.
//
//⭐ 5. So how to ensure ThreadLocal belongs ONLY to the current request?
//Use a try-finally block:
//UserContext.set(userId);
//try {
//    // process
//} finally {
//    UserContext.clear();  // removes value for this thread
//}
//
//
//Now:
//
//When Request A finishes → Thread-1 locker becomes empty
//
//Thread-1 is safe for next request
//
//No leak
//
//Correct userId per request
//
//🎯 6. FINAL ANSWER (Simple)
//✔ YES — UserContext.set(userId) runs once per request
//✔ The stored value belongs to only the thread handling that request
//✔ Next request may use a different thread → gets its own storage
//✔ Threads are reused → so must call remove()
//✔ UserContext is NOT recreated per request
//
//Only the ThreadLocal value is created for that request.
//
//🧠 Visual Summary
//BEFORE request:
//Thread-1 locker → empty
//Thread-2 locker → empty
//Thread-3 locker → empty
//
//DURING Request A:
//UserContext.set("A") on Thread-1
//Thread-1 locker → "A"
//
//AFTER request:
//UserContext.remove()
//Thread-1 locker → empty
//
//NEXT Request uses Thread-1:
//Thread-1 locker is fresh → no leak
