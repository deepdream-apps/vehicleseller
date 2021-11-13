package cm.deepdream.vehicleseller.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import cm.deepdream.vehicleseller.dto.ModelDTO;
import cm.deepdream.vehicleseller.model.Model;
import cm.deepdream.vehicleseller.repository.ModelRepository;
import lombok.extern.log4j.Log4j2;

@Transactional
@Service
@Log4j2
public class ModelService {
	private ModelRepository modelRepository ;
	
	public ModelService(ModelRepository modelRepository) {
		this.modelRepository = modelRepository;
	}


	public ModelDTO create (ModelDTO modelDTO) {
		Model model = Model.builder()
				.label(modelDTO.getLabel())
				.labelBrand(modelDTO.getLabelBrand())
				.build() ;
		
		Model createdModel = modelRepository.save(model) ;
		
		return ModelDTO.builder()
				.id(createdModel.getId())
				.label(createdModel.getLabel())
				.labelBrand(createdModel.getLabelBrand())
				.build() ;
	}
	
	
	public ModelDTO update (ModelDTO modelDTO) {
		Optional<Model> optModel = modelRepository.findById(modelDTO.getId()) ;
		
		if(optModel.isEmpty()) {
			throw new IllegalArgumentException("The provided model does not exist") ;
		}
		
		Model model = optModel.get() ;
		model.setLabel(modelDTO.getLabel());
		model.setLabelBrand(modelDTO.getLabelBrand());
		Model updatedModel = modelRepository.save(model) ;
		
		return ModelDTO.builder()
				.id(updatedModel.getId())
				.label(updatedModel.getLabel())
				.labelBrand(updatedModel.getLabelBrand())
				.build() ;
	}
	
	
	public void delete(ModelDTO modelDTO) {
		Model model = Model.builder()
				.id(modelDTO.getId())
				.label(modelDTO.getLabel())
				.labelBrand(modelDTO.getLabelBrand())
				.build() ;
		modelRepository.delete(model);
	}
	
	
	public Optional<ModelDTO> get (Long id) {
		Optional<Model> optModel = modelRepository.findById(id)  ;
		
		if(optModel.isEmpty()) {
			return Optional.empty() ;
		}
		
		Model model = optModel.get() ;
		
		ModelDTO modelDTO = ModelDTO.builder()
				.id(model.getId())
				.label(model.getLabel())
				.labelBrand(model.getLabelBrand())
				.build() ;
		return Optional.of(modelDTO) ;
	}
	
	
	public List<ModelDTO> getModels (String labelBrand) {
		List<Model> itModels =  modelRepository.findByLabelBrand(labelBrand) ;
		final List<ModelDTO> listModels = new ArrayList<>() ;
		itModels.forEach(model -> {
			ModelDTO modelDTO = ModelDTO.builder()
					  				.id(model.getId())
					  				.label(model.getLabel())
					  				.labelBrand(model.getLabelBrand())
					  				.build() ;
			listModels.add(modelDTO) ;
		});
		
		return listModels ;
	}
	
	
	public List<ModelDTO> getAll () {
		Iterable<Model> itModels  = modelRepository.findAll() ;
		final List<ModelDTO> listModels = new ArrayList<>() ;
		itModels.forEach(model -> {
			ModelDTO modelDTO = ModelDTO.builder()
					  				.id(model.getId())
					  				.label(model.getLabel())
					  				.labelBrand(model.getLabelBrand())
					  				.build() ;
			listModels.add(modelDTO) ;
		});
		
		return listModels ;
	}
	
	
}
