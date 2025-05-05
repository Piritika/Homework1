import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    void testCanAffordProductTrue() {
        Person person = new Person("Anna", 100);
        Product product = new Product("Book", 50);
        assertTrue(person.canAfford(product));
    }

    @Test
    void testBuyProductReducesMoneyAndAddsToBag() {
        Person person = new Person("Ivan", 100);
        Product product = new Product("Pen", 30);
        person.buyProduct(product);
        assertEquals(70, person.getMoney());
        assertTrue(person.getBag().contains(product));
    }

    @ParameterizedTest
    @CsvSource({
            "Alice, 200",
            "Bob, 0",
            "Charlie, 1000"
    })
    void testPersonConstructorValid(String name, int money) {
        Person person = new Person(name, money);
        assertEquals(name, person.getName());
        assertEquals(money, person.getMoney());
    }

    @ParameterizedTest
    @CsvSource({
            "'', 100",
            " , 100",
            "John, -50"
    })
    void testPersonConstructorInvalid(String name, int money) {
        assertThrows(IllegalArgumentException.class, () -> new Person(name, money));
    }
}
