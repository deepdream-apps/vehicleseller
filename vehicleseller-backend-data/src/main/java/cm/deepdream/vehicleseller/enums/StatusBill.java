package cm.deepdream.vehicleseller.enums;

public enum StatusBill {
	NON_REGLE ("Non_Regle"), REGLE ("Regle"), ANNULE ("Annule")  ;
	
	String label ;
	
	StatusBill (String label){
		this.label = label ;
	}
	
	public String getLabel() {
		return label;
	}
	
}
