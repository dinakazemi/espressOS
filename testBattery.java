/*
1. This class is intended to test the methods written in the StandardBatterty class and any methods in the EspressOSMobile class
	that is related to the Battery. 
*/


import org.junit.*;
import static org.junit.Assert.*;

public class testBattery{
	
	EspressOSMobile my_mobile;
	Battery battery;
	Battery invalid_battery;
	Battery another_battery;
	@Before 
	public void BatterySetUp(){
		
		battery = new StandardBattery();
		invalid_battery = new invalidBattery();
		another_battery = new anotherBattery();
	}
	
	@Before
	public void MobileSetUp(){
		my_mobile = new EspressOSMobile();
		
	}
	
	@Test
	public void StandardBatteryTest(){
		assertTrue(battery.getLevel() == 25);
		battery.setLevel(30);
		assertTrue(battery.getLevel() == 30);
	}
	
	@Test
	//this test case makes sure each method in the EspressOSMobile that consumes battery is doing so properly
	public void InitialBatteryConditionChecks(){
		my_mobile.setPhoneOn(true);
		assertTrue(my_mobile.getBatteryLife() == 20);
		my_mobile.connectNetwork();
		assertTrue(my_mobile.getBatteryLife() == 18);
		my_mobile.setPhoneOn(false);
		my_mobile.setPhoneOn(true);
		assertTrue(my_mobile.getBatteryLife() == 13);
		my_mobile.chargePhone();
		assertTrue(my_mobile.getBatteryLife() == 23);
	}
	
	@Test
	public void invalidChangeBatteryTest(){
		my_mobile.setPhoneOn(true);
		assertFalse(my_mobile.changeBattery(invalid_battery));
	}
	
	@Test
	public void validChangeBatteryTest(){
		my_mobile.setPhoneOn(true);
		my_mobile.changeBattery(another_battery);
		assertFalse(my_mobile.isPhoneOn());
		my_mobile.setPhoneOn(true);
		assertTrue(my_mobile.getBatteryLife() == 95);
	}
	
	@Test
	public void overChargeTest1(){
		my_mobile.changeBattery(another_battery);
		my_mobile.setPhoneOn(true);
		assertTrue(my_mobile.getBatteryLife() == 95);
		my_mobile.chargePhone();
		assertTrue(my_mobile.getBatteryLife() == 100);
	}
	
	@Test
	public void overChargeTest2(){
		my_mobile.changeBattery(another_battery);
		my_mobile.setPhoneOn(true);
		another_battery.setLevel(100);
		assertTrue(my_mobile.getBatteryLife() == 100);
		assertFalse(my_mobile.chargePhone());
	}
	
	@Test
	public void DieThenTurnOn(){
		my_mobile.setPhoneOn(true);
		my_mobile.usePhone(30);
		assertFalse(my_mobile.isPhoneOn());
		assertFalse(my_mobile.setPhoneOn(true));
		my_mobile.chargePhone();
		assertTrue(my_mobile.isPhoneOn() && my_mobile.getBatteryLife() == 5);
	}
	
	@Test
	public void getBatteryLifeTest(){
		my_mobile.setPhoneOn(true);
		my_mobile.usePhone(10);
		my_mobile.connectNetwork();
		my_mobile.chargePhone();
		my_mobile.chargePhone();
		assertTrue(my_mobile.getBatteryLife() == 28);
	}
	
	@Test
	public void changeBattery3(){
		my_mobile.setPhoneOn(true);
		another_battery.setLevel(0);
		my_mobile.usePhone(20);
		assertFalse(my_mobile.isPhoneOn());
		my_mobile.changeBattery(another_battery);
		assertFalse(my_mobile.setPhoneOn(true));
		another_battery.setLevel(70);
		my_mobile.setPhoneOn(true);
		assertTrue(my_mobile.getBatteryLife() == 65);
	}
	
	
	@After
	//dereferences all the temporary objects created for testing 
	public void delete(){
		my_mobile = null;
		battery = null;
		another_battery = null;
		invalid_battery = null;
		
	}
}
