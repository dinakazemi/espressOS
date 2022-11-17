//this file tests my app features.
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.rules.ExpectedException;
public class testAppFeatures{
	EspressOSMobile my_mobile;
	App app_dual_feature_1;
	App app_dual_feature_2;
	App background_app_1;
	App background_app_2;
	App notify_app_1;
	App notify_app_2;
	App not_background_notify;
	
	@Before
	public void SetUp(){
		my_mobile = new EspressOSMobile();
		my_mobile.setPhoneOn(true);
		app_dual_feature_1 = new App("Find Friends", true, true,my_mobile);
		app_dual_feature_2 = new App("Telegram",true,true,my_mobile);
		background_app_1 = new App("Photos",false,true,my_mobile);
		background_app_2 = new App("weather",false,true,my_mobile);
		notify_app_1 = new App("Outlook",true,false,my_mobile);
		notify_app_2 = new App("Emiraites Mobile App",true,false,my_mobile);
		not_background_notify = new App("Duolingo",false,false,my_mobile);
	}
	
	@Test
	//tests the initial conditions for methods in EspressOSMobile related to app features to check pre-conditions
	public void mobileInitialConditionsTest(){
		assertTrue(my_mobile.getInstalledApps().size()==0);
		assertTrue(my_mobile.getBackgroundApps().size() == 0);
		assertTrue(my_mobile.getNotificationApp().size() == 0);
		assertTrue(my_mobile.getNotifications().size() == 0);
		assertTrue(my_mobile.getRunningApps().size() == 0);
	}
	
	@Test
	//tests the iniial conditions for apps --> checks the pre-conditions before doing anything else
	public void appInitialConditionsTest(){
		assertTrue(app_dual_feature_1.isRunning() == false && app_dual_feature_1.IsBackground() && app_dual_feature_1.HasNotification());
		assertTrue(app_dual_feature_1.getName().equals("Find Friends"));
		assertTrue(background_app_1.isRunning() == false && background_app_1.IsBackground() && background_app_1.HasNotification() == false);
		assertTrue(background_app_1.getName().equals("Photos"));
		assertTrue(notify_app_2.isRunning() == false && notify_app_2.IsBackground() == false && notify_app_2.HasNotification());
		assertTrue(notify_app_2.getName().equals("Emiraites Mobile App"));
		assertTrue(not_background_notify.isRunning() == false && not_background_notify.IsBackground() == false && not_background_notify.HasNotification() == false);
		assertTrue(not_background_notify.getName().equals("Duolingo"));
	}
	
	public void installSetUp(){
		my_mobile.install(app_dual_feature_1);
		my_mobile.install(app_dual_feature_2);
		my_mobile.install(background_app_1);
		my_mobile.install(background_app_2);
		my_mobile.install(notify_app_1);
		my_mobile.install(notify_app_2);
		my_mobile.install(not_background_notify);
	}
	
	@Test
	public void installingAppsTest(){
		assertFalse(my_mobile.install(null));
		my_mobile.install(app_dual_feature_1);
		assertFalse(my_mobile.install(app_dual_feature_1));
		my_mobile.install(app_dual_feature_2);
		my_mobile.install(background_app_1);
		assertTrue(my_mobile.install(background_app_2));
		my_mobile.install(background_app_2);
		my_mobile.install(notify_app_1);
		my_mobile.install(notify_app_2);
		my_mobile.install(not_background_notify);
	}
	
	@Test
	public void uninstallingAppsTest(){
		this.installSetUp();
		assertTrue(my_mobile.getInstalledApps().size() == 7);
		assertFalse(my_mobile.uninstall(null));
		assertFalse(my_mobile.uninstall("Does not Exist"));
		assertTrue(my_mobile.uninstall("Emiraites Mobile App"));
		my_mobile.uninstall("Outlook");
		assertTrue(my_mobile.getInstalledApps().size() == 5);
	}
	
	public void runAppsSetUp(){
		my_mobile.run("Find Friends");
		my_mobile.run("Telegram");
		my_mobile.run("Photos");
		//my_mobile.run("weather");
		my_mobile.run("Emiraites Mobile App");
	}
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	//when a non-notifying app is said to send a notification, it has to throw an error
	public void throwsDoesNotNotifyException(){
		this.installSetUp();
		this.runAppsSetUp();
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("non-notifying apps cannot use properties of notifying apps");
		background_app_1.notifyOS();
	}
	
	@Test
	//when a non-background app is said to send getData, it has to throw an error
	public void throwsIsNotBackgroundApp(){
		this.installSetUp();
		this.runAppsSetUp();
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("non-background apps cannot use getData()");
		Object data=new Object();
		notify_app_2.getData(data);
	}
	
	@Test
	//when sending notification from a non-running app, it has to produce an exception
	public void throwsnotRunningError(){
		this.installSetUp();
		this.runAppsSetUp();
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("a non-running application cannot send notification");
		notify_app_1.notifyOS("my notification");
	}
	
	@Test
	public void runningNotInstalledApps(){
		this.installSetUp();
		this.runAppsSetUp();
		my_mobile.uninstall("Duolingo");
		assertFalse(my_mobile.run("Duolingo"));
	}
	
	@Test 
	//trying to run when the phone is off, or no name is given
	public void falseRun(){
		this.installSetUp();
		this.runAppsSetUp();
		assertFalse(my_mobile.run(null));
		my_mobile.setPhoneOn(false);
		assertFalse(my_mobile.run("weather"));
	}
	// public static void main(String[] args){
	// 	EspressOSMobile my_mobile = new EspressOSMobile();
	// 	my_mobile.setPhoneOn(true);
	// 	App app_dual_feature_1 = new App("Find Friends", true, true,my_mobile);
	// 	App app_dual_feature_2 = new App("Telegram",true,true,my_mobile);
	// 	App background_app_1 = new App("Photos",false,true,my_mobile);
	// 	App background_app_2 = new App("weather",false,true,my_mobile);
	// 	App notify_app_1 = new App("Outlook",true,false,my_mobile);
	// 	App notify_app_2 = new App("Emiraites Mobile App",true,false,my_mobile);
	// 	App not_background_notify = new App("Duolingo",false,false,my_mobile);
	// 	my_mobile.install(app_dual_feature_1);
	// 	my_mobile.install(app_dual_feature_2);
	// 	my_mobile.install(background_app_1);
	// 	my_mobile.install(background_app_2);
	// 	my_mobile.install(notify_app_1);
	// 	my_mobile.install(notify_app_2);
	// 	my_mobile.install(not_background_notify);
	// 	List<App> apps = my_mobile.getInstalledApps();
	// 	for (int i=0;i<apps.size();i++){
	// 		System.out.println(apps.get(i).getName());
	// 	}
	// 	System.out.println();
	// 	my_mobile.uninstall("Outlook");
	// 	apps = my_mobile.getInstalledApps();
	// 	System.out.println(apps.size() == 6);
	// 	for (int i=0;i<apps.size();i++){
	// 		System.out.println(apps.get(i).getName());
	// 	}
	// }
	
}
