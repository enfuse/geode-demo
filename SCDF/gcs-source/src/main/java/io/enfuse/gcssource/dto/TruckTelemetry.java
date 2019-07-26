package io.enfuse.gcssource.dto;

import com.google.gson.annotations.SerializedName;

public class TruckTelemetry {

  @SerializedName("MSG_SequenceNumber")
  private String sequenceNumber;

  @SerializedName("MSG_TimeTransmitted")
  private String TimeTransmitted;

  @SerializedName("MSG_TimeReceived")
  private String TimeReceived;

  @SerializedName("MSG_TimeZoneOffset")
  private String TimeZoneOffset;

  @SerializedName("COM_CompanyName")
  private String CompanyName;

  @SerializedName("VEH_VehicleId")
  private String VehicleId;

  @SerializedName("VEH_VehicleName")
  private String VehicleName;


  @SerializedName("VEH_VIN")
  private String VIN;

  @SerializedName("ENG_MaxSpeed")
  private String MaxSpeed;

  @SerializedName("ENG_OdometerInMiles")
  private String OdometerInMiles;

  @SerializedName("ENG_TotalFuelUsed")
  private String TotalFuelUsed;

  @SerializedName("ENG_TotalEngineHours")
  private String TotalEngineHours;

  @SerializedName("ENG_TotalIdleHours")
  private String TotalIdleHours;

  @SerializedName("ENG_RPM100Minutes")
  private String RPM100Minutes;

  @SerializedName("ENG_RPM1500Minutes")
  private String RPM1500Minutes;

  @SerializedName("ENG_RPM2000Minutes")
  private String RPM2000Minutes;

  @SerializedName("ENG_CruiseMinutes")
  private String CruiseMinutes;

  @SerializedName("ENG_MPH01Minutes")
  private String MPH01Minutes;

  @SerializedName("ENG_MPH35Minutes")
  private String MPH35Minutes;

  @SerializedName("ENG_MPH55Minutes")
  private String MPH55Minutes;

  @SerializedName("ENG_MPH66Minutes")
  private String MPH66Minutes;

  @SerializedName("ENG_HardBrakingMinutes")
  private String HardBrakingMinutes;

  @SerializedName("ENG_PID1")
  private String PID1;

  @SerializedName("ENG_FMI1")
  private String FMI1;

  @SerializedName("ENG_PID2")
  private String PID2;

  @SerializedName("ENG_FMI2")
  private String FMI2;

  @SerializedName("ENG_PID3")
  private String PID3;

  @SerializedName("ENG_FMI3")
  private String FMI3;

  @SerializedName("ENG_PID4")
  private String PID4;

  @SerializedName("ENG_FMI4")
  private String FMI4;

  @SerializedName("ENG_TankPercent")
  private String TankPercent;

  @SerializedName("DEV_DeviceNumber")
  private String DeviceNumber;

  @SerializedName("DEV_ModelPCB")
  private String ModelPCB;

  @SerializedName("LOC_Latitude")
  private String Latitude;

  @SerializedName("LOC_Longitude")
  private String Longitude;

  @SerializedName("LOC_Address")
  private String Address;

  @SerializedName("LOC_City")
  private String City;

  @SerializedName("LOC_Province")
  private String Province;

  @SerializedName("LOC_PostalCode")
  private String PostalCode;

  @SerializedName("LOC_Country")
  private String Country;

  @SerializedName("LOC_CardinalDirection")
  private String CardinalDirection;

  @SerializedName("LOC_AltitudeInFeet")
  private String AltitudeInFeet;

  @SerializedName("LOC_Speed")
  private String Speed;

  @SerializedName("LOC_GPSFix")
  private String GPSFix;

  @SerializedName("ReasonCode")
  private String ReasonCode;


  public String getSequenceNumber() {
    return sequenceNumber;
  }

  public void setSequenceNumber(String sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }

  public String getTimeTransmitted() {
    return TimeTransmitted;
  }

  public void setTimeTransmitted(String timeTransmitted) {
    this.TimeTransmitted = timeTransmitted;
  }

  public String getTimeReceived() {
    return TimeReceived;
  }

  public void setTimeReceived(String timeReceived) {
    TimeReceived = timeReceived;
  }

  public String getTimeZoneOffset() {
    return TimeZoneOffset;
  }

  public void setTimeZoneOffset(String timeZoneOffset) {
    TimeZoneOffset = timeZoneOffset;
  }

  public String getCompanyName() {
    return CompanyName;
  }

  public void setCompanyName(String companyName) {
    CompanyName = companyName;
  }

  public String getVehicleId() {
    return VehicleId;
  }

