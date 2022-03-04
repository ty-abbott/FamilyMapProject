package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import responses.defaultResponse;
import services.clearService;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

public class clearHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
    try{
        if(exchange.getRequestMethod().toUpperCase().equals("POST")) {
            clearService clear = new clearService();
            defaultResponse resp = clear.clearDB();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            Writer respData = new OutputStreamWriter(exchange.getResponseBody());
            gson.toJson(resp, respData);
            respData.close();
        }
        else{
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
    } catch (IOException | DataAccessException e){
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
        exchange.getResponseBody().close();
        e.printStackTrace();
    }
    }
}