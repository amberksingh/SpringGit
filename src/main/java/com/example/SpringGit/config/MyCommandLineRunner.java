//package com.example.SpringGit.config;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.tutofox.Boot.model.Model;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.io.InputStream;
//import java.util.Arrays;
//
////@Configuration
//@Component
//public class MyCommandLineRunner implements CommandLineRunner {
//
//    @Override
//    public void run(String... args) throws Exception {
//
//       System.out.println(Arrays.toString(args));
//       TypeReference<Model> typeReference = new TypeReference<Model>(){};
//        InputStream inputStream = TypeReference.class.getResourceAsStream("/data/data.json");
//        Model model = new ObjectMapper().readValue(inputStream, typeReference);
////        TypeReference<List<Model>> typeReference = new TypeReference<List<Model>>() {};
////        InputStream inputStream = TypeReference.class.getResourceAsStream("/data/dataJsonArray.json");
////        List<Model> model = new ObjectMapper().readValue(inputStream, typeReference);
//        if (model != null) {
//            System.out.println("Name : " + model.getName());
//            System.out.println("Position : " + model.getPosition());
//            System.out.println("Id : " + model.getId());
//            System.out.println("DepartmentId : " + model.getDepartmentId());
//            System.out.println("model object read from json file..");
////            model.forEach(e -> {
////                System.out.println(e);
////            });
////            System.out.println("gggg");
//        }
//        System.out.println("ending...");
//
//    }
//
//    @Bean
//    CommandLineRunner runAtStartup() {
//
////        return (String... args) -> {
////
////        };
//
//        CommandLineRunner c = (args) -> {
//            System.out.println("gg");
//        };
//        return c;
//
////        return args -> {
////            System.out.println("Application started. Args:");
////            for (String arg : args) {
////                System.out.println(arg);
////            }
////        };
//    }
//
//}
