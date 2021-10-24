package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cm.deepdream.vehicleseller.model.Model;
import cm.deepdream.vehicleseller.repository.ModelRepository;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ModelService {
	@Autowired
	private ModelRepository modelRepository ;
	
	public Model create (Model model) {
		return modelRepository.save(model) ;
	}
	
	
	public Model modify (Model model) {
		return modelRepository.save(model) ;
	}
	
	
	public void delete(Model model) {
		modelRepository.delete(model);
	}
	
	
	public Optional<Model> get (Long id) {
		return modelRepository.findById(id)  ;
	}
	
	
	public List<Model> getModels (String labelBrand) {
		return  modelRepository.findByLabelBrand(labelBrand) ;
	}
	
	
	public List<Model> getAll () {
		Iterable<Model> countries  = modelRepository.findAll() ;
		List<Model> countriesList = new ArrayList<>() ;
		countries.forEach(countriesList::add) ;
		return countriesList ;
	}
}
