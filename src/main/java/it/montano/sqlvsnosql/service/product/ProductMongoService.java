package it.montano.sqlvsnosql.service.product;

import it.montano.sqlvsnosql.document.product.ProductDocument;
import it.montano.sqlvsnosql.dto.ProductRequest;
import it.montano.sqlvsnosql.dto.ProductResponse;
import it.montano.sqlvsnosql.mapper.product.ProductMapper;
import it.montano.sqlvsnosql.repository.product.ProductMongoRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "datasource", havingValue = "MONGODB")
public class ProductMongoService implements ProductService {

  private final ProductMongoRepository repo;
  private final ProductMapper mapper;

  @Override
  public @NonNull ProductResponse createProduct(@NonNull ProductRequest request) {
    ProductDocument doc = mapper.toDocument(request);
    return mapper.toResponse(repo.save(doc));
  }

  @Override
  public @NonNull List<ProductResponse> getProducts() {
    return repo.findAll().stream().map(mapper::toResponse).toList();
  }
}
