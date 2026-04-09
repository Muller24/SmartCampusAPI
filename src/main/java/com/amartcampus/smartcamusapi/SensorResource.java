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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    public static Map<String, Sensor> sensors = new HashMap<>();

    @GET
    public Collection<Sensor> getSensors(@QueryParam("type") String type) {

        if (type == null || type.isEmpty()) {
            return sensors.values();
        }

        List<Sensor> filtered = new ArrayList<>();

        for (Sensor sensor : sensors.values()) {
            if (sensor.getType() != null && sensor.getType().equalsIgnoreCase(type)) {
                filtered.add(sensor);
            }
        }

        return filtered;
    }

    @POST
    public Response createSensor(Sensor sensor) {

        Room room = RoomResource.rooms.get(sensor.getRoomId());

        if (room == null) {
         throw new LinkedResourceNotFoundException("Room does not exist");
         }

        sensors.put(sensor.getId(), sensor);

        room.getSensorIds().add(sensor.getId());

        return Response.status(Response.Status.CREATED)
                .entity(sensor)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getSensor(@PathParam("id") String id) {
        Sensor sensor = sensors.get(id);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Sensor not found")
                    .build();
        }

        return Response.ok(sensor).build();
    }

    @Path("/{id}/readings")
    public SensorReadingResource getReadingResource(@PathParam("id") String id) {
        return new SensorReadingResource(id);
    }
}
