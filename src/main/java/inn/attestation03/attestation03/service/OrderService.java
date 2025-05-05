package inn.attestation03.attestation03.service;

import inn.attestation03.attestation03.model.dto.OrderRequestDTO;

public interface OrderService {
    void createOrder(OrderRequestDTO dto);
    void updateOrder(Long orderId, OrderRequestDTO dto);
    void deleteOrder(Long orderId);
}
