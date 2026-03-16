package com.app1.shipmenttracker.soap;

import com.app1.shipmenttracker.model.CustomsStatus;
import com.app1.shipmenttracker.service.CustomsService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.math.BigDecimal;

/**
 * Spring-WS SOAP endpoint for B2G Customs Clearance declarations.
 *
 * Handles:  {http://app1.com/customs}GetCustomsStatusRequest
 * Returns:  {http://app1.com/customs}GetCustomsStatusResponse
 *
 * Test with SoapUI or Postman:
 *   URL    : POST http://localhost:8080/ws
 *   Headers: Content-Type: text/xml
 *   Body   :
 *     <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
 *                       xmlns:cust="http://app1.com/customs">
 *       <soapenv:Body>
 *         <cust:GetCustomsStatusRequest>
 *           <cust:shipmentId>SHP001</cust:shipmentId>
 *           <cust:hsCode>8471.30.0000</cust:hsCode>
 *           <cust:declaredValue>215000.00</cust:declaredValue>
 *           <cust:currency>USD</cust:currency>
 *           <cust:originCountry>CN</cust:originCountry>
 *           <cust:destinationCountry>US</cust:destinationCountry>
 *         </cust:GetCustomsStatusRequest>
 *       </soapenv:Body>
 *     </soapenv:Envelope>
 */
@Endpoint
public class CustomsEndpoint {

    private static final String NS = "http://app1.com/customs";

    private final CustomsService customsService;

    public CustomsEndpoint(CustomsService customsService) {
        this.customsService = customsService;
    }

    @PayloadRoot(namespace = NS, localPart = "GetCustomsStatusRequest")
    @ResponsePayload
    public Element getCustomsStatus(@RequestPayload Element request) throws Exception {

        // ── 1. Parse XML request fields ──────────────────────────────────────
        String shipmentId    = field(request, "shipmentId");
        String hsCode        = field(request, "hsCode");
        String rawValue      = field(request, "declaredValue");
        String currency      = field(request, "currency");
        String originCountry = field(request, "originCountry");
        String destCountry   = field(request, "destinationCountry");

        BigDecimal declaredValue = rawValue.isEmpty() ? BigDecimal.ZERO : new BigDecimal(rawValue);

        // ── 2. Call business logic ───────────────────────────────────────────
        CustomsStatus status = customsService.getCustomsStatus(
                shipmentId, hsCode, declaredValue, currency, originCountry, destCountry);

        // ── 3. Build XML response ────────────────────────────────────────────
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().newDocument();

        Element resp = doc.createElementNS(NS, "GetCustomsStatusResponse");
        child(doc, resp, "declarationNumber",      status.getDeclarationNumber());
        child(doc, resp, "clearanceStatus",        status.getClearanceStatus());
        child(doc, resp, "dutyRate",               String.valueOf(status.getDutyRate()));
        child(doc, resp, "dutyAmount",             String.format("%.2f", status.getDutyAmount()));
        child(doc, resp, "estimatedClearanceDate", status.getEstimatedClearanceDate());
        child(doc, resp, "remarks",                status.getRemarks());
        doc.appendChild(resp);

        return doc.getDocumentElement();
    }

    // ── XML helpers ──────────────────────────────────────────────────────────

    private String field(Element parent, String name) {
        NodeList nl = parent.getElementsByTagNameNS(NS, name);
        if (nl.getLength() == 0) nl = parent.getElementsByTagName(name); // namespace-agnostic fallback
        return nl.getLength() > 0 ? nl.item(0).getTextContent().trim() : "";
    }

    private void child(Document doc, Element parent, String tag, String value) {
        Element el = doc.createElementNS(NS, tag);
        el.setTextContent(value != null ? value : "");
        parent.appendChild(el);
    }
}
