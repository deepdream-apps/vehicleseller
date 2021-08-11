package cm.deepdream.vehicleseller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cm.deepdream.vehicleseller.model.Model;
@Repository
public interface ModelRepository extends CrudRepository<Model, Long>{

}
