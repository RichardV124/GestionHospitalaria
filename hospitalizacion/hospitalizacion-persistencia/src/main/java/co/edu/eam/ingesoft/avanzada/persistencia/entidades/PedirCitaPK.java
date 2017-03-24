package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

public class PedirCitaPK implements Serializable{

	private int horarioMedicoHor;
	
	private String horarioMedicoMed;
	
	private String paciente;

	public PedirCitaPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PedirCitaPK(int horarioMedicoHor, String horarioMedicoMed, String paciente) {
		super();
		this.horarioMedicoHor = horarioMedicoHor;
		this.horarioMedicoMed = horarioMedicoMed;
		this.paciente = paciente;
	}

	public int getHorarioMedicoHor() {
		return horarioMedicoHor;
	}

	public void setHorarioMedicoHor(int horarioMedicoHor) {
		this.horarioMedicoHor = horarioMedicoHor;
	}

	public String getHorarioMedicoMed() {
		return horarioMedicoMed;
	}

	public void setHorarioMedicoMed(String horarioMedicoMed) {
		this.horarioMedicoMed = horarioMedicoMed;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + horarioMedicoHor;
		result = prime * result + ((horarioMedicoMed == null) ? 0 : horarioMedicoMed.hashCode());
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
		if (horarioMedicoHor != other.horarioMedicoHor)
			return false;
		if (horarioMedicoMed == null) {
			if (other.horarioMedicoMed != null)
				return false;
		} else if (!horarioMedicoMed.equals(other.horarioMedicoMed))
			return false;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		return true;
	}
	
}
