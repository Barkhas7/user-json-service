package com.barkhas.json;
import com.barkhas.json.controller.UserController;
import com.barkhas.json.middleware.AuthFilter;
import org.glassfish.jersey.server.ResourceConfig;

public class RestApplicationConfig extends ResourceConfig {
    public RestApplicationConfig() {
        register(UserController.class);
        register(AuthFilter.class);
        register(CorsResponseFilter.class);
        packages("com.barkhas.json");
    }
}