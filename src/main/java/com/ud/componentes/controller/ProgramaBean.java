package com.ud.componentes.controller;

import java.io.Serializable;
import java.util.List;

import com.ud.componentes.model.ProgramaAcademico;
import com.ud.componentes.model.ProgramaAcademicoDAO;

import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;

@Named("programaBean")
@ApplicationScoped
public class ProgramaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public List<ProgramaAcademico> getProgramas() {
        return ProgramaAcademicoDAO.listarProgramas();
    }
    
    public ProgramaAcademico buscarPorNombre(String nombre) {
        return ProgramaAcademicoDAO.buscarPrograma(nombre);
    }
}