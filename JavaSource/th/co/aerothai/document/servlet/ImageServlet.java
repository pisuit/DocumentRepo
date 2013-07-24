package th.co.aerothai.document.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.co.aerothai.document.dao.GeneralDAO;
import th.co.aerothai.document.model.Photo;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/photo/*")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String staffCode = String.valueOf(request.getPathInfo().substring(1));
		Photo photo = GeneralDAO.getPhoto(staffCode);
	
		if(photo != null){
			response.setHeader("Content-Type", getServletContext().getMimeType(photo.getStaffCode()));
			response.setHeader("Content-Disposition", "inline; filename=\"" + photo.getStaffCode() + "\"");
			
			BufferedInputStream input = null;
	        BufferedOutputStream output = null;
	        
	        try {
	        	input = new BufferedInputStream(new ByteArrayInputStream(photo.getPhoto()));
	        	output = new BufferedOutputStream(response.getOutputStream());
	        	
	        	byte[] buffer = new byte[8192];
	        	for(int l = 0;(l = input.read(buffer)) > 0;){
	        		output.write(buffer, 0, l);
	        	}
	        } finally {
	        	 if (output != null) try { output.close(); } catch (IOException e) {}
	             if (input != null) try { input.close(); } catch (IOException e) {}
	        }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
