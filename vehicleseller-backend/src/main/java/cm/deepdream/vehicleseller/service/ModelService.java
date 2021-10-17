package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.deepdream.vehicleseller.model.Brand;
import cm.deepdream.vehicleseller.model.Model;
import cm.deepdream.vehicleseller.repository.ModelRepository;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ModelService {
	@Autowired
	private ModelRepository modelRepository ;
	
	public Model create (Model model) {
		Model savedModel  = modelRepository.save(model) ;
		return savedModel ;
	}
	
	
	public Model modify (Model model) {
		Model savedModel  = modelRepository.save(model) ;
		return savedModel ;
	}
	
	
	public void delete(Model model) {
		modelRepository.delete(model);
	}
	
	
	public Model get (Long id) {
		Model savedModel  = modelRepository.findById(id).orElseGet(null) ;
		return savedModel ;
	}
	
	
	public List<Model> getModels (String labelBrand) {
		List<Model> modelsList =  modelRepository.findByLabelBrand(labelBrand) ;
		return modelsList ;
	}
	
	
	public List<Model> getAll () {
		Iterable<Model> countries  = modelRepository.findAll() ;
		List<Model> countriesList = new ArrayList<Model>() ;
		countries.forEach(countriesList::add) ;
		return countriesList ;
	}
}
