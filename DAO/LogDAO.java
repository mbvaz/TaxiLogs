package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.LogsGPS;
import utils.Cronometro;


public class LogDAO {	
	
	public LogDAO (Connection connection) {
		this.connection = connection;
	}

	private Connection connection;

	public List<LogsGPS> list() {
		
		LogsGPS p =  null;
		ArrayList<LogsGPS> logs = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet elements = stmt.executeQuery("select * from logsTaxi");
			while(elements.next()) {
				p = new LogsGPS();
				p.setId(elements.getInt("id"));
				p.setData(elements.getDate("dataEntrada"));
				p.setIdTaxi(elements.getInt("idTaxi"));
				p.setLatitude(elements.getString("latitude"));
				p.setLongitude(elements.getString("longitude"));
				logs.add(p);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}

		return logs;
	}

	public void save(LogsGPS t) {		
		String sql ="insert into logsTaxi(dataEntrada, idTaxi, latitude, longitude) values(?,?,?,?)";
		try {
			PreparedStatement pstmt = this.connection.prepareStatement(sql);
			
			pstmt.setDate(1, (Date) t.getData());
			pstmt.setInt(2, t.getIdTaxi());
			pstmt.setString(3, t.getLatitude());
			pstmt.setString(4, t.getLongitude());
			pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
