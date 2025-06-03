package net.serikat.practicas.beans;

public class ErrorApi {

	private String campo;
	private String valorOriginal;
	private String mensaje;

	public ErrorApi(String nombreCampo, String valorOriginal, String mensaje) {
		// TODO Auto-generated constructor stub
		this.campo= nombreCampo;
		this.valorOriginal = valorOriginal;
		this.mensaje = mensaje;
	}

	public ErrorApi() {
		// TODO Auto-generated constructor stub
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(String valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

}
