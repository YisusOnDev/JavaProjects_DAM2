package main;

import java.util.ArrayList;

import org.hibernate.Session;

public class MainApp {
	
	public static void main(String[] args) {
		HibernateUtil.buildSessionFactory();
		
		try {
			 HibernateUtil.openSessionAndBindToThread();
			 Session s = HibernateUtil.getSessionFactory().getCurrentSession();

				ArrayList<Student> oxfordStudents = new ArrayList<Student>();
				
				University u1 = new University();
				u1.setId(1);
				u1.setName("Oxford");
				
				Student st1 = new Student();
				st1.setName("Johnny");
				
				Student st2 = new Student();
				st2.setName("Toto");
				
				Student st3 = new Student();
				st3.setName("Juan");
				
				Student st4 = new Student();
				st4.setName("Lidia");
				
				oxfordStudents.add(st1);
				oxfordStudents.add(st2);
				oxfordStudents.add(st3);
				oxfordStudents.add(st4);
				
				u1.setStudents(oxfordStudents);
				
				for (Student student : oxfordStudents) {
					student.setUniversity(u1);
				}
				
				s.save(u1);
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		
		HibernateUtil.closeSessionFactory();
		
		
	}
}
