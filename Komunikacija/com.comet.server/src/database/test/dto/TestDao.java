package database.test.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import database.dao.*;
import database.dto.*;

public class TestDao {

	public static void main(String[] args) {

		UserDao ud = new UserDao();
		DocumentDao dd = new DocumentDao();
		DocumentVersionDao dvd = new DocumentVersionDao();
		WorksOnDao wd = new WorksOnDao();

		//Add user
		//User u = new User("P1","asdf1","P@gmail.com","http://ppicture.jpg");
		//ud.addUser(u);
		
		//Delete user
		//ud.deleteUser(10);
		
		//Update user
//		User u1 = ud.getUserById(4);
//		u1.setUsername("Pera1");
//		u1.setPassword("asdf2");
//		u1.setEmail("Pera@gmail.com");
//		u1.setImage("http://peraPicture.jpg");
//		ud.updateUser(u1);

		//Get documents user works on
//		ArrayList<Document> docs = ud.getAllDocuments(4);
//		for(Document d : docs) {
//			System.out.println(d.getFilename()+" "+d.getProgramLaunguage());
//		}
		
		//Get WorksOn objects where beside document there are privileges
//		ArrayList<WorksOn> workings = ud.getAllWorksOn(4);
//		for(WorksOn w : workings) {
//			System.out.println(w.getDocument().getProgramLaunguage());
//		}

		
		//========================== DOCUMENT ================================
		
		
		//Add document
//		Document d = new Document("Lisp");
//		dd.addDocument(d);
		
		//Delete document
//		dd.deleteDocument(2);
		
		//Update document
//		Document d = dd.getDocumentById(8);
//		d.setProgramLaunguage("PHP");
//		dd.updateDocument(d);
		
		//Get document an all its versions
//		Document d = dd.getDocumentById(3);							//TODO: Promeniti! Dodati u Document f-ju koja vraca sve njegove verzije
//		for(DocumentVersion v : d.getVersions())
//			System.out.println(v.getContent());
		
		//Get last version of document given by id
//		DocumentVersion v = dd.getDocumentLastVersion(3);
//		System.out.println(v.getContent());
		
		//Print username of all Coloborators on document given by id
//		ArrayList<WorksOn> wList = dd.getUsersWorksOnDocument(5);
//		for(WorksOn w : wList) {
//			System.out.println(w.getUser().getUsername());
//		}
		
		
		//======================== DOCUMENT VERSION ======================
		
		//GET Document Version by id
//		DocumentVersion v = dvd.getDocumentVersionById(2);
//		System.out.println(v.getContent());
		
		//ADD new version of document saved in current time
//		Document doc = dd.getDocumentById(3);
//		DocumentVersion v = new DocumentVersion(doc,"yokphgnlmkhtrpt40952345",new Date());
//		dvd.addDocumentVersion(v);
		
		//GET Document Version specified by date and time
//		Document doc = dd.getDocumentById(3);
//		String dat = "2016-10-23 18:06:54";
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Date dateTime=null;
//		try {
//			dateTime = df.parse(dat);
//		} catch (ParseException e) {
//			System.out.println("Parse Exception!");
//			e.printStackTrace();
//		}
//		DocumentVersion v = dvd.getDocumentVersionByDate(dateTime, doc);
//		System.out.println(v.getContent());
		
		//DELETE Document version
//		dvd.deleteDocumentVersion(1);
		
		//UPDATE Document version (append new text)
//		DocumentVersion v = dvd.getDocumentVersionById(4);
//		v.setContent(v.getContent().concat("DODATO"));
//		dvd.updateDocumentVersion(v);

//		User usr = ud.getUserById(3);
//		Document doc = dd.getDocumentById(3);
//		ArrayList<DocumentVersion> vers = dd.getDocumentVersionsByUser(usr, doc);
//		for(DocumentVersion dv : vers) {
//			System.out.println(dv.getContent());
//		}
		//======================== WORKS_ON =========================
		
		//ili cuvati prvo d i u pa onda w
		//ili odvoji f-je: ako su novi objekt d i u onda persist, a ako su postojeci onda save
//		Document d = dd.getDocumentById(3);
//		User u = ud.getUserById(3);
//		WorksOn w = new WorksOn(d,u,"RW");
//		wd.addWorksOn(w);
		
//		Document d = new Document("C#");
//		User u = new User("kika","K234","kika@hotmail.com","http://kikaSlika.jpg");
//		WorksOn w = new WorksOn(d,u,"R");
//		wd.addWorksOn(w);
	}

}
