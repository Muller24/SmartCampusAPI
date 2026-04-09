/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amartcampus.smartcamusapi;

/**
 *
 * @author mullerzerufael
 */



import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    private String sensorId;

    public static Map<String, List<SensorReading>> readingStore = new HashMap<>();

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    @GET
    public Response getReadings() {
        List<SensorReading> readings = readingStore.get(sensorId);

        if (readings == null) {
            readings = new ArrayList<>();
        }

        return Response.ok(readings).build();
    }

    @POST
    public Response addReading(SensorReading reading) {

        Sensor sensor = SensorResource.sensors.get(sensorId);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Sensor not found")
                    .build();
        
        
        }
        readingStore.computeIfAbsent(sensorId, k -> new ArrayList<>()).add(reading);
        
        sensor.setCurrentValue(reading.getValue());
        
        
        if ("MAINTENANCE".equalsIgnoreCase(sensor.getStatus())) {
           throw new SensorUnavailableException("Sensor is in maintenance mode");
}

        

        if (reading.getId() == null || reading.getId().isEmpty()) {
            reading.setId(UUID.randomUUID().toString());
        }

        if (reading.getTimestamp() == 0) {
            reading.setTimestamp(System.currentTimeMillis());
        }

        readingStore.computeIfAbsent(sensorId, k -> new ArrayList<>()).add(reading);

        sensor.setCurrentValue(reading.getValue());

        return Response.status(Response.Status.CREATED)
                .entity(reading)
                .build();
    
    
    
    }
    
    @Path("/{id}/readings")
     public SensorReadingResource getReadingResource(@PathParam("id") String id) {
    return new SensorReadingResource(id);

     }
     
}