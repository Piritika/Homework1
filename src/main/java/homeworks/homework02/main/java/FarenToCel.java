import java.util.Scanner;

public class FarenToCel {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите степень в градусах Фаренгейта: ");
        float faren = in.nextFloat();


        float cel = (float) ((faren - 32.0) * 5/9);
        System.out.println(Float.parseFloat(String.valueOf(faren)) + " градусов по Фаренгейту равна " + Float.parseFloat(String.valueOf(cel)) + "");

    }
}
