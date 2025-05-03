import java.util.Objects;

public class Product {
    private String name;
    private int cost;

    public Product(String name, int cost) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        if (name.length() < 3 || name.matches("\\d+")) {
            throw new IllegalArgumentException("Недопустимое название продукта: " + name);
        }
        if (cost <= 0) {
            throw new IllegalArgumentException("Цена должна быть положительной");
        }
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " (" + cost + "₽)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return name.equalsIgnoreCase(product.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}
