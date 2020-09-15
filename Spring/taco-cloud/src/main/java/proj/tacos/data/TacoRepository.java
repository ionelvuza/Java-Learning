package proj.tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import proj.tacos.model.Taco;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {

}
