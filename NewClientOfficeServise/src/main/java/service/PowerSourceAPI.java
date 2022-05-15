package service;
import model.PowerSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PowerSourceAPI
 */
@WebServlet("/SourceAPI")
public class PowerSourceAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	PowerSource ps = new PowerSource(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PowerSourceAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = ps.insertPowerSource(request.getParameter("officeID"),
				request.getParameter("officeName"),
				request.getParameter("officeType"),
				request.getParameter("officeAddress"),		
				request.getParameter("officePhone"),
				request.getParameter("officeManager"));
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = ps.updatePowerSource(paras.get("hidPowerIDSubmit").toString(),
				paras.get("officeID").toString(),
				paras.get("officeName").toString(),
				paras.get("officeType").toString(),
				paras.get("officeAddress").toString(),
				paras.get("officePhone").toString(),
				paras.get("officeManager").toString());
			
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = ps.deletePowerSource(paras.get("ID").toString());
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			
			for (String param : params)
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}
		catch (Exception e){}
		return map;
	}

}
