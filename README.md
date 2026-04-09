# SmartCampus API

## Overview
This project implements a RESTful API for managing rooms, sensors, and sensor readings in a smart campus system.

## How to Run
1. Open the project in NetBeans
2. Run `Main.java`
3. The server will start at:
   http://localhost:9191/api/v1/

## Features
- Create and manage rooms
- Create and manage sensors
- Add sensor readings
- Error handling for invalid operations

## Endpoints

### Root
GET /api/v1/

### Rooms
POST /api/v1/rooms  
GET /api/v1/rooms  
GET /api/v1/rooms/{id}  
DELETE /api/v1/rooms/{id}  

### Sensors
POST /api/v1/sensors  
GET /api/v1/sensors  
GET /api/v1/sensors/{id}  
GET /api/v1/sensors?type=Temperature  

### Sensor Readings
POST /api/v1/sensors/{id}/readings  
GET /api/v1/sensors/{id}/readings  

## Sample cURL Commands

```bash
curl -X POST http://localhost:9191/api/v1/rooms \
-H "Content-Type: application/json" \
-d '{"id":"R1","name":"Lab","capacity":30}'

curl -X GET http://localhost:9191/api/v1/rooms

curl -X POST http://localhost:9191/api/v1/sensors \
-H "Content-Type: application/json" \
-d '{"id":"S1","type":"Temperature","status":"ACTIVE","currentValue":0,"roomId":"R1"}'

curl -X GET http://localhost:9191/api/v1/sensors

curl -X POST http://localhost:9191/api/v1/sensors/S1/readings \
-H "Content-Type: application/json" \
-d '{"value":45.2}'

curl -X GET http://localhost:9191/api/v1/sensors/S1/readings
