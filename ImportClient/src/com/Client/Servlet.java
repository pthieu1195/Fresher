
package com.Client;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientDAO ClientDAO;

	public void init() {
		ClientDAO = new ClientDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filePath = "";
		for (Part part : request.getParts()) {
			String fileName = part.getSubmittedFileName();
			;
			fileName = new File(fileName).getName();
			String uploadFolder = getServletContext().getRealPath("");
			filePath = uploadFolder + fileName;
			part.write(filePath);
		}
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Clients.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Clients list = (Clients) jaxbUnmarshaller.unmarshal(new File(filePath));
			if(ClientDAO.ValidateClient(list)==true)
			{
				request.setAttribute("message","fail to import client ");
				request.getRequestDispatcher("Views/importClient.jsp").forward(request, response);
				
				
			}
			else {
			ClientDAO.insertClient(list);
			
			request.setAttribute("message","Import Client Success ");
			request.getRequestDispatcher("Views/importClient.jsp").forward(request, response);
			}
		} catch (JAXBException e) {
			
			request.setAttribute("message","Cannot read xml file ");
			request.getRequestDispatcher("Views/importClient.jsp").forward(request, response);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("message","SQL Syntax Error ");
			request.getRequestDispatcher("Views/importClient.jsp").forward(request, response);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/importClient":
			importView(request, response);
			break;
		case "/setup":
			try {
				setupView(request, response);
			} catch (SQLException | ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "/searchClient":
			try {
				if(request.getParameter("ClientID")!=null && request.getParameter("FirstName")==null && request.getParameter("LastName")==null && request.getParameter("ClientDOB")==null)
				{
					searchByID(request, response);
				}
				else if(request.getParameter("ClientID")==null && request.getParameter("FirstName")!=null && request.getParameter("LastName")!=null && request.getParameter("ClientDOB")==null)
				{
					searchByName(request, response);
				}
				else if(request.getParameter("ClientID")==null && request.getParameter("FirstName")==null && request.getParameter("LastName")==null && request.getParameter("ClientDOB")!=null)
				{
					searchByDOB(request, response);
				}
				else if(request.getParameter("ClientID")!=null && request.getParameter("FirstName")!=null && request.getParameter("LastName")!=null && request.getParameter("ClientDOB")!=null)
				{
					searchByAll(request, response);
				}
				else searchView(request, response);
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		default:
			homeView(request, response);
			break;
		}
	}

	private void setupView(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		

		List<Mapping> list = ClientDAO.getMapping();
		request.setAttribute("listMapping", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Views/Setup.jsp");
		dispatcher.forward(request, response);
		
	}

	private void homeView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
	}
	private void importView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Views/importClient.jsp");
		dispatcher.forward(request, response);
		
	}

	private void searchView(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		int page = 1;
		int recordsPerPage = 10;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		List<Client> list = ClientDAO.getClients((page - 1) * recordsPerPage, recordsPerPage);
		int noOfRecords = ClientDAO.getN();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		request.setAttribute("listClient", list);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Views/searchClient.jsp");
		dispatcher.forward(request, response);
	}

	private void searchByID(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int page = 1;
		int recordsPerPage = 10;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		List<Client> list = ClientDAO.getClients(request.getParameter("ClientID"),(page - 1) * recordsPerPage, recordsPerPage);
		int noOfRecords = ClientDAO.getN();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		request.setAttribute("listClient", list);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
		if(list.size()==0)
		{
			request.setAttribute("message","No Clients Found");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("Views/searchClient.jsp");
		dispatcher.forward(request, response);
		return;
	}
	private void searchByName(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int page = 1;
		int recordsPerPage = 10;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		List<Client> list = ClientDAO.getClients(request.getParameter("FirstName"), request.getParameter("LastName"),(page - 1) * recordsPerPage, recordsPerPage);
		int noOfRecords = ClientDAO.getN();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		request.setAttribute("listClient", list);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
		if(list.size()==0)
		{
			request.setAttribute("message","No Clients Found");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("Views/searchClient.jsp");
		dispatcher.forward(request, response);
		
	}
	private void searchByDOB(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int page = 1;
		int recordsPerPage = 10;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		Date date=Date.valueOf(request.getParameter("ClientDOB"));
		List<Client> list = ClientDAO.getClients(date,(page - 1) * recordsPerPage, recordsPerPage);
		int noOfRecords = ClientDAO.getN();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		request.setAttribute("listClient", list);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
		if(list.size()==0)
		{
			request.setAttribute("message","No Clients Found");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("Views/searchClient.jsp");
		dispatcher.forward(request, response);
		
	}
	private void searchByAll(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int page = 1;
		int recordsPerPage = 10;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		Date date=Date.valueOf(request.getParameter("ClientDOB"));
		List<Client> list = ClientDAO.getClients(request.getParameter("ClientID"),request.getParameter("FirstName"), request.getParameter("LastName"),date,(page - 1) * recordsPerPage, recordsPerPage);
		int noOfRecords = ClientDAO.getN();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		request.setAttribute("listClient", list);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
		if(list.size()==0)
		{
			request.setAttribute("message","No Clients Found");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("Views/searchClient.jsp");
		dispatcher.forward(request, response);
		
	}

}
