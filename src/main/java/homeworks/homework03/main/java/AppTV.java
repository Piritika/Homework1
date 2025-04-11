public class AppTV {
    public static void main(String[] args) {
        Television television = new Television();
        television.setModel("Samsung");
        television.setDiagonal("55");
        System.out.println(television);

        Television tv = new Television();
        tv.setModel("Xiaomi");
        System.out.println(tv);

        Television tele = new Television();
        tele.setDiagonal("32");
        System.out.println(tele);
    }
}
