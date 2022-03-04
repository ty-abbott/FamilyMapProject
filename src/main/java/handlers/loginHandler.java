package handlers;
import dao.DataAccessException;
import requests.loginRequest;
import services.loginService;
import responses.loginResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

import com.google.gson.*;

public class loginHandler implements HttpHandler {
    Gson gson = new Gson();
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("We are in the handle for login");
    try{
        if(exchange.getRequestMethod().toUpperCase().equals("POST")){
            Reader reqBody = new InputStreamReader(exchange.getRequestBody());
            loginRequest req = (loginRequest) gson.fromJson(reqBody, loginRequest.class);//might have to change this to
            loginService service = new loginService();
            loginResponse resp = service.login(req);
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
