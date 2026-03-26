package it.montano.multipersistencebackend.order.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEmbedded {
  UUID productId;
  String name;
  BigDecimal price;
}
