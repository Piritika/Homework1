import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //Задача 1
        int[] numbers = {12, 7, 45, 28, 91, 66, 123};

        int[] evenNumbers = Sequence.filter(numbers, n -> n % 2 == 0);
        System.out.println("Четные числа: " + Arrays.toString(evenNumbers));

        int[] sumEvenDigits = Sequence.filter(numbers, n -> {
            int sum = 0, temp = n;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            return sum % 2 == 0;
        });
        System.out.println("Числа с чётной суммой цифр: " + Arrays.toString(sumEvenDigits));

        //Дополнительная задача
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst();
        String s = pair.getSecond();

        Pair<Integer, String> pair2 = Pair.of(1, "hello");

        boolean mustBeTrue = pair.equals(pair2);
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode();

        System.out.println("equals: " + mustBeTrue);
        System.out.println("hashCode equals: " + mustAlsoBeTrue);
    }
}
