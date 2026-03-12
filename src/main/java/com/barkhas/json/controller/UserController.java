package com.barkhas.json.controller;
import com.barkhas.json.model.UserProfile;
import com.barkhas.json.model.UserRequest;
import com.barkhas.json.service.UserService;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService = new UserService();
    
    @GET
    @Path("/by-user")
    public Response getUserByUserId(@QueryParam("userId") int userId) {
        UserProfile profile = userService.getUserProfileByUserId(userId);

        if (profile != null) {
            return Response.ok(profile).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Хэрэглэгчийн хуудас олдсонгүй.")
                    .build();
        }
    }

    @OPTIONS
    public Response options() {
        return Response.ok().build();
    }

    @OPTIONS
    @Path("/{id}")
    public Response optionsById() {
        return Response.ok().build();
    }

    @POST
    public Response createUser(UserRequest request) {
        String result = userService.createUserProfile(request);
        return Response.ok(result).build();
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") int id) {
        UserProfile profile = userService.getUserProfileById(id);

        if (profile != null) {
            return Response.ok(profile).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Хэрэглэгчийн хуудас олдсонгүй.")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") int id, UserRequest request) {
        String result = userService.updateUserProfile(id, request);
        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        String result = userService.deleteUserProfile(id);
        return Response.ok(result).build();
    }
}