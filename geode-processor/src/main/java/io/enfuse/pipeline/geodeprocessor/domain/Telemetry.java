package io.enfuse.pipeline.geodeprocessor.domain;

import org.springframework.data.gemfire.mapping.annotation.Region;

//  truck’s identifier and its lat and long.
@Region("telemetryRegion")
public class Telemetry {
  // VEH_VehicleId
  String vehicleId;
  // LOC_Latitude
  String latitude;
  // LOC_Longitude
  String longitude;
  // LOC_Speed
  String speed;

  String value;

  private Telemetry(Builder builder) {
    setVehicleId(builder.vehicleId);
    setLatitude(builder.latitude);
    setLongitude(builder.longitude);
    setSpeed(builder.speed);
    setValue(builder.value);
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

  public static final class Builder {
    private String vehicleId;
    private String latitude;
    private String longitude;
    private String speed;
    private String value;

    public Builder() {}

    public Builder withVehicleId(String val) {
      vehicleId = val;
      return this;
    }

    public Builder withLatitude(String val) {
      latitude = val;
      return this;
    }

    public Builder withLongitude(String val) {
      longitude = val;
      return this;
    }

    public Builder withSpeed(String val) {
      speed = val;
      return this;
    }

    public Builder withValue(String val) {
      value = val;
      return this;
    }

    public Telemetry build() {
      return new Telemetry(this);
    }
  }

  @Override
  public String toString() {
    return "Telemetry{"
        + "vehicleId='"
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
