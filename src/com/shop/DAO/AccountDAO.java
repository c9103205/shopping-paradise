package com.shop.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.shop.model.AccountBean;

public class AccountDAO implements IAccountDAO {
	private DataSource dataSource;
	private Connection conn;
	private PreparedStatement stmt;
	private SQLException ex;
	private ResultSet rs;

	public AccountDAO(DataSource defaultDS) {
		this.dataSource = defaultDS;
	}

	@Override
	public boolean isUserExisted(AccountBean account) {
		boolean isExisted = false;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM member WHERE account=?");
			stmt.setString(1, account.getAccount());
			rs = stmt.executeQuery();
			if (rs.next())
				isExisted = true;
			else
				isExisted = false;

		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}
		return isExisted;
	}

	@Override
	public void addAccount(AccountBean account) {
		String[] accountContent = { account.getAccount(), account.getPassword(), account.getName(), account.getAddr(),
				account.getTel(), account.getEmail() };
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(
					"INSERT INTO member(account,password,name,addr,tel,email,privilege) VALUES(?,?,?,?,?,?,0)");
			for (int i = 0; i < accountContent.length; i++)
				stmt.setString(i + 1, accountContent[i]);
			stmt.executeUpdate();
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}
	}

	@Override
	public void getAccount(AccountBean account) {
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM member WHERE account=? AND password=?");
			stmt.setString(1, account.getAccount());
			stmt.setString(2, account.getPassword());
			rs = stmt.executeQuery();
			if (rs.next()) {
				account.setUid(rs.getInt("uid"));
				account.setAccount(rs.getString("account"));
				account.setPassword(rs.getString("password"));
				account.setAddr(rs.getString("addr"));
				account.setEmail(rs.getString("email"));
				account.setName(rs.getString("name"));
				account.setPrivilege(rs.getInt("privilege"));
				account.setTel(rs.getString("tel"));
			}
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}
	}
	
	@Override
	public void getAccountById(AccountBean account,String uid) {
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM member WHERE uid=?");
			stmt.setInt(1, Integer.parseInt(uid));
			rs = stmt.executeQuery();
			if (rs.next()) {
				account.setUid(rs.getInt("uid"));
				account.setAccount(rs.getString("account"));
				account.setPassword(rs.getString("password"));
				account.setAddr(rs.getString("addr"));
				account.setEmail(rs.getString("email"));
				account.setName(rs.getString("name"));
				account.setPrivilege(rs.getInt("privilege"));
				account.setTel(rs.getString("tel"));
			}
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}
	}
	
	public List<AccountBean> getAccount() {					//for Administrator
		List<AccountBean> list = new ArrayList<AccountBean>();
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM member");
			rs = stmt.executeQuery();
			while (rs.next()) {
				AccountBean account = new AccountBean();
				account.setUid(rs.getInt("uid"));
				account.setAccount(rs.getString("account"));
				account.setPassword(rs.getString("password"));
				account.setAddr(rs.getString("addr"));
				account.setEmail(rs.getString("email"));
				account.setName(rs.getString("name"));
				account.setPrivilege(rs.getInt("privilege"));
				account.setTel(rs.getString("tel"));
				list.add(account);
			}
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}
		return list;
	}

	public void updateAccount(AccountBean account) {
		try {
			conn = dataSource.getConnection();
			stmt = conn
					.prepareStatement("UPDATE member SET password=?, name=?, addr=?, tel=?, email=? WHERE account=?");
			stmt.setString(1, account.getPassword());
			stmt.setString(2, account.getName());
			stmt.setString(3, account.getAddr());
			stmt.setString(4, account.getTel());
			stmt.setString(5, account.getEmail());
			stmt.setString(6, account.getAccount());
			stmt.executeUpdate();
		} catch (SQLException e) {
			ex = e;
		} finally {
			closeConnect();
		}
	}
	
	public void updateAccount(int uid, int privilege) {
		try {
			conn = dataSource.getConnection();
			stmt = conn
					.prepareStatement("UPDATE member SET privilege=? WHERE uid=?");
			stmt.setInt(1, privilege);
			stmt.setInt(2, uid);
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
	}

}
