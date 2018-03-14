package database.test.dto;

import java.util.ArrayList;

import database.dao.DocumentDao;
import database.dao.DocumentVersionDao;
import database.dao.JoinRequestDao;
import database.dao.UserDao;
import database.dao.WorksOnDao;
import database.dto.Document;
import database.dto.DocumentVersion;
import database.dto.JoinRequest;
import database.dto.User;
import database.dto.WorksOn;

public class TestRepository {

	public static void main(String[] args) {

		UserDao ud = new UserDao();
		DocumentDao dd = new DocumentDao();
		DocumentVersionDao dvd = new DocumentVersionDao();
		WorksOnDao wd = new WorksOnDao();
		JoinRequestDao jd = new JoinRequestDao();
		
//		User u = new User("P1","asdf1","P@gmail.com","http://ppicture.jpg");
//		ud.add(u);
//		System.out.println("Dodato.");
		
//		User u2 = ud.getById(3);
//		System.out.println(ud.getClassType());
//		System.out.println(u2.getEmail());
		
//		User u3 = ud.getById(12);
//		Document doc1 = new Document("Java","Preduzece");
//		dd.add(doc1);
//		WorksOn wo1 = new WorksOn(doc1,u3,WorksOn.Privilege.Owner);
//		wd.add(wo1);


//		User u4 = ud.getById(3);
//		Document doc2 = dd.getById(14);
//		JoinRequest jr = new JoinRequest(u4,doc2);
//		jd.add(jr);jd.add(jr);
		
//		JoinRequest jr2 = jd.getById(1);
//		jd.delete(jr2);
		
//		ArrayList<JoinRequest> list = jd.getAll(3);
//		for(JoinRequest j : list)
//			System.out.println(j.getRequestTime());
		

//		User u5 = ud.getById(3);
//		ArrayList<JoinRequest> list = wd.getRequestsForOwnerDocuments(u5);
//		for(JoinRequest joinr : list) {
//			System.out.println(joinr.getRequestTime());
//		}
		

//		Document doc3 = dd.getById(3);
//		ArrayList<DocumentVersion>  vers = dvd.getAllDocumentVersions(doc3);
//		for(DocumentVersion v : vers) {
//			System.out.println(v.getContent());
//		}
//		DocumentVersion dver1 = dvd.getDocumentLastVersion(doc3);
//		System.out.println(dver1.getContent());
		
		
//		User novi = new User("NewUser2","NewPass","newEmail@gmail.com","http://ImgurImage/323.jpg");
//		Info i = ud.register(novi);
//		System.out.println(i.getMessage());
		

		Document doc3 = dd.getById(3);
		ArrayList<WorksOn> wo = dd.getAllWorksOnDocument(doc3.getId());
		for(WorksOn w: wo) {
			System.out.println(w.getUser().getUsername());
		}
	}
}
