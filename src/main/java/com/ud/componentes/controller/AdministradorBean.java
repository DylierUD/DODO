package com.ud.componentes.controller;

import java.io.Serializable;



import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("administradorBean")
@ViewScoped
public class AdministradorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProgramaBean programaBean;

	public ProgramaBean getProgramaBean() {
		return programaBean;
	}
	
	
}