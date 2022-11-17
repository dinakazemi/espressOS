public class invalidBattery extends Battery{
	
	private int battery_lvl;
	
	public invalidBattery(){
		battery_lvl = 105;
	}
	public void setLevel(int value){
		battery_lvl = value;
	}
	
	 public int getLevel(){
		 return this.battery_lvl;
	 }
}
