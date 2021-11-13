package cm.deepdream.vehicleseller.webservice;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import cm.deepdream.vehicleseller.dto.ModelDTO;
import cm.deepdream.vehicleseller.service.ModelService;

@RestController
@RequestMapping("/api/model")
public class ModelWS {
	private ModelService modelService ;
	
	
	public ModelWS(ModelService modelService) {
		this.modelService = modelService;
	}


	@PostMapping("/add")
	public ResponseEntity<ModelDTO> addModel(@Valid  @RequestBody ModelDTO modelDTO)  {
	    if(modelDTO.getLabel() == null || modelDTO.getLabel().isBlank()) {
	         throw new IllegalArgumentException("Error ! Bad model object submitted") ;
	    }
	    
	    ModelDTO newModelDTO = modelService.create(modelDTO) ;
	    return ResponseEntity.ok(newModelDTO) ;
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<ModelDTO> updateModel(@RequestBody ModelDTO modelDTO)  {
		if(modelDTO.getId() == null) {
			 throw new IllegalArgumentException("Error ! Bad model object submitted") ;
		}

	    ModelDTO updatedModelDTO = modelService.update(modelDTO) ;
	    
	    return ResponseEntity.ok(updatedModelDTO) ;
	}
	
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity deleteModel(@PathVariable("id") Long id) {
		if(id == null) {
			 throw new IllegalArgumentException("Error ! Bad model id submitted") ;
		}
		
	    ModelDTO modelDTO = ModelDTO.builder()
	    						.id(id)
	    						.build() ;
	    modelService.delete(modelDTO) ;
	    return ResponseEntity.ok().build() ;
	}
	
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<ModelDTO> getModel(@PathVariable("id") Long id)  {
		if(id == null) {
			 throw new IllegalArgumentException("Error ! Bad model id submitted") ;
		}
		
	    Optional<ModelDTO> optModelDTO = modelService.get(id) ;
	    if(optModelDTO.isEmpty()) {
	    	return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(optModelDTO.get()) ;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<ModelDTO>> getAllModels() {
	    List<ModelDTO> listModels = modelService.getAll() ;
	    return ResponseEntity.ok(listModels) ;
	}
	
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public void illegalArgumentException(IllegalArgumentException ex) {}
}
