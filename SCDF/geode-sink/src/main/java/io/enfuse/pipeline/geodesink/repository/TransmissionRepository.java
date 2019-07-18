package io.enfuse.pipeline.geodesink.repository;

import io.enfuse.pipeline.geodesink.domain.Transmission;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.data.gemfire.repository.GemfireRepository;

@Region("transmissionRegion")
public interface TransmissionRepository extends GemfireRepository<Transmission, String> {}
