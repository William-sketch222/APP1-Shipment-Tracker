# APP1 — Shipment Tracker

A simple Java web application that displays global shipments on an interactive world map with dummy data.

**Purpose:** Claude Code learning exercise — GitHub branching, local build, and full dev workflow.

## Tech Stack
- **Backend:** Java 17 + Spring Boot 3.2
- **Frontend:** HTML/CSS/JS + Leaflet.js (world map)
- **Build:** Maven
- **Data:** Dummy in-memory data (no database)

## Quick Start
```bash
git clone https://github.com/William-sketch222/APP1-Shipment-Tracker.git
cd APP1-Shipment-Tracker
mvn spring-boot:run
```
Then open: http://localhost:8080

## Branch Strategy
| Branch | Purpose |
|--------|---------|
| `main` | Production-ready code |
| `develop` | Integration branch |
| `feature/*` | Individual features |

## Framework
This project follows the [Claude Framework v3.0](https://github.com/William-sketch222/Claude-Framework).
