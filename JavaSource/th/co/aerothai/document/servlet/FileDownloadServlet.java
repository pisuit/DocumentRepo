package th.co.aerothai.document.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import th.co.aerothai.document.dao.GeneralDAO;
import th.co.aerothai.document.model.Document;
import th.co.aerothai.document.utils.CharacterUtils;
import th.co.aerothai.document.utils.SVNUtils;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/FileDownloadServlet/*")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownloadServlet() {
        super();
        System.out.println("download");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id =request.getParameter("id");
		System.out.println(id);
		
		Document doc = GeneralDAO.getDocument(Long.valueOf(id));
//		FacesContext fc = FacesContext.getCurrentInstance();
//		ExternalContext ec = fc.getExternalContext();
		InputStream is = null;
		byte[] data;
		try {
			is = SVNUtils.downloadFile(doc.getStoredName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		data = IOUtils.toByteArray(is);
		
		response.setContentType(doc.getContentType());
		response.setHeader("Content-Disposition", "attachment;charset=utf-8; filename=\""+CharacterUtils.Unicode2ASCII(doc.getFileName()) + "\"");
		response.setCharacterEncoding("UTF-8");
		response.setContentLength(data.length);
		
//		ec.setResponseContentType(doc.getContentType());
//		ec.setResponseContentLength(data.length);
//		ec.setResponseCharacterEncoding("UTF-8");
//		ec.setResponseHeader("Content-Disposition", "attachment;charset=utf-8; filename=\""+CharacterUtils.Unicode2ASCII(doc.getFileName()) + "\""); 
		
		try {
//			OutputStream out = ec.getResponseOutputStream();
//			out.write(data);
			OutputStream out = response.getOutputStream();
			out.write(data);
			out.close();
			
//			fc.responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
