package com.shop.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.shop.model.MarqueeBean;

public class MarqueeDAO implements IMarqueeDAO {
	private DataSource dataSource;
	private Connection conn;
	private PreparedStatement stmt;
	private SQLException ex; 
	private ResultSet rs;

	public MarqueeDAO(DataSource defaultDS) {
		this.dataSource = defaultDS;
	}

	@Override
	public MarqueeBean getById(int id) {
		MarqueeBean marquee = new MarqueeBean();
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM marquee WHERE id=?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				marquee.setContext(rs.getString("context"));
				marquee.setId(rs.getInt("id"));
				marquee.setSelected(rs.getInt("selected"));
			}
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}

		return marquee;

	}

	@Override
	public List<MarqueeBean> getAll() {
		List<MarqueeBean> list = new ArrayList<MarqueeBean>();
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM marquee");
			rs = stmt.executeQuery();
			while (rs.next()) {
				MarqueeBean marquee = new MarqueeBean();
				marquee.setContext(rs.getString("context"));
				marquee.setId(rs.getInt("id"));
				marquee.setSelected(rs.getInt("selected"));
				list.add(marquee);
			}
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}

		return list;
	}

	@Override
	public void add(String context) {
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("INSERT INTO marquee(context) VALUE(?)");
			stmt.setString(1, context);
			stmt.executeUpdate();
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}

	}

	@Override
	public void remove(int id) {
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("DELETE FROM marquee WHERE id=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}

	}

	@Override
	public void selected(int id) {
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("UPDATE marquee SET selected=0");
			stmt.executeUpdate();
			stmt = conn.prepareStatement("UPDATE marquee SET selected=1 where id=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}

	}

	public void closeConnect() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				if (ex == null)
					ex = e;
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				if (ex == null) {
					ex = e;
				}
			}
		}
		if (ex != null)
			throw new RuntimeException(ex);
		
		//RuntimeException：可以不使用try…catch进行处理，但是如果有异常产生，则异常将由JVM进行处理。
	}

}
