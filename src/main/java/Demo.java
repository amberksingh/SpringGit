import java.util.List;

public class Demo {

    public static void main(String[] args) {

        List<String> countryNames= List.of("India", "Australia", "Italy", "USA");
        countryNames.stream()
                .forEach(System.out::println);

        //reverse
        System.out.println("Reversed country names : ");
        countryNames.stream()
                .map(StringBuffer::new)
                .map(StringBuffer::reverse)
                .map(StringBuffer::toString)
                .forEach(System.out::println);
    }
}
