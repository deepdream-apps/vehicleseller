package cm.deepdream.vehicleseller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cm.deepdream.vehicleseller.model.Driver;
@Repository
public interface DriverRepository extends CrudRepository<Driver, Long>{

}
