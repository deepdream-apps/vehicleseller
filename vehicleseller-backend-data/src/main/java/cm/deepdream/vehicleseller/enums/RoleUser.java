package cm.deepdream.vehicleseller.enums;

public enum RoleUser {
	CUSTOMER("Client"), OPERATOR("Operateur"), DRIVER("Chauffeur"), ADMINISTRATOR("Administrateur"), MANAGER("Gerant");
	
	String label ;
	
	RoleUser (String label){
		this.label = label ;
	}

	public String getLabel() {
		return label;
	}

}
