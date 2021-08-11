package cm.deepdream.vehicleseller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cm.deepdream.vehicleseller.model.Town;
@Repository
public interface TownRepository extends CrudRepository<Town, Long>{

}
