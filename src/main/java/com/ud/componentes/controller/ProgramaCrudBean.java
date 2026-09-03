package com.ud.componentes.controller;

import java.io.Serializable;
import java.util.List;

import com.ud.componentes.model.ProgramaAcademico;
import com.ud.componentes.model.ProgramaAcademicoDAO;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("programaCrudBean")
@ViewScoped
public class ProgramaCrudBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombrePrograma;
    private String nombreAnterior;

    public List<ProgramaAcademico> getProgramas() {
        return ProgramaAcademicoDAO.listarProgramas();
    }

    public void nuevoPrograma() {
        nombrePrograma = "";
        nombreAnterior = null;
    }

    public void agregarPrograma() {

        if (nombrePrograma == null || nombrePrograma.trim().isEmpty()) {
            mensaje(FacesMessage.SEVERITY_ERROR, "Error", "Debe ingresar el nombre del programa." );
            return;
        }

        ProgramaAcademico programa = new ProgramaAcademico(nombrePrograma.trim());
        boolean agregado = ProgramaAcademicoDAO.agregarPrograma(programa);

        if (agregado) {
            mensaje(FacesMessage.SEVERITY_INFO, "Éxito", "El programa fue agregado correctamente.");
            nombrePrograma = "";

        } else {
            mensaje(FacesMessage.SEVERITY_ERROR, "Error", "Ya existe un programa con ese nombre." );
        }
    }

    public void prepararEdicion(ProgramaAcademico programa) {

        nombreAnterior = programa.getNombrePrograma();

        nombrePrograma = programa.getNombrePrograma();
    }

    public void actualizarPrograma() {

        if (nombrePrograma == null || nombrePrograma.trim().isEmpty()) {
            mensaje( FacesMessage.SEVERITY_ERROR, "Error", "Debe ingresar el nombre del programa." );
            return;
        }

        boolean actualizado = ProgramaAcademicoDAO.actualizarPrograma( nombreAnterior,nombrePrograma.trim());

        if (actualizado) {

            mensaje(FacesMessage.SEVERITY_INFO, "Éxito", "El programa fue actualizado correctamente.");

            nombrePrograma = "";
            nombreAnterior = null;

        } else {
            mensaje( FacesMessage.SEVERITY_ERROR, "Error",  "No se pudo actualizar. Verifique que el programa exista y que el nuevo nombre no esté repetido.");
        }
    }

    public void eliminarPrograma(ProgramaAcademico programa) {

        if (programa == null) {
            return;
        }

        String nombre = programa.getNombrePrograma();
        
        if (ProgramaAcademicoDAO.tieneAspirantes(nombre)) {

            mensaje(FacesMessage.SEVERITY_WARN,"No se puede eliminar", "El programa " + nombre+ " tiene estudiantes asociados." );
            return;
        }

        boolean eliminado = ProgramaAcademicoDAO.eliminarPrograma(nombre);

        if (eliminado) {

            mensaje( FacesMessage.SEVERITY_INFO, "Eliminación exitosa", "El programa fue eliminado correctamente.");

        } else {

            mensaje( FacesMessage.SEVERITY_ERROR,"Error", "No se pudo eliminar el programa.");
        }
    }

    private void mensaje(FacesMessage.Severity tipo, String titulo,String detalle) {

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, titulo, detalle));
    }


    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public String getNombreAnterior() {
        return nombreAnterior;
    }

    public void setNombreAnterior(String nombreAnterior) {
        this.nombreAnterior = nombreAnterior;
    }
}