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
	
	public static boolean eliminarAspirante(String nombreAspirante) {
		for (AspiranteDTO aspirante : aspirantes) {
			if (aspirante.getIdentificacion().equals(nombreAspirante)) {
				aspirantes.remove(aspirante);
				return true;
			}
		}
		return false;
	}	
}
