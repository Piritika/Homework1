import java.util.Objects;

public class Product {
    private String name;
    private int cost;

    public Product(String name, int cost) {
        validateName(name);
        validateCost(cost);
        this.name = name;
        this.cost = cost;
    }

    protected void validateName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Название продукта не может быть пустым");

        if (name.matches("\\d+"))
            throw new IllegalArgumentException("Название продукта не должно содержать только цифры");

        if (name.length() < 3)
            throw new IllegalArgumentException("Название продукта должно быть не короче 3 символов");
    }

    protected void validateCost(int cost) {
        if (cost <= 0)
            throw new IllegalArgumentException("Стоимость продукта должна быть положительной");
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " (" + cost + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return cost == product.cost && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
    }
}
