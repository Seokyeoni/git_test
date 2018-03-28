package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBUtil;


public class DAO {
	
	public static ArrayList<String[]>selectAll() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<String[]> woori = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from woori1 limit 10");
			rset = pstmt.executeQuery();
			
			woori = new ArrayList<String[]>();
			while(rset.next()) {
				String[] row = null;
				row = new String[]{String.valueOf(rset.getString(1)), String.valueOf(rset.getString(2))};
				woori.add(row);
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return woori;
	}
	

}
