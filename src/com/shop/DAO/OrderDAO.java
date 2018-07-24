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

import com.shop.model.AccountBean;
import com.shop.model.CommodityBean;
import com.shop.model.OrderExtBean;
import com.shop.model.OrderMainBean;

public class OrderDAO implements IOrderDAO {
	private DataSource dataSource;
	private Connection conn, conn1;
	private PreparedStatement stmt, stmt1;
	private SQLException ex;
	private ResultSet rs, rs1;

	public OrderDAO(DataSource defaultDS) {
		this.dataSource = defaultDS;
	}

	@Override
	public void addOrderMain(OrderMainBean omb) {
		omb.setId(todayNum());
		try {
			conn = dataSource.getConnection();
			// 主檔
			stmt = conn.prepareStatement(
					"INSERT INTO ordermain(id,mem_id,date,receiver,addr,tel,process,note) VALUES(?,?,?,?,?,?,?,?)");
			stmt.setString(1, omb.getId());
			//從第一個?，塞入檔案庫中的id
			stmt.setString(2, omb.getMemId());
			stmt.setString(3, omb.getDate());
			stmt.setString(4, omb.getReceiver());
			stmt.setString(5, omb.getAddr());
			stmt.setString(6, omb.getTel());
			stmt.setString(7, omb.getProcess());
			stmt.setString(8, omb.getNote());
			stmt.executeUpdate();
		
			stmt = conn.prepareStatement("INSERT INTO orderext(id,commodity_id,price,buyquantity) VALUES(?,?,?,?)");
			for (int i = 0; i < omb.getExt().length; i++) {
				stmt.setString(1, omb.getId());
				stmt.setString(2, omb.getExt()[i].getCommodityId());
				stmt.setInt(3, omb.getExt()[i].getPrice());
				stmt.setInt(4, omb.getExt()[i].getBuyquantity());
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}

	}

	@Override
	public void updateProcess(String id, String process) {
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("UPDATE ordermain SET process=? WHERE id=?");
			stmt.setString(1, process);
			stmt.setString(2, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}
	}

	@Override
	public List<OrderMainBean> getAllOrderMain() {
		List<OrderMainBean> list = new ArrayList<OrderMainBean>();
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM ordermain");
			rs = stmt.executeQuery();
			while (rs.next()) {
				OrderMainBean omb = new OrderMainBean();
				omb.setAddr(rs.getString("addr"));
				omb.setDate(rs.getString("date"));
				omb.setId(rs.getString("id"));
				omb.setNote(rs.getString("note"));
				omb.setProcess(rs.getString("process"));
				omb.setReceiver(rs.getString("receiver"));
				omb.setTel(rs.getString("tel"));
				omb.setExt(getOeb(omb.getId()));
				list.add(omb);
			}
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}
		return list;
	}

	@Override
	public List<OrderMainBean> getOrderMain(AccountBean account) {
		List<OrderMainBean> list = new ArrayList<OrderMainBean>();
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM ordermain WHERE mem_id=?");
			stmt.setInt(1, account.getUid());
			rs = stmt.executeQuery();
			while (rs.next()) {
				OrderMainBean omb = new OrderMainBean();
				omb.setAddr(rs.getString("addr"));
				omb.setDate(rs.getString("date"));
				omb.setId(rs.getString("id"));
				omb.setNote(rs.getString("note"));
				omb.setProcess(rs.getString("process"));
				omb.setReceiver(rs.getString("receiver"));
				omb.setTel(rs.getString("tel"));
				omb.setExt(getOeb(omb.getId()));
				list.add(omb);
			}
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}
		return list;
	}

	public OrderExtBean[] getOeb(String id) {
		int amount = 0;
		OrderExtBean[] oeb = null;
		try {
			// 取得單筆訂單有的商品種類數
			conn1 = dataSource.getConnection();
			stmt1 = conn1.prepareStatement("SELECT count(id) AS num FROM orderext WHERE id=?");
			stmt1.setString(1, id);
			rs1 = stmt1.executeQuery();
			if (rs1.next())
				amount = rs1.getInt("num");
			oeb = new OrderExtBean[amount];
			// --------------------------------------------------------------------------------------------
			stmt1 = conn1.prepareStatement("SELECT * FROM orderext WHERE id=?");
			stmt1.setString(1, id);
			rs1 = stmt1.executeQuery();
			int i = 0;
			while (rs1.next()) {
				oeb[i] = new OrderExtBean();
				oeb[i].setBuyquantity(rs1.getInt("buyquantity"));
				oeb[i].setCommodityId(rs1.getString("commodity_id"));
				oeb[i].setId(rs1.getString("id"));
				oeb[i].setPrice(rs1.getInt("price"));
				i++;
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
		return oeb;
	}

	public String todayNum() {
		String num;
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
		num = bartDateFormat.format(new Date());
		try {
			conn1 = dataSource.getConnection();
			stmt1 = conn1
					.prepareStatement("SELECT LPAD(MAX(right(id,4))+1,4,0) AS max FROM ordermain WHERE LEFT(id,9)=?");
			stmt1.setString(1, "A" + num);
			rs = stmt1.executeQuery();
			rs.next();
			if (rs.getString("max") != null) {
				num += rs.getString("max");
			} else {
				num += "0001";
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
		return "A" + num;
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
