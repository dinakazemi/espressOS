public class StandardAntenna extends Antenna{
	
	private int signal_strength;
	private boolean has_network;
	
	public StandardAntenna (){
		this.signal_strength = 0;
		this.has_network = false;
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
		this.signal_strength = n;
		if (n == 0){
			this.setNetwork(false);
		}
		else{
			this.setNetwork(true);
		}
	}
	
}
