package com.app1.shipmenttracker.model;

/**
 * SAP S/4HANA Transportation Management master data for a shipment.
 *
 * In a real integration this would be populated by calling SAP's OData v4 API:
 *   GET /sap/opu/odata4/tms/transportorder/0001/TransportOrder
 *
 * Contains trade-relevant data: HS codes, incoterms, weights,
 * volumes, freight costs and customs declared value.
 */
public class SapShipmentData {

    private String shipmentId;          // Links back to Shipment.id
    private String sapTransportOrder;   // SAP Transport Order number (10-digit)
    private String sapCarrierCode;      // SAP vendor / carrier code
    private String incoterms;           // Trade terms: FOB, CIF, DDP, EXW, DAP, FCA, CFR
    private String incotermLocation;    // Named delivery/risk transfer place
    private String hsCode;              // Harmonized System tariff classification (WCO)
    private String hsDescription;       // Human-readable HS code description
    private String countryOfOrigin;     // ISO 3166-1 alpha-2 country code
    private double grossWeightKg;
    private double netWeightKg;
    private double volumeM3;
    private double freightCostUSD;
    private double customsValueUSD;     // Declared customs value
    private int    numberOfPackages;
    private String packageType;         // Cartons, Pallets, Bales, Bulk, etc.
    private String lastSapSync;         // Timestamp of last data sync from SAP

    public SapShipmentData() {}

    public SapShipmentData(String shipmentId, String sapTransportOrder, String sapCarrierCode,
                            String incoterms, String incotermLocation,
                            String hsCode, String hsDescription, String countryOfOrigin,
                            double grossWeightKg, double netWeightKg, double volumeM3,
                            double freightCostUSD, double customsValueUSD,
                            int numberOfPackages, String packageType, String lastSapSync) {
        this.shipmentId        = shipmentId;
        this.sapTransportOrder = sapTransportOrder;
        this.sapCarrierCode    = sapCarrierCode;
        this.incoterms         = incoterms;
        this.incotermLocation  = incotermLocation;
        this.hsCode            = hsCode;
        this.hsDescription     = hsDescription;
        this.countryOfOrigin   = countryOfOrigin;
        this.grossWeightKg     = grossWeightKg;
        this.netWeightKg       = netWeightKg;
        this.volumeM3          = volumeM3;
        this.freightCostUSD    = freightCostUSD;
        this.customsValueUSD   = customsValueUSD;
        this.numberOfPackages  = numberOfPackages;
        this.packageType       = packageType;
        this.lastSapSync       = lastSapSync;
    }

    public String getShipmentId()         { return shipmentId; }
    public void   setShipmentId(String v) { this.shipmentId = v; }
    public String getSapTransportOrder()         { return sapTransportOrder; }
    public void   setSapTransportOrder(String v) { this.sapTransportOrder = v; }
    public String getSapCarrierCode()         { return sapCarrierCode; }
    public void   setSapCarrierCode(String v) { this.sapCarrierCode = v; }
    public String getIncoterms()         { return incoterms; }
    public void   setIncoterms(String v) { this.incoterms = v; }
    public String getIncotermLocation()         { return incotermLocation; }
    public void   setIncotermLocation(String v) { this.incotermLocation = v; }
    public String getHsCode()         { return hsCode; }
    public void   setHsCode(String v) { this.hsCode = v; }
    public String getHsDescription()         { return hsDescription; }
    public void   setHsDescription(String v) { this.hsDescription = v; }
    public String getCountryOfOrigin()         { return countryOfOrigin; }
    public void   setCountryOfOrigin(String v) { this.countryOfOrigin = v; }
    public double getGrossWeightKg()        { return grossWeightKg; }
    public void   setGrossWeightKg(double v){ this.grossWeightKg = v; }
    public double getNetWeightKg()        { return netWeightKg; }
    public void   setNetWeightKg(double v){ this.netWeightKg = v; }
    public double getVolumeM3()        { return volumeM3; }
    public void   setVolumeM3(double v){ this.volumeM3 = v; }
    public double getFreightCostUSD()        { return freightCostUSD; }
    public void   setFreightCostUSD(double v){ this.freightCostUSD = v; }
    public double getCustomsValueUSD()        { return customsValueUSD; }
    public void   setCustomsValueUSD(double v){ this.customsValueUSD = v; }
    public int    getNumberOfPackages()      { return numberOfPackages; }
    public void   setNumberOfPackages(int v) { this.numberOfPackages = v; }
    public String getPackageType()         { return packageType; }
    public void   setPackageType(String v) { this.packageType = v; }
    public String getLastSapSync()         { return lastSapSync; }
    public void   setLastSapSync(String v) { this.lastSapSync = v; }
}
