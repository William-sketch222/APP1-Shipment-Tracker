package com.app1.shipmenttracker.controller;

import com.app1.shipmenttracker.model.SapShipmentData;
import com.app1.shipmenttracker.service.SapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing mock SAP S/4HANA Transportation Management data.
 *
 * Endpoints:
 *   GET /api/sap/shipments       - All SAP transport orders
 *   GET /api/sap/shipments/{id}  - SAP data for a specific shipment
 *
 * In production this controller would delegate to an SAP OData v4 client
 * with retry logic, circuit breaker (Resilience4j), and response caching.
 */
@RestController
@RequestMapping("/api/sap")
public class SapController {

    private final SapService sapService;

    public SapController(SapService sapService) {
        this.sapService = sapService;
    }

    @GetMapping("/shipments")
    public List<SapShipmentData> getAllSapShipments() {
        return sapService.getAllSapData();
    }

    @GetMapping("/shipments/{id}")
    public ResponseEntity<SapShipmentData> getSapShipment(@PathVariable String id) {
        return sapService.getSapDataByShipmentId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
