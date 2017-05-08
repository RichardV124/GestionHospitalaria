package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

public class PatologiaTratamientoPK implements Serializable{

	
	int patologia;
	
	int tratamiento;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + patologia;
		result = prime * result + tratamiento;
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
		PatologiaTratamientoPK other = (PatologiaTratamientoPK) obj;
		if (patologia != other.patologia)
			return false;
		if (tratamiento != other.tratamiento)
			return false;
		return true;
	}
	
	
	
}
