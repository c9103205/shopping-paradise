package com.shop.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.shop.model.CommodityBean;
import com.shop.model.OrderExtBean;

public class CommodityDAO implements ICommodityDAO {
	private DataSource dataSource;
	private Connection conn, conn1;
	private PreparedStatement stmt, stmt1;
	private SQLException ex;
	private ResultSet rs;

	public CommodityDAO(DataSource defaultDS) {
		this.dataSource = defaultDS;
	}

	@Override
	public void getCommodityById(CommodityBean cb, String id) {
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM commodity WHERE id=?");
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				//get資料表中相對應的資料。
				cb.setId(rs.getString("id"));
				cb.setName(rs.getString("name"));
				cb.setCategory(rs.getString("category"));
				cb.setPrice(rs.getInt("price"));
				cb.setQuantity(rs.getInt("quantity"));
				cb.setDetail(rs.getString("detail"));
				cb.setSpec(rs.getString("spec"));
				cb.setImage(rs.getString("image"));
				//設定商品的屬性。
			}
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}

	}

	@Override
	public List<CommodityBean> getCommodity() {
		List<CommodityBean> list = new ArrayList<CommodityBean>();

		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM commodity");
			rs = stmt.executeQuery();
			while (rs.next()) {
				CommodityBean cb = new CommodityBean();
				cb.setId(rs.getString("id"));
				cb.setName(rs.getString("name"));
				cb.setCategory(rs.getString("category"));
				cb.setPrice(rs.getInt("price"));
				cb.setQuantity(rs.getInt("quantity"));
				cb.setDetail(rs.getString("detail"));
				cb.setSpec(rs.getString("spec"));
				cb.setImage(rs.getString("image"));
				list.add(cb);
			}
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}

		return list;
	}

	public List<CommodityBean> getCommodity(String cate) { // 給MEMBER用的
		List<CommodityBean> list = new ArrayList<CommodityBean>();

		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM commodity WHERE category=?");
			stmt.setString(1, cate);
			rs = stmt.executeQuery();
			while (rs.next()) {
				CommodityBean cb = new CommodityBean();
				cb.setId(rs.getString("id"));
				cb.setName(rs.getString("name"));
				cb.setCategory(rs.getString("category"));
				cb.setPrice(rs.getInt("price"));
				cb.setQuantity(rs.getInt("quantity"));
				cb.setDetail(rs.getString("detail"));
				cb.setSpec(rs.getString("spec"));
				cb.setImage(rs.getString("image"));
				list.add(cb);
			}
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}

		return list;
	}

	@Override
	public void updateCommodity(CommodityBean commodity, boolean hasImage) {

		try {
			conn = dataSource.getConnection();
			if (hasImage) {
				stmt = conn.prepareStatement(
						"UPDATE commodity SET id=?, name=?, category=?, price=?, quantity=?, detail=?, spec=?, image=? WHERE id=?");
				stmt.setString(8, commodity.getImage());
				stmt.setString(9, commodity.getId());
			} else {
				stmt = conn.prepareStatement(
						"UPDATE commodity SET id=?, name=?, category=?, price=?, quantity=?, detail=?, spec=? WHERE id=?");
				stmt.setString(8, commodity.getId());
			}
			stmt.setString(1, commodity.getId());
			stmt.setString(2, commodity.getName());
			stmt.setString(3, commodity.getCategory());
			stmt.setInt(4, commodity.getPrice());
			stmt.setInt(5, commodity.getQuantity());
			stmt.setString(6, commodity.getDetail());
			stmt.setString(7, commodity.getSpec());

			stmt.executeUpdate();
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}

	}

	@Override
	public void deleteCommodity(String id) {
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("DELETE FROM commodity WHERE id=?");
			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}
	}

	@Override
	public void addCommodity(CommodityBean commodity) {
		System.out.println(commodity.getDetail());
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(
					"INSERT INTO commodity(id,name,category,price,quantity,detail,spec,image) VALUES(?,?,?,?,?,?,?,?)");
			stmt.setString(1, todayNum());
			stmt.setString(2, commodity.getName());
			stmt.setString(3, commodity.getCategory());
			stmt.setInt(4, commodity.getPrice());
			stmt.setInt(5, commodity.getQuantity());
			stmt.setString(6, commodity.getDetail());
			stmt.setString(7, commodity.getSpec());
			stmt.setString(8, commodity.getImage());
			stmt.executeUpdate();
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}
	}

	public String todayNum() {
		String num;
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
		num = bartDateFormat.format(new Date());
		try {
			conn1 = dataSource.getConnection();
			stmt1 = conn1
					.prepareStatement("SELECT LPAD(MAX(right(id,3))+1,3,0) AS max FROM commodity WHERE LEFT(id,8)=?");
			stmt1.setString(1, num);
			rs = stmt1.executeQuery();
			rs.next();
			if (rs.getString("max") != null) {
				num += rs.getString("max");
			} else {
				num += "001";
			}
		} catch (SQLException e) {
			ex = e;
		} finally {
			if (conn1 != null) {
				try {
					conn1.close();
				} catch (SQLException e) {
					if (ex == null)
						ex = e;
				}
			}
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					if (ex == null) {
						ex = e;
					}
				}
			}
			if (ex != null)
				throw new RuntimeException(ex);
		}
		return num;
	}

	@Override
	public void alterQuantity(OrderExtBean[] oeb) {
		try {
			conn = dataSource.getConnection();
			for (OrderExtBean comm : oeb) {
				stmt = conn.prepareStatement("UPDATE commodity SET quantity=quantity-? WHERE id=?");
				stmt.setInt(1, comm.getBuyquantity());
				stmt.setString(2, comm.getCommodityId());
				stmt.executeUpdate();
			}
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
	}

}
