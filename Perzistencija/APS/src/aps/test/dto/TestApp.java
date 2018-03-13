//package aps.test.dto;
//
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Scanner;
//import aps.dao.*;
//import aps.dto.*;
//
//public class TestApp {
//
//	public static void main(String[] args) {
//
//		Scanner scan = new Scanner(System.in);
//		UserDao ud = new UserDao();
//		DocumentDao dd = new DocumentDao();
//		WorksOnDao wod = new WorksOnDao();
//		
//		boolean prijavljen = false;
//		User user = null;
//
//		System.out.println("1) Login\n2) Register"); 
//		int lr = scan.nextInt();
//		if(lr == 1) {
//			while(!prijavljen) {
//				System.out.print("Unesite username i password: ");
//				String username = scan.next();
//				String password = scan.next();
//				
//				user = ud.login(username, password);
//				if(user != null) {
//					System.out.println("Dobrodosli "+user.getUsername());
//					prijavljen = true;
//				}
//				else 
//					System.out.println("Uneli ste nevalidne podatke, pokusajte ponovo");
//			}
//			
//			System.out.println("Vasi fajlovi na kojima radite:");
//			ArrayList<Document> docs = ud.getAllDocuments(user.getId());
//			for(Document document : docs) {
//				System.out.println(document.getId()+" "+document.getFilename()+" "+document.getProgramLaunguage());
//			}
//			
//			boolean kraj = false;
//			while(!kraj) {
//				System.out.println("\n1) Izaberi dokument\n2) Kreiraj novi dokument\n3) Prikljuci se dokumentu\n4) Prikazi moje aktivne fajlove\n5) Kraj");
//				int d = scan.nextInt();
//				//Chose document
//				if(d == 1) {
//					System.out.print("Unesite id dokumenta:");
//					int docId = scan.nextInt();
//					System.out.println("1)Prikazi sve verzije dokumenta\n2) Edituj dokument ");
//					int u = scan.nextInt();
//					if(u == 1) {
//						ArrayList<DocumentVersion> vers = dd.getAllDocumentVersions(docId);
//						for(DocumentVersion v : vers) 
//							System.out.println(v.getDocument().getFilename()+" "+v.getDateTime());
//					}
//					else if(u == 2) {
//						DocumentVersion v = dd.getDocumentLastVersion(docId);
//						System.out.println("Sadrzaj dokumenta:\n"+v.getContent());
//						String dodato = scan.nextLine();		//preskace jednu naredbu...(bug)
//						dodato = scan.nextLine();
//						v.setContent(v.getContent()+dodato);
//						v.setDateTime(new Date());
//						dd.addDocumentVersion(v);
//						System.out.println("Dodato u bazu+");
//					}
//				}
//				//Creating document
//				else if (d == 2) {
//					Document document = new Document();
//					System.out.print("Naziv dokumenta: ");document.setFilename(scan.next());
//					System.out.print("Programski jezik: ");document.setProgramLaunguage(scan.next());
//					wod.addWorksOnExistingUser(user.getId(), document, "OWNER");
//				}
//				//Joining on document
//				else if(d == 3) {
//					ArrayList<Document> allDocs = dd.getAllDocuments();
//					for(Document doc : allDocs) {
//						System.out.println(doc.getId()+" "+doc.getFilename()+" "+doc.getProgramLaunguage());
//					}
//					System.out.print("Izaberite dokument kome se prikljucujete: ");
//					int docId = scan.nextInt();
//					wod.addWorksOnAllExisting(docId, user.getId(), "R");
//				}
//				//Active files
//				else if(d==4) {
//					System.out.println("Vasi fajlovi na kojima radite:");
//					docs = ud.getAllDocuments(user.getId());
//					for(Document document : docs) 
//						System.out.println(document.getId()+" "+document.getFilename()+" "+document.getProgramLaunguage());
//				}
//				//End
//				else if(d == 5){
//					kraj = true;
//				}
//				else {
//					System.out.print("Pogresan unos.");
//				}
//			}
//		}else if (lr == 2) {
//			boolean end = false;
//			while(!end) {
//				System.out.print("Email: ");	String email = scan.next();
//				System.out.print("Username: ");	String username = scan.next();
//				System.out.print("Password: ");	String password = scan.next();
//				User u = new User(username, password, email, "http://default.jpg");
//				Info i = ud.addUser(u);
//				if(i.isSuccessful()) {
//					System.out.println("Sucessfully registered!");
//					end =true;
//				}else
//					System.out.println(i.getMessage());
//			}
//		}
//	
//		scan.close();	
//	}
//
//}
