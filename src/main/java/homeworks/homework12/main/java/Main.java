import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные: Фамилия Имя Отчество Дата рождения Номер телефона Пол Возраст");
        String input = scanner.nextLine();
        String[] data = input.split("\\s+");

        try {
            if (data.length < 7) {
                throw new IllegalArgumentException("Недостаточно данных: требуется 7 полей, введено " + data.length);
            } else if (data.length > 7) {
                throw new IllegalArgumentException("Слишком много данных: требуется 7 полей, введено " + data.length);
            }

            Person person = new Person(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
            writeToFile(person);
            System.out.println("Данные успешно сохранены.");
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Произошла ошибка при обработке данных:");
            e.printStackTrace();
        }
    }

    private static void writeToFile(Person person) {
        String filename = person.getLastName() + ".txt";
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(person.toFileFormat() + "\n");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл:");
            e.printStackTrace();
        }
    }
}