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
import model.DB.Cars;
import model.Model;

/**
 *
 * @author kiskin
 */
public class CarInfo extends HttpServlet {

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
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			Integer userid = (Integer) request.getSession().getAttribute("userid");
			if (userid != null) {
				Cars car = Model.getUserCar(userid);
				String rstr = String.format("model=%d;color1=#%02x%02x%02x;color2=#%02x%02x%02x", car.getModel(),
						car.getColor1()[0], car.getColor1()[1], car.getColor1()[2],
						car.getColor2()[0], car.getColor2()[1], car.getColor2()[2]);
				out.write(rstr);
				}
		}catch(Throwable exc){
			int i =2;
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
		protected void doGet
		(HttpServletRequest request, HttpServletResponse response)
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
		protected void doPost
		(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			processRequest(request, response);
		}

		/**
		 * Returns a short description of the servlet.
		 *
		 * @return a String containing servlet description
		 */
		@Override
		public String getServletInfo
		
			() {
		return "Short description";
		}// </editor-fold>

	}
