package com.app1.shipmenttracker.service;

import com.app1.shipmenttracker.model.Shipment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {

    private final List<Shipment> shipments = Arrays.asList(
        new Shipment("SHP001", "TRK-2026-001",
            "Shanghai, China", "Los Angeles, USA",
            31.2304, 121.4737, 34.0522, -118.2437,
            "IN_TRANSIT", "Ocean Freight Co.",
            "2026-03-25", "Electronics \u2014 20ft Container", "2026-03-15"),

        new Shipment("SHP002", "TRK-2026-002",
            "New York, USA", "London, UK",
            40.7128, -74.0060, 51.5074, -0.1278,
            "DELIVERED", "Atlantic Express",
            "2026-03-10", "Pharmaceuticals \u2014 Air Freight", "2026-03-10"),

        new Shipment("SHP003", "TRK-2026-003",
            "Dubai, UAE", "Singapore",
            25.2048, 55.2708, 1.3521, 103.8198,
            "IN_TRANSIT", "Gulf Cargo",
            "2026-03-20", "Luxury Goods \u2014 Air Freight", "2026-03-14"),

        new Shipment("SHP004", "TRK-2026-004",
            "Sydney, Australia", "Tokyo, Japan",
            -33.8688, 151.2093, 35.6762, 139.6503,
            "PROCESSING", "Pacific Lines",
            "2026-03-28", "Raw Materials \u2014 Bulk Carrier", "2026-03-16"),

        new Shipment("SHP005", "TRK-2026-005",
            "Frankfurt, Germany", "Chicago, USA",
            50.1109, 8.6821, 41.8781, -87.6298,
            "IN_TRANSIT", "Euro Air Cargo",
            "2026-03-18", "Auto Parts \u2014 Air Freight", "2026-03-15"),

        new Shipment("SHP006", "TRK-2026-006",
            "Mumbai, India", "Rotterdam, Netherlands",
            19.0760, 72.8777, 51.9244, 4.4777,
            "DELIVERED", "India Ocean Shipping",
            "2026-03-08", "Textiles \u2014 40ft Container", "2026-03-08"),

        new Shipment("SHP007", "TRK-2026-007",
            "S\u00e3o Paulo, Brazil", "Miami, USA",
            -23.5505, -46.6333, 25.7617, -80.1918,
            "IN_TRANSIT", "South American Lines",
            "2026-03-22", "Coffee & Commodities", "2026-03-13"),

        new Shipment("SHP008", "TRK-2026-008",
            "Toronto, Canada", "Mexico City, Mexico",
            43.6532, -79.3832, 19.4326, -99.1332,
            "PROCESSING", "North America Freight",
            "2026-03-30", "Industrial Machinery", "2026-03-16"),

        new Shipment("SHP009", "TRK-2026-009",
            "Cape Town, South Africa", "Amsterdam, Netherlands",
            -33.9249, 18.4241, 52.3676, 4.9041,
            "IN_TRANSIT", "African Shipping Co.",
            "2026-04-02", "Diamonds & Minerals", "2026-03-12"),

        new Shipment("SHP010", "TRK-2026-010",
            "Seoul, South Korea", "Seattle, USA",
            37.5665, 126.9780, 47.6062, -122.3321,
            "DELIVERED", "Pacific Star Cargo",
            "2026-03-14", "Consumer Electronics", "2026-03-14")
    );

    public List<Shipment> getAllShipments() {
        return shipments;
    }

    public Optional<Shipment> getShipmentById(String id) {
        return shipments.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }
}
