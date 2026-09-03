package com.ud.componentes.model;

import java.util.ArrayList;
import java.util.List;

public class ProgramaAcademicoDAO {
	public static List<ProgramaAcademico> programasAcademicos = new ArrayList<ProgramaAcademico>();
	
	public static boolean agregarPrograma(ProgramaAcademico programaNuevo) {
		boolean existente = false;
		for (ProgramaAcademico programaAcademico : programasAcademicos) {
			if (programaAcademico.getNombrePrograma().equals(programaNuevo.getNombrePrograma())) {
				existente = true;
			}
		}
		if (!existente) {
			programasAcademicos.add(programaNuevo);
		}
		return !existente;
	}	
	
	public static boolean eliminarPrograma(String nombrePrograma) {
		for (ProgramaAcademico programaAcademico : programasAcademicos) {
			if (programaAcademico.getNombrePrograma().equals(nombrePrograma)) {
				programasAcademicos.remove(programaAcademico);
			}
		}
		return false;
	}	
}
