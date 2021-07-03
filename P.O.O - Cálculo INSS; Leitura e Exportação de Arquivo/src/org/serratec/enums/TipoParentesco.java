package org.serratec.enums;

public enum TipoParentesco {
	FILHO("filho"), SOBRINHO("sobrinho"), OUTROS("Outros tipos de parentesco");

	private final String desc;
	
	TipoParentesco(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
