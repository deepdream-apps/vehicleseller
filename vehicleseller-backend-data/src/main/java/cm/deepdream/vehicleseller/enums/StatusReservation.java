package cm.deepdream.vehicleseller.enums;

public enum StatusReservation {
	CANCELLED ("Annule"), WAITING("En_Attente"), HANDLED("Traite"), CONFIRMED("Confirme"), TERMINATED("Termine") ;
	
	
	private String label ;
	
	StatusReservation(String label) {
		this.label = label ;
	}

	public String getLabel() {
		return label;
	}
	
	
	
}
