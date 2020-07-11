package org.gradle.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "hello" })
public class HelloServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().print("Hello,␣World!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String nachName = request.getParameter("nachName");
		if (name == null)
			name = "World";
		response.getWriter().print("Hello␣" + name + "!");
		if (nachName.equals(""))
			nachName = "World";
		response.getWriter().print("Hello␣" + nachName + " dummmmbaasss!");
	}
}