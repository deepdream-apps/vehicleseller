package cm.deepdream.vehicleseller.enums;

public enum StatusSession {
	EN_COURS ("En_Cours"), TERMINE ("Termine")  ;
	
	String label ;
	
	StatusSession (String label){
		this.label = label ;
	}
	
	public String getLabel() {
		return label;
	}
	
}
