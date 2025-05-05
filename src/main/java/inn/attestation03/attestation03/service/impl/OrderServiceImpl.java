package inn.attestation03.attestation03.service.impl;

import inn.attestation03.attestation03.model.dto.OrderRequestDTO;
import inn.attestation03.attestation03.model.entity.Order;
import inn.attestation03.attestation03.model.entity.Product;
import inn.attestation03.attestation03.model.entity.User;
import inn.attestation03.attestation03.repository.OrderRepository;
import inn.attestation03.attestation03.repository.ProductRepository;
import inn.attestation03.attestation03.repository.UserRepository;
import inn.attestation03.attestation03.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public void createOrder(OrderRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
    .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = Order.builder()
                .user(user)
                .product(product)
                .orderDate(dto.getOrderDate() != null ? dto.getOrderDate() : LocalDateTime.now())
                .build();

        orderRepository.save(order);
    }

    @Override
    public void updateOrder(Long orderId, OrderRequestDTO dto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            order.setUser(user);
        }

        if (dto.getProductId() != null) {
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            order.setProduct(product);
        }

        if (dto.getOrderDate() != null) {
            order.setOrderDate(dto.getOrderDate());
        }

        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.findById(orderId)
                .ifPresentOrElse(orderRepository::delete, () -> {
                    throw new RuntimeException("Order not found");
                });
    }
}