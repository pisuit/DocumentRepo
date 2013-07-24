package th.co.aerothai.document.servlet;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import th.co.aerothai.document.utils.HibernateUtil;

/**
 * Servlet implementation class StatusCheckerServlet
 */
@WebServlet("/StatusCheckerServlet/*")
public class StatusCheckerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatusCheckerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String parameter = String.valueOf(request.getPathInfo().substring(1));
		BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream());
		
		if(parameter.equals("available")){
			os.write("yes".getBytes());
			os.close();
		} else {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = null;
			Transaction tx = null;
			try {
				session = sf.openSession();
				tx = session.beginTransaction();
				
				Object result = session.createSQLQuery(
						"SELECT * " +
						"FROM MDLOG " +
						"WHERE ROWNUM < 2")
						.uniqueResult();
				tx.commit();
				
				if(result != null){
					os.write("yes".getBytes());
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (tx != null) {
					tx.rollback();
				}
			} finally {
				session.clear();
				session.close();
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
