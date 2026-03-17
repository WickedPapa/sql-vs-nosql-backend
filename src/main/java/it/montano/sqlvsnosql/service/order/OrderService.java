package it.montano.sqlvsnosql.service.order;

import it.montano.sqlvsnosql.dto.OrderRequest;
import it.montano.sqlvsnosql.dto.OrderResponse;
import java.util.List;

public interface OrderService {

  OrderResponse createOrder(OrderRequest request);

  void deleteOrder(String orderId);

  List<OrderResponse> getOrders();
}
