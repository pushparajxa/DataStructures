/*
 ** COPYRIGHT **
 */
package com.lang.http;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.Authenticator;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpClientTest {
    
    public static void main(String[] args)
        throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://www.google.com/"))
            .GET()
            .build();
        
        HttpResponse<String> response = HttpClient.newBuilder()
            .build()
            .send(request, BodyHandlers.ofString());
        
       // System.out.println(response.headers());
        
        request = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:7898/user"))
            .GET()
            .build();
        
        
        response = HttpClient.newBuilder()
            .authenticator(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                        "john",
                        "test123".toCharArray());
                }
            }).build()
            .send(request, BodyHandlers.ofString());
        
       // System.out.println(response.body());
       // System.out.println(response.statusCode());
        String jsonBody = """
            {
              "firstName": "Johny",
              "lastName": "Peter",
              "age": 45
            }
        """;
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:7898/user")) // Example API endpoint
            .header("Content-Type", "application/json") // Crucial for JSON requests
            .POST(BodyPublishers.ofString(jsonBody)) // Set the method to POST and add the body
            .build();
        
        response = HttpClient.newBuilder()
            .authenticator(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                        "john",
                        "test123".toCharArray());
                }
            }).build()
            .send(request, BodyHandlers.ofString());
        
       // System.out.println(response.body());
      //  System.out.println(response.statusCode());
        
        
        Map<String, String> formData = Map.of(
            "firstName", "John",
            "lastName", "Doe",
            "age", "30"
        );
        
        String formBody = formData.entrySet().stream()
            .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8) + "=" +
                URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
            .collect(Collectors.joining("&"));

        request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:7898/user"))
            .header("Content-Type", "application/x-www-form-urlencoded") // Required
            .POST(HttpRequest.BodyPublishers.ofString(formBody))
            .build();
        
         response =HttpClient.newBuilder()
             .authenticator(new Authenticator() {
                 @Override
                 protected PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication(
                         "john",
                         "test123".toCharArray());
                 }
             }).build()
             .send(request, BodyHandlers.ofString());
        
         System.out.println(response.body());
         System.out.println(response.statusCode());
        
        
    }
    
}
