package cm.deepdream.vehicleseller.webservice;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import cm.deepdream.vehicleseller.model.Model;
import cm.deepdream.vehicleseller.service.ModelService;
import lombok.extern.slf4j.Slf4j;

@Path("/api/model")
@Slf4j
public class ModelWS {
	@Autowired
	private ModelService modelService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addModel(Model model) throws URISyntaxException {
	    if(model.getLabel() == null || model.getLabel().equals("")) {
	            return Response.status(400).entity("Please provide all mandatory inputs").build();
	     }
	    Model newModel = modelService.create(model) ;
	    return Response.ok(newModel).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateModel(@PathParam("id") Long id, Model model) throws URISyntaxException {
	    if(model.getLabel() == null || model.getLabel().equals("")) {
	         return Response.status(400).build();
	     }
	    Model existingModel = modelService.get(id) ;
	    if(existingModel == null) {
	    	return Response.status(400).build();
	    }
	    existingModel.setLabel(model.getLabel());
	    existingModel.setDescription(model.getDescription());
	    Model upadatedModel = modelService.create(existingModel) ;
	    return Response.ok(upadatedModel).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteModel(@PathParam("id") Long id) throws URISyntaxException {
	    Model existingModel = modelService.get(id) ;
	    if(existingModel == null) {
	    	return Response.status(400).build();
	    }
	    modelService.delete(existingModel) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getModel(@PathParam("id") Long id) throws URISyntaxException {
	    Model existingModel = modelService.get(id) ;
	    if(existingModel == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingModel).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllModels() throws URISyntaxException {
	    List<Model> listModels = modelService.getAll() ;
	    return Response.ok(listModels).build();
	}
}
