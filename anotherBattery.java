public class anotherBattery extends Battery{
	
	private int battery_lvl;
	
	public anotherBattery(){
		battery_lvl = 100;
	}
	public void setLevel(int value){
		battery_lvl = value;
	}
	
	 public int getLevel(){
		 return this.battery_lvl;
	 }
}
