package io.enfuse.pipeline.postgresprocessor.repository;

import io.enfuse.pipeline.postgresprocessor.domain.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelemetryRepository extends JpaRepository<Telemetry, Long> {
  Telemetry findOneByVehicleId(String vehicleId);
}
