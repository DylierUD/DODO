package com.ud.componentes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ud.componentes.model.AspiranteDAO;
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
    
    
    private String carreraSeleccionada;
    //---para poder editar los datos del aspirante
    private AspiranteDTO aspiranteEdicion;
    private boolean mostrarFormularioEdicion = false;

    @Inject
    private ProgramaBean programaBean;

    public void inscribirse() {
        if (AspiranteDAO.validarAspirante(identificacion)) {
            mensaje(FacesMessage.SEVERITY_ERROR, "Error", "El aspirante ya tiene una inscripción activa.");
            return;
        }

        if (carreraSeleccionada == null || carreraSeleccionada.trim().isEmpty()) {
            mensaje(FacesMessage.SEVERITY_ERROR, "Error", "Debes elegir una carrera.");
            return;
        }

        AspiranteDTO aspirante = new AspiranteDTO(identificacion, nombres, apellidos, correo, telefono);
        aspirante.setFechaInscripcion(new Date());

        ProgramaAcademico programa = programaBean.buscarPorNombre(carreraSeleccionada);
        
        if (programa != null) {
            if (programa.postularAspirante(aspirante)) {
                aspirante.setPrograma(programa);
                
                AspiranteDAO.agregarAspirante(aspirante);
                mensaje(FacesMessage.SEVERITY_INFO, "Éxito", "¡Inscripción realizada exitosamente!");
                limpiarFormulario();
                
            } else {
                mensaje(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo realizar la postulación en la carrera.");
            }
        } else {
            mensaje(FacesMessage.SEVERITY_ERROR, "Error", "El programa seleccionado no existe.");
        }
    }

    public void prepararEdicion(AspiranteDTO aspirante) {
        if (aspirante != null) {
            this.aspiranteEdicion = aspirante;
            this.identificacion = aspirante.getIdentificacion();
            this.nombres = aspirante.getNombres();
            this.apellidos = aspirante.getApellidos();
            this.correo = aspirante.getCorreo();
            this.telefono = aspirante.getTelefono();
            this.mostrarFormularioEdicion = true;
        }
    }

    public void actualizarAspirante() {
        if (aspiranteEdicion != null) {
            aspiranteEdicion.setNombres(nombres);
            aspiranteEdicion.setApellidos(apellidos);
            aspiranteEdicion.setCorreo(correo);
            aspiranteEdicion.setTelefono(telefono);
            mensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Datos actualizados correctamente.");
            cancelarEdicion();
        }
    }

    public void cancelarEdicion() {
        limpiarFormulario();
        this.aspiranteEdicion = null;
        this.mostrarFormularioEdicion = false;
    }

    private void limpiarFormulario() {
        identificacion = null;
        nombres = null;
        apellidos = null;
        correo = null;
        telefono = null;
        carreraSeleccionada = null;
    }

    public void eliminarInscripcion(AspiranteDTO aspirante) {
        if (aspirante != null) {
            if (aspirante.getPrograma() != null) {
                aspirante.getPrograma().retirarAspirante(aspirante.getIdentificacion());
            }
            AspiranteDAO.eliminarAspirante(aspirante.getIdentificacion());
            mensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Aspirante eliminado.");
        }
    }

    private void mensaje(FacesMessage.Severity tipo, String titulo, String detalle) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, titulo, detalle));
    }

    public List<AspiranteDTO> getTodosLosAspirantes() {
        if (AspiranteDAO.aspirantes == null) {
            return new ArrayList<>();
        }
        return AspiranteDAO.aspirantes;
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

    public String getCarreraSeleccionada() { return carreraSeleccionada; }
    public void setCarreraSeleccionada(String carreraSeleccionada) { this.carreraSeleccionada = carreraSeleccionada; }

    public ProgramaBean getProgramaBean() { return programaBean; }
    public void setProgramaBean(ProgramaBean programaBean) { this.programaBean = programaBean; }

    public boolean isMostrarFormularioEdicion() { return mostrarFormularioEdicion; }
    public void setMostrarFormularioEdicion(boolean mostrarFormularioEdicion) { this.mostrarFormularioEdicion = mostrarFormularioEdicion; }

    public boolean getMostrarFormularioEdicion() { return mostrarFormularioEdicion; }
}