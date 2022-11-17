public class StandardBattery extends Battery{
	
	private int battery_lvl;
	
	public StandardBattery(){
		battery_lvl = 25;
	}
	public void setLevel(int value){
		battery_lvl = value;
	}
	
	 public int getLevel(){
		 return this.battery_lvl;
	 }
}
