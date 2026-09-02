package com.ud.componentes.model;

import java.io.Serializable;

public class AspiranteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2281000422903697905L;
	private String identificacion;
	private String nombres;
	private String apellidos;
	private String correo;
	private String telefono;
	private ProgramaAcademico programa;
		
	
	public AspiranteDTO() {
		super();
	}


	public AspiranteDTO(String identificacion, String nombres, String apellidos, String correo, String telefono) {
		super();
		this.identificacion = identificacion;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.telefono = telefono;
	}


	public String getIdentificacion() {
		return identificacion;
	}


	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public ProgramaAcademico getPrograma() {
		return programa;
	}


	public void setPrograma(ProgramaAcademico programa) {
		this.programa = programa;
	}


	@Override
	public String toString() {
		return "Aspirante [nombres=" + nombres + ", apellidos=" + apellidos + "]";
	}

	
	
}
