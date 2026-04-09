/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amartcampus.smartcamusapi;

/**
 *
 * @author mullerzerufael
 */



import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.net.URI;

public class Main {

    public static void main(String[] args) {
      URI uri = URI.create("http://localhost:9191/api/v1/");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, new ApplicationConfig());

      System.out.println("Server running at http://localhost:9191/api/v1/");

        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        server.shutdownNow();
    }
}
