package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

public class MedicamentosRecetadosPK {

	int medicamento;
	
	int cita;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cita;
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
		MedicamentosRecetadosPK other = (MedicamentosRecetadosPK) obj;
		if (cita != other.cita)
			return false;
		if (medicamento != other.medicamento)
			return false;
		return true;
	}
	
	
	
}
