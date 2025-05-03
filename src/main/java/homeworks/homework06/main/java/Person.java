import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private int money;
    private List<Product> bag = new ArrayList<>();

    public Person(String name, int money) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public List<Product> getBag() {
        return bag;
    }

    public boolean canAfford(Product product) {
        return money >= product.getCost();
    }

    public void buyProduct(Product product) {
        if (canAfford(product)) {
            money -= product.getCost();
            bag.add(product);
        }
    }

    @Override
    public String toString() {
        return name + " (" + money + "₽)";
    }
}
