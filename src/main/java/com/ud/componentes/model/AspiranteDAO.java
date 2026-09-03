package com.ud.componentes.model;

import java.util.ArrayList;
import java.util.List;

public class AspiranteDAO {
	public static List<AspiranteDTO> aspirantes = new ArrayList<>();
	
	
	public static boolean agregarAspirante(AspiranteDTO aspiranteNuevo) {
		boolean existente = false;
		for (AspiranteDTO aspirante : aspirantes) {
			if (aspirante.getIdentificacion().equals(aspiranteNuevo.getIdentificacion())) {
				existente = true;
			}
		}
		if (!existente) {
			aspirantes.add(aspiranteNuevo);
		}
		return !existente;
	}	
	
	public static boolean eliminarAspirante(String idAspirante) {

	    for (int i = 0; i < aspirantes.size(); i++) {

	        AspiranteDTO aspirante = aspirantes.get(i);

	        if (aspirante.getIdentificacion().equals(idAspirante)) {

	            aspirantes.remove(i);
	            return true;
	        }
	    }

	    return false;
	}	
	
	public static boolean validarAspirante(String idAspirante) {
		boolean existente = false;
		for (AspiranteDTO aspirante : aspirantes) {
			if (aspirante.getIdentificacion().equals(idAspirante)) {
				existente = true;
			}
		}
		return existente;
	}
}
