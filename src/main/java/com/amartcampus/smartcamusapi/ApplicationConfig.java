/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amartcampus.smartcamusapi;

/**
 *
 * @author mullerzerufael
 */



import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        register(DiscoveryResource.class);
        register(RoomResource.class);
        register(SensorResource.class);
        register(RoomNotEmptyExceptionMapper.class);
        register(LinkedResourceNotFoundExceptionMapper.class);
        register(SensorUnavailableExceptionMapper.class);
        register(GlobalExceptionMapper.class);
        register(LoggingFilter.class);
    }
}