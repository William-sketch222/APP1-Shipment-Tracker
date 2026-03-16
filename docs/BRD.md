# BRD — APP1 Shipment Tracker
**Version:** 1.1 | **Date:** 2026-03-16 | **Status:** Approved

---

## 1. Purpose
Build a Java web application that visualises global shipments on an interactive world map, enriched with SAP Transportation Management data and B2G customs clearance status via SOAP.

## 2. Requirements Matrix

### Milestone 1 — MVP (Complete)
| ID | Priority | Requirement | Status |
|----|----------|-------------|--------|
| R01 | Must Have | Display world map | ✅ Done |
| R02 | Must Have | Show shipment markers | ✅ Done |
| R03 | Must Have | Draw shipment routes | ✅ Done |
| R04 | Must Have | Shipment list/table | ✅ Done |
| R05 | Must Have | Dummy data (10 shipments) | ✅ Done |
| R06 | Must Have | Status colour coding | ✅ Done |
| R07 | Should Have | Click to highlight | ✅ Done |
| R08 | Should Have | Shipment detail popup | ✅ Done |
| R09 | Could Have | Filter by status | ✅ Done |

### Milestone 2 — SAP + B2G Integration (Complete)
| ID | Priority | Requirement | Status |
|----|----------|-------------|--------|
| R10 | Must Have | SAP S/4HANA data per shipment (HS code, incoterms, weight, customs value) | ✅ Done |
| R11 | Must Have | B2G SOAP customs endpoint (Spring-WS) | ✅ Done |
| R12 | Must Have | REST facade for customs check (/api/customs/{id}) | ✅ Done |
| R13 | Must Have | SAP data panel in UI drawer | ✅ Done |
| R14 | Must Have | Customs status check button with SOAP simulation | ✅ Done |
| R15 | Should Have | Show raw SOAP request/response XML in UI | ✅ Done |
| R16 | Should Have | WSDL auto-generated from XSD and served | ✅ Done |

### Milestone 3 — Admin Login + Deployment (Next)
| ID | Priority | Requirement | Status |
|----|----------|-------------|--------|
| R17 | Must Have | Admin login page (Spring Security) | Pending |
| R18 | Must Have | BCrypt password hashing (cost 12) | Pending |
| R19 | Must Have | Protect shipment API behind auth | Pending |
| R20 | Must Have | Logout endpoint | Pending |
| R21 | Must Have | Dockerize app | Pending |
| R22 | Must Have | Deploy to Railway.app | Pending |

## 3. Out of Scope
- Real SAP or government API connections
- Database persistence
- Multi-user / role-based access
- Mobile-specific design

## 4. Sign-off
Owner approved: William | 2026-03-16
