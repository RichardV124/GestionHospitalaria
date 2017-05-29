package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

public class CitaCirugiaPK implements Serializable{

	int cita;
	
	int cirugia;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cirugia;
		result = prime * result + cita;
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
		CitaCirugiaPK other = (CitaCirugiaPK) obj;
		if (cirugia != other.cirugia)
			return false;
		if (cita != other.cita)
			return false;
		return true;
	}

	/**
	 * @return the cita
	 */
	public int getCita() {
		return cita;
	}

	/**
	 * @param cita the cita to set
	 */
	public void setCita(int cita) {
		this.cita = cita;
	}

	/**
	 * @return the cirugia
	 */
	public int getCirugia() {
		return cirugia;
	}

	/**
	 * @param cirugia the cirugia to set
	 */
	public void setCirugia(int cirugia) {
		this.cirugia = cirugia;
	}
	
	
	
}
