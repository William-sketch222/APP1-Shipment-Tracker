package com.app1.shipmenttracker.service;

import com.app1.shipmenttracker.model.SapShipmentData;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Simulates a SAP S/4HANA Transportation Management data source.
 *
 * Real integration would call:
 *   GET /sap/opu/odata4/tms/transportorder/0001/TransportOrder
 * with OAuth2 or Basic Auth and parse OData JSON responses.
 *
 * Dummy data covers all 10 shipments with realistic SAP fields:
 * HS codes, incoterms, weights, volumes, freight and customs values.
 */
@Service
public class SapService {

    private final Map<String, SapShipmentData> sapDataMap;

    public SapService() {
        List<SapShipmentData> data = Arrays.asList(
            new SapShipmentData("SHP001", "0010000001", "OCEAN-FRGHT-CO",
                "FOB", "Shanghai Port",
                "8471.30.0000", "Portable automatic data processing machines",
                "CN", 18500.0, 17200.0, 32.5, 4800.0, 215000.0, 850, "Cartons", "2026-03-15"),

            new SapShipmentData("SHP002", "0010000002", "ATLA-EXPRESS",
                "CIF", "London Heathrow",
                "3004.90.9999", "Medicaments \u2014 mixed or unmixed products",
                "US", 850.0, 820.0, 4.2, 12400.0, 89000.0, 120, "Boxes", "2026-03-10"),

            new SapShipmentData("SHP003", "0010000003", "GULF-CARGO",
                "DDP", "Singapore Changi",
                "7113.19.2100", "Jewellery of precious metal",
                "AE", 125.0, 118.0, 0.8, 8200.0, 450000.0, 24, "Cases", "2026-03-14"),

            new SapShipmentData("SHP004", "0010000004", "PACIF-LINES",
                "FOB", "Port Botany Sydney",
                "7204.10.0000", "Waste and scrap of cast iron",
                "AU", 45000.0, 44800.0, 38.0, 6100.0, 32000.0, 1, "Bulk", "2026-03-16"),

            new SapShipmentData("SHP005", "0010000005", "EURO-AIR-CGO",
                "DAP", "Chicago O'Hare",
                "8708.29.5060", "Parts and accessories for motor vehicles",
                "DE", 3200.0, 3050.0, 12.8, 9800.0, 78000.0, 215, "Pallets", "2026-03-15"),

            new SapShipmentData("SHP006", "0010000006", "IND-OCN-SHIP",
                "CFR", "Port of Rotterdam",
                "5208.11.0010", "Plain woven cotton fabrics",
                "IN", 12400.0, 12100.0, 45.0, 3200.0, 28000.0, 480, "Bales", "2026-03-08"),

            new SapShipmentData("SHP007", "0010000007", "SAMR-LINES",
                "FOB", "Port of Santos",
                "0901.11.0000", "Coffee \u2014 not roasted, not decaffeinated",
                "BR", 22000.0, 21500.0, 28.5, 5400.0, 42000.0, 900, "Bags", "2026-03-13"),

            new SapShipmentData("SHP008", "0010000008", "NAMR-FREIGHT",
                "FCA", "Toronto Pearson",
                "8425.39.0100", "Jacks and hoists \u2014 industrial",
                "CA", 8500.0, 8200.0, 18.0, 7800.0, 185000.0, 42, "Crates", "2026-03-16"),

            new SapShipmentData("SHP009", "0010000009", "AFR-SHIP-CO",
                "CIF", "Amsterdam Schiphol",
                "7102.31.0000", "Diamonds \u2014 non-industrial, unworked",
                "ZA", 12.0, 12.0, 0.02, 18500.0, 890000.0, 3, "Sealed Cases", "2026-03-12"),

            new SapShipmentData("SHP010", "0010000010", "PAC-STAR-CGO",
                "DAP", "Seattle Tacoma",
                "8525.80.3000", "Television cameras and digital cameras",
                "KR", 9800.0, 9400.0, 22.5, 11200.0, 320000.0, 620, "Cartons", "2026-03-14")
        );

        sapDataMap = data.stream()
                .collect(Collectors.toMap(SapShipmentData::getShipmentId, Function.identity()));
    }

    public List<SapShipmentData> getAllSapData() {
        return List.copyOf(sapDataMap.values());
    }

    public Optional<SapShipmentData> getSapDataByShipmentId(String shipmentId) {
        return Optional.ofNullable(sapDataMap.get(shipmentId));
    }
}
