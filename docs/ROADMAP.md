# ROADMAP — APP1 Shipment Tracker
**Version:** 1.1 | **Date:** 2026-03-16

---

## Milestone 1 — MVP ✅ COMPLETE
**Goal:** Working app with world map and dummy shipment data

| Task | Status | Req IDs |
|------|--------|---------|
| Spring Boot project setup | ✅ Done | — |
| Shipment model + dummy data | ✅ Done | R05 |
| REST API /api/shipments | ✅ Done | R01–R06 |
| Leaflet.js world map | ✅ Done | R01 |
| Markers + routes on map | ✅ Done | R02, R03 |
| Shipment list panel | ✅ Done | R04 |
| Status colour coding | ✅ Done | R06 |
| Click to highlight | ✅ Done | R07 |
| Popup detail on marker | ✅ Done | R08 |
| Filter by status | ✅ Done | R09 |
| Unit tests | ✅ Done | — |

---

## Milestone 2 — Admin Login + Deployment 🚧 NEXT
**Goal:** Secure admin login page + deploy app to cloud
**Branch:** `feature/admin-login`

| Task | Status | Notes |
|------|--------|-------|
| Add Spring Security dependency | Pending | pom.xml |
| Create admin login page (HTML form) | Pending | /login |
| Secure /api/shipments behind auth | Pending | Spring Security config |
| BCrypt password hashing | Pending | Best practice, cost 12 |
| In-memory admin user (no DB needed for exercise) | Pending | Spring Security UserDetailsService |
| Logout endpoint | Pending | /logout |
| Redirect to tracker after login | Pending | |
| Dockerize app (Dockerfile) | Pending | For deployment |
| Deploy to Railway.app | Pending | Free tier, simple Git deploy |
| Environment variables for secrets | Pending | No hardcoded passwords |

---

## Milestone 3 — New Feature (TBD by Owner)
**Goal:** Owner adds a new feature using the full branch → PR → merge workflow
**Branch:** `feature/<owner-chosen-name>`

| Task | Status |
|------|--------|
| Owner defines feature requirement | Pending |
| Claude drafts BRD update | Pending |
| Create feature branch | Pending |
| Build + test | Pending |
| PR → develop → main | Pending |

---

## Branch Merge History
| Date | Branch | Action |
|------|--------|--------|
| 2026-03-16 | feature/shipment-tracker-ui | Merged → develop |
| 2026-03-16 | develop | Merged → main (M1 release) |
