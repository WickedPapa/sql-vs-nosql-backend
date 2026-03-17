package it.montano.sqlvsnosql.repository.order;

import it.montano.sqlvsnosql.document.order.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderMongoRepository extends MongoRepository<OrderDocument, String> {}
