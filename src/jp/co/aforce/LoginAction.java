package jp.co.aforce;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.Customer;
import jp.co.aforce.dao.CustomerDAO;

@WebServlet(urlPatterns = { "/jp.co.aforce/LoginAction" })
public class LoginAction extends HttpServlet {

	protected  void doPost(
		HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String password = request.getParameter("password");


		CustomerDAO dao = new CustomerDAO();

		try {

			Customer c = dao.search(id, password);

			String logId = c.getId();

			if (logId != null) {
				session.setAttribute("customer", c);
				request.getRequestDispatcher("../jsp/login-in.jsp").forward(request, response);

			} else {
//				out.println("<p>IDもしくはパスワードが間違えています</p>");
//				request.setAttribute("id",c.getId());

				request.setAttribute("loginErrorMessage","入力内容に誤りがあります。");
				getServletContext().getRequestDispatcher("../jsp/login-in.jsp").forward(request, response);


			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
