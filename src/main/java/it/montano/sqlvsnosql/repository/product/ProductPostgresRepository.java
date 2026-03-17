package it.montano.sqlvsnosql.repository.product;

import it.montano.sqlvsnosql.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPostgresRepository extends JpaRepository<ProductEntity, String> {}
