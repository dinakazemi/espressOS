//this 


import org.junit.*;
import static org.junit.Assert.*;

public class EspressOSContactTest{
	
	 EspressOSContact c1;
	 EspressOSContact c2;
	 EspressOSContact c3;
	 EspressOSContact c4;
	 EspressOSContact c5;
	 EspressOSContact c6;
	 EspressOSContact c7;
	 EspressOSContact c8;
	 EspressOSContact c9;
	 EspressOSContact c10;
	 EspressOSContact c11;
	 EspressOSMobile my_mobile;
		
	@Before 
	public void setUp(){
		c1 = new EspressOSContact("Jake","Martin","48958956");
		c2 = new EspressOSContact("Susan","gulberg","5986958");
		c3 = new EspressOSContact("Ryan","williams","98347231");
		c4 = new EspressOSContact("Robert","Rinnois","0421231430");
		c5 = new EspressOSContact("Ben","Rickman","768943733");
		c6 = new EspressOSContact("Amy","papadopolus","0932413454");
		c7 = new EspressOSContact("Mark","Hardman","495823847");
		c8 = new EspressOSContact("Daniella","Sirk","3493123");
		c9 = new EspressOSContact("Adrien","Atkins","85738535");
		c10 = new EspressOSContact("Joe","Jills","39482234");
		c11 = new EspressOSContact("May","Andrews","234934");
		my_mobile = new EspressOSMobile();
		my_mobile.setPhoneOn(true);
	}
	
	@Test
	public void getCopyOfOwnerContactTest(){
		this.contactSetUp();
		assertTrue(my_mobile.getCopyOfOwnerContact().getFirstName().equals("EspressOS"));
		assertTrue(my_mobile.getCopyOfOwnerContact().getLastName().equals("Incorporated"));
		assertTrue(my_mobile.getCopyOfOwnerContact().getPhoneNumber().equals("180076237867"));
	}
	
	public void contactSetUp(){
		my_mobile.addContact(c1);
		my_mobile.addContact(c2);
		my_mobile.addContact(c3);
		my_mobile.addContact(c4);
		my_mobile.addContact(c5);
		my_mobile.addContact(c6);
		my_mobile.addContact(c7);
		my_mobile.addContact(c8);
		my_mobile.addContact(c9);
		my_mobile.addContact(c10);
	}
	
	@Test
	public void addContactTest(){
		
		this.contactSetUp();
		assertFalse(my_mobile.addContact(c1));
		assertFalse(my_mobile.addContact(c11));
		
	}
	
	public static void main(String[] args){
		// EspressOSMobile mo = new EspressOSMobile();        
		// mo.setPhoneOn(true);
		// EspressOSContact test = new EspressOSContact("dean","dean","23132132");
		// mo.addContact(test);
		// mo.addContact(test);
		// mo.addContact(new EspressOSContact("dean","dean","23132132"));
		// mo.addContact(new EspressOSContact("dean","dean","23132132"));
		// mo.addContact(new EspressOSContact("leo","leo1","23132132"));
		// mo.addContact(new EspressOSContact("leo2","leo","23132132"));
		// mo.addContact(new EspressOSContact("leo3","leo4","23132132"));
		// mo.addContact(new EspressOSContact("leo5","leo4","23132132"));
		// mo.addContact(new EspressOSContact("leo3","leo10","23132132"));
		// mo.addContact(new EspressOSContact("leo","leo","23132132"));
		// mo.addContact(new EspressOSContact("leoleo","leoleo","23132132"));
		// mo.searchContact("dean");
		// System.out.println("---------");
		// mo.searchContact("leo");
		// System.out.println("---------");
		// mo.searchContact(null);
		// System.out.println("---------");
		// mo.searchContact("leo3");
		// System.out.println("---------");
		// mo.searchContact("leo10");
// 		 EspressOSMobile my_mobile = new EspressOSMobile();
// 		 my_mobile.setPhoneOn(true);
		
// 		 EspressOSContact c1 =  new EspressOSContact("Jake","Martin","48958956");
// 		 EspressOSContact c2 = new EspressOSContact("Susan","gulberg","5986958");
// 		 EspressOSContact c3 = new EspressOSContact("Ryan","williams","98347231");
// 		 EspressOSContact c4 = new EspressOSContact("Robert","Rinnois","0421231430");
// 		 EspressOSContact c5 = new EspressOSContact("Ben","Rickman","768943733");
// 		 EspressOSContact c6 = new EspressOSContact("Amy","papadopolus","0932413454");
// 		 EspressOSContact c7 = new EspressOSContact("Daniella","Atkins","3493123");
// 		 EspressOSContact c8 = new EspressOSContact("Joe","Atkins","85738535");
// 		 EspressOSContact c9 = new EspressOSContact("Joe","Jills","39482234");
// 		 EspressOSContact c10 = new EspressOSContact("May","Andrews","234934");
// 		 EspressOSContact c11 = new EspressOSContact("Mark","Hardman","495823847");
		
// 		 my_mobile.addContact(c1);
// 		 my_mobile.addContact(c2);
// 		 my_mobile.addContact(c3);
// 		 my_mobile.addContact(c4);
// 		 my_mobile.addContact(c5);
// 		 my_mobile.addContact(c6);
// 		 my_mobile.addContact(c7);
// 		 my_mobile.addContact(c8);
// 		 my_mobile.addContact(c9);
// 		 my_mobile.addContact(c10);
// 		 my_mobile.addContact(c11);
		
// 		EspressOSContact[] result;
// 		result = my_mobile.searchContact("Atkins");
// 		System.out.println(result.length);
// 		for (int i=0;i<result.length;i++){
// 			System.out.println(result[i].getFirstName());
// 			System.out.println(result[i].getLastName());
// 			System.out.println(result[i].getPhoneNumber());
// 		}
	}
	// c.updateFirstName(null);
	// c.updateLastName("K");
	// c.updatePhoneNumber(null);
	// System.out.println(c.getFirstName());
	// System.out.println(c.getLastName());
	// System.out.println(c.getPhoneNumber());
	// System.out.println();
	// c.addChatMessage("Dina","hello, where are u?");
	// c.addChatMessage("David","outside");
	// c.addChatMessage("Dina","saw u");
	// c.addChatMessage("David","where should we go?");
	// c.addChatMessage("Dina","hi");
	// c.addChatMessage("Dina","bye");
	// c.addChatMessage("Dina","a");
	// c.addChatMessage("Dina","b");
	// c.addChatMessage("Dina","c");
	// c.addChatMessage("Dina","d");
	// c.addChatMessage("Dina","w");
	// c.addChatMessage("Dan","djhdjfhkjh");
	// c.addChatMessage("gloria","qrewqr");
	// c.addChatMessage("Sam","a");
	// c.addChatMessage("Dina","q");
	// c.addChatMessage("Dina","r");
	// c.addChatMessage("gloria","qrewqr");
	// c.addChatMessage("gloria","qrewqr");
	// c.addChatMessage("gloria","qrewqr");
	// c.addChatMessage("gloria","qrewqr");
	// c.addChatMessage("gloria","qrewqr");
	// System.out.println(c.getOldestMessage());
	// System.out.println();
	// c.printMessagesOldestToNewest();
	}

