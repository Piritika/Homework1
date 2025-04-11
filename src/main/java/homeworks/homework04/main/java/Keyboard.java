import java.util.*;

public class Keyboard {
    public static final String key = "qwertyuiopasdfghjklzxcvbnm";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите одну букву: ");
        String letter = scanner.nextLine().toLowerCase();

        if (letter.length() == 1 && Character.isLetter(letter.charAt(0))) {
            char let = letter.charAt(0);
            int index = key.indexOf(let);

            if (index != -1) {
                char left = key.charAt((index - 1+ key.length()) % key.length());
                System.out.println(left);

                scanner.close();
            }
        }
    }
}