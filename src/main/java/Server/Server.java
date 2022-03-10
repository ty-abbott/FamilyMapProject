package Server;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
import handlers.*;

public class Server {
    private static final int MAX_WAITING_CONNECTIONS = 12;
    private HttpServer server;

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        new Server().run(port);
    }

    private void run(int port) {
        System.out.println("Initializing HTTP Server.Server");
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
        System.out.println("Server.Server started. Listening on port " + port);

    }

    private void registerHandlers() {
        System.out.println("Creating Contexts");
        server.createContext("/", new FileHandler());
        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/person/", new SinglePersonHandler());
        server.createContext("/event/", new SingleEventHandler());
        server.createContext("/event", new EventsHandler());
        server.createContext("/fill", new FillHandler());
        server.createContext("/load", new LoadHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/person", new PersonHandler());

    }
}
