import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
/**
 * EspressOS Mobile Phone Class.
 *
 *
 * EspressOSMobile
 * In this assignment you will be creating an EspressOS Mobile Phone as part of a simulation.
 * The Mobile phone includes several attributes unique to the phone and has simple functionality.
 * You are to complete 2 classes. EspressOSMobile and EspressOSContact
 *
 * The phone has data
 *  Information about the phone state. 
 *    If it is On/Off
 *    Battery level 
 *    If it is connected to network. 
 *    Signal strength when connected to network
 *  Information about the current owner saved as contact information. 
 *    First name
 *    Last name
 *    Phone number
 *  A list of 10 possible contacts.
 *    Each contact stores first name, last name, phone number and chat history up to 20 messages
 *  
 * The phone has functionality
 *  Turning on the phone
 *  Charging the phone. Increase battery level
 *  Change battery (set battery level)
 *  Use phone for k units of battery (decreases battery level by k)
 *  Search/add/remove contacts
 *
 * Attribute features
 *  if the phone is off. It is not connected. 
 *  if the phone is not connected there is no signal strength
 *  the attribute for battery life has valid range [0,100]. 0 is flat, 100 is full.
 *  the attribute for signal strength has a valid range [0, 5]. 0 is no signal, 5 is best signal.
 * 
 * Please implement the methods provided, as some of the marking is
 * making sure that these methods work as specified.
 *
 *
 */
public class EspressOSMobile 
{
	public static final int MAXIMUM_CONTACTS = 10;
	private boolean is_on;
	private Battery battery;
	//private int battery_lvl;
	private Antenna mobile_antenna;
	private boolean has_network;
	//private boolean has_signal;
	private int signal_strength;
	private String owner_first_name;
	private String owner_last_name;
	private String phone_number;
	private int contact_number;
	private int last_signal_strength;
	//private int counter;
	/* Use this to store contacts. Do not modify. */
	protected EspressOSContact[] contacts;
	private EspressOSContact owner;
	private List<App> installed_apps;
	private List<String> notifications;
	
	/* Every phone manufactured has the following attributes
	 * 
	 * the phone is off
	 * the phone has battery life 25
	 * the phone is not connected
	 * the phone has signal strength 0
	 * Each of the contacts stored in the array contacts has a null value
	 * 
	 * the owner first name "EspressOS"
	 * the owner last name is "Incorporated"
	 * the owner phone number is "180076237867"
	 * the owner chat message should have only one message 
	 *         "Thank you for choosing EspressOS products"
	 *
	 */
	public EspressOSMobile() {
		/* given */
		is_on = false;
		contact_number=0;
		//counter = 0;
		battery = new StandardBattery();
		mobile_antenna = new StandardAntenna();
		has_network = false;
		//has_signal = false;
		signal_strength = 0;
		last_signal_strength = signal_strength;
		owner_first_name = "EspressOS";
		owner_last_name = "Incorporated";
		phone_number = "180076237867";
		contacts = new EspressOSContact[MAXIMUM_CONTACTS];
		//contacts[0] = new EspressOSContact(owner_first_name, owner_last_name, phone_number);;
		//contact_number++;
		//battery_lvl = 0;
		//System.out.println(contacts[0].getPhoneNumber());
		//contacts[0].addChatMessage(owner_first_name, "Thank you for choosing EspressOS products");
		owner =  new EspressOSContact(owner_first_name, owner_last_name, phone_number);
		owner.addChatMessage(owner_first_name, "Thank you for choosing EspressOS products");
		installed_apps = new ArrayList<App>();
		notifications = new ArrayList<String>();

	}

	//-------------Contatct management methods start here------------------
	
	/* returns a copy of the owner contact details
	 * return null if the phone is off
	 */
	public EspressOSContact getCopyOfOwnerContact() {
		
		if (this.isPhoneOn()==false){
			return null;
		}
		return owner.copy();

	}


