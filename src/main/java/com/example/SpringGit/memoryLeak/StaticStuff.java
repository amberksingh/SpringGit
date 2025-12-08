package com.example.SpringGit.memoryLeak;

import java.util.ArrayList;
import java.util.List;

public class StaticStuff {

    private static final List<String> users = new ArrayList<>();

    public void addUser(String user) {
        users.add(user);   // grows forever
    }

    public static void clearCache() {
        users.clear();
    }

    public static void main(String[] args) {

        StaticStuff staticStuff = new StaticStuff();
        staticStuff.addUser("hello");
        staticStuff.addUser("good");
        staticStuff.addUser("bad");
        staticStuff.addUser("best");
        staticStuff.addUser("morning");

        System.out.println("users = " + users);
        clearCache();
        System.out.println("users = " + users);

//        byte[] a = new byte[11];
//        System.out.println(a.length);

    }

}
//✅ 1. FIRST — Simulate the Static Memory Leak Example
//
//Your code:
//
//private static final List<String> users = new ArrayList<>();
//
//❗Why this causes a memory leak in a real server
//
//static means this List is created when the class loads
//(usually at application startup).
//
//It stays for the entire lifetime of the JVM process.
//
//Even if all objects of MemoryLeakStuff are destroyed,
//users List is still alive.
//
//If your application keeps adding users (1000s, 10 lakhs, 1 crore…),
//the List grows forever → memory leak.
//
//🧪 2. What Your Simulation Code Does
//memoryLeakStuff.addUser("hello");
//...
//System.out.println("users = " + users);
//clearCache();
//System.out.println("users = " + users);
//
//
//Output:
//
//users = [hello, good, bad, best, morning]
//users = []
//
//❗Does this simulate a memory leak?
//
//Not really.
//
//Because in your small main() program, you manually call clearCache().
//
//But in a real running application (Spring Boot, backend service):
//
//→ Requests keep adding items to the static list
//→ Nobody clears it
//→ List grows forever
//→ GC cannot delete items
//→ Memory leak happens
//🧩 3. When Should clearCache() Be Called in a Real Application?
//
//You must call clearCache() in situations where old data is no longer needed.
//
//Examples:
//
//🕒 Scenario 1 — Periodically clean cache (scheduled job)
//@Scheduled(fixedRate = 60000)  // every 1 minute
//public void cleanup() {
//    MemoryLeakStuff.clearCache();
//}
//
//
//Purpose:
//
//Prevent list from growing too large
//
//Allow GC to free old objects
//
//📌 Scenario 2 — When a feature is done (e.g., on logout, end of session)
//
//If this was a session list:
//
//public void logout() {
//    MemoryLeakStuff.clearCache();
//}
//
//🚀 Scenario 3 — When server shutdown hook runs
//Runtime.getRuntime().addShutdownHook(new Thread(MemoryLeakStuff::clearCache));
//
//👀 4. How Does Garbage Collection Work? (Beginner Explanation)
//
//GC removes objects when no references are pointing to them.
//
//Example:
//users -> ["hello"]
//
//
//The List is still referenced → GC CANNOT delete it.
//
//After clearCache():
//
//users -> []
//
//
//Now elements are not referenced by anything → GC can delete them.
//
//❓ When does GC run?
//
//❌ Not immediately after clearCache()
//
//❌ Not at fixed times
//
//✔ GC runs automatically, when JVM decides memory pressure is high
//
//Java GC decides based on:
//
//heap usage percentage
//
//young/old generation fullness
//
//allocation failures
//
//GC tuning algorithms
//
//You cannot force GC (calling System.gc() is only a suggestion).
//
//🧹 5. finalize() vs. Modern GC (Beginner Explanation)
//Old Java:
//
//finalize() was a method that ran before GC collected an object.
//
//Developers misused it → performance issues → unpredictable behavior.
//
//Modern Java:
//
//finalize() is deprecated and removed.
//
//Java uses automatic, optimized garbage collectors:
//
//G1 (default)
//
//ParallelGC
//
//ZGC (low latency)
//
//Shenandoah
//
//You no longer write cleanup in finalize() — instead:
//
//Close resources using try-with-resources
//
//Remove references manually (like clearCache)
//
//🔥 6. Very Important: Does restarting the application remove the leak?
//✔ YES — restarting JVM always clears memory.
//
//Because:
//
//A memory leak lives inside the JVM process memory
//
//When the process stops → all heap is cleared
//
//When the app starts again → fresh heap, no more leak
//
//So:
//
//Action	Leak resolved?	Why?
//Restart JVM (server restart)	✔ Yes	New JVM gets fresh memory
//Redeploy war/jar	✔ Yes	JVM reloads class and static fields
//Reload spring context only	❌ No	Same JVM still running
//
//This is why memory leaks often get fixed temporarily by restarting servers, but leak returns later.
//
//🧠 7. Summary of Your Static Example — Interview Ready
//private static final List<String> users = new ArrayList<>();
//
//Why this leaks?
//
//Static = lives as long as JVM lives
//
//Data keeps getting added
//
//Nothing removes old data
//
//GC cannot clean because List still references items
//
//Heap keeps growing
//
//Eventually → OutOfMemoryError
//
//How to fix?
//
//Add size limit
//
//Add expiry
//
//Clear periodically
//
//Use proper caching (Caffeine, Redis)
//
//Avoid static unless truly needed
//
//Ready for ThreadLocal?
//
//If you say “ThreadLocal next”, I’ll explain:
//
//what ThreadLocal is
//
//why it leaks
//
//real-world use case
//
//complete beginner explanation
//
//diagram
//
//correct fix (remove())
//
//Just tell me: “ThreadLocal next”.
//
//You said:
//
//users -> \["hello"\]
//
//here the list needs tobe removed or stored string objs? when list gets empty, then whole list is ready to be GC right?
//ChatGPT said:
//
//Great question — this is exactly the right doubt when learning memory leaks.
//
//Let’s answer VERY clearly, beginner-friendly:
//
//✅ **1. When we say “memory leak happens because the static list never gets GC’ed”…
//
//do we mean the list object itself or the strings inside it?**
//
//✔ The memory leak is caused by the objects stored inside the list,
//
//not the list object itself.
//
//Let’s break this down.
//
//✅ 2. What actually gets GC’ed?
//Case 1️⃣ — The List is NOT cleared
//users -> ["hello", "good", "bad"]
//
//
//users list is static → reference stays forever
//
//It contains references to 3 strings
//
//So:
//
//Object	GC can delete?	Why?
//List object	❌ No	because static reference keeps it alive
//String objects	❌ No	because list still references them
//
//Thus → memory leak continues.
//
//✅ 3. Case 2️⃣ — We call clearCache() which does:
//public static void clearCache() {
//    users.clear();
//}
//
//
//Now the situation is:
//
//users -> []
//(contains 0 elements)
//
//After clear():
//Object	GC can delete?	Why?
//Strings	✔ Yes	NO more references in list → eligible for GC
//List object	❌ No	Still referenced by static variable
//Very important:
//
//The strings become eligible for GC
//
//The list object itself does NOT get GC’ed because users variable still points to it.
//
//This is perfectly fine.
//List object is small — it’s the contents that mattered.
//
//🧠 4. Does the List NEED to be GC’ed?
//
//No.
//
//A static list will always stay alive as long as JVM runs.
//
//But this is NOT a problem:
//
//➡️ Empty list consumes almost no heap
//(only a tiny internal array, like size 0–10)
//
//➡️ The important part is that the objects stored inside it were freed.
//
//So:
//
//✔ We must free the contents, not the list object itself.
//🎯 5. FINAL ANSWER (Interview Ready)
//
//“The memory leak happens because the static list keeps references to the string objects.
//When we call clear(), the list becomes empty and the string objects are eligible for GC.
//The list object itself is not GC’ed because it is static, but that is fine — it's small.
//We only needed to remove the contents so GC can reclaim memory.”
//
//🧪 Example Visualization
//
//Before clear():
//
//static users list
//      |
//      v
//[ "a", "b", "c" ]
//
//
//After clear():
//
//static users list
//      |
//      v
//[ ]
//
//
//GC sees:
//
//"a" — no references → delete
//
//"b" — no references → delete
//
//"c" — no references → delete
//
//List remains because static → OK.
