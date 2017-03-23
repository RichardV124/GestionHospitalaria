package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_MEDICAMENTO")
public class Medicamento {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="descripcion", nullable=false, length=50)
	private String descripcion;
	
	@Column(name="fecha_vencimiento", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;
	
	@JoinColumn(name="tipo_medicamento")
	@ManyToOne(cascade={})
	private TipoMedicamento tipoMedicamento;
}
