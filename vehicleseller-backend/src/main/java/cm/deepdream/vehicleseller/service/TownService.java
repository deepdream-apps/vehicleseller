package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.deepdream.vehicleseller.model.Town;
import cm.deepdream.vehicleseller.repository.TownRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TownService {
	@Autowired
	private TownRepository townRepository ;
	
	public Town create (Town town) {
		Town savedTown  = townRepository.save(town) ;
		return savedTown ;
	}
	
	
	public Town modify (Town town) {
		Town savedTown  = townRepository.save(town) ;
		return savedTown ;
	}
	
	
	public void delete(Town town) {
		townRepository.delete(town);
	}
	
	
	public Town get (Long id) {
		Town savedTown  = townRepository.findById(id).orElseGet(null) ;
		return savedTown ;
	}
	
	
	public List<Town> getAll () {
		Iterable<Town> towns  = townRepository.findAll() ;
		List<Town> townsList = new ArrayList<Town>() ;
		towns.forEach(townsList::add) ;
		return townsList ;
	}
}
