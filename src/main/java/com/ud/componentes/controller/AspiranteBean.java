package com.ud.componentes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ud.componentes.model.AspiranteDTO;
import com.ud.componentes.model.ProgramaAcademico;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("aspiranteBean")
@ViewScoped
public class AspiranteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String identificacion;
	private String nombres;
	private String apellidos;
	private String correo;
	private String telefono;
	private List<String> programasSeleccionados = new ArrayList<>();

	@Inject
	private ProgramaBean programaBean;

	
	private void mensaje(FacesMessage.Severity tipo, String titulo, String detalle) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, titulo, detalle));
	}

	public void inscribirse() {

		
		if (programasSeleccionados == null || programasSeleccionados.isEmpty()) {
			mensaje(FacesMessage.SEVERITY_ERROR, "Error", "Debes elegir al menos una carrera.");
			return;
		}

		
		if (programasSeleccionados.size() > 3) {
			mensaje(FacesMessage.SEVERITY_ERROR, "Máximo permitido", "Solo puedes elegir hasta 3 carreras.");
			return;
		}

		AspiranteDTO aspirante = new AspiranteDTO(identificacion, nombres, apellidos, correo, telefono);
		aspirante.setFechaInscripcion(new Date());

		List<ProgramaAcademico> inscritos = new ArrayList<>();

		for (String nombrePrograma : programasSeleccionados) {
			ProgramaAcademico programa = programaBean.buscarPorNombre(nombrePrograma);
			if (programa != null) {
				programa.postularAspirante(aspirante);
				inscritos.add(programa);
			}
		}

		aspirante.setProgramas(inscritos);

		mensaje(FacesMessage.SEVERITY_INFO, "Éxito",
			"¡Inscripción realizada en " + inscritos.size() + " carrera(s)!");

		limpiarFormulario();
	}

	private void limpiarFormulario() {
		identificacion = null;
		nombres = null;
		apellidos = null;
		correo = null;
		telefono = null;
		programasSeleccionados = new ArrayList<>();
	}

	
	public String getIdentificacion() { return identificacion; }
	public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

	public String getNombres() { return nombres; }
	public void setNombres(String nombres) { this.nombres = nombres; }

	public String getApellidos() { return apellidos; }
	public void setApellidos(String apellidos) { this.apellidos = apellidos; }

	public String getCorreo() { return correo; }
	public void setCorreo(String correo) { this.correo = correo; }

	public String getTelefono() { return telefono; }
	public void setTelefono(String telefono) { this.telefono = telefono; }

	public List<String> getProgramasSeleccionados() { return programasSeleccionados; }
	public void setProgramasSeleccionados(List<String> programasSeleccionados) { this.programasSeleccionados = programasSeleccionados; }

	public ProgramaBean getProgramaBean() { return programaBean; }
}