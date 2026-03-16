package com.app1.shipmenttracker.controller;

import com.app1.shipmenttracker.model.CustomsStatus;
import com.app1.shipmenttracker.model.SapShipmentData;
import com.app1.shipmenttracker.service.CustomsService;
import com.app1.shipmenttracker.service.SapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * REST facade for the B2G customs clearance SOAP service.
 *
 * Provides a JSON-friendly endpoint for the frontend while internally
 * calling the same CustomsService logic used by the SOAP endpoint.
 *
 * Endpoint:
 *   GET /api/customs/{shipmentId}  - Trigger customs check, return JSON result
 *
 * Note: The raw SOAP interface is also available at POST /ws
 *       WSDL: GET /ws/customs.wsdl
 *       (Use SoapUI or Postman to test the SOAP endpoint directly)
 */
@RestController
@RequestMapping("/api/customs")
public class CustomsController {

    private final CustomsService customsService;
    private final SapService     sapService;

    public CustomsController(CustomsService customsService, SapService sapService) {
        this.customsService = customsService;
        this.sapService     = sapService;
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<CustomsStatus> checkCustoms(@PathVariable String shipmentId) {
        // Look up SAP data to get HS code and declared value
        SapShipmentData sap = sapService.getSapDataByShipmentId(shipmentId).orElse(null);
        if (sap == null) return ResponseEntity.notFound().build();

        CustomsStatus status = customsService.getCustomsStatus(
                shipmentId,
                sap.getHsCode(),
                BigDecimal.valueOf(sap.getCustomsValueUSD()),
                "USD",
                sap.getCountryOfOrigin(),
                "DEST" // destination varies per shipment; simplified for demo
        );
        return ResponseEntity.ok(status);
    }
}
