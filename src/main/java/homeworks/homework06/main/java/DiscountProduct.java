import java.time.LocalDate;

public class DiscountProduct extends Product {
    private int discountPercent;
    private LocalDate discountUntil;

    public DiscountProduct(String name, int originalCost, int discountPercent, LocalDate discountUntil) {
        super(name, originalCost);
        if (discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100%");
        if (discountUntil == null)
            throw new IllegalArgumentException("Дата окончания скидки не может быть null");
        this.discountPercent = discountPercent;
        this.discountUntil = discountUntil;
    }

    @Override
    public int getCost() {
        if (LocalDate.now().isBefore(discountUntil)) {
            return super.getCost() * (100 - discountPercent) / 100;
        } else {
            return super.getCost(); // скидка не действует
        }
    }

    public LocalDate getDiscountUntil() {
        return discountUntil;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    @Override
    public String toString() {
        return getName() + " (со скидкой " + discountPercent + "% до " + discountUntil + ", цена сейчас: " + getCost() + ")";
    }
}