package com.app1.shipmenttracker.model;

/**
 * Customs clearance status returned by the B2G SOAP service.
 * Represents a government customs authority's response to a trade declaration.
 *
 * Clearance statuses:
 *   PENDING  - Under documentary review (est. 5-7 business days)
 *   APPROVED - Cleared for import/export, duties assessed
 *   HELD     - Physical inspection or additional documents required
 *   REJECTED - Declaration refused, shipper must resubmit
 */
public class CustomsStatus {

    private String shipmentId;
    private String declarationNumber;       // Unique customs reference, e.g. "CUST-2026-0001"
    private String clearanceStatus;         // PENDING | APPROVED | HELD | REJECTED
    private double dutyRate;                // Percentage, e.g. 3.7 = 3.7%
    private double dutyAmount;              // Amount payable in USD
    private String estimatedClearanceDate;  // ISO date string
    private String remarks;                 // Customs authority free-text comments
    private String checkedAt;              // Date this status was retrieved

    public CustomsStatus() {}

    public CustomsStatus(String shipmentId, String declarationNumber, String clearanceStatus,
                         double dutyRate, double dutyAmount,
                         String estimatedClearanceDate, String remarks, String checkedAt) {
        this.shipmentId             = shipmentId;
        this.declarationNumber      = declarationNumber;
        this.clearanceStatus        = clearanceStatus;
        this.dutyRate               = dutyRate;
        this.dutyAmount             = dutyAmount;
        this.estimatedClearanceDate = estimatedClearanceDate;
        this.remarks                = remarks;
        this.checkedAt              = checkedAt;
    }

    public String getShipmentId()             { return shipmentId; }
    public void   setShipmentId(String v)     { this.shipmentId = v; }
    public String getDeclarationNumber()         { return declarationNumber; }
    public void   setDeclarationNumber(String v) { this.declarationNumber = v; }
    public String getClearanceStatus()         { return clearanceStatus; }
    public void   setClearanceStatus(String v) { this.clearanceStatus = v; }
    public double getDutyRate()        { return dutyRate; }
    public void   setDutyRate(double v){ this.dutyRate = v; }
    public double getDutyAmount()        { return dutyAmount; }
    public void   setDutyAmount(double v){ this.dutyAmount = v; }
    public String getEstimatedClearanceDate()         { return estimatedClearanceDate; }
    public void   setEstimatedClearanceDate(String v) { this.estimatedClearanceDate = v; }
    public String getRemarks()         { return remarks; }
    public void   setRemarks(String v) { this.remarks = v; }
    public String getCheckedAt()         { return checkedAt; }
    public void   setCheckedAt(String v) { this.checkedAt = v; }
}
