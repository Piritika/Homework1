import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AppProduct {
    public static void main(String[] args)  {
//        Scanner scanner = new Scanner(System.in);
//
//        List<Person> people = new ArrayList<>();
//        List<Product> products = new ArrayList<>();
//
//        try {
//            // Ввод покупателей
//            System.out.println("Введите покупателей в формате: Имя = Сумма. Введите END для завершения:");
//            while (true) {
//                String line = scanner.nextLine();
//                if (line.equalsIgnoreCase("END")) break;
//                String[] parts = line.split(" = ");
//                String name = parts[0].trim();
//                int money = Integer.parseInt(parts[1].trim());
//                people.add(new Person(name, money));
//            }
//
//            // Ввод продуктов
//            System.out.println("Введите продукты в формате: Название = Стоимость. Введите END для завершения:");
//            while (true) {
//                String line = scanner.nextLine();
//                if (line.equalsIgnoreCase("END")) break;
//                String[] parts = line.split(" = ");
//                String productName = parts[0].trim();
//                int cost = Integer.parseInt(parts[1].trim());
//                products.add(new Product(productName, cost));
//            }
//
//            // Покупки
//            System.out.println("Добавьте товары в формате: Имя Продукт. Введите END для завершения:");
//            while (true) {
//                String line = scanner.nextLine();
//                if (line.equalsIgnoreCase("END")) break;
//
//                Person person = null;
//                Product product = null;
//
//                for (Person p : people) {
//                    if (line.startsWith(p.getName())) {
//                        person = p;
//                        String productPart = line.substring(p.getName().length()).trim();
//                        for (Product prod : products) {
//                            if (prod.getName().equals(productPart)) {
//                                product = prod;
//                                break;
//                            }
//                        }
//                        break;
//                    }
//                }
//
//                if (person != null && product != null) {
//                    if (person.getMoney() >= product.getCost()) {
//                        person.setMoney(person.getMoney() - product.getCost());
//                        person.addProduct(product);
//                        System.out.println(person.getName() + " купил " + product.getName());
//                    } else {
//                        System.out.println(person.getName() + " не может позволить себе " + product.getName());
//                    }
//                }
//            }
//
//            // Итоги
//            System.out.println("\nИтоги покупок:");
//            for (Person p : people) {
//                System.out.println(p);
//            }
//
//        } catch (Exception e) {
//            System.out.println("Ошибка: " + e.getMessage());
//        }
//
//        scanner.close();
//    }
        Scanner scanner = new Scanner(System.in);

        List<Product> products = new ArrayList<>();
        List<DiscountProduct> discountProducts = new ArrayList<>();

        System.out.println("Введите продукты в формате: Название = Стоимость или Название = Стоимость, Скидка%. Введите END для завершения:");
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("END")) break;

            try {
                String[] parts = line.split("=");
                if (parts.length != 2) throw new IllegalArgumentException("Неверный формат строки: " + line);

                String name = parts[0].trim();
                String rightSide = parts[1].trim();

                if (rightSide.contains(",")) {
                    Pattern pattern = Pattern.compile("(\\d+)\\s*,\\s*(\\d+)%");
                    Matcher matcher = pattern.matcher(rightSide);
                    if (matcher.find()) {
                        int price = Integer.parseInt(matcher.group(1));
                        int discount = Integer.parseInt(matcher.group(2));
                        LocalDate discountUntil = LocalDate.now().plusDays(7);
                        DiscountProduct dp = new DiscountProduct(name, price, discount, discountUntil);
                        discountProducts.add(dp);
                    } else {
                        throw new IllegalArgumentException("Неверный формат скидочного продукта: " + rightSide);
                    }
                } else {
                    int price = Integer.parseInt(rightSide);
                    Product p = new Product(name, price);
                    products.add(p);
                }

            } catch (Exception e) {
                System.out.println("Ошибка при разборе продукта: " + e.getMessage());
            }
        }

        // --- Вывод списков
        System.out.println("\nОбычные продукты: " +
                (products.isEmpty() ? "нет" :
                        products.stream().map(Product::getName).collect(Collectors.joining(", "))));

        System.out.println("Акционные продукты: " +
                (discountProducts.isEmpty() ? "нет" :
                        discountProducts.stream().map(Product::getName).collect(Collectors.joining(", "))));
    }
}