  public void setVehicleId(String vehicleId) {
    VehicleId = vehicleId;
  }

  public String getVehicleName() {
    return VehicleName;
  }

  public void setVehicleName(String vehicleName) {
    VehicleName = vehicleName;
  }

  public String getVIN() {
    return VIN;
  }

  public void setVIN(String VIN) {
    this.VIN = VIN;
  }

  public String getMaxSpeed() {
    return MaxSpeed;
  }

  public void setMaxSpeed(String maxSpeed) {
    MaxSpeed = maxSpeed;
  }

  public String getOdometerInMiles() {
    return OdometerInMiles;
  }

  public void setOdometerInMiles(String odometerInMiles) {
    OdometerInMiles = odometerInMiles;
  }

  public String getTotalFuelUsed() {
    return TotalFuelUsed;
  }

  public void setTotalFuelUsed(String totalFuelUsed) {
    TotalFuelUsed = totalFuelUsed;
  }

  public String getTotalEngineHours() {
    return TotalEngineHours;
  }

  public void setTotalEngineHours(String totalEngineHours) {
    TotalEngineHours = totalEngineHours;
  }

  public String getTotalIdleHours() {
    return TotalIdleHours;
  }

  public void setTotalIdleHours(String totalIdleHours) {
    TotalIdleHours = totalIdleHours;
  }

  public String getRPM100Minutes() {
    return RPM100Minutes;
  }

  public void setRPM100Minutes(String RPM100Minutes) {
    this.RPM100Minutes = RPM100Minutes;
  }

  public String getRPM1500Minutes() {
    return RPM1500Minutes;
  }

  public void setRPM1500Minutes(String RPM1500Minutes) {
    this.RPM1500Minutes = RPM1500Minutes;
  }

  public String getRPM2000Minutes() {
    return RPM2000Minutes;
  }

  public void setRPM2000Minutes(String RPM2000Minutes) {
    this.RPM2000Minutes = RPM2000Minutes;
  }

  public String getCruiseMinutes() {
    return CruiseMinutes;
  }

  public void setCruiseMinutes(String cruiseMinutes) {
    CruiseMinutes = cruiseMinutes;
  }

  public String getMPH01Minutes() {
    return MPH01Minutes;
  }

  public void setMPH01Minutes(String MPH01Minutes) {
    this.MPH01Minutes = MPH01Minutes;
  }

  public String getMPH35Minutes() {
    return MPH35Minutes;
  }

  public void setMPH35Minutes(String MPH35Minutes) {
    this.MPH35Minutes = MPH35Minutes;
  }

  public String getMPH55Minutes() {
    return MPH55Minutes;
  }

  public void setMPH55Minutes(String MPH55Minutes) {
    this.MPH55Minutes = MPH55Minutes;
  }

  public String getMPH66Minutes() {
    return MPH66Minutes;
  }

  public void setMPH66Minutes(String MPH66Minutes) {
    this.MPH66Minutes = MPH66Minutes;
  }

  public String getHardBrakingMinutes() {
    return HardBrakingMinutes;
  }

  public void setHardBrakingMinutes(String hardBrakingMinutes) {
    HardBrakingMinutes = hardBrakingMinutes;
  }

  public String getPID1() {
    return PID1;
  }

  public void setPID1(String PID1) {
    this.PID1 = PID1;
  }

  public String getFMI1() {
    return FMI1;
  }

  public void setFMI1(String FMI1) {
    this.FMI1 = FMI1;
  }

  public String getPID2() {
    return PID2;
  }

  public void setPID2(String PID2) {
    this.PID2 = PID2;
  }

  public String getFMI2() {
    return FMI2;
  }

  public void setFMI2(String FMI2) {
    this.FMI2 = FMI2;
  }

  public String getPID3() {
    return PID3;
  }

  public void setPID3(String PID3) {
    this.PID3 = PID3;
  }

  public String getFMI3() {
    return FMI3;
  }

  public void setFMI3(String FMI3) {
    this.FMI3 = FMI3;
  }

  public String getPID4() {
    return PID4;
  }

  public void setPID4(String PID4) {
    this.PID4 = PID4;
  }

  public String getFMI4() {
    return FMI4;
  }

  public void setFMI4(String FMI4) {
    this.FMI4 = FMI4;
  }

  public String getTankPercent() {
    return TankPercent;
  }

  public void setTankPercent(String tankPercent) {
    TankPercent = tankPercent;
  }

  public String getDeviceNumber() {
    return DeviceNumber;
  }

  public void setDeviceNumber(String deviceNumber) {
    DeviceNumber = deviceNumber;
  }

  public String getModelPCB() {
    return ModelPCB;
  }

