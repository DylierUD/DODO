package com.ud.componentes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ud.componentes.model.ProgramaAcademico;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("programaBean")
@ApplicationScoped
public class ProgramaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ProgramaAcademico> programas = new ArrayList<>();

	@PostConstruct
	public void init() {
		programas = new ArrayList<>();
		programas.add(new ProgramaAcademico("Ingeniería de Sistemas", new ArrayList<>()));
		programas.add(new ProgramaAcademico("Ingeniería Industrial", new ArrayList<>()));
		programas.add(new ProgramaAcademico("Matemática", new ArrayList<>()));
		programas.add(new ProgramaAcademico("Administración de empresas", new ArrayList<>()));
		programas.add(new ProgramaAcademico("Derecho", new ArrayList<>()));
		programas.add(new ProgramaAcademico("Ingeniería Temática", new ArrayList<>()));
	}

	public List<ProgramaAcademico> getProgramas() {
		return programas;
	}

	public ProgramaAcademico buscarPorNombre(String nombre) {
		for (ProgramaAcademico pro : programas) {
			if (pro.getNombrePrograma().equals(nombre)) {
				return pro;
			}
		}
		return null;
	}
}