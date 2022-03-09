package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Objects;

public class fileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try{
            if(exchange.getRequestMethod().toUpperCase().equals("GET")){
                String urlPath = exchange.getRequestURI().toString();
                if(Objects.equals(urlPath, "/")){
                    urlPath = "/index.html";
                }
                if(Objects.equals(urlPath, "/favicon.ico")){
                    String filePath = "web" + urlPath;
                    File file = new File(filePath);
                    if(file.exists()) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        OutputStream respBody = exchange.getResponseBody();
                        Files.copy(file.toPath(), respBody);
                        respBody.close();
                    }
                    else{
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                    }
                }
                String filePath = "web" + urlPath;
                File file = new File(filePath);
                if(file.exists()){
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    OutputStream respBody = exchange.getResponseBody();
                    Files.copy(file.toPath(), respBody);
                    respBody.close();
                }
                else{
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                    filePath = "web/html/404.html";
                    File file2 = new File(filePath);
                    OutputStream respBody = exchange.getResponseBody();
                    Files.copy(file2.toPath(), respBody);
                    respBody.close();
                }
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
