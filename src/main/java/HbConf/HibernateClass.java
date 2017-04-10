package HbConf;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateClass {

	private static SessionFactory sessionFactory = null;

	public static SessionFactory getInstance() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
		}
		return sessionFactory;
	}
}