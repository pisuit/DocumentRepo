package th.co.aerothai.document.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import th.co.aerothai.document.model.Category;
import th.co.aerothai.document.model.CategoryAdmin;
import th.co.aerothai.document.model.Department;
import th.co.aerothai.document.model.Document;
import th.co.aerothai.document.model.Log;
import th.co.aerothai.document.model.PersonalInfo;
import th.co.aerothai.document.model.Photo;
import th.co.aerothai.document.model.SubCategory;
import th.co.aerothai.document.utils.HibernateUtil;

public abstract class GeneralDAO {
	public static List<String> getCategoryListAsString(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<String> categoryList = session.createQuery(
					"SELECT category.name " +
					"FROM Category category " +
					"WHERE category.dataStatus = 'NORMAL' " +
					"ORDER BY category.ordering ")
					.list();
			tx.commit();
			
			return categoryList;
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
	
	@SuppressWarnings("unchecked")
	public static List<Department> getDepartments(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Department> depList = session.createQuery(
					"SELECT dep " +
					"FROM Department dep " +
					"WHERE dep.PFLAG = 'C' " +
					"AND dep.FFLAG = 'C' " +
					"ORDER BY dep.TDEPS")
					.list();
			
			tx.commit();
			
			return depList;
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
	
	public static List<Category> getCategoryList_M(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Category> categoryList = session.createQuery(
					"SELECT distinct category " +
					"FROM Category category " +
					"left join fetch category.subCategoryList subs " +
					"WHERE category.dataStatus = 'NORMAL' " +
					"ORDER BY category.ordering ")
					.list();
			tx.commit();
			
			return categoryList;
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
	
	public static List<Log> getLogList(Date start, Date end){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Log> logList = session.createQuery(
					"SELECT distinct log " +
					"FROM Log log " +
					"left join fetch log.staff staff " +
					"left join fetch staff.employeeInfos emp " +
					"left join fetch emp.profession pro " +
					"WHERE trunc(log.timeStamp) >= :pstart " +
					"AND trunc(log.timeStamp) <= :pend " +
					"AND staff.id != null " +
					"AND emp != null " +
					"AND pro.status = 'C' " +
					"ORDER BY log.timeStamp desc")
					.setParameter("pstart", start)
					.setParameter("pend", end)
					.list();
			
			tx.commit();
			return logList;		
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
	
	public static List<Category> getCategoryList(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;

		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Category> categoryList = session.createQuery(
					"SELECT distinct category " +
					"FROM Category category " +
					"WHERE category.dataStatus = 'NORMAL' " +
					"ORDER BY category.ordering ")
					.list();
			tx.commit();
			
			return categoryList;
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
	
	public static List<SubCategory> getSubCategoryList(Category category){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<SubCategory> subCategoryList = session.createQuery(
					"SELECT distinct sub " +
					"FROM SubCategory sub " +
					"left join fetch sub.category " +
					"left join fetch sub.admins mem " +
					"left join fetch mem.personalInfo person " +
					"left join fetch person.employeeInfos emp " +
					"left join fetch emp.profession pro " +
					"WHERE sub.dataStatus = 'NORMAL' " +
					"AND sub.category = :pcategory " +
					"AND emp != null " +
					"AND pro.status = 'C' " +
					"ORDER BY sub.ordering")
					.setParameter("pcategory", category)
					.list();
			tx.commit();
			
			return subCategoryList;
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
	
	public static List<String> getSubCategoryListAsString(Category category){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<String> subCategoryList = session.createQuery(
					"SELECT sub.description " +
					"FROM SubCategory sub " +
					"WHERE sub.dataStatus = 'NORMAL' " +
					"AND sub.category = :pcategory " +
					"ORDER BY sub.ordering")
					.setParameter("pcategory", category)
					.list();
			tx.commit();
			
			return subCategoryList;
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
	
	public static SubCategory getSubCategory(Long sub){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			SubCategory subcat = (SubCategory)session.createQuery(
					"SELECT distinct sub " +
					"FROM SubCategory sub " +
					"WHERE sub.id = :psub")
					.setParameter("psub", sub)
					.uniqueResult();
			tx.commit();
			
			return subcat;
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
	
	public static Category getCategory(String name){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			Category category = (Category) session.createQuery(
					"SELECT category " +
					"FROM Category category " +
					"WHERE category.name = :pname " +
					"AND category.dataStatus = 'NORMAL' ")
					.setParameter("pname", name)
					.uniqueResult();
			tx.commit();
			
			return category;
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
	
	public static Category getCategory(Category cat){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			Category category = (Category) session.createQuery(
					"SELECT category " +
					"FROM Category category " +
					"WHERE category = :pcat " +
					"AND category.dataStatus = 'NORMAL' ")
					.setParameter("pcat", cat)
					.uniqueResult();
			tx.commit();
			
			return category;
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
	
	public static Category saveCategory(Category category){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(category);
			tx.commit();
			
			return category;
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
	
	public static SubCategory saveSubcategory(SubCategory sub, List<CategoryAdmin> members){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(sub);
			if(members != null){
				for(CategoryAdmin member : GeneralDAO.getMemberList(sub)){
					session.delete(member);
				}
		
				for(CategoryAdmin member : members){
					member.setSubCategory(sub);
					session.saveOrUpdate(member);
				}
			}
			
			tx.commit();
			return sub;
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
	
	public static Document saveDocument(Document document){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(document);
			
			tx.commit();
			
			return document;
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
	
	public static List<PersonalInfo> getEmployeeList(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<PersonalInfo> personalList = session.createQuery(
					"SELECT distinct personal " +
					"FROM PersonalInfo personal " +
					"left join fetch personal.employeeInfos emp " +
					"left join fetch emp.profession pro " +
					"WHERE emp != null " +
					"AND pro.status = 'C' " +
					"ORDER BY personal.TNAME ")
					.list();
			tx.commit();
			
			return personalList;
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
	
	@SuppressWarnings("unchecked")
	public static List<Document> getRootDocumentsForSubCategoryASC(SubCategory sub){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Document> documents = session.createQuery(
					"SELECT distinct document " +
					"FROM Document document " +
					"left join fetch document.childFolder child " +
					"left join fetch document.rootFolder root " +
					"WHERE document.subCategory = :psub " +
					"AND document.dataStatus = 'NORMAL' " +
					"AND document.rootFolder = null " +
					"ORDER BY upper(document.fileName) asc, document.fileName asc")
					.setParameter("psub", sub)
					.list();
			
			tx.commit();
			
			return documents;
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
	
	@SuppressWarnings("unchecked")
	public static List<Document> getRootDocumentsForSubCategoryDESC(SubCategory sub){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Document> documents = session.createQuery(
					"SELECT distinct document " +
					"FROM Document document " +
					"left join fetch document.childFolder child " +
					"left join fetch document.rootFolder root " +
					"WHERE document.subCategory = :psub " +
					"AND document.dataStatus = 'NORMAL' " +
					"AND document.rootFolder = null " +
					"ORDER BY upper(document.fileName) desc, document.fileName desc")
					.setParameter("psub", sub)
					.list();
			
			tx.commit();
			
			return documents;
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
	
	public static Document getRootDocuments(Long doc){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			Document document = (Document) session.createQuery(
					"SELECT distinct document " +
					"FROM Document document " +
					"left join fetch document.rootFolder " +
					"WHERE document.dataStatus = 'NORMAL' " +
					"AND document.id = :pdoc ")
					.setParameter("pdoc", doc)
					.uniqueResult();
			
			tx.commit();
			
			return document;
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
	
	@SuppressWarnings("unchecked")
	public static List<Long> getViewList(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Long> documents = session.createQuery(
					"SELECT distinct document.id " +
					"FROM Document document " +
					"WHERE document.dataStatus = 'NORMAL' ")
//					"AND document.isFolder = true ")
					.list();
			
			tx.commit();
			
			return documents;
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
	
	@SuppressWarnings("unchecked")
	public static List<Document> getChildDocument(Document doc){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<Document> documents = session.createQuery(
					"SELECT distinct document " +
					"FROM Document document " +
					"left join fetch document.childFolder child " +
					"WHERE document.rootFolder = :pdoc " +
					"AND document.dataStatus = 'NORMAL' " +
					"ORDER BY upper(document.fileName) desc, document.fileName desc")
					.setParameter("pdoc", doc)
					.list();
			
			tx.commit();
			
			return documents;
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
	
	public static Document getDocument(String fileName, Document parent){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			Document document = (Document) session.createQuery(
					"SELECT document " +
					"FROM Document document " +
					"left join fetch document.childFolder " +
					"left join fetch document.rootFolder root " +
					"WHERE root = :pparent " +
					"AND document.fileName = :pfilename " +
					"AND document.dataStatus = 'NORMAL' ")
					.setParameter("pparent", parent)
					.setParameter("pfilename", fileName)
					.uniqueResult();
			
			tx.commit();
			
			return document;
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
	
	public static Document getDocument(Document doc){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			Document document = (Document) session.createQuery(
					"SELECT distinct document " +
					"FROM Document document " +
					"left join fetch document.childFolder child " +
					"left join fetch document.rootFolder root " +
					"WHERE document.id = :psubid " +
					"ORDER BY case child.isFolder when true then 1 else 2 end, upper(child.fileName) ")
					.setParameter("psubid", doc.getId())
					.uniqueResult();
			
			tx.commit();
			
			return document;
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
	
	public static Document getDocument(Long id){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			Document document = (Document) session.createQuery(
					"SELECT document " +
					"FROM Document document " +
					"left join fetch document.childFolder child " +
					"left join fetch document.rootFolder " +
					"WHERE document.id = :psubid " +
					"ORDER BY case child.isFolder when true then 1 else 2 end, upper(child.fileName)")
					.setParameter("psubid", id)
					.uniqueResult();
			
			tx.commit();
			
			return document;
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
	
	@SuppressWarnings("unchecked")
	public static List<CategoryAdmin> getMemberList(SubCategory sub){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			List<CategoryAdmin> memberList = session.createQuery(
					"SELECT distinct mem " +
					"FROM CategoryAdmin mem " +
					"left join fetch mem.personalInfo person " +
					"left join fetch person.employeeInfos emp " +
					"left join fetch emp.profession pro " +
					"WHERE mem.subCategory = :psub " +
					"AND emp != null " +
					"AND pro.status = 'C' " +
					"ORDER BY case mem.isAdmin when true then 1 else 2 end desc, cast(pro.tceiling, int), cast(pro.lceiling, int),person.TNAME desc")
					.setParameter("psub", sub)
					.list();
			tx.commit();

			return memberList;
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
	
	public static CategoryAdmin getBossMember(SubCategory sub, PersonalInfo person){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			CategoryAdmin member = (CategoryAdmin) session.createQuery(
					"SELECT distinct mem " +
					"FROM CategoryAdmin mem " +
					"WHERE mem.subCategory = :psub " +
					"AND mem.personalInfo = :pperson")
					.setParameter("psub", sub)
					.setParameter("pperson", person)
					.uniqueResult();
			tx.commit();

			return member;
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
	
	public static PersonalInfo getUser(String staffcode){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			PersonalInfo personalInfo = (PersonalInfo) session.createQuery(
					"SELECT person " +
					"FROM PersonalInfo person " +
					"left join fetch person.employeeInfos emp " +
					"left join fetch emp.profession pro " +
					"WHERE person.STAFFCODE = :pstaffcode " +
					"AND emp != null " +
					"AND pro.status = 'C' ")
					.setParameter("pstaffcode", staffcode)
					.uniqueResult();
			tx.commit();
			
			return personalInfo;
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
	
	public static Photo getPhoto(String staffCode){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			Photo photo = (Photo) session.createQuery(
					"SELECT photo " +
					"FROM Photo photo " +
					"WHERE photo.staffCode = :pstaffcode ")
					.setParameter("pstaffcode", staffCode)
					.uniqueResult();
			
			tx.commit();
			return photo;
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
	
	public static void saveLog(Log log){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(log);
			
			tx.commit();
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
	
	public static int getNextOrderCategory(){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			int max = (Integer) session.createQuery(
					"SELECT max(category.ordering) " +
					"FROM Category category " +
					"WHERE category.dataStatus = 'NORMAL' ")
					.uniqueResult();
			
			tx.commit();
			
			return max+1;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return 1;
		} finally {
			session.clear();
			session.close();
		}
	}
	
	public static int getNextOrderSubcategory(Category category){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			int max = (Integer) session.createQuery(
					"SELECT max(sub.ordering) " +
					"FROM SubCategory sub " +
					"WHERE sub.dataStatus = 'NORMAL' " +
					"AND sub.category = :pcategory ")
					.setParameter("pcategory", category)
					.uniqueResult();
			
			tx.commit();
			
			return max+1;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("catch error");
			return 1;
		} finally {
			session.clear();
			session.close();
		}
	}
	
	public static SubCategory getSubCategoryByName(String name, Category category){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			SubCategory sub = (SubCategory) session.createQuery(
					"SELECT sub " +
					"FROM SubCategory sub " +
					"WHERE sub.description = :pname " +
					"AND sub.dataStatus = 'NORMAL' " +
					"AND sub.category  = :pcategory ")
					.setParameter("pname", name)
					.setParameter("pcategory", category)
					.uniqueResult();
			
			tx.commit();
			return sub;
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
	
	public static Category getCategoryByName(String name){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			
			Category category = (Category) session.createQuery(
					"SELECT category " +
					"FROM Category category " +
					"WHERE category.name = :pname " +
					"AND category.dataStatus = 'NORMAL' ")
					.setParameter("pname", name)
					.uniqueResult();
			
			tx.commit();
			return category;
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

	
