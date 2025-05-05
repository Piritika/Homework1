import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    @Test
    @Disabled
     void testValidProductCreation() {
        Product product = new Product("Milk", 50);
        assertEquals("Milk", product.getName());
        assertEquals(50, product.getCost());
    }

    @ParameterizedTest
    @CsvSource({
            "Tea, 30",
            "Bread, 20",
            "Juice, 45"
    })
    void testParameterizedProductCreation(String name, int cost) {
        Product product = new Product(name, cost);
        assertEquals(name, product.getName());
        assertEquals(cost, product.getCost());
    }

    @ParameterizedTest
    @CsvSource({
            "'', 50",
            "12, 50",
            "Milk, 0",
            "Water, -10"
    })
    void testInvalidProductCreation(String name, int cost) {
        assertThrows(IllegalArgumentException.class, () -> new Product(name, cost));
    }
}
