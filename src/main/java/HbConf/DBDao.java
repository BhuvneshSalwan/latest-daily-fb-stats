package HbConf;

import org.hibernate.Session;

public class DBDao {

	private static DBDao dao;

	public static DBDao getDao() {
		if (dao == null) {
			dao = new DBDao();
		}
		return dao;
	}

	public void saveOrUpdate(Object obj) {
		Session session = null;
		try {
			session = HibernateClass.getInstance().openSession();
			session.beginTransaction();
			session.saveOrUpdate(obj);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
