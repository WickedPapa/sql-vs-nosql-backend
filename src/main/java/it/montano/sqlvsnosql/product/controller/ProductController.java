package it.montano.sqlvsnosql.product.controller;

import it.montano.sqlvsnosql.api.ProductsApi;
import it.montano.sqlvsnosql.common.util.UriUtil;
import it.montano.sqlvsnosql.dto.ProductRequest;
import it.montano.sqlvsnosql.dto.ProductResponse;
import it.montano.sqlvsnosql.product.service.ProductService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductsApi {

  private final ProductService productService;

  @Override
  public ResponseEntity<ProductResponse> createProduct(ProductRequest request) {
    ProductResponse productResponse = productService.createProduct(request);
    return ResponseEntity.created(
            UriUtil.buildUri(ProductsApi.PATH_GET_PRODUCT_BY_ID, productResponse.getId()))
        .body(productResponse);
  }

  @Override
  public ResponseEntity<Void> deleteProduct(UUID productId) {
    productService.deleteProduct(productId);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<ProductResponse> getProductById(UUID productId) {
    return ResponseEntity.ok(productService.getProductById(productId));
  }

  @Override
  public ResponseEntity<List<ProductResponse>> getProducts() {
    return ResponseEntity.ok(productService.getProducts());
  }

  @Override
  public ResponseEntity<ProductResponse> updateProduct(
      UUID productId, ProductRequest productRequest) {
    return ResponseEntity.ok(productService.updateProduct(productId, productRequest));
  }
}
