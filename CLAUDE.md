# CLAUDE.md — APP1 Shipment Tracker

> Read this file at the start of every session. Then read `docs/SESSION-NOTES.md`.

## Project Info
- **Name:** APP1 Shipment Tracker
- **Purpose:** Learning exercise — shipment tracking with world map, dummy data
- **Owner:** William
- **Framework:** [Claude Framework v3.0](https://github.com/William-sketch222/Claude-Framework)
- **Current Phase:** Phase 6 — Development
- **Stack:** Java 17, Spring Boot 3.2, Maven, Leaflet.js

## Rules (from Claude Framework)
- No committing directly to `main`
- All features go through a `feature/*` branch → PR → `develop` → PR → `main`
- Documentation stays in sync with code
- Honest feedback always
- Security by default — no hardcoded secrets

## Branch Strategy
- `main` — stable / production
- `develop` — integration
- `feature/shipment-tracker-ui` — current feature branch

## Local Run
```bash
mvn spring-boot:run
# Visit http://localhost:8080
```
