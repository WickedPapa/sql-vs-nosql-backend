package it.montano.sqlvsnosql.repository.order;

import it.montano.sqlvsnosql.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPostgresRepository extends JpaRepository<OrderEntity, String> {}
