import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private int money;
    private List<Product> bag;

    public Person(String name, int money) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.name = name;
        this.money = money;
        this.bag = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List<Product> getBag() {
        return new ArrayList<>(bag);
    }

    public void addProduct(Product product) {
        bag.add(product);
    }

    @Override
    public String toString() {
        if (bag.isEmpty()) {
            return name + " — Ничего не куплено";
        } else {
            StringBuilder sb = new StringBuilder(name + " купил: ");
            for (Product p : bag) {
                sb.append(p.getName()).append(", ");
            }
            return sb.substring(0, sb.length() - 2);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return money == person.money &&
                name.equals(person.name) &&
                bag.equals(person.bag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, bag);
    }
}
