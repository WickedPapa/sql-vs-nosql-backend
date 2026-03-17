package it.montano.sqlvsnosql.mapper.product;

import it.montano.sqlvsnosql.document.product.ProductDocument;
import it.montano.sqlvsnosql.dto.*;
import it.montano.sqlvsnosql.entity.product.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {

  ProductResponse toResponse(ProductEntity entity);

  ProductResponse toResponse(ProductDocument document);

  @Mapping(target = "id", ignore = true)
  ProductEntity toEntity(ProductRequest request);

  @Mapping(target = "id", ignore = true)
  ProductDocument toDocument(ProductRequest request);
}
