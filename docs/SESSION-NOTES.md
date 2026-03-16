# SESSION NOTES — APP1 Shipment Tracker

---

## Session 1 — 2026-03-16

### What Was Done
- Created GitHub repo: William-sketch222/APP1-Shipment-Tracker
- Phase 1: BRD written and approved
- Phase 2: Tech Spec written
- Phase 5: Repo + branches created (main, develop, feature/shipment-tracker-ui)
- Phase 6: Full app built:
  - Spring Boot 3.2 REST API
  - Leaflet.js world map (dark theme)
  - 10 dummy global shipments
  - Status filters + route highlighting
  - Unit tests (4 passing)
- PR #1: feature/shipment-tracker-ui → develop ✅ MERGED
- PR #2: develop → main ✅ MERGED
- Roadmap updated for Milestone 2

### Current Status
**Milestone 1 COMPLETE. App is live on `main`.**

### Next Session — Milestone 2: Admin Login + Deployment

#### What We Will Build
1. **Admin login page** using Spring Security
   - BCrypt password hashing (cost 12 — best practice)
   - In-memory admin user for this exercise
   - /login page, /logout, redirect after login
   - Protect the shipment API behind auth
2. **Deployment to Railway.app** (free, Git-based)
   - Dockerfile for the app
   - Environment variables for secrets (no hardcoded passwords)
   - Live URL after deploy

#### Branch Strategy for Next Session
```
main
 └── develop
      └── feature/admin-login  ← we create this next
```

#### After Milestone 2 Completes
- Owner picks a new feature (Milestone 3)
- We practice the full branch → PR → merge workflow again
- We review the Claude Framework and create/update skills

### Blockers
- Need to decide: Railway.app or Render.com for deployment? (Both are free, both support Java/Docker)
  - **Recommendation:** Railway.app — simplest Git-push deploy for Spring Boot

### How to Run Locally Right Now
```bash
git clone https://github.com/William-sketch222/APP1-Shipment-Tracker.git
cd APP1-Shipment-Tracker
mvn spring-boot:run
# Open http://localhost:8080
```
**Prerequisites:** Java 17+, Maven 3.9+
