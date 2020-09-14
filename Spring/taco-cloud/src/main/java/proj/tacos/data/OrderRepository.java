package proj.tacos.data;

import proj.tacos.model.Order;

public interface OrderRepository {

  Order save(Order order);
  
}
