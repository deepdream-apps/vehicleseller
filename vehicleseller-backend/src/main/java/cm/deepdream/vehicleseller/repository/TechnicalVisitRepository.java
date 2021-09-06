package cm.deepdream.vehicleseller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cm.deepdream.vehicleseller.model.TechnicalVisit;
@Repository
public interface TechnicalVisitRepository extends CrudRepository<TechnicalVisit, Long>{

}
