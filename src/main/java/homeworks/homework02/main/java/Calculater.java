import java.util.Scanner;

public class Calculater {
    public static void main(String[] args) {
        System.out.println("Введите 1-е целое число: ");
        Scanner scanner = new Scanner(System.in);
        int numbOne = scanner.nextInt();
        System.out.println("Введите 2-е целое число: ");
        int numbTwo = scanner.nextInt();

        int summ = numbOne + numbTwo;
        System.out.println("Сумма двух целых чисел: " + summ);

        int diff = numbOne + numbTwo;
        System.out.println("Разница двух целых чисел: " + diff);

        int mult = numbOne*numbTwo;
        System.out.println("Произведение из двух целых чисел: " + mult);

        float aver = ((float) numbOne + numbTwo)/2;
        System.out.println("Среднее из двух целых чисел: " + aver);

        if (diff<0) {
           diff = numbTwo - numbOne;
        System.out.println("Расстояние двух целых чисел: " + diff);
        }

        int max = Math.max(numbOne, numbTwo);
        System.out.println("Максимальное целое число: " + max);

        int min = Math.min(numbOne, numbTwo);
        System.out.println("Минимальное целое число: " + min);

    }
}
