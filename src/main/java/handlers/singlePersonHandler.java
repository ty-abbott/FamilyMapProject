package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import responses.PersonResponse;
import services.PersonService;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

public class SinglePersonHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
    System.out.println("We are in the single person handler");
    Gson gson = new Gson();
    PersonService service  = new PersonService();
    try{
        if(exchange.getRequestMethod().toUpperCase().equals("GET")) {
            Headers reqHeaders = exchange.getRequestHeaders();
            if(reqHeaders.containsKey("Authorization")){
                String authToken = reqHeaders.getFirst("Authorization");
                String fields = exchange.getRequestURI().toString();
                String[] data = fields.split("/");
                PersonResponse resp = service.personInfo(authToken, data[2]);
                //send data to the server
                if(resp.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }
                else{
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }
                Writer respData = new OutputStreamWriter(exchange.getResponseBody());
                gson.toJson(resp, respData);
                respData.close();
                //return data from the server
            }
        }
        else{
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
        }
    }catch(Exception e) {
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR,0);
        exchange.getResponseBody().close();
        e.printStackTrace();
    }
    }
}
