import java.time.LocalDate;

public class DiscountProduct extends Product {
    private int discountPercent;
    private LocalDate validUntil;

    public DiscountProduct(String name, int cost, int discountPercent, LocalDate validUntil) {
        super(name, cost);
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Недопустимая скидка: " + discountPercent);
        }
        this.discountPercent = discountPercent;
        this.validUntil = validUntil;
    }

    @Override
    public int getCost() {
        if (LocalDate.now().isAfter(validUntil)) {
            return super.getCost(); // скидка истекла
        }
        return super.getCost() * (100 - discountPercent) / 100;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    @Override
    public String toString() {
        return super.getName() + " (" + getCost() + "₽ со скидкой " + discountPercent + "% до " + validUntil + ")";
    }
}