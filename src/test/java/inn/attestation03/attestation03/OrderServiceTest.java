package inn.attestation03.attestation03;

import inn.attestation03.attestation03.model.dto.OrderRequestDTO;
import inn.attestation03.attestation03.model.entity.Order;
import inn.attestation03.attestation03.model.entity.Product;
import inn.attestation03.attestation03.model.entity.User;
import inn.attestation03.attestation03.repository.OrderRepository;
import inn.attestation03.attestation03.repository.ProductRepository;
import inn.attestation03.attestation03.repository.UserRepository;
import inn.attestation03.attestation03.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private User user;
    private Product product;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        user = userRepository.save(User.builder().firstName("Order").lastName("User").email("order@user.com").age(30).build());
        product = productRepository.save(Product.builder().nameOfProduct("Flower").price(50).build());
    }

    @Test
    void testCreateOrder() {
        OrderRequestDTO dto = new OrderRequestDTO();
        dto.setUserId(user.getId());
        dto.setProductId(product.getId());
        orderService.createOrder(dto);

        List<Order> orders = orderRepository.findAll();
        Assertions.assertEquals(1, orders.size());
        Assertions.assertEquals(user.getId(), orders.get(0).getUser().getId());
    }

    @Test
    void testUpdateOrder() {
        User user2 = userRepository.save(User.builder().firstName("U2").lastName("L2").email("u2@l2.com").age(25).build());
        Product product2 = productRepository.save(Product.builder().nameOfProduct("UpdatedFlower").price(99).build());

        Order order = orderRepository.save(Order.builder().user(user).product(product).orderDate(LocalDateTime.now()).build());

        OrderRequestDTO dto = new OrderRequestDTO();
        dto.setUserId(user2.getId());
        dto.setProductId(product2.getId());
        orderService.updateOrder(order.getId(), dto);

        Order updated = orderRepository.findById(order.getId()).orElseThrow();
        Assertions.assertEquals("UpdatedFlower", updated.getProduct().getNameOfProduct());
        Assertions.assertEquals("u2@l2.com", updated.getUser().getEmail());
    }

    @Test
    void testDeleteOrder() {
        Order order = orderRepository.save(Order.builder().user(user).product(product).orderDate(LocalDateTime.now()).build());
        orderService.deleteOrder(order.getId());

        Assertions.assertTrue(orderRepository.findById(order.getId()).isEmpty());
    }
}
