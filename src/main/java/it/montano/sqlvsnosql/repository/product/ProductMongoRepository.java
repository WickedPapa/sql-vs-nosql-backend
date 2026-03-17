package it.montano.sqlvsnosql.repository.product;

import it.montano.sqlvsnosql.document.product.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongoRepository extends MongoRepository<ProductDocument, String> {}