	/* only works if phone is on
	 * will add the contact in the array only if there is space and does not exist
	 * The method will find an element that is null and set it to be the contact
	 */
	public boolean addContact(EspressOSContact contact) {
		//System.out.println(contacts[0].getPhoneNumber());
		if (contact == null){
			return false;
		}
		if (this.isPhoneOn()){
			//int contact_num = 0;
			//boolean is_repeated = false;
			
			for (int i=0;i<MAXIMUM_CONTACTS;i++){
				//System.out.println(contacts[0].getPhoneNumber());
				
				if (contacts[i] == contact){
					return false;
				}
				if (contacts[i] == null){
					contacts[i] = contact;
					//contact_number ++ ;
					return true;
				}
				// if (contacts[i] == null){
				// 	break;
				// }
				//contact_num++;
				
			}
			// for (int i = 0;i<MAXIMUM_CONTACTS;i++){
			// 	System.out.println(contacts[i].getFirstName() + " " + contacts[i].getLastName() + " " + contacts[i].getPhoneNumber());
			// }
			//System.out.println(is_repeated + " " + contact_number);
			//System.out.println(contact_number + " " + is_repeated);
			// if (contact_number <= 9 && is_repeated == false){
			// 	contacts[contact_number] = contact;
			// 	contact_number++;
			// 	return true;
			// }
			//System.out.println("me");
			return false;
		}
		//System.out.println("You");
		return false; //?

	}

	/* only works if phone is on
	 * find the object and set the array element to null
 	 * return true on successful remove
	 */
	public boolean removeContact (EspressOSContact contact) {
		
		if (contact == null){
			return false;
		}
		if (this.isPhoneOn()){
			int counter = 0;
			for (EspressOSContact c:contacts){
			
				if (c==contact){
					
					contacts[counter] = null;
					//contact_number;
					return true;
					
				}
				
				counter++;
				
			}
			return false;
			
		}
		return false;

	}

	/* only works if phone is on
	 * return the number of contacts, or -1 if phone is off
	 */
	public int getNumberOfContacts() {
		//System.out.println(contacts[0].getPhoneNumber());
		if (this.isPhoneOn()){
			int number_of_contacts = 0;
			for (int i=0;i<contacts.length;i++){
				if (contacts[i] != null){
					number_of_contacts++;
				}
			}
			return number_of_contacts;
		}
		return -1;
	
	}

	/* only works if phone is on
	 * returns all contacts that match firstname OR lastname
	 * if phone is off, or no results, null is returned
	 */
	public EspressOSContact[] searchContact(String name) {
		
		if (name == null){
			return null;
		}
		if (this.isPhoneOn()){
			//System.out.println(contacts.length);
			
			EspressOSContact[] search_result = new EspressOSContact[contacts.length];
			int index = 0;
			for (EspressOSContact c:contacts){
				if (c == null){
					continue;
				}
				if (c.getFirstName().equals(name) || c.getLastName().equals(name)){

					search_result[index] = c;
					index++;

				}
			}
			
			//if no results
			if (search_result[0] == null) {
				return null;
			}
			//System.out.println(search_result.length);
			
			// for (int i=0;i<index;i++){
			// 	System.out.println(search_result[i].getFirstName() + " " + search_result[i].getLastName() +" " + search_result[i].getPhoneNumber());
			// }
			
			EspressOSContact[] final_result = new EspressOSContact[index];
			for (int i=0;i<index;i++){
				final_result[i] = search_result[i];
			}
			return final_result;
		}
		return null; //if the phone is off
		
		
	
	}
	//-------------Contatct management methods finish here------------------
	
	//-------------on and off methods start here------------------
	/* returns true if phone is on
	 */
	public boolean isPhoneOn() {
		
		return is_on;

	}

	/* when phone turns on, it costs 5 battery for startup. network is initially disconnected
	 * when phone turns off it costs 0 battery, network is disconnected
	 * always return true if turning off
	 * return false if do not have enough battery level to turn on
	 * return true otherwise
	 */
	 public boolean setPhoneOn(boolean on) {
		 //System.out.println(contacts[0].getPhoneNumber());
		 if (on == true && battery.getLevel()>=6 ){
			 is_on = on;
			 this.usePhone(5);
			 //this.disconnectNetwork();
			 //this.connectNetwork();
			 //this.setSignalStrength(0);
			 return true;
		 }
		 
		 if (on == false) { 
			this.disconnectNetwork();
			//this.setSignalStrength(0);
		 	is_on = on;
			return true;
		 }
		 return false;

	}
	//-------------on and off methods finish here------------------
	
	
	
	//-------------battery methods start here------------------
	/* Return the battery life level. if the phone is off, zero is returned.
	 */
	public int getBatteryLife() {
		
		if (this.isPhoneOn()){
			return battery.getLevel();
		}
		return 0;

	}
	
