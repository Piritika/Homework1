import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PerformanceCar extends Car {
    private List<String> addOns;

    public PerformanceCar() {
        super();
        this.addOns = new ArrayList<>();
    }

    public PerformanceCar(String brand, String model, int yearOfProduction,
                          int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction,
                (int)(horsepower * 1.5),
                acceleration,
                (int)(suspension * 0.75),
                durability);
        this.addOns = new ArrayList<>();
    }

    public List<String> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<String> addOns) {
        this.addOns = addOns;
    }

    public void addAddOn(String addOn) {
        this.addOns.add(addOn);
    }

    @Override
    public String toString() {
        return super.toString() + "\nAdd-ons: " +
                (addOns.isEmpty() ? "None" : String.join(", ", addOns));
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (!(o instanceof PerformanceCar)) return false;
        PerformanceCar that = (PerformanceCar) o;
        return Objects.equals(addOns, that.addOns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), addOns);
    }
}
