package it.montano.sqlvsnosql.common.mapper;

import it.montano.sqlvsnosql.common.dto.OrderItemRequestDto;
import it.montano.sqlvsnosql.common.dto.OrderRequestDto;
import it.montano.sqlvsnosql.dto.*;
import it.montano.sqlvsnosql.order.model.OrderDocument;
import it.montano.sqlvsnosql.order.model.OrderEntity;
import it.montano.sqlvsnosql.order.model.OrderItemDocument;
import it.montano.sqlvsnosql.order.model.OrderItemEntity;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(imports = {UUID.class})
public interface OrderMapper {

  @Mapping(target = "user.userId", source = "userId")
  @Mapping(target = "user.firstName", ignore = true)
  @Mapping(target = "user.lastName", ignore = true)
  @Mapping(target = "user.email", ignore = true)
  OrderResponse toResponse(OrderEntity entity);

  @Mapping(target = "name", ignore = true)
  OrderItemResponse toResponse(OrderItemEntity entity);

  OrderRequestDto toDto(OrderRequest request);

  @Mapping(target = "name", ignore = true)
  @Mapping(target = "price", ignore = true)
  OrderItemRequestDto toDto(OrderItemRequest request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "total", expression = "java(calculateTotal(request.getItems()))")
  OrderEntity toEntity(OrderRequestDto request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "order", ignore = true)
  OrderItemEntity toEntity(OrderItemRequestDto request);

  OrderResponse toResponse(OrderDocument entity);

  @Mapping(target = "productId", source = "productDocument.id")
  @Mapping(target = "name", source = "productDocument.name")
  @Mapping(target = "price", source = "productDocument.price")
  OrderItemResponse toResponse(OrderItemDocument entity);

  @Mapping(target = "id", expression = "java(UUID.randomUUID())")
  @Mapping(target = "user.userId", source = "userResponse.id")
  @Mapping(target = "user.firstName", source = "userResponse.firstName")
  @Mapping(target = "user.lastName", source = "userResponse.lastName")
  @Mapping(target = "user.email", source = "userResponse.email")
  @Mapping(target = "total", expression = "java(calculateTotal(request.getItems()))")
  OrderDocument toDocument(OrderRequestDto request, UserResponse userResponse);

  @Mapping(target = "productDocument.id", source = "productId")
  @Mapping(target = "productDocument.name", source = "name")
  @Mapping(target = "productDocument.price", source = "price")
  OrderItemDocument toDocument(OrderItemRequestDto request);

  @Mapping(target = "userId", source = "id")
  OrderUserResponse toOrderUserResponse(UserResponse userResponse);

  default @NonNull Double calculateTotal(@NonNull List<OrderItemRequestDto> items) {
    return items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
  }
}
