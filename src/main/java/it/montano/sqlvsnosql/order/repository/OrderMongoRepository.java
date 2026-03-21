package it.montano.sqlvsnosql.order.repository;

import it.montano.sqlvsnosql.dto.MostSoldProductResponse;
import it.montano.sqlvsnosql.dto.TotalSpentPerUserResponse;
import it.montano.sqlvsnosql.order.model.OrderDocument;
import java.util.List;
import java.util.UUID;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderMongoRepository extends MongoRepository<OrderDocument, UUID> {
  List<OrderDocument> findByUserUserId(UUID userId);

  @Aggregation(
      pipeline = {
        "{ $group: { "
            + "_id: '$user.userId', "
            + "total: { $sum: '$total' }, "
            + "firstName: { $first: '$user.firstName' }, "
            + "lastName: { $first: '$user.lastName' }, "
            + "email: { $first: '$user.email' } "
            + "} }",
        "{ $project: { "
            + "_id: 0, "
            + "userId: '$_id', "
            + "totalSpent: '$total', "
            + "firstName: 1, "
            + "lastName: 1, "
            + "email: 1 "
            + "} }"
      })
  List<TotalSpentPerUserResponse> getTotalSpentPerUser();

  @Aggregation(
      pipeline = {
        "{ $unwind: '$items' }",
        "{ $group: { _id: '$items.productEmbedded._id', name: { $first: '$items.productEmbedded.name' }, totalQuantity: { $sum: '$items.quantity' } } }",
        "{ $sort: { totalQuantity: -1 } }",
        "{ $project: { _id: 0, productId: '$_id', name: 1, totalQuantity: 1 } }"
      })
  List<MostSoldProductResponse> getMostSoldProduct();
}
