package com.example.SpringGit;

import com.example.SpringGit.controller.EntryController;
import com.example.SpringGit.service.ApiService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EntryController.class)
public class EntryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ApiService service;

    @Test
    void simulateArithmetic_okResponse() throws Exception {

        // Arrange
        when(service.simulateArithmeticException("10"))
                .thenReturn(new ResponseEntity<>("Success", HttpStatus.CREATED));

        // Act + Assert
        mockMvc.perform(get("/git/arithmetic/10"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Success"));

        // Verify delegation to service with correct path var
        verify(service).simulateArithmeticException("10");
    }


    //mockMvc.perform(get("/git/count"))
    //       .andExpect(status().isOk())
    //       .andExpect(content().string("42"));
    //
    //       for
    //
    //       @GetMapping("/count")
    //       public ResponseEntity<Integer> getCount() {
    //    return ResponseEntity.ok(42);
    //}
    //In JSON, the response body will be:
    //
    //42
    //Because even primitives are serialized to text (not binary).
    //Internally it’s just "42" in the response body.


    //@GetMapping("/student")
    //public ResponseEntity<Student> getStudent() {
    //    return ResponseEntity.ok(new Student(1, "Arjun", 20));
    //}
    //Student class:
    //
    //java
    //Copy code
    //public class Student {
    //    private int id;
    //    private String name;
    //    private int age;
    //
    //    // constructors, getters, setters
    //}
    //Response JSON:
    //
    //json
    //Copy code
    //{
    //  "id": 1,
    //  "name": "Arjun",
    //  "age": 20
    //}
    //✅ 4️⃣ How to test JSON response with MockMvc
    //Use jsonPath() for precise field assertions:
    //
    //java
    //Copy code
    //mockMvc.perform(get("/git/student"))
    //       .andExpect(status().isOk())
    //       .andExpect(jsonPath("$.id").value(1))
    //       .andExpect(jsonPath("$.name").value("Arjun"))
    //       .andExpect(jsonPath("$.age").value(20));
    //jsonPath works like XPath but for JSON — it lets you target specific fields without comparing the entire JSON string.
    //
    //🧩 5️⃣ Alternatively — assert entire JSON
    //If you expect small fixed JSON output:
    //
    //java
    //Copy code
    //mockMvc.perform(get("/git/student"))
    //       .andExpect(status().isOk())
    //       .andExpect(content().json("{\"id\":1,\"name\":\"Arjun\",\"age\":20}"));
    //✅ content().json() compares JSON structure ignoring field order.
    //
    //⚙️ 6️⃣ If controller returns a list of objects
    //Example:
    //
    //java
    //Copy code
    //@GetMapping("/students")
    //public ResponseEntity<List<Student>> getAllStudents() {
    //    return ResponseEntity.ok(List.of(
    //        new Student(1, "Arjun", 20),
    //        new Student(2, "Riya", 21)
    //    ));
    //}
    //Response JSON:
    //
    //json
    //Copy code
    //[
    //  {"id":1,"name":"Arjun","age":20},
    //  {"id":2,"name":"Riya","age":21}
    //]
    //✅ Test using JSON path:
    //
    //java
    //Copy code
    //mockMvc.perform(get("/git/students"))
    //       .andExpect(status().isOk())
    //       .andExpect(jsonPath("$[0].name").value("Arjun"))
    //       .andExpect(jsonPath("$[1].age").value(21));
    //Or full array:
    //
    //java
    //Copy code
    //mockMvc.perform(get("/git/students"))
    //       .andExpect(content().json("[{\"id\":1,\"name\":\"Arjun\",\"age\":20},{\"id\":2,\"name\":\"Riya\",\"age\":21}]"));



    //This setup means:
    //
    //✅ Spring Boot creates only the web layer (the EntryController and its MVC wiring).
    //
    //🚫 It does not start the full Spring Boot application (no real DB, no services, etc.).
    //
    //🧩 Your ApiService bean is replaced with a Mockito mock, which gets injected into the controller via @Autowired.
    //
    //So:
    //
    //When your test performs a GET request, the actual controller endpoint runs, but any call to the service inside
    // it goes to your mock — not the real ApiService.
    //
    //⚙️ 2️⃣ Flow when the test runs
    //
    //Here’s the exact flow for your test:
    //
    //mockMvc.perform(get("/git/arithmetic/10"))
    //
    //Simulates a real HTTP request to your controller.
    //
    //Triggers EntryController.simulateArithmeticException().
    //
    //Inside the controller:
    //
    //return service.simulateArithmeticException(value);
    //
    //
    //The controller calls the mocked ApiService (not the real one).
    //
    //Mockito returns what you configured with when(...).
    //
    //The controller returns the ResponseEntity to MockMvc.
    //
    //You then assert the response status & body:
    //
    //.andExpect(status().isCreated())
    //.andExpect(content().string("Success"));
    //
    //
    //✅ So the controller’s real code runs,
    //but its dependencies are mocked — this is why it’s a controller (web-layer) test, not a service test.
    //
    //🧠 3️⃣ How it’s different from a service-layer test
    //Layer Tested	What Runs	What’s Mocked	Type of Test
    //Controller Test (@WebMvcTest)	Real controller + Spring MVC routing	Service, Repo, etc.	🟩 Controller/Web-layer
    //Service Test (@SpringBootTest or JUnit + Mockito)	Real service logic	Repository / external APIs	🟦 Service-layer
    //Integration Test	Everything (controller + service + DB)	Nothing	🟧 End-to-end
    //
    //So in your test:
    //
    //You’re validating controller behavior (status codes, mappings, path variables).
    //
    //You’re not testing service logic (retries, recovery, exceptions).
    //
    //It’s not a unit test of the service.
    //
    //✅ It’s a proper controller-layer test.
    //
    //🔍 Example visualization
    //MockMvc (HTTP request)
    //      ↓
    // EntryController (real code)
    //      ↓
    // ApiService (mocked bean)
    //      ↓
    // returns mock ResponseEntity("Success", 201)
    //
    //
    //Your test verifies:
    //
    //GET /git/arithmetic/{value} maps correctly.
    //
    //The response matches expected status & body.
    //
    //The controller calls the service with correct arguments.
    //
    //✅ In short:
    //
    //Even though the service is mocked, the test is still a controller test because the controller’s real HTTP endpoint is invoked and verified —
    //you’re testing the web layer, not the service logic.
}
