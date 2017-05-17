package com.talentsprint.us.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.talentsprint.us.dao.BranchDAO;
import com.talentsprint.us.dao.FeedbackDAO;
import com.talentsprint.us.dao.RecipeDAO;
import com.talentsprint.us.dao.RestaurantDAO;
import com.talentsprint.us.dao.UserDAO;
import com.talentsprint.us.model.Branch;
import com.talentsprint.us.model.Feedback;
import com.talentsprint.us.model.Recipe;
import com.talentsprint.us.model.Restaurant;
import com.talentsprint.us.model.User;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (null == action) {
			List<Recipe> recipesList = new RecipeDAO().getRecipes();
			request.setAttribute("recipesList", recipesList);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} else if (action.equals("restaurant")) {
			List<Branch> branchesList = new BranchDAO().getBranches();
			request.setAttribute("branchesList", branchesList);
			request.getRequestDispatcher("viewBranches.jsp").forward(request, response);
		} else if (action.equals("register")) {
			request.getRequestDispatcher("registration.jsp").forward(request, response);
		} else if (action.equals("login")) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else if (action.equals("user_login_form")) {
			int recipeId = Integer.parseInt(request.getParameter("recipe_id"));
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("recipeId", recipeId);
			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
		} else if (action.equals("logout")) {
			request.getSession().invalidate();
			response.sendRedirect("home.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			DiskFileItemFactory dis = new DiskFileItemFactory();
			ServletFileUpload sfd = new ServletFileUpload(dis);

			try {
				List<FileItem> list = sfd.parseRequest(request);
				Restaurant restaurant = new Restaurant();
				Branch branch = new Branch();
				int restaurantId = 0;
				for (FileItem fileItem : list) {
					// System.out.println(fileItem.getFieldName());
					if (!fileItem.isFormField()) {
						int lastIndex = fileItem.getName().lastIndexOf("\\");
						String fileName = fileItem.getName().substring(lastIndex + 1);
						String imagePath = "/home/user/Desktop/WISE/" + getServletContext().getContextPath()
								+ "/WebContent/images/branches/" + fileName;
						fileItem.write(new File(imagePath));
						branch.setImagePath(fileName);
					} else {
						if (fileItem.getFieldName().equals("name")) {
							restaurant.setRegistrationName(fileItem.getString());
						} else if (fileItem.getFieldName().equals("password")) {
							restaurant.setPassword(fileItem.getString());
						} else if (fileItem.getFieldName().equals("registrationId")) {
							System.out.println(fileItem.getString());
							restaurant.setRegistartionId((fileItem.getString()));

						} else if (fileItem.getFieldName().equals("location")) {
							branch.setLocation((fileItem.getString()));
						} else if (fileItem.getFieldName().equals("city")) {
							branch.setCity((fileItem.getString()));
						} else if (fileItem.getFieldName().equals("state")) {
							branch.setState((fileItem.getString()));
						} else if (fileItem.getFieldName().equals("postalCode")) {
							branch.setPostalCode((fileItem.getString()));
						} else if (fileItem.getFieldName().equals("country")) {
							branch.setCountry((fileItem.getString()));
						} else if (fileItem.getFieldName().equals("emailId")) {
							branch.setEmailId((fileItem.getString()));
						} else if (fileItem.getFieldName().equals("phonenumber")) {
							branch.setPhoneNumber((Long.parseLong(fileItem.getString())));
						}

					}

				}
				RestaurantDAO restaurantDAO = new RestaurantDAO();
				restaurantId = restaurantDAO.insert(restaurant);
				branch.setRestaurntId(restaurantId);
				int status = new BranchDAO().insert(branch);
				if (status > 0) {
					request.setAttribute("userName", restaurant.getRegistrationName());
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					response.sendRedirect("registration.jsp");
				}

			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
			}

		} else {
			String action = request.getParameter("action");
			if (action.equals("user_registration")) {
				String firstName = request.getParameter("FirstName");
				String lastName = request.getParameter("LastName");
				String email = request.getParameter("EmailId");
				String password = request.getParameter("password");
				long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));

				User user = new User();
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmailId(email);
				user.setPassword(password);
				user.setPhoneNumber(phoneNumber);
				UserDAO userDAO = new UserDAO();
				if (userDAO.insert(user) > 0) {
					request.setAttribute("userName", email);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {

				}

			} else if (action.equals("restaurant_registration")) {

				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String registartionId = request.getParameter("registartionId");
				String location = request.getParameter("location");
				String city = request.getParameter("city");
				String imagePath = request.getParameter("imagepath");
				String state = request.getParameter("state");
				String postalCode = request.getParameter("postalCode");
				String country = request.getParameter("country");
				String emailId = request.getParameter("emailId");
				long phoneNumber = Long.parseLong(request.getParameter("phonenumber"));
				DiskFileItemFactory dis = new DiskFileItemFactory();
				ServletFileUpload sfd = new ServletFileUpload(dis);

				Branch branch = new Branch();

				branch.setLocation(location);
				branch.setCity(city);
				branch.setPostalCode(postalCode);
				branch.setPhoneNumber(phoneNumber);
				branch.setEmailId(emailId);
				branch.setState(state);
				branch.setImagePath(imagePath);
				branch.setCountry(country);
				List<Branch> branchesList = new ArrayList<>();
				branchesList.add(branch);
				Restaurant restaurant = new Restaurant();
				restaurant.setBranchesList(branchesList);
				restaurant.setRegistrationName(name);
				restaurant.setPassword(password);
				restaurant.setRegistartionId(registartionId);
				if (new BranchDAO().insert(branch) > 0) {
					request.setAttribute("userName", restaurant.getRegistrationName());
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					response.sendRedirect("registration.jsp");
				}

			} else if (action.equals("restaurant_login")) {

				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				Restaurant restaurant = new RestaurantDAO().getRestaurant(userName);
				System.out.println(restaurant.getPassword());
				if (restaurant != null && restaurant.getPassword().equals(password)) {
					HttpSession httpSession = request.getSession();
					httpSession.setAttribute("loggedUser", restaurant.getRestaurantId());
					response.sendRedirect("restaurantHome.jsp");
				} else {
					response.sendRedirect("login.jsp");
				}
			} else if (action.equals("feedback_login")) {
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				User user = new UserDAO().getuser(userName);
				if (user != null && user.getPassword().equals(password)) {
					HttpSession httpSession = request.getSession();
					httpSession.setAttribute("userid", user.getUser_id());
					ArrayList<Branch> branchesList = new BranchDAO().getBranches();
					List<Restaurant> restaurantList = new RestaurantDAO().getRestaurants();
					request.setAttribute("branchesList", branchesList);
					request.setAttribute("restaurantList", restaurantList);
					int recipeId = (int) (httpSession.getAttribute("recipeId"));
					System.out.println("recipeId" + recipeId);
					Recipe recipe = new RecipeDAO().getRecipe(recipeId);
					request.setAttribute("recipe", recipe);
					request.getRequestDispatcher("addFeedback.jsp").forward(request, response);
				}
			} else if (action.equals("feedbackuser")) {
				System.out.println("HomeController");
				HttpSession httpSession = request.getSession();
				Feedback feedback = new Feedback();
				String comments = request.getParameter("feedback");
				System.out.println("comments");
				int branchId = Integer.parseInt(request.getParameter("branch"));
				int recipeId = (int) (httpSession.getAttribute("recipeId"));
				int rating = Integer.parseInt(request.getParameter("rating"));
				System.out.println(rating);
				int userId = (int) (httpSession.getAttribute("userid"));
				feedback.setBranchId(branchId);
				feedback.setComments(comments);
				feedback.setRecipeId(recipeId);
				feedback.setRating(rating);
				feedback.setUserId(userId);
				FeedbackDAO feedbackDAO = new FeedbackDAO();
				int i = feedbackDAO.insert(feedback);
				if (i > 0) {
					response.sendRedirect("logout.jsp");
				} else {
					response.sendRedirect("home.jsp");
				}
			}

		}

	}

}