package com.khalid.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.khalid.AppProperties;
import com.khalid.model.StateDetails;
//import com.mysql.jdbc.Driver;

public class DatabaseUtil {

	public static List<StateDetails> getListStates(AppProperties appProperties) {
		Connection conn = getConnection(appProperties);

		try {
			QueryRunner queryRunner = new QueryRunner();
			ResultSetHandler<List<StateDetails>> resultSetHandler = new BeanListHandler<StateDetails>(StateDetails.class);
			List<StateDetails> stateList = queryRunner.query(conn, "SELECT * FROM States", resultSetHandler);
			return stateList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
	}

	private static Connection getConnection(AppProperties appProperties) {
		Connection conn = null;
		String jdbcURL = appProperties.getProperty("jdbc.url");
		String jdbcDriver = appProperties.getProperty("jdbc.driver");
		String user = appProperties.getProperty("jdbc.username");
		String password = appProperties.getProperty("jdbc.password");

		try {
			DbUtils.loadDriver(jdbcDriver);
			conn = DriverManager.getConnection(jdbcURL, user, password);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}