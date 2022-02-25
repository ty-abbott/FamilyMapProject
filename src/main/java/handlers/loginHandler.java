package handlers;
import requests.loginRequest;
import services.loginService;
import responses.loginResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import com.google.gson.*;

public class loginHandler implements HttpHandler {
    Gson gson = new Gson();
    @Override
    public void handle(HttpExchange exchange) throws IOException {
    try{
        if(exchange.getRequestMethod().toUpperCase().equals("POST")){
            InputStream reqBody = exchange.getRequestBody();
            System.out.println(reqBody);
            loginRequest req = gson.fromJson(String.valueOf(reqBody), loginRequest.class);//might have to change this to
            // follow the code examples more and have a read string thing
            loginService service = new loginService();
            loginResponse resp = service.login(req);

        }
        else{
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
    } catch (IOException e){
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
        exchange.getResponseBody().close();
        e.printStackTrace();
    }
    }
}
