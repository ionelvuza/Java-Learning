package proj.tacos.data;

import org.springframework.data.repository.CrudRepository;
import proj.tacos.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
  
}