	/* Change battery of phone.
	 * On success. The phone is off and new battery level adjusted and returns true
	 * If newBatteryLevel is outside manufacturer specification of [0,100], then 
	 * no changes occur and returns false.
	 */
	public boolean changeBattery(Battery battery) {
		
		if (battery.getLevel()<0 || battery.getLevel()>100){
			return false;
		}
		this.battery.setLevel(battery.getLevel());
		this.battery = battery;
		this.setPhoneOn(false);
		return true;
		

	}
	
	/* only works if phone is on. 
	 * returns true if the phone is connected to the network
	 */
	
	
	
	//-------------network connection and antenna methods start here------------------
	public boolean isConnectedNetwork() {
		if (this.isPhoneOn()){
			return mobile_antenna.isConnected();
		}
		return false;

 	}
	
	/* only works if phone is on. 
	 * when disconnecting, the signal strength becomes zero
	 */
 	public void disconnectNetwork() {
		
		if(this.isPhoneOn()){
			//this.has_network = false;
			//antenna.setNetwork(false);
			this.setSignalStrength(0);
		}

 	}
	
	/* only works if phone is on. 
	 * Connect to network
	 * if already connected do nothing
	 * if connecting: 
	 *  1) signal strength is set to 1 if it was 0
	 *  2) signal strength will be the previous value if it is not zero
	 *  3) it will cost 2 battery life to do so
	 * returns the network connected status
	 */
 	public boolean connectNetwork() {
		if (this.isPhoneOn()){
			if (this.isConnectedNetwork()){
				return true;
			}
			if (last_signal_strength == 0 ){
				this.usePhone(2);
				if (this.isPhoneOn() == false){
					return false;
				}
				this.setSignalStrength(1);
				return true;
			}
			else{
				this.usePhone(2);
				if (this.isPhoneOn() == false){
					return false;
				}
				this.setSignalStrength(last_signal_strength);
				return true;
			}
			
		}
		return false;
 	}
	
	/* only works if phone is on. 
	 * returns a value in range [1,5] if connected to network
	 * otherwise returns 0
	 */
 	public int getSignalStrength() {
		
		if (this.isPhoneOn()){
			if(this.isConnectedNetwork()){
				return mobile_antenna.getSignalStrength();
			}
			return 0;
		}
		return 0;

 	}

	/* only works if phone is on. 
	 * sets the signal strength and may change the network connection status to on or off
	 * signal of 0 disconnects network
	 * signal [1,5] can connect to network if not already connected
	 * if the signal is set outside the range [0,5], nothing will occur and will return false
	 */
 	public boolean setSignalStrength(int x) {
		//int last_signal;
		if (this.isPhoneOn()){
			if (x>=0 && x<=5){
				if (x==0){
					last_signal_strength = this.getSignalStrength();
					mobile_antenna.setNetwork(false);
				}
				this.mobile_antenna.setSignalStrength(x);
				return true;
			}
			return false;
			
		}
		return false;

     }
	
	/* changes the antenna object
	 * signal strength is set to default and is not connected to a network
	 * if this constraint is violated then the antenna should not be changed.
	 * return true if antenna is changed.
	 */
 	public boolean changeAntenna(Antenna antenna) {
		if (this.isPhoneOn() && antenna.getSignalStrength()>=0 && antenna.getSignalStrength()<=5){
			if (this.isConnectedNetwork() == false && antenna.getSignalStrength() > 0){
				this.connectNetwork();
				this.setSignalStrength(antenna.getSignalStrength());
				this.mobile_antenna = antenna;
				return true;
			}
			if (this.isConnectedNetwork() && antenna.getSignalStrength() == 0){
				this.disconnectNetwork();
				last_signal_strength = 0;
				this.mobile_antenna = antenna;
				return true;
			}
			if (this.isConnectedNetwork() && antenna.getSignalStrength() > 0){
				this.setSignalStrength(antenna.getSignalStrength());
				this.mobile_antenna = antenna;
				return true;
			}
		}
		return false;
 	}
	//-------------network connection and antenna methods finish here------------------
	
	
	
