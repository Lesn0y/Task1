package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTaskExecutor {

    public static List<Integer> filterEvenAndOrdered(List<Integer> list) {
        return list.stream()
                .filter(el -> el % 2 == 0)
                .sorted()
                .toList();
    }

    public static List<String> stringsToUpperCase(List<String> list) {
        return list.stream()
                .map(String::toUpperCase)
                .toList();
    }

    public static int findSum(int[] arr) {
        return Arrays.stream(arr)
                .sum();
//                .reduce(0, Integer::sum);
//                .reduce(0, (a, b) -> a + b);
    }

    public static Map<Integer, List<Person>> groupPersonByAge(List<Person> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Person::age));
    }

    public static double findAverageAge(List<Person> list) {
        return list.stream()
                .mapToDouble(Person::age)
                .average()
                .getAsDouble();
    }

    

}
