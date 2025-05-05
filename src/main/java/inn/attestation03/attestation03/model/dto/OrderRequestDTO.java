package inn.attestation03.attestation03.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class OrderRequestDTO {
    private Long userId;
    private Long productId;
    private LocalDateTime orderDate;
}