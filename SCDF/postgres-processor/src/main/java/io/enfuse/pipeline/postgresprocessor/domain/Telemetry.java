package io.enfuse.pipeline.postgresprocessor.domain;

import javax.persistence.*;

//  truck’s identifier and its lat and long.

@Entity
@Table(name = "telemetry")
public class Telemetry {
  // VEH_VehicleId
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private long id;

  String vehicleId;
  // LOC_Latitude
  String latitude;
  // LOC_Longitude
  String longitude;
  // LOC_Speed
  String speed;

  String value;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getSpeed() {
    return speed;
  }

  public void setSpeed(String speed) {
    this.speed = speed;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Telemetry{"
        + "id="
        + id
        + ", vehicleId='"
        + vehicleId
        + '\''
        + ", latitude='"
        + latitude
        + '\''
        + ", longitude='"
        + longitude
        + '\''
        + ", speed='"
        + speed
        + '\''
        + ", value='"
        + value
        + '\''
        + '}';
  }
}
