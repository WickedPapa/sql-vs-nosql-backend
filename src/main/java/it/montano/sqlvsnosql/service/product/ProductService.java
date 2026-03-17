package it.montano.sqlvsnosql.service.product;

import it.montano.sqlvsnosql.dto.ProductRequest;
import it.montano.sqlvsnosql.dto.ProductResponse;
import java.util.List;

public interface ProductService {

  ProductResponse createProduct(ProductRequest request);

  List<ProductResponse> getProducts();
}
