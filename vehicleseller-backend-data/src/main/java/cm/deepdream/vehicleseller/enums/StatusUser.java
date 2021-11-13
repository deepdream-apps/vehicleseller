package cm.deepdream.vehicleseller.enums;

public enum StatusUser {
	NOT_ACTIVATED ("Non_Active"), ACTIVATED ("Active"), SUSPENDED ("Suspendu")  ;
	
	String label ;
	
	StatusUser (String label){
		this.label = label ;
	}
	
	public String getLabel() {
		return label;
	}
	
}
