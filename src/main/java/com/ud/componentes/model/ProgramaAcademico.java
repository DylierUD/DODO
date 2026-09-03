package com.ud.componentes.model;

import java.io.Serializable;
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
		if (!aspirantes.contains(aspirante)) {
			aspirantes.add(aspirante);
			return true;
		}
		return false;
	}
	
	public boolean retirarAspirante(String idAspirante) {
		boolean eliminado = false;
		for (AspiranteDTO aspirante : aspirantes) {
			if (aspirante.getIdentificacion().equals(idAspirante)) {
				aspirantes.remove(aspirante);
				eliminado = true;
			}
		}
		return eliminado;
	}
	
	
}
