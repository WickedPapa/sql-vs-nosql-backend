package it.montano.sqlvsnosql.common.mapper;

import it.montano.sqlvsnosql.dto.*;
import it.montano.sqlvsnosql.product.model.ProductDocument;
import it.montano.sqlvsnosql.product.model.ProductEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(imports = {UUID.class})
public interface ProductMapper {

  ProductResponse toResponse(ProductEntity entity);

  ProductResponse toResponse(ProductDocument document);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "price", expression = "java(roundPrice(request.getPrice()))")
  ProductEntity toEntity(ProductRequest request);

  @Mapping(target = "id", expression = "java(UUID.randomUUID())")
  @Mapping(target = "price", expression = "java(roundPrice(request.getPrice()))")
  ProductDocument toDocument(ProductRequest request);

  @Mapping(target = "id", ignore = true)
  void updateEntity(ProductRequest request, @MappingTarget ProductEntity entity);

  @Mapping(target = "id", ignore = true)
  void updateDocument(ProductRequest productRequest, @MappingTarget ProductDocument doc);

  default double roundPrice(double price) {
    return BigDecimal.valueOf(price)
            .setScale(2, RoundingMode.HALF_UP)
            .doubleValue();
  }
}
