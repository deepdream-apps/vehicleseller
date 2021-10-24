package cm.deepdream.vehicleseller.webservice;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cm.deepdream.vehicleseller.model.Model;
import cm.deepdream.vehicleseller.service.ModelService;

@RestController
@RequestMapping("/api/model")
public class ModelWS {
	@Autowired
	private ModelService modelService ;
	
	
	@PostMapping("/add")
	public ResponseEntity<Model> addModel(@RequestBody Model model)  {
	    if(model.getLabel() == null || model.getLabel().isBlank()) {
	         return ResponseEntity.badRequest().build();
	     }
	    Model newModel = modelService.create(model) ;
	    return ResponseEntity.ok(newModel) ;
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Model> updateModel(@RequestBody Model model)  {

	    Optional<Model> optModel = modelService.get(model.getId()) ;
	    
	    if(optModel.isEmpty() || model.getLabel() == null || model.getLabel().isBlank()) {
	    	return ResponseEntity.badRequest().build() ;
	    }
	    
	    Model existingModel = optModel.get() ;
	    
	    existingModel.setLabel(model.getLabel());
	    Model upadatedModel = modelService.create(existingModel) ;
	    return ResponseEntity.ok(upadatedModel) ;
	}
	
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity deleteModel(@PathVariable("id") Long id) {
	    Optional<Model> optModel = modelService.get(id) ;
	    if(optModel.isEmpty()) {
	    	return ResponseEntity.badRequest().build();
	    }
	    modelService.delete(optModel.get()) ;
	    return ResponseEntity.ok().build() ;
	}
	
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Model> getModel(@PathVariable("id") Long id)  {
	    Optional<Model> optModel = modelService.get(id) ;
	    if(optModel.isEmpty()) {
	    	return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(optModel.get()) ;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Model>> getAllModels() {
	    List<Model> listModels = modelService.getAll() ;
	    return ResponseEntity.ok(listModels) ;
	}
}
