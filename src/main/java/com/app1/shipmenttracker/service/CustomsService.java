package com.app1.shipmenttracker.service;

import com.app1.shipmenttracker.model.CustomsStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Simulates a B2G (Business-to-Government) customs authority SOAP service.
 *
 * In production this would call an external government web service:
 *   - EU: ICS2 (Import Control System 2) via ebMS3 / AS4
 *   - US: CBP ACE (Automated Commercial Environment)
 *   - Standard: WCO / UN/CEFACT SOAP services
 *
 * Duty rates are determined by HS code, country of origin,
 * declared value, and applicable trade agreements (FTA, USMCA, etc.).
 */
@Service
public class CustomsService {

    /**
     * Pre-computed customs decisions for all 10 demo shipments.
     * Reflects realistic customs scenarios:
     *   - Trade agreement zero-duty (USMCA, Korea FTA, EU pharma)
     *   - Standard tariff rates (textiles, electronics, auto parts)
     *   - Physical holds (bulk materials, high-value goods)
     *   - Pending inspections (agricultural, diamonds)
     */
    private static final Map<String, CustomsDecision> DECISIONS = Map.of(
        "SHP001", new CustomsDecision("CUST-2026-0001", "PENDING",   3.7,  7955.00, "2026-03-22",
                    "Standard documentary review \u2014 est. 5\u20137 business days"),
        "SHP002", new CustomsDecision("CUST-2026-0002", "APPROVED",  0.0,     0.00, "2026-03-10",
                    "Cleared \u2014 Medicaments exempt under EU\u2013USA mutual recognition agreement"),
        "SHP003", new CustomsDecision("CUST-2026-0003", "PENDING",   8.5, 38250.00, "2026-03-24",
                    "Pending Kimberley Process certificate validation for precious stones"),
        "SHP004", new CustomsDecision("CUST-2026-0004", "HELD",      0.0,     0.00, "2026-04-05",
                    "HELD \u2014 Mandatory quarantine & biosecurity inspection for bulk raw material"),
        "SHP005", new CustomsDecision("CUST-2026-0005", "PENDING",   2.5,  1950.00, "2026-03-19",
                    "Standard clearance \u2014 auto parts under USMCA tariff classification review"),
        "SHP006", new CustomsDecision("CUST-2026-0006", "APPROVED", 12.0,  3360.00, "2026-03-08",
                    "Cleared \u2014 Standard textile duty rate applied, no MFN preference claimed"),
        "SHP007", new CustomsDecision("CUST-2026-0007", "PENDING",   0.0,     0.00, "2026-03-25",
                    "Agricultural commodity \u2014 USDA phytosanitary certificate inspection pending"),
        "SHP008", new CustomsDecision("CUST-2026-0008", "PENDING",   0.0,     0.00, "2026-04-02",
                    "CUSMA/USMCA duty-free preference claimed \u2014 certificate of origin under review"),
        "SHP009", new CustomsDecision("CUST-2026-0009", "PENDING",   0.0,     0.00, "2026-04-05",
                    "High-value special goods \u2014 Enhanced KYC and AML verification in progress"),
        "SHP010", new CustomsDecision("CUST-2026-0010", "APPROVED",  0.0,     0.00, "2026-03-14",
                    "Cleared \u2014 Korea\u2013USA FTA zero duty rate applied successfully")
    );

    public CustomsStatus getCustomsStatus(String shipmentId, String hsCode,
                                          BigDecimal declaredValue, String currency,
                                          String originCountry, String destinationCountry) {
        CustomsDecision d = DECISIONS.getOrDefault(shipmentId,
            new CustomsDecision(
                "CUST-2026-UNKN", "PENDING", 5.0,
                declaredValue.doubleValue() * 0.05,
                LocalDate.now().plusDays(7).format(DateTimeFormatter.ISO_DATE),
                "Shipment not in pre-clearance registry \u2014 manual review assigned"
            ));

        return new CustomsStatus(
                shipmentId,
                d.declarationNumber(),
                d.clearanceStatus(),
                d.dutyRate(),
                d.dutyAmount(),
                d.estimatedClearanceDate(),
                d.remarks(),
                LocalDate.now().format(DateTimeFormatter.ISO_DATE)
        );
    }

    private record CustomsDecision(
            String declarationNumber,
            String clearanceStatus,
            double dutyRate,
            double dutyAmount,
            String estimatedClearanceDate,
            String remarks
    ) {}
}
