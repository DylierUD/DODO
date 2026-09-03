package com.ud.componentes.controller;

import java.io.Serializable;

import com.ud.componentes.model.AspiranteDAO;
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
	private String programaSeleccionado;

	@Inject
	private ProgramaBean programaBean;

	
	private void mensaje(FacesMessage.Severity tipo, String titulo, String detalle) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, titulo, detalle));
	}

	public void inscribirse() {
		
		
		mensaje(FacesMessage.SEVERITY_ERROR, "Error", "inicio ");
		
		if (AspiranteDAO.validarAspirante(identificacion)) {
			mensaje(FacesMessage.SEVERITY_ERROR, "Error", "El aspirante ya tiene una inscripcion.");
			return;
		}

		if (programaSeleccionado == null || programaSeleccionado.isEmpty()) {
			mensaje(FacesMessage.SEVERITY_ERROR, "Error", "Debes elegir al menos una carrera.");
			return;
		}


		AspiranteDTO aspirante = new AspiranteDTO(identificacion, nombres, apellidos, correo, telefono);
		aspirante.setFechaInscripcion(new Date());
 
		ProgramaAcademico programa = programaBean.buscarPorNombre(programaSeleccionado);
		
		if (programa != null) {

	        if (programa.postularAspirante(aspirante)) {
	            aspirante.setPrograma(programa);
	            AspiranteDAO.agregarAspirante(aspirante);
	            mensaje(FacesMessage.SEVERITY_INFO,"Éxito", "¡Inscripción realizada!");

	        } else {
	        	mensaje(FacesMessage.SEVERITY_INFO,"Éxito", "¡Inscripción fallida!");
	        }

	    } else {
	    	mensaje(FacesMessage.SEVERITY_INFO,"Éxito", "¡el programa seleccionado no existe!");
	    }

	    limpiarFormulario();
	}
	
	private void limpiarFormulario() {
		identificacion = null;
		nombres = null;
		apellidos = null;
		correo = null;
		telefono = null;
		programaSeleccionado = null;
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


	

	public String getProgramaSeleccionado() {
		return programaSeleccionado;
	}

	public void setProgramasSeleccionado(String programaSeleccionado) {
		this.programaSeleccionado = programaSeleccionado;
	}

	public ProgramaBean getProgramaBean() { return programaBean; }
}