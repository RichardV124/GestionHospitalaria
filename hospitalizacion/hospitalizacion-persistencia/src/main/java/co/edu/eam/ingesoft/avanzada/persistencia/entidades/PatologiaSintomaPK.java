package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

public class PatologiaSintomaPK implements Serializable{

	int patologia;

	int sintoma;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + patologia;
		result = prime * result + sintoma;
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
		PatologiaSintomaPK other = (PatologiaSintomaPK) obj;
		if (patologia != other.patologia)
			return false;
		if (sintoma != other.sintoma)
			return false;
		return true;
	}
	
	

}
