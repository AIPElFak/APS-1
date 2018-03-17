package database.test.dto;

import java.util.Date;

import org.hibernate.Session;

import database.dto.*;

public class Test {

	public static void main(String[] args)
	{
//		User user = new User("pera12","1234","pera@gmail.com","http://slika.jpg");
//		User user2 = new User("mika","4321","mika@gmail.com","http://image.jpg");
//		
//		String text = "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf"
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf "
//				+ "asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf asdfzxc vqewr poif;gkhmnfghofgijhf ";
//		
//		Document doc = new Document("JavaScript");
//		DocumentVersion v1 = new DocumentVersion(doc,text,new Date());
//		//DocumentVersion v2 = new DocumentVersion(doc,"zxcvzxcvzxcv",new Date());
//		doc.getVersion().add(v1);
//		//doc.getVersion().add(v2);
//
//		System.out.println("Pre cuvanja(samo tekst): "+text.length());
//		System.out.println("Pre cuvanja: "+v1.getContent().length());
//		User u1 = new User("Mika","asdf123","mika@hotmail.com","http://slika12.jpg");
//		Document d1 = new Document("C++");
//		WorksOn w1 = new WorksOn();
//		w1.setDocument(d1);
//		w1.setUser(u1);
//		w1.setPrivilege("owner");
//		
//		Session s = HibernateUtil.getSessionFactory().openSession();
//		s.beginTransaction();

//		Document d = new Document("Python");
//		User u2 = new User("Tika2","qwery123","tika@gmial.com","http://img3.jpg");
//		WorksOn w = new WorksOn();
//		w.setDocument(d);
//		w.setUser(u2);
//		w.setPrivilege("RW");
//		s.persist(w);
		//WorksOn w = s.load(WorksOn.class, 4);
		
		
		
		/*Document d = s.load(Document.class, 3);

		System.out.println("Jezik: "+d.getProgramLaunguage());

		for(DocumentVersion v : d.getVersions()) {
			System.out.println("Sadrzaj: "+v.getContent());
		}*/
		
		//User usr = s.load(User.class, 2);
//		User usr = s.get(User.class, 222);
//		if(usr != null)
//			System.out.println("Username: "+usr.getUsername());
//		else
//			System.out.println("Ne postoji ");
//		for(WorksOn v : usr.getDocuments()) {
//			System.out.println("Sadrzaj: "+v.getDocument().getProgramLaunguage());
//		}
		//s.delete(d);
		//s.save(user);
		//s.save(doc);
		//s.save(v1);
		
		
//		s.getTransaction().commit();
//		s.close();
		
	}
}
