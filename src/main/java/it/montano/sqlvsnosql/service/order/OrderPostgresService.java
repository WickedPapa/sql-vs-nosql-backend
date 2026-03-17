package it.montano.sqlvsnosql.service.order;

import it.montano.sqlvsnosql.dto.OrderRequest;
import it.montano.sqlvsnosql.dto.OrderResponse;
import it.montano.sqlvsnosql.entity.order.OrderEntity;
import it.montano.sqlvsnosql.mapper.order.OrderMapper;
import it.montano.sqlvsnosql.repository.order.OrderPostgresRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "datasource", havingValue = "POSTGRES")
public class OrderPostgresService implements OrderService {

  private final OrderPostgresRepository repo;
  private final OrderMapper mapper;

  @Override
  public @NonNull OrderResponse createOrder(@NonNull OrderRequest request) {
    OrderEntity saved = repo.save(mapper.toEntity(request));
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
