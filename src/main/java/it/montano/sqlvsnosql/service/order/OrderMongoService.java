package it.montano.sqlvsnosql.service.order;

import it.montano.sqlvsnosql.document.order.OrderDocument;
import it.montano.sqlvsnosql.dto.OrderRequest;
import it.montano.sqlvsnosql.dto.OrderResponse;
import it.montano.sqlvsnosql.mapper.order.OrderMapper;
import it.montano.sqlvsnosql.repository.order.OrderMongoRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "datasource", havingValue = "MONGODB")
public class OrderMongoService implements OrderService {

  private final OrderMongoRepository repo;
  private final OrderMapper mapper;

  @Override
  public @NonNull OrderResponse createOrder(@NonNull OrderRequest request) {
    OrderDocument saved = repo.save(mapper.toDocument(request));
    return mapper.toResponse(saved);
  }

  @Override
  public void deleteOrder(String orderId) {
    repo.deleteById(orderId);
  }

  @Override
  public @NonNull List<OrderResponse> getOrders() {
    return repo.findAll().stream().map(mapper::toResponse).toList();
  }
}
