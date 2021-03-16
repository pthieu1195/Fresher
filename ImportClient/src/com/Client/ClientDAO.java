package com.Client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClientDAO {
	private Connection conn;
	private int n;

	protected void connect() throws SQLException {
		if (conn == null || conn.isClosed()) {

			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn = DBUtil.getConnection();
		}
	}

	protected void disconnect() throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}

	// get all clients
	public ArrayList<Client> getClients(int offset, int n) throws SQLException {
		ArrayList<Client> list = new ArrayList<>();

		String sql = "SELECT * FROM Client ORDER BY ClientID" + " OFFSET " + offset + " ROWS" + " FETCH NEXT " + n + " ROWS ONLY;";
		connect();
		Statement ps = conn.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		while (rs.next()) {
			String clientId = rs.getString("ClientID");
			String firstName = rs.getString("FirstName");
			String lastName = rs.getString("LastName");
			String gender = rs.getString("Gender");
			String martialStatus = rs.getString("MartialStatus");
			Date date = rs.getDate("DateOfBirth");
			LocalDate dob = date.toLocalDate();
			String address = rs.getString("Address");
			String country = rs.getString("Country");

			Client client = new Client(clientId, firstName, lastName, gender, martialStatus, dob, address, country);
			list.add(client);

		}
		rs.close();
		rs = ps.executeQuery("SELECT COUNT(*) FROM Client");
		if (rs.next()) {
			this.n = rs.getInt(1);
		}
		ps.close();
		disconnect();
		return list;
	}

	public int getN() {
		return n;
	}

