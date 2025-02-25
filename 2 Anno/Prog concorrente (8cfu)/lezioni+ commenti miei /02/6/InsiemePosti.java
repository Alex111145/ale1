public class InsiemePosti {
	private int totPostiDisponibili=20;
	public boolean assegnaPosti(String cliente,
			int numPostiRichiesti){
		if(numPostiRichiesti<=totPostiDisponibili) {
			for(int jj=0; jj<5000; jj++) {
				int a=0;
			}
			totPostiDisponibili-=numPostiRichiesti;
			return true;
		} else { return false; }
	}
	public int getPostiDisponibili(){
		return totPostiDisponibili;
	}
}
