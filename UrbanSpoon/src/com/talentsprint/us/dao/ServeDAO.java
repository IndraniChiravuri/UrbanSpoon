package com.talentsprint.us.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.talentsprint.us.dao.util.DAOUtility;
import com.talentsprint.us.model.Serve;

public class ServeDAO {
	public int insert(Serve serve) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = DAOUtility.getConncetion();
		try {

			preparedStatement = connection.prepareStatement(
					"insert into serve(price,branch_id,recipe_id) values (?,?,?)");

			preparedStatement.setDouble(1, serve.getPrice());
			preparedStatement.setInt(2, serve.getBranchId());
			preparedStatement.setInt(3, serve.getRecipeId());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
		  System.out.println(e);
		} finally {
			try {  

				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;

	}
	public ArrayList<Serve> getServes(int branch_id) {
		ArrayList<Serve> serveList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DAOUtility.getConncetion();
			preparedStatement = connection.prepareStatement("select * from serve where branch_id=?");
			preparedStatement.setInt(1, branch_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Serve serve = new Serve();
				serve.setPrice(resultSet.getDouble(1));
				serve.setBranchId(resultSet.getInt(2));
				serve.setRecipeId(resultSet.getInt(3));
				serveList.add(serve);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null)
				preparedStatement.close();
			if (connection != null) {
				connection.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return serveList;

	}

}