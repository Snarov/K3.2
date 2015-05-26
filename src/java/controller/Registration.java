/**
 * БГУИР Кафедра экономической информатики Курсовой проект ВСРПП Снаров Иван гр. 272303
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DB.Users;
import model.Model;

/**
 *
 * @author Kiskin
 */
public class Registration extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String userPw = request.getParameter("user-pwd");
		String userPwRepeat = request.getParameter("user-pwd-repeat");

		HttpSession session = request.getSession();

		if (username.isEmpty() || userPw.isEmpty()) {
			response.sendRedirect(session.getServletContext().getContextPath());
		} else if (!userPw.equals(userPwRepeat)) {
			session.setAttribute("userid", null);
			session.setAttribute("msgR", "Пароли не совпадают");

		} else {
			Users user = Model.registerNewUser(username, userPw);
			if (user != null) {
				session.setAttribute("userid", user.getId());
			} else {
				session.setAttribute("userid", null);
				session.setAttribute("msgR", "Пользователь с таким именем уже существует");
			}

			
		}
		response.sendRedirect(session.getServletContext().getContextPath());
	}

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
