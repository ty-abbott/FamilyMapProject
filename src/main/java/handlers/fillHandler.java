package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import responses.defaultResponse;
import services.fillService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

public class fillHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        fillService service;
        Gson gson = new Gson();
        System.out.println("We are in the fill handler");
        try{
            if(exchange.getRequestMethod().toUpperCase().equals("POST")){
                String fields = exchange.getRequestURI().toString();
                System.out.println(fields);
                String[] data = fields.split("/");
                for(int i = 0; i < data.length; i++){
                    System.out.println(data[i]);
                }
                System.out.println(data.length);
                if (data.length>2){
                    service = new fillService(data[2], Integer.parseInt(data[3]));
                }

                else {
                    service = new fillService(data[2]);
                }

                defaultResponse resp = service.fill();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Writer respData = new OutputStreamWriter(exchange.getResponseBody());
                gson.toJson(resp, respData);
                respData.close();
            }
            else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        }catch(Exception e){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
