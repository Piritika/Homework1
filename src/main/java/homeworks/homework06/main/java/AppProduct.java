import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AppProduct {
    public static void main(String[] args) {

        Map<String, Person> people = new LinkedHashMap<>();
        Map<String, Product> products = new LinkedHashMap<>();
        List<String> outputLines = new ArrayList<>();

        File inputFile = new File("C:\\projects\\homework\\src\\main\\java\\homeworks\\homework06\\main\\java\\resources\\input.txt");
        File outputFile = new File("C:\\projects\\homework\\src\\main\\java\\homeworks\\homework06\\main\\java\\resources\\output.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(outputFile)) {

            // Чтение первой строки — Покупатели
            String peopleLine = reader.readLine();
            for (String part : peopleLine.split("[;,]")) {
                part = part.trim();
                if (part.isEmpty()) continue;
                String[] tokens = part.split("=");
                if (tokens.length != 2) continue;
                String name = tokens[0].trim();
                int money = Integer.parseInt(tokens[1].trim());
                people.put(name, new Person(name, money));
            }

            // Чтение второй строки — Продукты
            String productLine = reader.readLine();
            for (String part : productLine.split("[;,]")) {
                part = part.trim();
                if (part.isEmpty()) continue;
                String[] tokens = part.split("=");
                if (tokens.length != 2) continue;
                String name = tokens[0].trim();
                int cost = Integer.parseInt(tokens[1].trim());
                products.put(name, new Product(name, cost));
            }

            // Обработка покупок
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.equalsIgnoreCase("END")) break;

                String personName = null;
                String productName = null;

                for (String name : people.keySet()) {
                    if (line.startsWith(name)) {
                        personName = name;
                        productName = line.substring(name.length()).trim();
                        break;
                    }
                }

                if (personName == null || productName == null) continue;

                Person person = people.get(personName);
                Product product = products.get(productName);

                if (person == null || product == null) continue;

                if (person.canAfford(product)) {
                    person.buyProduct(product);
                    outputLines.add(personName + " купил " + productName);
                } else {
                    outputLines.add(personName + " не может позволить себе " + productName);
                }
            }

            // Итоги покупок
            for (Person person : people.values()) {
                String result = person.getName() + " - ";
                if (person.getBag().isEmpty()) {
                    result += "Ничего не куплено";
                } else {
                    result += person.getBag().stream()
                            .map(Product::getName)
                            .collect(Collectors.joining(", "));
                }
                outputLines.add(result);
            }

            // Запись в output.txt
            for (String outputLine : outputLines) {
                writer.println(outputLine);
            }

            System.out.println("Результаты сохранены в output.txt");

        } catch (IOException | NumberFormatException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
    }

