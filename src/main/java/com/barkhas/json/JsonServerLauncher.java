package com.barkhas.json;
import com.barkhas.json.util.DatabaseInitializer;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import java.net.URI;

public class JsonServerLauncher {
	public static void main(String[] args) {
		DatabaseInitializer.initialize();
		String baseUri = "http://localhost:8082/";
		RestApplicationConfig config = new RestApplicationConfig();
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri), config);
		System.out.println("JSON Service is running at: " + baseUri);
		System.out.println("Press Ctrl+C to stop");
		try {
			Thread.currentThread().join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			server.shutdownNow();
		}
	}
}
