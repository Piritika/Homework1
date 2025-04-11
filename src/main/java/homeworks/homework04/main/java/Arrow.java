import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arrow {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        Pattern pattern = Pattern.compile(">>-->|<--<<");

        Matcher matcher = pattern.matcher(str);

        int count = 0;

        while (matcher.find()) {
            count++;
        }
        System.out.println(count);
    }
}
