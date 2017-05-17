package com.talentsprint.us.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.talentsprint.us.dao.util.DAOUtility;
import com.talentsprint.us.model.Branch;

public class BranchDAO {

	public int insert(Branch branch) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DAOUtility.getConncetion();
			preparedStatement = connection.prepareStatement(
					"insert into branch (location,city,state,postal_code,country,email_id,phone_number,restaurant_id,image_path)values(?,?,?,?,?,?,?,?,?)");

			preparedStatement.setString(1, branch.getLocation());
			preparedStatement.setString(2, branch.getCity());
			preparedStatement.setString(3, branch.getState());
			preparedStatement.setString(4, branch.getPostalCode());
			preparedStatement.setString(5, branch.getCountry()); 
			preparedStatement.setString(6, branch.getEmailId());
			preparedStatement.setLong(7, branch.getPhoneNumber());
			preparedStatement.setInt(8,branch.getRestaurntId());
			preparedStatement.setString(9, branch.getImagePath());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}

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
		return 0;
	}

	public int deleteBranch(int id) {
		int status = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = DAOUtility.getConncetion();
		try {
			preparedStatement = connection.prepareStatement("delete from branch where id=? ");

			preparedStatement.setInt(1, id);
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;

	}

	public ArrayList<Branch> getBrances(int restautrant_id) {
		ArrayList<Branch> branchList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DAOUtility.getConncetion();
			preparedStatement = connection.prepareStatement("select * from branch where restaurant_id=?");
			preparedStatement.setInt(1, restautrant_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Branch branch = new Branch();
				branch.setBranchId(resultSet.getInt(1)); 
				branch.setLocation(resultSet.getString(2));
				branch.setCity(resultSet.getString(3));
				branch.setState(resultSet.getString(4));
				branch.setPostalCode(resultSet.getString(5));
				branch.setCountry(resultSet.getString(6));
				branch.setEmailId(resultSet.getString(7));
				branch.setPhoneNumber(resultSet.getLong(8));
				branch.setRestaurntId(resultSet.getInt(9));
				branch.setImagePath(resultSet.getString(10));
				branchList.add(branch);
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

		return branchList;

	}
	public ArrayList<Branch> getBranches() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Branch> branchesList = new ArrayList<>();
		try {
			connection = DAOUtility.getConncetion();
			preparedStatement = connection.prepareStatement("select * from branch");

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())

			{
				Branch branch = new Branch();
				branch.setBranchId(resultSet.getInt(1)); 
				branch.setLocation(resultSet.getString(2));
				branch.setCity(resultSet.getString(3));
				branch.setState(resultSet.getString(4));
				branch.setPostalCode(resultSet.getString(5));
				branch.setCountry(resultSet.getString(6));
				branch.setEmailId(resultSet.getString(7));
				branch.setPhoneNumber(resultSet.getLong(8));
				branch.setRestaurntId(resultSet.getInt(9));
				branch.setImagePath(resultSet.getString(10));
				branchesList.add(branch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return branchesList;
	}
}