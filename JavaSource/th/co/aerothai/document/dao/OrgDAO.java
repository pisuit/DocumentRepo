package th.co.aerothai.document.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import th.co.aerothai.document.model.Profession;
import th.co.aerothai.document.utils.HibernateUtil;

public abstract class OrgDAO {
	public static Object getNodeIDForStaff(String staffCode) {
		SessionFactory sf = HibernateUtil.getSessionfactoryorg();
		Session session = null;
		Transaction tx = null;

		try {
			session = sf.openSession();
			tx = session.beginTransaction();

			Object node = session.createSQLQuery(
						"select e.nodeID " 
						+ "from struct_title_emp e "
						+ "where enddate = '0000-00-00' "
						+ "and employeeID = :pempid")
						.setParameter("pempid", staffCode)
						.uniqueResult();

			tx.commit();

			return node;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			session.clear();
			session.close();
		}
	}
	
	public static List<Object[]> getNodeIDForMaster(String masterID) {
		SessionFactory sf = HibernateUtil.getSessionfactoryorg();
		Session session = null;
		Transaction tx = null;

		try {
			session = sf.openSession();
			tx = session.beginTransaction();

			List<Object[]> node = session.createSQLQuery(
						"select m.nodeID, m.remark " 
						+ "from struct_node_master m "
						+ "where enddate = '0000-00-00' "
						+ "and masterID = :pmasterid")
						.setParameter("pmasterid", masterID)
						.list();

			tx.commit();

			return node;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			session.clear();
			session.close();
		}
	}
	
	public static Object getDepartmentForNodeID(String nodeID) {
		SessionFactory sf = HibernateUtil.getSessionfactoryorg();
		Session session = null;
		Transaction tx = null;

		try {
			session = sf.openSession();
			tx = session.beginTransaction();

			Object node = session.createSQLQuery(
						"select m.remark " 
						+ "from struct_node_master m "
						+ "where enddate = '0000-00-00' "
						+ "and nodeID = :pnodeid")
						.setParameter("pnodeid", nodeID)
						.uniqueResult();

			tx.commit();

			return node;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			session.clear();
			session.close();
		}
	}
	
	public static Profession getProfessionByPositionName(String positionName) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;

		try {
			session = sf.openSession();
			tx = session.beginTransaction();

			Profession profession = (Profession) session.createSQLQuery(
						"select pro " +
						"from Profession pro " +
						"where pro.status = 'C' " +
						"and pro.profession = :pposition")
						.setParameter("pposition", positionName)
						.uniqueResult();

			tx.commit();

			return profession;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			session.clear();
			session.close();
		}
	}
}
