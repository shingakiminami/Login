package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.beans.Customer;

public class CustomerDAO extends DAO{
	public Customer search(String id, String password)throws Exception{



		Connection con=getConnection();

		PreparedStatement st;
		st=con.prepareStatement(
				"SELECT * FROM login WHERE id=? and password=?" );

		st.setString(1, id);
		st.setString(2, password);

		ResultSet rs=st.executeQuery();

		Customer c = new Customer();

		while (rs.next()) {
			c.setId(rs.getString("id"));
			c.setName(rs.getString("name"));
			c.setPassword(rs.getString("password"));
		}

		st.close();
		con.close();
		return c;
	}

}
