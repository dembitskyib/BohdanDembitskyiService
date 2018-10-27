package com.epam.lab.service.responce;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

public class RestResponseBuilder {
	private static Gson gson = new Gson();

	public static <T> Response getResponse(Status status, T entity) {
		return Response.status(status).entity(gson.toJson(entity)).type(MediaType.APPLICATION_JSON).build();
	}
}
