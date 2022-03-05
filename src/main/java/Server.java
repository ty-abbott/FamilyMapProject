import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
import handlers.*;
import helpers.loadJSON;
import models.Location;
import models.LocationData;

public class Server {
    private static final int MAX_WAITING_CONNECTIONS = 12;
    private HttpServer server;

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        new Server().run(port);
    }

    private void run(int port) {
        System.out.println("Initializing HTTP Server");
        InetSocketAddress serverAddress = new InetSocketAddress(port);
        try{
            server = HttpServer.create(serverAddress, MAX_WAITING_CONNECTIONS);

        } catch (IOException e){
            e.printStackTrace();
            return;
        }
        registerHandlers();
        System.out.println("Starting server");
        server.start();
        System.out.println("Server started. Listening on port " + port);

    }

    private void registerHandlers() {
        System.out.println("Creating Contexts");
        server.createContext("/", new fileHandler());
        server.createContext("/user/register", new registerHandler());
        server.createContext("/user/login", new loginHandler());
        server.createContext("/person/", new singlePersonHandler());
        server.createContext("/event/", new singleEventHandler());
        server.createContext("/event", new eventsHandler());
        server.createContext("/fill", new fillHandler());
        server.createContext("/load", new loadHandler());
        server.createContext("/clear", new clearHandler());
        server.createContext("/person", new personHandler());

    }
}
