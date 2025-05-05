package inn.attestation03.attestation03.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String nameOfProduct;
    private int price;
}
