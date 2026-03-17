package it.montano.sqlvsnosql.service.product;

import it.montano.sqlvsnosql.dto.ProductRequest;
import it.montano.sqlvsnosql.dto.ProductResponse;
import it.montano.sqlvsnosql.entity.product.ProductEntity;
import it.montano.sqlvsnosql.mapper.product.ProductMapper;
import it.montano.sqlvsnosql.repository.product.ProductPostgresRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "datasource", havingValue = "POSTGRES")
public class ProductPostgresService implements ProductService {

  private final ProductPostgresRepository repo;
  private final ProductMapper mapper;

  @Override
  public @NonNull ProductResponse createProduct(@NonNull ProductRequest request) {
    ProductEntity entity = mapper.toEntity(request);
    return mapper.toResponse(repo.save(entity));
  }

  @Override
  public @NonNull List<ProductResponse> getProducts() {
    return repo.findAll().stream().map(mapper::toResponse).toList();
  }
}
