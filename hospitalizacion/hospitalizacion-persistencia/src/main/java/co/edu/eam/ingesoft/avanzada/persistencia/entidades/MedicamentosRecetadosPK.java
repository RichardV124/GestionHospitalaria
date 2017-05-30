package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

public class MedicamentosRecetadosPK implements Serializable{

	private int medicamento;
	
	private int cita;
	
	public MedicamentosRecetadosPK() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public MedicamentosRecetadosPK(int medicamento, int cita) {
		super();
		this.medicamento = medicamento;
		this.cita = cita;
	}

	public int getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(int medicamento) {
		this.medicamento = medicamento;
	}

	public int getCita() {
		return cita;
	}

	public void setCita(int cita) {
		this.cita = cita;
	}

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