	//rest of battery methods
	/* each charge increases battery by 10
	 * the phone has overcharge protection and cannot exceed 100
	 * returns true if the phone was charged by 10
	 */
	public boolean chargePhone() {
		if (this.battery.getLevel() == 100){
			return false;
		}
		if (100 - this.battery.getLevel()<=10){
			this.battery.setLevel(100);
			return true;
			
		}
		this.battery.setLevel(this.battery.getLevel()+10);
		if (this.getBatteryLife() == 0){
			this.setPhoneOn(true);
		}
		//this.battery.setLevel(this.battery.getLevel()+10);
		return true;

	}
	
	/* Use the phone which costs k units of battery life.
	 * if the activity exceeds the battery life, the battery automatically 
	 * becomes zero and the phone turns off.
	 */
	public void usePhone(int k) {
		
		if (this.battery.getLevel() - k <= 0){
			this.battery.setLevel(0);
			this.setPhoneOn(false);
		}
		else{
			this.battery.setLevel(this.battery.getLevel()-k);
		}

	}
	
	//--------------app features-----------------
	
	// if the given app is not null and does not already exist, it will install it
 	public boolean install(App new_app){
 		if (new_app == null || this.isPhoneOn() == false){
 			return false;
 		}
 		for (App a:installed_apps){
 			if (a == new_app){
 				return false;
 			}
 		}
		
		installed_apps.add(new_app);
 		return true;
 	}
	
	//if the given name is not null and it is installed, it will remove it
 	public boolean uninstall(String name){
 		if (name == null || this.isPhoneOn() == false){
 			return false;
 		}
		
 		for (int i=0;i<installed_apps.size();i++){
 			if (installed_apps.get(i).getName().equals(name)){
 				installed_apps.remove(i);
 				return true;
 			}
 		}
 		return false;
 	}
	
	//returns the list of installed apps
 	public List<App> getInstalledApps(){
		if (this.isPhoneOn() == false){
			return null;
		}
 		return this.installed_apps;
 	}
	
	//returns the list of background apps
	public List<App> getBackgroundApps(){
		if (this.isPhoneOn() == false){
			return null;
		}
		List<App> background_apps = new ArrayList<App> ();
		for (App a:installed_apps){
			if (a.IsBackground()){
				background_apps.add(a);
			}
		}
		return background_apps;
	}
	
	//returns the list of notifying apps
	public List<App> getNotificationApp(){
		if (this.isPhoneOn() == false){
			return null;
		}
		List<App> notification_apps = new ArrayList<App> ();
		for (App a:installed_apps){
			if (a.HasNotification()){
				notification_apps.add(a);
			}
		}
		return notification_apps;
	}
	
	//recievs the notifications and add the to the relevant list
	public void receiveNotifications(String notification){
		if (this.isPhoneOn() == false){
			return;
		}
		notifications.add(notification);
	}
	
	//returns the notifications list
	public List<String> getNotifications(){
		if (this.isPhoneOn() == false){
			return null;
		}
		return this.notifications;
	}
	
	//returns the list of running apps
	public List<App> getRunningApps(){
		if (this.isPhoneOn() == false){
			return null;
		}
		List<App> running_apps = new ArrayList<App> ();
		for (App a:installed_apps){
			if (a.isRunning()){
				running_apps.add(a);
			}
		}
		return running_apps;
	} 
	
	//closes the app with the given name (if it exists)
	public boolean close(String name){
		if (name == null || this.isPhoneOn() == false){
			return false;
		}
		for (App a:installed_apps){
			if (a.getName().equals(name)){
				a.exit();
				return true;
			}
		}
		return false;
	}
	
	//starts the app with the given name
	//this was not in the specifications but was discussed on ed. Since only one notification app can run at one time, the method checks that first before calling the start method on the app with the given name
 	public boolean run (String name){
		if (name == null || this.isPhoneOn() == false){
			return false;
		}
 		for (App a:installed_apps){
 			if (a.getName().equals(name)){ //if there is an installed app with the given name
				if (a.IsBackground() == false){ //if it is not a background app, we have to check if there are not any non-background apps running
					List<App> running_apps = this.getRunningApps(); //get the list of all running apps
					for (App running:running_apps){
						if (running.IsBackground() == false){//if there is a non-background running app, do nothing and return false.
							return false;
						}
					}
					a.start();//if there are no non-background apps, start the app.
					return true;
				}
				else{//if the found app is background, it can start straightaway.
					a.start();
					return true;
				}
 				
 			}
 		}
 		return false;
	}
	
}