  public void setModelPCB(String modelPCB) {
    ModelPCB = modelPCB;
  }

  public String getLatitude() {
    return Latitude;
  }

  public void setLatitude(String latitude) {
    Latitude = latitude;
  }

  public String getLongitude() {
    return Longitude;
  }

  public void setLongitude(String longitude) {
    Longitude = longitude;
  }

  public String getAddress() {
    return Address;
  }

  public void setAddress(String address) {
    Address = address;
  }

  public String getCity() {
    return City;
  }

  public void setCity(String city) {
    City = city;
  }

  public String getProvince() {
    return Province;
  }

  public void setProvince(String province) {
    Province = province;
  }

  public String getPostalCode() {
    return PostalCode;
  }

  public void setPostalCode(String postalCode) {
    PostalCode = postalCode;
  }

  public String getCountry() {
    return Country;
  }

  public void setCountry(String country) {
    this.Country = country;
  }

  public String getCardinalDirection() {
    return CardinalDirection;
  }

  public void setCardinalDirection(String cardinalDirection) {
    CardinalDirection = cardinalDirection;
  }

  public String getAltitudeInFeet() {
    return AltitudeInFeet;
  }

  public void setAltitudeInFeet(String altitudeInFeet) {
    AltitudeInFeet = altitudeInFeet;
  }

  public String getSpeed() {
    return Speed;
  }

  public void setSpeed(String speed) {
    Speed = speed;
  }

  public String getGPSFix() {
    return GPSFix;
  }

  public void setGPSFix(String GPSFix) {
    this.GPSFix = GPSFix;
  }

  public String getReasonCode() {
    return ReasonCode;
  }

  public void setReasonCode(String reasonCode) {
    ReasonCode = reasonCode;
  }

  @Override
  public String toString() {
    return "TruckTelemetry{" +
        "SequenceNumber='" + sequenceNumber + '\'' +
        ", TimeTransmitted='" + TimeTransmitted + '\'' +
        ", TimeReceived='" + TimeReceived + '\'' +
        ", TimeZoneOffset='" + TimeZoneOffset + '\'' +
        ", CompanyName='" + CompanyName + '\'' +
        ", VehicleId='" + VehicleId + '\'' +
        ", VehicleName='" + VehicleName + '\'' +
        ", VIN='" + VIN + '\'' +
        ", MaxSpeed='" + MaxSpeed + '\'' +
        ", OdometerInMiles='" + OdometerInMiles + '\'' +
        ", TotalFuelUsed='" + TotalFuelUsed + '\'' +
        ", TotalEngineHours='" + TotalEngineHours + '\'' +
        ", TotalIdleHours='" + TotalIdleHours + '\'' +
        ", RPM100Minutes='" + RPM100Minutes + '\'' +
        ", RPM1500Minutes='" + RPM1500Minutes + '\'' +
        ", RPM2000Minutes='" + RPM2000Minutes + '\'' +
        ", CruiseMinutes='" + CruiseMinutes + '\'' +
        ", MPH01Minutes='" + MPH01Minutes + '\'' +
        ", MPH35Minutes='" + MPH35Minutes + '\'' +
        ", MPH55Minutes='" + MPH55Minutes + '\'' +
        ", MPH66Minutes='" + MPH66Minutes + '\'' +
        ", HardBrakingMinutes='" + HardBrakingMinutes + '\'' +
        ", PID1='" + PID1 + '\'' +
        ", FMI1='" + FMI1 + '\'' +
        ", PID2='" + PID2 + '\'' +
        ", FMI2='" + FMI2 + '\'' +
        ", PID3='" + PID3 + '\'' +
        ", FMI3='" + FMI3 + '\'' +
        ", PID4='" + PID4 + '\'' +
        ", FMI4='" + FMI4 + '\'' +
        ", TankPercent='" + TankPercent + '\'' +
        ", DeviceNumber='" + DeviceNumber + '\'' +
        ", ModelPCB='" + ModelPCB + '\'' +
        ", Latitude='" + Latitude + '\'' +
        ", Longitude='" + Longitude + '\'' +
        ", Address='" + Address + '\'' +
        ", City='" + City + '\'' +
        ", Province='" + Province + '\'' +
        ", PostalCode='" + PostalCode + '\'' +
        ", Country='" + Country + '\'' +
        ", CardinalDirection='" + CardinalDirection + '\'' +
        ", AltitudeInFeet='" + AltitudeInFeet + '\'' +
        ", Speed='" + Speed + '\'' +
        ", GPSFix='" + GPSFix + '\'' +
        ", ReasonCode='" + ReasonCode + '\'' +
        '}';
  }
}
