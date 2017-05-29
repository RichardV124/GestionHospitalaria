package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

public class HorarioMedicoPK implements Serializable{

	private int horario;
	
	private int medico;

	public HorarioMedicoPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HorarioMedicoPK(int horario, int medico) {
		super();
		this.horario = horario;
		this.medico = medico;
	}

	public int getHorario() {
		return horario;
	}

	public void setHorario(int horario) {
		this.horario = horario;
	}

	public int getMedico() {
		return medico;
	}

	public void setMedico(int medico) {
		this.medico = medico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + horario;
		result = prime * result + medico;
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
		HorarioMedicoPK other = (HorarioMedicoPK) obj;
		if (horario != other.horario)
			return false;
		if (medico != other.medico)
			return false;
		return true;
	}
	
}
