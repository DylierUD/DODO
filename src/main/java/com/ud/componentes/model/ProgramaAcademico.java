package com.ud.componentes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProgramaAcademico implements Serializable{

	
	private static final long serialVersionUID = 4955141732410652831L;
	private String nombrePrograma;
	private List<AspiranteDTO> aspirantes;
	
	public ProgramaAcademico() {
		super();
	}

	public ProgramaAcademico(String nombrePrograma, List<AspiranteDTO> aspirantes) {
		super();
		this.nombrePrograma = nombrePrograma;
		this.aspirantes = aspirantes;
	}
	
	public ProgramaAcademico(String nombrePrograma) {
	    this.nombrePrograma = nombrePrograma;
	    this.aspirantes = new ArrayList<>();
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public List<AspiranteDTO> getAspirantes() {
		return aspirantes;
	}

	public void setAspirantes(List<AspiranteDTO> aspirantes) {
		this.aspirantes = aspirantes;
	}

	@Override
	public String toString() {
		return "ProgramaAcademico [nombrePrograma=" + nombrePrograma + ", aspirantes=" + aspirantes.toString() + "]";
	}
	
	public boolean postularAspirante(AspiranteDTO aspirante) {
		for (AspiranteDTO aspiranteDTO : aspirantes) {
			if (aspiranteDTO.getIdentificacion().equals(aspirante.getIdentificacion())) {
				return false;
			}
		}	
		aspirantes.add(aspirante);
		return true;
	}
	
	public boolean retirarAspirante(String idAspirante) {
		for (int i = 0; i < aspirantes.size(); i++) {

	        AspiranteDTO aspirante = aspirantes.get(i);

	        if (aspirante.getIdentificacion().equals(idAspirante)) {
	            aspirantes.remove(i);
	            return true;
	        }
	    }
		return false;
	}
	
	
}
