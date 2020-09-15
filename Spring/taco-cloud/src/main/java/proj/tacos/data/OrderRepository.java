package proj.tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import proj.tacos.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
