package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import responses.EventResponse;
import services.EventService;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

public class SingleEventHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
    System.out.println("We are in the single event handler");
    Gson gson = new Gson();
    EventService service = new EventService();

    try{
        if(exchange.getRequestMethod().toUpperCase().equals("GET")){
            Headers reqHeaders = exchange.getRequestHeaders();
            if(reqHeaders.containsKey("Authorization")){
                String authToken = reqHeaders.getFirst("Authorization");
                String fields = exchange.getRequestURI().toString();
                String[] data = fields.split("/");
                EventResponse resp = service.getEvent(authToken, data[2]);
                if(resp.isSuccess()){
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }
                Writer respData = new OutputStreamWriter(exchange.getResponseBody());
                gson.toJson(resp, respData);
                respData.close();
            }
            else{
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        }
        else{
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
    } catch(Exception e){
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR,0);
        exchange.getResponseBody().close();
        e.printStackTrace();
    }
    }
}
