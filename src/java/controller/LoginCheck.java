/**
 * БГУИР Кафедра экономической информатики Курсовой проект ВСРПП Снаров Иван гр. 272303
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
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
public class LoginCheck extends HttpServlet {

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
		HttpSession session = request.getSession();
	
		try (PrintWriter out = response.getWriter()) {
			String username = request.getParameter("username");
			String pwd = request.getParameter("user-pwd");

			Users user = Model.checkUser(username, pwd);
			
			if (user != null) {
				session.setAttribute("userid", user.getId());
			    session.setAttribute("username", user.getName());
				response.sendRedirect(session.getServletContext().getContextPath());
			} else {
				session.setAttribute("userid", null);
				session.setAttribute("username", null);
				session.setAttribute("msg", "Неверное имя пользователя или пароль");
//				request.setCharacterEncoding("UTF-8");
//				RequestDispatcher disp = request.getRequestDispatcher("/login");
//				disp.forward(request, response);
			    response.sendRedirect(session.getServletContext().getContextPath());
				
			}
		}catch(Throwable exc){
			int i = 2;
			response.getWriter().println(exc.getMessage());
		}
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
		return "Сервлет аутентификации пользователя";
	}// </editor-fold>

}
