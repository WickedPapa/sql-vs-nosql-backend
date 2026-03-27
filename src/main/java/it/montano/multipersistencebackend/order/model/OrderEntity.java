package it.montano.multipersistencebackend.order.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  /**
   * User data is intentionally denormalized inside the order. We store a snapshot of the user's
   * information at the time the order is created, instead of referencing UserEntity. This ensures
   * historical consistency: changes to the user profile do not affect existing orders. Only userId
   * is kept as a reference, without a relational mapping.
   */
  @Column(nullable = false)
  UUID userId;

  @Column(nullable = false, length = 100)
  String firstName;

  @Column(nullable = false, length = 100)
  String lastName;

  @Column(nullable = false)
  String email;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  List<OrderItemEntity> items = new ArrayList<>();

  @Column(nullable = false)
  BigDecimal total;
}
