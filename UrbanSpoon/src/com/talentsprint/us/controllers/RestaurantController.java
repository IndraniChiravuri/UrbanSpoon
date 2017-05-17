package com.talentsprint.us.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.talentsprint.us.dao.BranchDAO;
import com.talentsprint.us.dao.CuisineDAO;
import com.talentsprint.us.dao.RecipeDAO;
import com.talentsprint.us.dao.RestaurantDAO;
import com.talentsprint.us.dao.ServeDAO;
import com.talentsprint.us.model.Recipe;
import com.talentsprint.us.model.Serve;
import com.talentsprint.us.model.Branch;
import com.talentsprint.us.model.Cuisine;

@WebServlet("/RestaurantController")
public class RestaurantController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (null == action) {
			request.getRequestDispatcher("restaurantHome.jsp").forward(request, response);
		} else if (action.equals("Branch")) {
			request.getRequestDispatcher("addBranch.jsp").forward(request, response);
		} else if (action.equals("Recipe")) {
			ArrayList<Cuisine> cuisinesList = new CuisineDAO().getCuisines();
			HttpSession httpSession = request.getSession();
			int restaurant_id = (int) httpSession.getAttribute("loggedUser");
			ArrayList<Branch> branchesList = new BranchDAO().getBrances(restaurant_id);
			request.setAttribute("cuisinesList", cuisinesList);
			request.setAttribute("branchesList", branchesList);
			request.getRequestDispatcher("addRecipe.jsp").forward(request, response);
		} else if(action.equals("Cuisine")){
			request.getRequestDispatcher("addCuisine.jsp").forward(request, response);
		} else if(action.equals("view_cuisines")) {
			List<Cuisine> cuisinesList = new CuisineDAO().getCuisines();
			HttpSession httpSession = request.getSession();
			int restaurant_id = (int) httpSession.getAttribute("loggedUser");
			request.setAttribute("cuisinesList", cuisinesList);
			request.getRequestDispatcher("viewCuisines.jsp").forward(request, response);
		} else if(action.equals("view_branches")) {
			HttpSession httpSession = request.getSession();
			int restaurant_id = (int) httpSession.getAttribute("loggedUser");
			List<Branch> branchesList = new BranchDAO().getBrances(restaurant_id);
			request.setAttribute("branchesList", branchesList);
			request.getRequestDispatcher("viewBranches.jsp").forward(request, response);
		} else {
			HttpSession httpSession = request.getSession();
			int restaurant_id = (int) httpSession.getAttribute("loggedUser");
			List<Branch> branchesList = new BranchDAO().getBrances(restaurant_id);
			List<Recipe> recipesList = new RecipeDAO().getRecipes();
			List<Serve> serveList = null;
			for (Branch branch:  branchesList) {
				serveList = new ServeDAO().getServes(branch.getBranchId());
			}
			for (Serve serve: serveList) {
				recipesList = new RecipeDAO().getRecipes(serve.getRecipeId());
			}
			request.setAttribute("recipesList", recipesList);
			request.getRequestDispatcher("viewItems.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		Branch branch = new Branch();
		if (isMultipart) {
			DiskFileItemFactory dis = new DiskFileItemFactory();
			ServletFileUpload sfd = new ServletFileUpload(dis);

			try {

				List<FileItem> list = sfd.parseRequest(request);
				String action = list.get(0).getString();

				if (action.equals("add_branch")) {
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
							if (fileItem.getFieldName().equals("location")) {
								branch.setLocation((fileItem.getString()));
							} else if (fileItem.getFieldName().equals("city")) {
								branch.setCity((fileItem.getString()));
							} else if (fileItem.getFieldName().equals("state")) {
								branch.setState((fileItem.getString()));
							} else if (fileItem.getFieldName().equals("postal_code")) {
								branch.setPostalCode((fileItem.getString()));
							} else if (fileItem.getFieldName().equals("country")) {
								branch.setCountry((fileItem.getString()));
							} else if (fileItem.getFieldName().equals("email_id")) {
								branch.setEmailId((fileItem.getString()));
							} else if (fileItem.getFieldName().equals("phone_number")) {
								branch.setPhoneNumber((Long.parseLong(fileItem.getString())));
							}
							HttpSession httpSession = request.getSession();
							branch.setRestaurntId((int) httpSession.getAttribute("loggedUser"));
						}

					}
					BranchDAO branchadd = new BranchDAO();
					// System.out.println(branch);
					if ((branchadd.insert(branch)) > 0) {
						request.getRequestDispatcher("restaurantHome.jsp").forward(request, response);
					} else {
						response.sendRedirect("addBranch.jsp");

					}

				} else if (action.equals("add_recipe")) {
					Recipe recipe = new Recipe();
					Double price = 0.0;
					int recipeId = 0;
					int status = 0;
					List<String> branchesIdList = new ArrayList<>();
					for (FileItem fileItem : list) {
						if (!fileItem.isFormField()) {
							int lastIndex = fileItem.getName().lastIndexOf("\\");
							String fileName = fileItem.getName().substring(lastIndex + 1);
							String imagePath = "/home/user/Desktop/WISE/" + getServletContext().getContextPath()
									+ "/WebContent/images/recipes/" + fileName;
							fileItem.write(new File(imagePath));
							recipe.setImagePath(fileName);
						} else {
							if (fileItem.getFieldName().equals("name")) {
								System.out.println(fileItem.getString());
								recipe.setName((fileItem.getString()));
							} else if (fileItem.getFieldName().equals("description")) {
								System.out.println(fileItem.getString());
								recipe.setDescription((fileItem.getString()));
							} else if (fileItem.getFieldName().equals("veg")) {
								System.out.println(fileItem.getString());
								if (fileItem.getString().equals("Veg"))
									recipe.setVeg(true);
								else
									recipe.setVeg(false);
							} else if (fileItem.getFieldName().equals("cuisine")) {
								System.out.println(Integer.parseInt(fileItem.getString()));
								recipe.setCuisineId(Integer.parseInt(fileItem.getString()));
							} else if (fileItem.getFieldName().equals("branch")) {
								branchesIdList.add(fileItem.getString());
							} else if (fileItem.getFieldName().equals("price")) {
								System.out.println(fileItem.getString());
								price = Double.parseDouble(fileItem.getString());
							}
						}
					}
					System.out.println(branchesIdList);
					recipeId = new RecipeDAO().save(recipe);
					System.out.println("Recipe id : "+recipeId);
					for (String string : branchesIdList) {
						Serve serve = new Serve();
						serve.setPrice(price);
						serve.setRecipeId(recipeId);
						serve.setBranchId(Integer.parseInt(string));
						status = new ServeDAO().insert(serve);
					}
					if (status > 0) {
							request.getRequestDispatcher("restaurantHome.jsp").forward(request, response);
					} else {
							response.sendRedirect("addRecipe.jsp");
					}
				}
			} catch (Exception e) {
				System.out.println("ERROR:" + e);
			}

		} else {
			String action = request.getParameter("action");
			if (action.equals("add_cuisine")) {
				String country = request.getParameter("txtClassroomName");
				String Name = request.getParameter("txtSchoolName");
				String des = request.getParameter("description");
				Cuisine c = new Cuisine();
				c.setCountry(country);
				c.setName(Name);
				c.setDescription(des);
				CuisineDAO cuisineDAO = new CuisineDAO();
				if (cuisineDAO.insert(c) > 0) {
					System.out.println("Cuisine added");
					request.getRequestDispatcher("restaurantHome.jsp").forward(request, response);
				}
				else {
					response.sendRedirect("addCuisine.jsp");
				}
			}
		}
	}
}