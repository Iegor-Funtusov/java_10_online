package ua.com.alevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTest {

    public void test() {
//        buildIntStream();
//        exam();
        parallelStream();
    }

    private void createStream() {
        List<Integer> integers = Arrays.asList(1,3,2,5,9,3,6,8,3);
        Stream<Integer> integerStream = integers.stream();
        Stream<Integer> integerStream1 = Stream.of(1, 3, 5, 7, 34, 0);
        Stream<Integer> integerStream2 = Arrays.stream(new Integer[]{1, 3, 6});
        Stream<Object> integerStream3 = Stream.builder().add(1).add(2).build();
    }

    private void buildIntStream() {
        //1
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            integers.add(i);
        }
        System.out.println("integers = " + integers);

        // 2
//        integers = IntStream.range(0, 100).boxed().toList();
        integers = IntStream
                .rangeClosed(0, 100)
                .boxed()
                .toList();
        System.out.println("integers = " + integers);

        // scala exam: let i = [0...100]
    }

    private void exam() {
        List<Integer> integers = IntStream
                .rangeClosed(0, 100)
                .boxed()
                .toList();

        List<Integer> filter = integers
                .stream()
                .skip(10)
                .limit(25)
                .filter(i -> i %  2 == 0)
                .toList();
        System.out.println("filter = " + filter);

        Stream<String> stringStream = Stream.of("1", "gg", "0", "ttt", "fsda", "1");
        List<String> strings = stringStream.toList();
        int sum = strings
                .stream()
                .filter(el -> el.matches("[0-9]*"))
                .map(el -> Integer.parseInt(el))
                .distinct()
                .map(el -> el * 10)
                .filter(i -> i %  2 == 0)
                .reduce(0,  (a, b) -> a + b)
                .intValue();
        System.out.println("sum = " + sum);

        sum = strings
                .stream()
                .filter(el -> el.matches("[0-9]*"))
                .mapToInt(el -> Integer.parseInt(el))
                .distinct()
                .sum();
        System.out.println("sum = " + sum);


        List<String> stringList = Arrays.asList("h", "e", "l", "l", "o0");
        String hello = stringList
                .stream()
                .map(el -> el.toUpperCase())
                .collect(Collectors.joining());

        System.out.println("hello = " + hello);

        boolean isPresentO = stringList.stream().allMatch(el -> el.toLowerCase().equals("o"));
        System.out.println("isPresentO = " + isPresentO);
    }

    private void parallelStream() {
        List<Long> integers = LongStream
                .rangeClosed(0, 1_000_000_000)
                .boxed()
                .toList();

        long start = System.currentTimeMillis();
        long sum = 0;
        for (Long l : integers) {
//            sum += l;
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("sum = " + sum + ", time: " + end);

//        start = System.currentTimeMillis();
//        int streamSum = integers.stream().reduce(Integer::sum).get();
//        end = System.currentTimeMillis() - start;
//        System.out.println("streamSum = " + streamSum + ", time: " + end);
//
//        start = System.currentTimeMillis();
//        int potentialParallelSum = integers.stream().parallel().reduce(Integer::sum).get();
//        end = System.currentTimeMillis() - start;
//        System.out.println("potentialParallelSum = " + potentialParallelSum + ", time: " + end);
//
//        start = System.currentTimeMillis();
//        int parallelSum = integers.parallelStream().reduce(Integer::sum).get();
//        end = System.currentTimeMillis() - start;
//        System.out.println("parallelSum = " + parallelSum + ", time: " + end);

    }

    private void some(Object ... o) {
        Stream.of(o);
    }
}
