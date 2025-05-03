import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppProduct {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);

        List<Person> people = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        try {
            // Ввод покупателей
            System.out.println("Введите покупателей в формате: Имя = Сумма. Введите END для завершения:");
            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("END")) break;
                String[] parts = line.split(" = ");
                String name = parts[0].trim();
                int money = Integer.parseInt(parts[1].trim());
                people.add(new Person(name, money));
            }

            // Ввод продуктов
            System.out.println("Введите продукты в формате: Название = Стоимость. Введите END для завершения:");
            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("END")) break;
                String[] parts = line.split(" = ");
                String productName = parts[0].trim();
                int cost = Integer.parseInt(parts[1].trim());
                products.add(new Product(productName, cost));
            }

            // Покупки
            System.out.println("Добавьте товары в формате: Имя Продукт. Введите END для завершения:");
            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("END")) break;

                Person person = null;
                Product product = null;

                for (Person p : people) {
                    if (line.startsWith(p.getName())) {
                        person = p;
                        String productPart = line.substring(p.getName().length()).trim();
                        for (Product prod : products) {
                            if (prod.getName().equals(productPart)) {
                                product = prod;
                                break;
                            }
                        }
                        break;
                    }
                }

                if (person != null && product != null) {
                    if (person.getMoney() >= product.getCost()) {
                        person.setMoney(person.getMoney() - product.getCost());
                        person.addProduct(product);
                        System.out.println(person.getName() + " купил " + product.getName());
                    } else {
                        System.out.println(person.getName() + " не может позволить себе " + product.getName());
                    }
                }
            }

            // Итоги
            System.out.println("\nИтоги покупок:");
            for (Person p : people) {
                System.out.println(p);
            }

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }
}
