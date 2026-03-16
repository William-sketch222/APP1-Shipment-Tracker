package com.app1.shipmenttracker;

import com.app1.shipmenttracker.service.ShipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ShipmentTrackerApplicationTests {

    @Autowired
    ShipmentService shipmentService;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldReturn10Shipments() {
        assertThat(shipmentService.getAllShipments()).hasSize(10);
    }

    @Test
    void shouldFindShipmentById() {
        assertThat(shipmentService.getShipmentById("SHP001")).isPresent();
    }

    @Test
    void shouldReturnEmptyForUnknownId() {
        assertThat(shipmentService.getShipmentById("UNKNOWN")).isEmpty();
    }
}
