public class WeakAntenna extends Antenna{
	
	private int signal_strength;
	private boolean has_network;
	
	public WeakAntenna (){
		this.signal_strength = 1;
		this.has_network = true;
	}
	
	public boolean isConnected(){
		return this.has_network;
	}
	
	public void setNetwork(boolean isConnected){
		
		has_network = isConnected;
		
	}
	
	public int getSignalStrength(){
		return this.signal_strength;
	}
	
	public void setSignalStrength(int n){
		
		if (n == 0){
			this.signal_strength = n;
			this.setNetwork(false);
		}
		else if (n>3){
			signal_strength +=0;
			return;
		}
		else{
			this.signal_strength = n;
			this.setNetwork(true);
		}
	}
	
}
