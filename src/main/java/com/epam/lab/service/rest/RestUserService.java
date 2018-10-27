package com.epam.lab.service.rest;

import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(value = "/restService")
public interface RestUserService {

	@GET
	@Path("/authorize")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authorize(@HeaderParam("userName") String userName, @HeaderParam("password") String password);

	@GET
	@Path("/getAllUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(@HeaderParam("userName") String userName, @HeaderParam("password") String password);

	@GET
	@Path("/getUser/{role}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsersByRole(@HeaderParam("userName") String userName, @HeaderParam("password") String password,
			@PathParam("role") String role);

	@DELETE
	@Path("/deleteUser/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@HeaderParam("userName") String userName, @HeaderParam("password") String password,
			@PathParam("name") String name);

	@GET
	@Path("/authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(@HeaderParam("userName") String userName, @HeaderParam("password") String password);

}
