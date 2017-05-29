package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

public class FarmaciaMedicamentoPK implements Serializable{

	private int farmacia;
	
	private int medicamento;

	public FarmaciaMedicamentoPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(int farmacia) {
		this.farmacia = farmacia;
	}

	public int getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(int medicamento) {
		this.medicamento = medicamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + farmacia;
		result = prime * result + medicamento;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FarmaciaMedicamentoPK other = (FarmaciaMedicamentoPK) obj;
		if (farmacia != other.farmacia)
			return false;
		if (medicamento != other.medicamento)
			return false;
		return true;
	}
	
}
