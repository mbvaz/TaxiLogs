package domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LogsGPS {

	@Id
	@GeneratedValue
	int id;
	String latitude;
	String longitude;
	Date Data;
	int idTaxi;
	
	public LogsGPS() {}
	
	public LogsGPS(String latitude,	String longitude, Date Data, int idTaxi) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.Data = Data;
		this.idTaxi = idTaxi;
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getData() {
		return Data;
	}

	public void setData(Date data) {
		Data = data;
	}

	public int getIdTaxi() {
		return idTaxi;
	}

	public void setIdTaxi(int idTaxi) {
		this.idTaxi = idTaxi;
	}
	
	
	
}
