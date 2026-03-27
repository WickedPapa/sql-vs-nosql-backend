package it.montano.multipersistencebackend.common.dto;

import java.util.List;
import java.util.UUID;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDto {
  UUID userId;
  String firstName;
  String lastName;
  String email;
  List<OrderItemRequestDto> items;
}
