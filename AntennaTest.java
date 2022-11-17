/*
1. This class is intended to test the methods written in the StandardAntenna class and any methods in the EspressOSMobile class
	that is related to the Antenna.
2. Helped me find two problems with my code that was not recognised by automatic test cases, further explained below
3. There are a total number of 16 test cases, but most of the test cases related to EspressOSMObile Antenna methods are fairly complicated and hence, test more than one thing, which make the test cases cover a variety of scenarios.
*/


import org.junit.*;
import static org.junit.Assert.*;

public class AntennaTest{
	
	EspressOSMobile my_mobile;
	StandardAntenna antenna;
	TooStrongAntenna too_strong;
	WeakAntenna weak_antenna;
	@Before 
	public void AntennaSetUp(){
		
		antenna = new StandardAntenna();
		too_strong = new TooStrongAntenna();
		weak_antenna = new WeakAntenna();
	}
	@Before
	public void MobileSetUp(){
		my_mobile = new EspressOSMobile();
		my_mobile.setPhoneOn(true);
	}
	@Test
	public void ConstructorTest(){
		//assertTrue(my_mobile.isPhoneOn());
		assertTrue(antenna.getSignalStrength() == 0 && antenna.isConnected() == false);
	}
	
	@Test
	public void SetSignalStrengthTestSimple(){
		antenna.setSignalStrength(4);
		assertTrue(antenna.getSignalStrength() == 4);
		antenna.setSignalStrength(0);
		assertFalse(antenna.isConnected());
	} 
	//StandardAntenna methods are working correctly.
	
	@Test
	public void changeAntennaTestSimple(){
		my_mobile.changeAntenna(antenna);
		assertFalse(my_mobile.isConnectedNetwork());
		assertTrue(my_mobile.getBatteryLife() == 20);
	}
	
	@Test
	public void connectNetworkTestSimple(){
		my_mobile.connectNetwork();
		assertTrue(my_mobile.getSignalStrength() == 1);
		this.setUpsignalStrength();
		assertTrue(my_mobile.connectNetwork());
		my_mobile.disconnectNetwork();
		my_mobile.connectNetwork();
		assertTrue(my_mobile.getSignalStrength() == 3);
	}
	
	@Test
	public void setSignalStrengthTestInvalid(){
		//my_mobile.changeAntenna(antenna);
		assertFalse(my_mobile.setSignalStrength(7));
		assertFalse(my_mobile.setSignalStrength(-1));
		assertTrue(my_mobile.setSignalStrength(3));
	}
	
	public void setUpsignalStrength(){
		my_mobile.setSignalStrength(3);
	}
	
	@Test
	public void getSignalStrengthTestSimple(){
		this.setUpsignalStrength();
		assertTrue(my_mobile.getSignalStrength() == 3);
	}
	
	@Test
	//this test case helped me find a bug in my code that was not covered by the automatic testing. When charging a phone that has died, first the phone has to be charged, then, it has to be set on, doing it in the reverse order (which was what my code was doing) would not make the setPhoneOn method work
	public void ConnectDieOn(){
		my_mobile.connectNetwork();
		assertTrue(my_mobile.setSignalStrength(4));
		my_mobile.setSignalStrength(4);
		my_mobile.usePhone(20);
		assertFalse(my_mobile.isPhoneOn());
		my_mobile.chargePhone();
		my_mobile.connectNetwork();
		System.out.println(my_mobile.getSignalStrength());
	}
	
	@Test
	public void DieWhileConnecting(){
		my_mobile.usePhone(19);
		assertTrue(my_mobile.getBatteryLife() == 1);
		my_mobile.connectNetwork();
		assertFalse(my_mobile.isPhoneOn());
		my_mobile.chargePhone();
		assertFalse(my_mobile.isConnectedNetwork());
	}
	
	@Test
	public void InvalidAntennaChange(){
		assertFalse(my_mobile.changeAntenna(too_strong));
	}
	
	@Test
	//this test case helped me find a bug in my code. In my changeAntenna method, although I changed the signal strength, I did not change the actual antenna object
	public void WeakAntennaChange(){
		assertTrue(my_mobile.changeAntenna(weak_antenna));
		my_mobile.changeAntenna(weak_antenna);
		assertTrue(my_mobile.isConnectedNetwork() && my_mobile.getSignalStrength() == 1);
		my_mobile.setSignalStrength(4);
		assertTrue(my_mobile.getSignalStrength() == 1);
		my_mobile.setSignalStrength(3);
		assertTrue(my_mobile.getSignalStrength() == 3);
	}
	
	@Test
	public void DisconnectThenConnect(){
		my_mobile.connectNetwork();
		assertTrue(my_mobile.getSignalStrength() == 1);
		my_mobile.setSignalStrength(4);
		my_mobile.disconnectNetwork();
		assertFalse(my_mobile.isConnectedNetwork());
		my_mobile.connectNetwork();
		assertTrue(my_mobile.getSignalStrength() == 4);
	}
		
	@Test
	public void SignalStrengthWhileOff(){
		my_mobile.setPhoneOn(false);
		assertTrue(my_mobile.getSignalStrength() == 0);
		
	}
	
	@Test
	public void ConnectWhileOff(){
		my_mobile.setPhoneOn(false);
		assertFalse(my_mobile.connectNetwork());
	}
	
	@Test
	public void changeAntennaWhileOff(){
		my_mobile.usePhone(20);
		assertFalse(my_mobile.isPhoneOn());
		assertFalse(my_mobile.changeAntenna(weak_antenna));
	}
	
	@Test
	public void changeAntennaZeroStrnegth(){
		my_mobile.connectNetwork();
		weak_antenna.setSignalStrength(0);
		assertTrue(my_mobile.changeAntenna(weak_antenna));
		my_mobile.changeAntenna(weak_antenna);
		assertFalse(my_mobile.isConnectedNetwork());
	}
	
	@Test
	public void changeAntennaConnect(){
		assertFalse(my_mobile.isConnectedNetwork());
		my_mobile.changeAntenna(weak_antenna);
		assertTrue(my_mobile.isConnectedNetwork());
		
	}
	
	@After
	//all the temporary objects created for testing will be collected by the garbage collector
	public void delete(){
		my_mobile = null;
		antenna = null;
		weak_antenna = null;
		too_strong = null;
	}
	//public static void main(String[] args){
			// EspressOSMobile my_mobile  =  new EspressOSMobile();
			// StandardAntenna antenna = new StandardAntenna();
			// WeakAntenna weak_antenna = new WeakAntenna();
			// my_mobile.setPhoneOn(true);
			// my_mobile.changeAntenna(weak_antenna);
			// System.out.println(my_mobile.isConnectedNetwork() + " " + my_mobile.getSignalStrength());
			// my_mobile.setSignalStrength(4);
			// System.out.println(my_mobile.getSignalStrength());
	// 		my_mobile.setSignalStrength(4);
	// 		System.out.println(my_mobile.getSignalStrength());
	// 		my_mobile.usePhone(20);
	// 		System.out.println(my_mobile.isPhoneOn());
	// 		my_mobile.chargePhone();
	// 		System.out.println(my_mobile.getBatteryLife());
	// 		my_mobile.connectNetwork();
	// 		System.out.println(my_mobile.getSignalStrength());
	 //	}

}
