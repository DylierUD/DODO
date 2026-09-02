package com.ud.componentes.controller;

import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("prueba")
@ViewScoped
public class Prueba implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8046137774257226226L;

	public String mensaje() {
		return "Hola Mundo Beans Dyl";
	}
}
