# BRD — APP1 Shipment Tracker
**Version:** 1.0 | **Date:** 2026-03-16 | **Status:** Approved

---

## 1. Purpose
Build a simple Java web application that visualises global shipments on an interactive world map using dummy data. Primary goal is learning the Claude Code + GitHub workflow.

## 2. Requirements Matrix

| ID | Priority | Requirement | Acceptance Criteria |
|----|----------|-------------|---------------------|
| R01 | Must Have | Display world map | Leaflet.js map loads, centred on world view |
| R02 | Must Have | Show shipment markers | Origin & destination pins visible on map |
| R03 | Must Have | Draw shipment routes | Lines connecting origin → destination |
| R04 | Must Have | Shipment list/table | Side panel lists all shipments with status |
| R05 | Must Have | Dummy data (8-10 shipments) | Diverse global routes, varied statuses |
| R06 | Must Have | Status colour coding | IN_TRANSIT=blue, DELIVERED=green, PROCESSING=orange |
| R07 | Should Have | Click to highlight | Clicking a shipment highlights it on map |
| R08 | Should Have | Shipment detail popup | Clicking marker shows tracking details |
| R09 | Could Have | Filter by status | Filter list and map markers by status |

## 3. Out of Scope
- Real tracking data / external APIs
- User authentication
- Database persistence
- Mobile-specific design

## 4. Honest Assessment
This is a well-scoped learning exercise. Complexity is low. Estimated 1 session to build fully.

## 5. Sign-off
Owner approved: William | 2026-03-16
