// APP1 Shipment Tracker — Frontend Logic

const STATUS_COLORS = {
    IN_TRANSIT: '#4a90d9',
    DELIVERED:  '#4caf7d',
    PROCESSING: '#f0a500'
};

const STATUS_LABELS = {
    IN_TRANSIT: 'In Transit',
    DELIVERED:  'Delivered',
    PROCESSING: 'Processing'
};

let map;
let allShipments = [];
let shipmentLayers = {}; // id → { originMarker, destMarker, line }
let selectedId = null;
let currentFilter = 'ALL';

// --- INIT MAP ---
function initMap() {
    map = L.map('map', {
        center: [20, 10],
        zoom: 2,
        minZoom: 2,
        maxZoom: 10
    });

    L.tileLayer('https://{s}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}{r}.png', {
        attribution: '&copy; OpenStreetMap contributors &copy; CARTO',
        subdomains: 'abcd',
        maxZoom: 19
    }).addTo(map);
}

// --- CREATE MARKER ---
function createMarker(lat, lng, color, isOrigin) {
    const icon = L.divIcon({
        className: '',
        html: `<div style="
            width: ${isOrigin ? 12 : 14}px;
            height: ${isOrigin ? 12 : 14}px;
            border-radius: 50%;
            background: ${color};
            border: 2px solid #fff;
            box-shadow: 0 0 6px ${color}88;
        "></div>`,
        iconSize: [14, 14],
        iconAnchor: [7, 7]
    });
    return L.marker([lat, lng], { icon });
}

// --- DRAW SHIPMENT ON MAP ---
function drawShipment(s) {
    const color = STATUS_COLORS[s.status] || '#888';

    const originMarker = createMarker(s.originLat, s.originLng, color, true);
    const destMarker   = createMarker(s.destLat,   s.destLng,   color, false);

    const line = L.polyline(
        [[s.originLat, s.originLng], [s.destLat, s.destLng]],
        { color, weight: 2, opacity: 0.7, dashArray: '6,4' }
    );

    const popupHtml = `
        <div class="popup-title">${s.trackingNumber}</div>
        <div class="popup-row"><span>From:</span>${s.origin}</div>
        <div class="popup-row"><span>To:</span>${s.destination}</div>
        <div class="popup-row"><span>Status:</span>${STATUS_LABELS[s.status]}</div>
        <div class="popup-row"><span>Carrier:</span>${s.carrier}</div>
        <div class="popup-row"><span>Cargo:</span>${s.description}</div>
        <div class="popup-row"><span>ETA:</span>${s.estimatedDelivery}</div>
    `;

    originMarker.bindPopup(popupHtml);
    destMarker.bindPopup(popupHtml);

    originMarker.addTo(map);
    destMarker.addTo(map);
    line.addTo(map);

    shipmentLayers[s.id] = { originMarker, destMarker, line };
}

// --- REMOVE A SHIPMENT FROM MAP ---
function removeShipment(id) {
    const layer = shipmentLayers[id];
    if (!layer) return;
    map.removeLayer(layer.originMarker);
    map.removeLayer(layer.destMarker);
    map.removeLayer(layer.line);
    delete shipmentLayers[id];
}

// --- HIGHLIGHT SELECTED ---
function highlightShipment(id) {
    // Reset previous
    if (selectedId && shipmentLayers[selectedId]) {
        const prev = allShipments.find(s => s.id === selectedId);
        const prevColor = STATUS_COLORS[prev.status];
        shipmentLayers[selectedId].line.setStyle({ weight: 2, opacity: 0.7 });
        document.querySelectorAll('.shipment-item').forEach(el => el.classList.remove('selected'));
    }

    selectedId = id;
    const s = allShipments.find(s => s.id === id);
    if (!s || !shipmentLayers[id]) return;

    // Highlight line
    shipmentLayers[id].line.setStyle({ weight: 4, opacity: 1 });

    // Fit map to route
    map.fitBounds([
        [s.originLat, s.originLng],
        [s.destLat, s.destLng]
    ], { padding: [60, 60] });

    // Highlight list item
    const listItem = document.querySelector(`.shipment-item[data-id="${id}"]`);
    if (listItem) listItem.classList.add('selected');
}

// --- RENDER LIST ---
function renderList(shipments) {
    const ul = document.getElementById('shipment-list');
    document.getElementById('shipment-count').textContent = shipments.length;
    ul.innerHTML = '';

    shipments.forEach(s => {
        const li = document.createElement('li');
        li.className = 'shipment-item';
        li.dataset.id = s.id;
        li.innerHTML = `
            <div class="shipment-id">${s.trackingNumber}</div>
            <div class="shipment-route">${s.origin} → ${s.destination}</div>
            <div class="shipment-desc">${s.description}</div>
            <div class="shipment-footer">
                <span class="status-badge status-${s.status}">${STATUS_LABELS[s.status]}</span>
                <span class="eta">ETA: ${s.estimatedDelivery}</span>
            </div>
        `;
        li.addEventListener('click', () => highlightShipment(s.id));
        ul.appendChild(li);
    });
}

// --- APPLY FILTER ---
function applyFilter(status) {
    currentFilter = status;

    // Update button states
    document.querySelectorAll('.filter-btn').forEach(btn => {
        btn.classList.toggle('active', btn.dataset.status === status);
    });

    // Remove all layers from map
    allShipments.forEach(s => removeShipment(s.id));

    // Re-draw filtered shipments
    const filtered = status === 'ALL'
        ? allShipments
        : allShipments.filter(s => s.status === status);

    filtered.forEach(drawShipment);
    renderList(filtered);
}

// --- FILTER BUTTON EVENTS ---
function bindFilters() {
    document.querySelectorAll('.filter-btn').forEach(btn => {
        btn.addEventListener('click', () => applyFilter(btn.dataset.status));
    });
}

// --- LOAD DATA & START ---
async function loadShipments() {
    const res = await fetch('/api/shipments');
    allShipments = await res.json();
    allShipments.forEach(drawShipment);
    renderList(allShipments);
}

// --- MAIN ---
document.addEventListener('DOMContentLoaded', () => {
    initMap();
    bindFilters();
    loadShipments();
});
