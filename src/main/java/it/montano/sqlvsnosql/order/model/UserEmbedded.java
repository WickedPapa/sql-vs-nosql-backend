package it.montano.sqlvsnosql.order.model;

import java.util.UUID;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEmbedded {
  UUID userId;
  String firstName;
  String lastName;
  String email;
}
