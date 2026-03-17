package it.montano.sqlvsnosql.mapper.order;

import it.montano.sqlvsnosql.document.order.OrderDocument;
import it.montano.sqlvsnosql.dto.OrderItemRequest;
import it.montano.sqlvsnosql.dto.OrderRequest;
import it.montano.sqlvsnosql.dto.OrderResponse;
import it.montano.sqlvsnosql.entity.order.OrderEntity;
import it.montano.sqlvsnosql.entity.order.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderMapper {

  OrderResponse toResponse(OrderEntity entity);

  @Mapping(target = "id", ignore = true)
  OrderEntity toEntity(OrderRequest request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "order", ignore = true)
  OrderItemEntity toEntity(OrderItemRequest request);

  OrderResponse toResponse(OrderDocument entity);

  @Mapping(target = "id", ignore = true)
  OrderDocument toDocument(OrderRequest request);
}
