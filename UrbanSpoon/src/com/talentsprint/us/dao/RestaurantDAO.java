package com.talentsprint.us.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.talentsprint.us.dao.util.DAOUtility;
import com.talentsprint.us.model.Restaurant;

public class RestaurantDAO {
	public int insert(Restaurant restaurant) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DAOUtility.getConncetion();
			preparedStatement = connection.prepareStatement(
					"insert into restaurant (registration_name,password,registration_id) values(?,?,?)");

			preparedStatement.setString(1, restaurant.getRegistrationName());
			preparedStatement.setString(2, restaurant.getPassword());
			preparedStatement.setString(3, restaurant.getRegistartionId());
			if (preparedStatement.executeUpdate() > 0) {
				System.out.println(getRestaurant(restaurant.getRegistrationName()).getRestaurantId());
				return getRestaurant(restaurant.getRegistrationName()).getRestaurantId();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
				e.printStackTrace();
			}
		}
		return 0;
	}

	public Restaurant getRestaurant(int restaurantId) {
		Restaurant restaurant = new Restaurant();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DAOUtility.getConncetion();
			preparedStatement = connection.prepareStatement("select * from restaurant where restaurant_id=?");
			preparedStatement.setInt(1, restaurantId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				restaurant.setRestaurantId(resultSet.getInt(1));
				restaurant.setPassword(resultSet.getString(2));
				restaurant.setRegistartionId(resultSet.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return restaurant;
	}

	public List<Restaurant> getRestaurants() {
		ArrayList<Restaurant> restaurantList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DAOUtility.getConncetion();
			preparedStatement = connection.prepareStatement("select * from restaurant");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantId(resultSet.getInt(1));
				restaurant.setRegistrationName(resultSet.getString(2));
				restaurant.setPassword(resultSet.getString(3));
				restaurant.setRegistartionId(resultSet.getString(4));
				restaurantList.add(restaurant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {

					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return restaurantList;
	}

	public int delete(int restaurantId) {
		int status = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DAOUtility.getConncetion();
			preparedStatement = connection.prepareStatement("delete from restaurant where restaurant_id=?");
			preparedStatement.setInt(1, restaurantId);
			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {

					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;

	}

	public Restaurant getRestaurant(String regstrationName) {
		System.out.println("hello");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Restaurant restaurant = new Restaurant();
		connection = DAOUtility.getConncetion();
		try {
			preparedStatement = connection.prepareStatement("select * from restaurant where registration_name=?");
			preparedStatement.setString(1, regstrationName);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				restaurant.setRestaurantId((resultSet.getInt(1)));
				restaurant.setRegistrationName(resultSet.getString(2));
				restaurant.setPassword(resultSet.getString(3));
				restaurant.setRegistartionId(resultSet.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(restaurant);
		return restaurant;

	}
}