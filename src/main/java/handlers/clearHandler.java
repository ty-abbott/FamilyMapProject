package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import responses.DefaultResponse;
import services.ClearService;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

public class ClearHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
    try{
        if(exchange.getRequestMethod().toUpperCase().equals("POST")) {
            ClearService clear = new ClearService();
            DefaultResponse resp = clear.clearDB();
            //send to the service to clear the database
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            Writer respData = new OutputStreamWriter(exchange.getResponseBody());
            gson.toJson(resp, respData);
            respData.close();
            //return response message
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
