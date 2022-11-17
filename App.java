/*
1. App is an abstract class while notify and background are interfaces (as they are behaviuors). Each app needs to have a separate thread
 	therefore, we need the App class to implement Runnable
2. In order to distinguish between notification apps and background apps and the methods related to each other, whenever an App
	object is being constructed, the deleveloper has to specify whether it is background app, a notify app, or both.
3. I know it can be challenging to understand and mark someone else's code and logic, so, please check the comments above each method if you are unsure what they do :)
*/

import java.util.List;
import java.util.ArrayList;
public class App implements notify, Runnable,background{
	
	private String name; //name of the app, it acts like a modifier
	private boolean has_notification; //does the app sends notifications to the operating system?
	private boolean is_background; //is it a background app?
	private boolean running; //specifies whether the app is running or not
	private EspressOSMobile operating_system; //specifies the os with which the app is communicating
	private BackgroundThread app_background_thread;//this will be initialised only if it is a background app
	private Thread app_thread;//only initialised if it is not a background app
	private List<Object> app_data; //the data that the app wishes to send to the operating system
	
	public App(String name, boolean has_notification,boolean is_background, EspressOSMobile os){
		this.name = name;
		this.has_notification = has_notification;
		this.is_background = is_background;
		operating_system = os;
		app_data = new ArrayList<Object>();
		
		if (is_background){
			app_background_thread = new BackgroundThread(this);
		}
		else{
			app_thread = new Thread(this);
		}
	}
	
	//returns the name of the app
	public String getName(){
		return this.name;
	}
	
	//if the app IS a notification app, it will send the default notification to the operating system, the receiveNotification method is in charge of receiving and collecting notifications in the operating system.
	//but if it is not a notify app, it will produce a runtime error.
	public void notifyOS(){
		if (has_notification){
			if (this.isRunning()){
				operating_system.receiveNotifications(this.name + ": "+ "this is a random notification");
			}
			else{
				throw new notRunningError();
			}
		}
		else{
			throw new doesNotNotifyException();
		}
	}
	
	//same as the previuos method, excvept that it produces a customised notification.
	public void notifyOS(String msg){
		if (has_notification){
			if (this.isRunning()){
				operating_system.receiveNotifications(this.name + ": " + msg);
			}
			else{
				throw new notRunningError();
			}
		}
		else{
			throw new doesNotNotifyException();
		}
	}
	
	//this method sends data that the app wishes to communicate to the app_data list
	public void sendData(Object obj){
		app_data.add(obj);
	}
	//searches for the Object that is requested (obj) and returns it if is in the app_data list.
	//if the app is not background app, the method won't work on it and will throw a runtime error called isNotBackrgoundApp().
	//Note: The specifications about getData were quite ambiguous for me. I desgined my sendData and getData according to the instrucations given on ed posts, but I am not still sure if this is how they are supposed to work.
	public Object getData(Object obj){
		if (this.IsBackground()){
			for (Object o:app_data){
				if (o == obj){
					return o;
				}
			}
			return null;
		}
		else{
			throw new isNotBackgroundApp();
		}
	}
	
	//returns whether the app is a backround app or not
	public boolean IsBackground(){
		return this.is_background;
	}
	
	//returns whether the app is a notification app or not
	public boolean HasNotification(){
		return this.has_notification;
	}
	
	//returns whether the app is running or not
	public boolean isRunning(){
		return this.running;
	}
	
	public void backgroundStart(){
		app_background_thread = new BackgroundThread(this);
		this.app_background_thread.start();
	}
	//if the app is a background app, it will start the background thread associated to it
	//if it is not a background app, it will start the app_thread
	//the App's running attribute will become true
	public void start(){
		if (this.IsBackground()){
			if (this.running == false){
				this.run();
				this.running = true;
			
			}
			
		}
		else{
			this.running = true;
			app_thread.start();
		}
	}
	
	//same logic as above, except that it does the opposite
	public void exit(){
		if (this.IsBackground()){
			this.running = false;
			app_background_thread.exit();
		}
		else{
			this.running = false;
			app_thread.interrupt();
		}
	}
	
	//I had to override this method as the App object implements Runnable
	public void run(){
		if (this.IsBackground()){
			this.app_background_thread.run();
		}
	}
	
	
}
