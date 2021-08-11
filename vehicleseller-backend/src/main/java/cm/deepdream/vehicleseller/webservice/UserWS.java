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
import cm.deepdream.vehicleseller.model.User;
import cm.deepdream.vehicleseller.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Path("/api/user")
@Slf4j
public class UserWS {
	@Autowired
	private UserService userService ;
	
	
	@POST
	@Path("/add/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addUser(User user) throws URISyntaxException {
	    if(user.getEmailAddress() == null || user.getEmailAddress().equals("")) {
	            return Response.status(400).entity("Please provide all mandatory inputs").build();
	     }
	    User newUser = userService.create(user) ;
	    return Response.ok(newUser).build();
	}
	
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateUser(@PathParam("id") Long id, User user) throws URISyntaxException {
	    if(user.getEmailAddress() == null || user.getEmailAddress().equals("")) {
	         return Response.status(400).build();
	     }
	    User existingUser = userService.get(id) ;
	    if(existingUser == null) {
	    	return Response.status(400).build();
	    }
	    existingUser.setFirstName(user.getFirstName());
	    existingUser.setLastName(user.getLastName());
	    User upadatedUser = userService.create(existingUser) ;
	    return Response.ok(upadatedUser).build();
	}
	
	
	
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteUser(@PathParam("id") Long id) throws URISyntaxException {
	    User existingUser = userService.get(id) ;
	    if(existingUser == null) {
	    	return Response.status(400).build();
	    }
	    userService.delete(existingUser) ;
	    return Response.ok(1).build();
	}
	
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getUser(@PathParam("id") Long id) throws URISyntaxException {
	    User existingUser = userService.get(id) ;
	    if(existingUser == null) {
	    	return Response.noContent().build();
	    }
	    return Response.ok(existingUser).build();
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllUsers() throws URISyntaxException {
	    List<User> listCountries = userService.getAll() ;
	    return Response.ok(listCountries).build();
	}
}
