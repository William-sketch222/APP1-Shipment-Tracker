# TECH-SPEC — APP1 Shipment Tracker
**Version:** 1.0 | **Date:** 2026-03-16

---

## Architecture
```
Browser (Leaflet.js + JS)
        |
        | HTTP GET /api/shipments
        |
 Spring Boot (Java 17)
   ShipmentController
        |
   ShipmentService
   (in-memory dummy data)
```

## Stack
| Layer | Technology | Version |
|-------|-----------|--------|
| Backend | Java | 17 |
| Framework | Spring Boot | 3.2.3 |
| Build | Maven | 3.9+ |
| Map Library | Leaflet.js | 1.9.4 (CDN) |
| Frontend | HTML5 / CSS3 / Vanilla JS | — |
| Server Port | 8080 | — |

## Package Structure
```
com.app1.shipmenttracker
├── ShipmentTrackerApplication.java
├── controller/
│   └── ShipmentController.java   (GET /api/shipments)
├── model/
│   └── Shipment.java
└── service/
    └── ShipmentService.java      (dummy data)
```

## API Endpoints
| Method | Path | Response |
|--------|------|----------|
| GET | /api/shipments | List<Shipment> (JSON) |
| GET | /api/shipments/{id} | Shipment (JSON) |
| GET | / | index.html (static) |

## Shipment Model
```json
{
  "id": "SHP001",
  "trackingNumber": "TRK-2026-001",
  "origin": "Shanghai, China",
  "destination": "Los Angeles, USA",
  "originLat": 31.2304, "originLng": 121.4737,
  "destLat": 34.0522, "destLng": -118.2437,
  "status": "IN_TRANSIT",
  "carrier": "Ocean Freight Co.",
  "estimatedDelivery": "2026-03-25",
  "description": "Electronics — 20ft Container",
  "lastUpdate": "2026-03-15"
}
```

## Security
- No authentication required (learning app, dummy data only)
- No external API calls
- No database, no SQL
- CORS: localhost only

## Dependencies (pom.xml)
- spring-boot-starter-web
- spring-boot-starter-test (test scope)
