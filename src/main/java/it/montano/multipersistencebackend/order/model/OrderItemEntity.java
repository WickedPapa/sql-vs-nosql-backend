package it.montano.multipersistencebackend.order.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  /**
   * Product data is intentionally denormalized inside the order item. We store a snapshot of the
   * product information at the time the order is created, instead of referencing ProductEntity.
   * This ensures historical consistency: changes to the product catalog do not affect existing
   * orders. Only productId is kept as a reference, without a relational mapping. The price
   * represents the value at purchase time, not the current product price.
   */
  @Column(nullable = false)
  UUID productId;

  @Column(nullable = false, length = 150)
  String name;

  @Column(nullable = false)
  Integer quantity;

  @Column(nullable = false)
  BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  OrderEntity order;
}
