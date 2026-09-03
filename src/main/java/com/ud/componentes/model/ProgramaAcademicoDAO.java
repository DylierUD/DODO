package com.ud.componentes.model;

import java.util.ArrayList;
import java.util.List;

public class ProgramaAcademicoDAO {

    private static List<ProgramaAcademico> programasAcademicos =
            new ArrayList<>();

    
    static {

        programasAcademicos.add(new ProgramaAcademico("Ingeniería de Sistemas"));
        programasAcademicos.add(new ProgramaAcademico("Ingeniería Industrial"));
        programasAcademicos.add(new ProgramaAcademico("Matemática"));
        programasAcademicos.add(new ProgramaAcademico("Administración de empresas"));
        programasAcademicos.add(new ProgramaAcademico("Derecho"));
        programasAcademicos.add(new ProgramaAcademico("Ingeniería Temática"));
    }    

    public static boolean agregarPrograma(ProgramaAcademico programaNuevo) {

        if (programaNuevo == null ||  programaNuevo.getNombrePrograma() == null || programaNuevo.getNombrePrograma().trim().isEmpty()) {
            return false;
        }

        String nombreNuevo = programaNuevo.getNombrePrograma().trim();
        
        for (ProgramaAcademico programa : programasAcademicos) {
            if (programa.getNombrePrograma().equalsIgnoreCase(nombreNuevo)) {
                return false;
            }
        }

        programaNuevo.setNombrePrograma(nombreNuevo);

        if (programaNuevo.getAspirantes() == null) {
            programaNuevo.setAspirantes(new ArrayList<>());
        }
        programasAcademicos.add(programaNuevo);
        return true;
    }


    public static List<ProgramaAcademico> listarProgramas() {
        return programasAcademicos;
    }

    public static ProgramaAcademico buscarPrograma(String nombrePrograma) {

        if (nombrePrograma == null) {
            return null;
        }
        for (ProgramaAcademico programa : programasAcademicos) {
            if (programa.getNombrePrograma().equalsIgnoreCase(nombrePrograma)) {
                return programa;
            }
        }
        return null;
    }

    public static boolean actualizarPrograma(String nombreAnterior, String nuevoNombre) {
        if (nombreAnterior == null || nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            return false;
        }

        for (ProgramaAcademico programa : programasAcademicos) {
            if (programa.getNombrePrograma()
                    .equalsIgnoreCase(nuevoNombre.trim())
                    && !programa.getNombrePrograma()
                    .equalsIgnoreCase(nombreAnterior)) {
                return false;
            }
        }

        ProgramaAcademico programa = buscarPrograma(nombreAnterior);
        if (programa == null) {
            return false;
        }

        programa.setNombrePrograma(nuevoNombre.trim());
        return true;
    }

    public static boolean eliminarPrograma(String nombrePrograma) {

        ProgramaAcademico programa = buscarPrograma(nombrePrograma);
        if (programa == null) {
            return false;
        }
        if (programa.getAspirantes() != null && !programa.getAspirantes().isEmpty()) {

            return false;
        }
        return programasAcademicos.remove(programa);
    }


    public static boolean tieneAspirantes(String nombrePrograma) {
        ProgramaAcademico programa = buscarPrograma(nombrePrograma);

        if (programa == null) {
            return false;
        }

        return programa.getAspirantes() != null && !programa.getAspirantes().isEmpty();
    }
}