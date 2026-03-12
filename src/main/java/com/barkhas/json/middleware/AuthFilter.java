package com.barkhas.json.middleware;
import com.barkhas.json.service.AuthServiceClient;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    private final AuthServiceClient authServiceClient = new AuthServiceClient();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String method = requestContext.getMethod();
        System.out.println("Incoming method: " + method);

        if ("OPTIONS".equalsIgnoreCase(method)) {
            System.out.println("OPTIONS request allowed.");
            requestContext.abortWith(Response.ok().build());
            return;
        }

        if ("POST".equalsIgnoreCase(method)) {
            System.out.println("POST request allowed without token check.");
            return;
        }

        String authHeader = requestContext.getHeaderString("Authorization");
        System.out.println("Authorization header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Missing or invalid Authorization header.")
                            .build()
            );
            return;
        }

        String token = authHeader.substring("Bearer ".length());
        System.out.println("Extracted token: " + token);

        boolean isValid = authServiceClient.validateToken(token);
        System.out.println("Token valid: " + isValid);

        if (!isValid) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Invalid token.")
                            .build()
            );
        }
    }
}