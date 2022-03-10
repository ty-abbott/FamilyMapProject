package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

import com.google.gson.*;
import dao.DataAccessException;
import requests.RegisterRequest;
import services.RegisterService;
import responses.RegisterResponse;

public class RegisterHandler implements HttpHandler {
    Gson gson = new Gson();
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("We are in the handle for register");
        try{
            if(exchange.getRequestMethod().toUpperCase().equals("POST")){
                Reader reqBody = new InputStreamReader(exchange.getRequestBody());
                RegisterRequest req = (RegisterRequest) gson.fromJson(reqBody, RegisterRequest.class);
                RegisterService service = new RegisterService();
                RegisterResponse resp = service.createAccount(req);
                if(resp.getSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }
                else{
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
                }
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
