package it.montano.multipersistencebackend.common.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemRequestDto {
  UUID productId;
  String name;
  Integer quantity;
  BigDecimal price;
}
