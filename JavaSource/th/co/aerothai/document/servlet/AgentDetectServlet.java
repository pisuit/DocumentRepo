package th.co.aerothai.document.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.co.aerothai.document.utils.UAgentInfo;

/**
 * Servlet implementation class AgentDetect
 */
@WebServlet("/AgentDetect")
public class AgentDetectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgentDetectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UAgentInfo agentInfo = new UAgentInfo(request.getHeader("user-agent"), request.getHeader("Accept"));
		System.out.println(request.getHeader("user-agent"));
		
		if(agentInfo.detectMobileLong() == true){
			response.sendRedirect("/document/pages/main_m.jsf");
		} else {
			response.sendRedirect("/document/pages/main.jsf");
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
