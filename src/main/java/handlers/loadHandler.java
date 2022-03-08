package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import requests.loadRequest;
import requests.registerRequest;
import responses.defaultResponse;
import services.loadService;

import java.io.*;
import java.net.HttpURLConnection;

public class loadHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        Gson gson = new Gson();
        System.out.println("We are in the load handler");

        try{
            if(exchange.getRequestMethod().toUpperCase().equals("POST")){
                Reader reqBody = new InputStreamReader(exchange.getRequestBody());
                loadRequest req = (loadRequest) gson.fromJson(reqBody, loadRequest.class);
                loadService service = new loadService();
                defaultResponse resp = service.loadData(req);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Writer respData = new OutputStreamWriter(exchange.getResponseBody());
                gson.toJson(resp, respData);
                respData.close();
            }
            else{
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        } catch (IOException | DataAccessException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
