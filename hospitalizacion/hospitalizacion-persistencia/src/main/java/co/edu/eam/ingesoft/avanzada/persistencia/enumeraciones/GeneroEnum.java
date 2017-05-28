package co.edu.eam.ingesoft.avanzada.persistencia.enumeraciones;

public enum GeneroEnum {

	MASCULINO("Masculino"),
	FEMENINO("Femenino"),
	OTRO("Otro");
	
	private final String label;
	
	private GeneroEnum(String label) {
	    this.label = label;
	  }
	
	public String getLabel() {
	    return this.label;
	  }
}