//get data by id
	public ArrayList<Client> getClients(String clientId,int offset,int n) throws SQLException {
		connect();
		String sql = "SELECT * FROM Client WHERE ClientID = '" + clientId + "'";
		Statement ps = conn.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		ArrayList<Client> list = new ArrayList<>();
		while (rs.next()) {
			String gender = rs.getString("Gender");
			String martialStatus = rs.getString("MartialStatus");
			Date date = rs.getDate("DateOfBirth");
			LocalDate dob = date.toLocalDate();
			String address = rs.getString("Address");
			String country = rs.getString("Country");
			String firstName = rs.getString("FirstName");
			String lastName = rs.getString("LastName");
			
			Client client = new Client(clientId, firstName, lastName, gender, martialStatus, dob, address, country);
			list.add(client);
		}
		rs.close();
		rs = ps.executeQuery("SELECT COUNT(*) FROM Client where ClientID='"+clientId+"'");
		if (rs.next()) {
			this.n = rs.getInt(1);
		}
		ps.close();
		disconnect();
		return list;
	}

	// get data by name
	public ArrayList<Client> getClients(String firstName, String lastName,int offset,int n) throws SQLException {
		connect();
		String sql = "SELECT * FROM Client WHERE FirstName = '" + firstName + "' and LastName='" + lastName + "'  ORDER BY ClientID"  + " OFFSET " + offset + " ROWS" + " FETCH NEXT " + n + " ROWS ONLY;";
		Statement ps = conn.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		ArrayList<Client> list = new ArrayList<>();
		while (rs.next()) {

			String clientId = rs.getString("ClientID");
			String gender = rs.getString("Gender");
			String martialStatus = rs.getString("MartialStatus");
			Date date = rs.getDate("DateOfBirth");
			LocalDate dob = date.toLocalDate();
			String address = rs.getString("Address");
			String country = rs.getString("Country");

			Client client = new Client(clientId, firstName, lastName, gender, martialStatus, dob, address, country);
			list.add(client);
		}
		rs.close();
		rs = ps.executeQuery("SELECT COUNT(*) FROM Client WHERE FirstName = '" + firstName + "' and LastName='" + lastName + "'");
		if (rs.next()) {
			this.n = rs.getInt(1);
		}
		ps.close();
		disconnect();
		return list;
	}

	// get data by date of birth
	public ArrayList<Client> getClients(Date dob,int offset,int n) throws SQLException {
		connect();
		String sql = "SELECT * FROM Client WHERE DateOfBirth = '" + dob + "'  ORDER BY ClientID"  + " OFFSET " + offset + " ROWS" + " FETCH NEXT " + n + " ROWS ONLY;";
		Statement ps = conn.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		ArrayList<Client> list = new ArrayList<>();
		while (rs.next()) {
			String firstName = rs.getString("FirstName");
			String lastName = rs.getString("LastName");
			String clientId = rs.getString("ClientID");
			String gender = rs.getString("Gender");
			String martialStatus = rs.getString("MartialStatus");
			String address = rs.getString("Address");
			String country = rs.getString("Country");
			LocalDate date=dob.toLocalDate();
			Client client = new Client(clientId, firstName, lastName, gender, martialStatus,date, address, country);
			list.add(client);
		}
		rs.close();
		rs = ps.executeQuery("SELECT COUNT(*) FROM Client WHERE DateOfBirth = '" + dob + "'");
		if (rs.next()) {
			this.n = rs.getInt(1);
		}
		ps.close();
		disconnect();
		return list;
	}

	// get data by id,name and date of birth
	public ArrayList<Client> getClients(String clientId, String firstName, String lastName, Date dob,int offset,int n)
			throws SQLException {
		connect();
		StringBuilder sql = new StringBuilder("select * from client where FirstName='" + firstName + "'");
		sql.append("and LastName='" + lastName + "'");
		sql.append("and ClientID='" + clientId + "'");
		sql.append("and DateOfBirth='" + dob + "'");
		Statement ps = conn.createStatement();
		ResultSet rs = ps.executeQuery(sql.toString());
		ArrayList<Client> list = new ArrayList<>();
		while (rs.next()) {
			String gender = rs.getString("Gender");
			String martialStatus = rs.getString("MartialStatus");
			String address = rs.getString("Address");
			String country = rs.getString("Country");
			LocalDate date=dob.toLocalDate();
			Client client = new Client(clientId, firstName, lastName, gender, martialStatus, date, address, country);
			list.add(client);
		}
		rs.close();
		
			this.n = 1;
		
		ps.close();
		disconnect();
		return list;
	}
	public int compareLocalDate(LocalDate date) {
		LocalDate currentDate = LocalDate.now();
		int cmp = (currentDate.getYear() - date.getYear());
	    if (cmp == 0) {
	        cmp = (currentDate.getMonthValue() - date.getMonthValue());
	        if (cmp == 0) {
	            cmp = (currentDate.getDayOfMonth() - date.getDayOfMonth());
	        }
	    }
	    return cmp;
	}
	
	public boolean ValidateClient(Clients list) {
		
		boolean check = false;
		if (list==null)
		{
			check=true;
			return check;
		}
		else
		{
		for (Client client : list.getClients()) {
			if(client.getClientId()==null || client.getClientId().length()>15)
			{	
				check=true;
				break;
			}
			if(client.getFirstName()==null||client.getLastName()==null)
			{
				
				check=true;
				break;
			}
			if(client.getGender()==null||!client.getGender().matches("Male|Female|Unknown"))
			{
				
				check=true;
				break;
			}
			if(client.getMartialStatus()==null||!client.getMartialStatus().matches("Single|Married|Divorced"))
			{
				
				check=true;
				break;
			}
			if(client.getDob()==null||compareLocalDate(client.getDob())<=0)
			{
				
				check=true;
				break;
			}
			if(client.getAddress()==null)
			{
				
				check=true;
				break;
			}
			if(client.getCountry()==null||!client.getCountry().matches("VietNam|Singapore|Malaysia|United States"))
			{
				
				check=true;
				break;
			}
		}
		}
		return check;
		}
		
	public ArrayList<Mapping> getMapping() throws SQLException {
		ArrayList<Mapping> list = new ArrayList<>();

		String sql = "SELECT * FROM MappingScreen " ;
		connect();
		Statement ps = conn.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		while (rs.next()) {
			String clientColumn = rs.getString("ClientColumn");
			String xmlTagName = rs.getString("TagName");
			Mapping map=new Mapping(clientColumn,xmlTagName);
			list.add(map);
		}
		rs.close();
		ps.close();
		disconnect();
		return list;
	}
	
	// import and update
	public int insertClient(Clients list) throws SQLException {
		connect();
		int check = 0;
		for (Client client : list.getClients()) {
			StringBuilder sql = new StringBuilder("MERGE Client WITH (SERIALIZABLE) AS T");
			sql.append(" USING (VALUES (?, ?,?,?,?,?,?,?)) AS ");
			sql.append(" U (ClientID, FirstName,LastName,Gender,MartialStatus,DateOfBirth,Address,Country) ");
			sql.append("  ON ? = T.ClientID ");
			sql.append(" WHEN MATCHED THEN ");
			sql.append(" UPDATE SET T.FirstName = ?,T.LastName=?,T.Gender=? ");
			sql.append(",T.MartialStatus=?,T.DateOfBirth=?,T.Address=?,T.Country=? ");
			sql.append(" WHEN NOT MATCHED THEN ");
			sql.append(
					" INSERT (ClientID, FirstName,LastName,Gender,MartialStatus,DateOfBirth,Address,Country) VALUES (?, ?,?,?,?,?,?,?);");

			PreparedStatement statement = conn.prepareStatement(sql.toString());
			statement.setString(1, client.getClientId());
			statement.setString(2, client.getFirstName());
			statement.setString(3, client.getLastName());
			statement.setString(4, client.getGender());
			statement.setString(5, client.getMartialStatus());
			statement.setDate(6, Date.valueOf(client.getDob()));
			statement.setString(7, client.getAddress());
			statement.setString(8, client.getCountry());
			statement.setString(9, client.getClientId());

			statement.setString(10, client.getFirstName());
			statement.setString(11, client.getLastName());
			statement.setString(12, client.getGender());
			statement.setString(13, client.getMartialStatus());
			statement.setDate(14, Date.valueOf(client.getDob()));
			statement.setString(15, client.getAddress());
			statement.setString(16, client.getCountry());

			statement.setString(17, client.getClientId());
			statement.setString(18, client.getFirstName());
			statement.setString(19, client.getLastName());
			statement.setString(20, client.getGender());
			statement.setString(21, client.getMartialStatus());
			statement.setDate(22, Date.valueOf(client.getDob()));
			statement.setString(23, client.getAddress());
			statement.setString(24, client.getCountry());
			check += statement.executeUpdate();
			statement.close();
		}
		disconnect();
		return check;
	}
}
