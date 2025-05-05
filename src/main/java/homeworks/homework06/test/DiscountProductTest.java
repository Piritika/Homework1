import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiscountProductTest {

    @Test
    void testDiscountIsAppliedBeforeExpiry() {
        DiscountProduct product = new DiscountProduct("Cheese", 100, 20, LocalDate.now().plusDays(1));
        assertEquals(80, product.getCost());
    }

    @Test
    void testDiscountIsNotAppliedAfterExpiry() {
        DiscountProduct product = new DiscountProduct("Cheese", 100, 20, LocalDate.now().minusDays(1));
        assertEquals(100, product.getCost());
    }

    @Test
    void testInvalidDiscountThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new DiscountProduct("Wine", 100, 150, LocalDate.now().plusDays(5))
        );
    }
}
