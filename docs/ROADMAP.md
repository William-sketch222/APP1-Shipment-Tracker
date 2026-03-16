# ROADMAP — APP1 Shipment Tracker
**Version:** 1.0 | **Date:** 2026-03-16

---

## Milestone 1 — MVP (Current)
**Goal:** Working app with world map and dummy shipment data
**Branch:** `feature/shipment-tracker-ui`

| Task | Status | Req IDs |
|------|--------|---------|
| Spring Boot project setup | ✅ Done | — |
| Shipment model + dummy data | ✅ Done | R05 |
| REST API /api/shipments | ✅ Done | R01-R06 |
| Leaflet.js world map | ✅ Done | R01 |
| Markers + routes on map | ✅ Done | R02, R03 |
| Shipment list panel | ✅ Done | R04 |
| Status colour coding | ✅ Done | R06 |
| Click to highlight | ✅ Done | R07 |
| Popup detail on marker | ✅ Done | R08 |

## Milestone 2 — Nice to Have (Future)
| Task | Status | Req IDs |
|------|--------|---------|
| Filter by status | Backlog | R09 |
| Search by tracking number | Backlog | — |

## Branch Merge Plan
1. `feature/shipment-tracker-ui` → PR → `develop`
2. `develop` → PR → `main`
