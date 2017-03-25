package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

public class PedirCitaPK implements Serializable{

	private HorarioMedicoPK horarioMedico;
	
	private String paciente;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((horarioMedico == null) ? 0 : horarioMedico.hashCode());
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
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
		PedirCitaPK other = (PedirCitaPK) obj;
		if (horarioMedico == null) {
			if (other.horarioMedico != null)
				return false;
		} else if (!horarioMedico.equals(other.horarioMedico))
			return false;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		return true;
	}


	
}
