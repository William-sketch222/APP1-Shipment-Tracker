# TECH-SPEC — APP1 Shipment Tracker
**Version:** 1.1 | **Date:** 2026-03-16

---

## Architecture

```
Browser (Leaflet.js + Vanilla JS)
        |
        |── GET /api/shipments          → ShipmentController
        |── GET /api/sap/shipments/{id} → SapController → SapService
        |── GET /api/customs/{id}       → CustomsController → CustomsService
        |── POST /ws (SOAP)             → CustomsEndpoint → CustomsService
        |── GET /ws/customs.wsdl        → Auto-generated from XSD
```

## Stack
| Layer | Technology | Version |
|-------|-----------|--------|
| Backend | Java | 17 |
| Framework | Spring Boot | 3.2.3 |
| SOAP | Spring-WS | 4.0.10 |
| WSDL | WSDL4J | 1.6.3 |
| Build | Maven | 3.9+ |
| Map | Leaflet.js | 1.9.4 (CDN) |
| Frontend | HTML5/CSS3/Vanilla JS | — |

## Package Structure
```
com.app1.shipmenttracker
├── ShipmentTrackerApplication.java
├── controller/
│   ├── ShipmentController.java    GET /api/shipments
│   ├── SapController.java         GET /api/sap/shipments
│   └── CustomsController.java     GET /api/customs/{id}
├── model/
│   ├── Shipment.java
│   ├── SapShipmentData.java
│   └── CustomsStatus.java
├── service/
│   ├── ShipmentService.java
│   ├── SapService.java
│   └── CustomsService.java
└── soap/
    ├── CustomsEndpoint.java       @Endpoint (Spring-WS)
    └── WebServiceConfig.java      WSDL + servlet config
```

## API Endpoints
| Method | Path | Description |
|--------|------|-------------|
| GET | /api/shipments | All shipments (JSON) |
| GET | /api/shipments/{id} | Single shipment |
| GET | /api/sap/shipments | All SAP transport data (JSON) |
| GET | /api/sap/shipments/{id} | SAP data for one shipment |
| GET | /api/customs/{id} | Trigger customs check, return JSON |
| POST | /ws | SOAP customs declaration endpoint |
| GET | /ws/customs.wsdl | Auto-generated WSDL |
| GET | / | index.html (static frontend) |

## SOAP Contract
- **Namespace:** `http://app1.com/customs`
- **Request:** `GetCustomsStatusRequest` (shipmentId, hsCode, declaredValue, currency, originCountry, destinationCountry)
- **Response:** `GetCustomsStatusResponse` (declarationNumber, clearanceStatus, dutyRate, dutyAmount, estimatedClearanceDate, remarks)
- **XSD:** `src/main/resources/xsd/customs-service.xsd`
- **WSDL:** auto-generated, served at `/ws/customs.wsdl`

## Test the SOAP Endpoint (SoapUI / Postman)
```xml
POST http://localhost:8080/ws
Content-Type: text/xml

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:cust="http://app1.com/customs">
  <soapenv:Body>
    <cust:GetCustomsStatusRequest>
      <cust:shipmentId>SHP001</cust:shipmentId>
      <cust:hsCode>8471.30.0000</cust:hsCode>
      <cust:declaredValue>215000.00</cust:declaredValue>
      <cust:currency>USD</cust:currency>
      <cust:originCountry>CN</cust:originCountry>
      <cust:destinationCountry>US</cust:destinationCountry>
    </cust:GetCustomsStatusRequest>
  </soapenv:Body>
</soapenv:Envelope>
```
