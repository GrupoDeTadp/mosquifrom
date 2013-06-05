package tadp.dependency.injector.fixture.velador;

import java.util.List;

import tadp.dependency.injector.ConectenloAMisVenas;

public class Velador {

	private Enchufable enchufe;
	private Alumbrable luz;
	private String marca;
	private Integer alto;
	private List<Mampara> mamparas;
	
	@ConectenloAMisVenas
	public Velador(Alumbrable luz, Enchufable enchufe){
		this.luz = luz;
		this.enchufe = enchufe;
	}
	
	public Velador(){
		
	}

	public Enchufable getEnchufe() {
		return enchufe;
	}

	public void setEnchufe(Enchufable enchufe) {
		this.enchufe = enchufe;
	}

	public Alumbrable getLuz() {
		return luz;
	}

	public void setLuz(Alumbrable luz) {
		this.luz = luz;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getAlto() {
		return alto;
	}

	public void setAlto(Integer alto) {
		this.alto = alto;
	}

	public List<Mampara> getMamparas() {
		return mamparas;
	}

	public void setMamparas(List<Mampara> mamparas) {
		this.mamparas = mamparas;
	}
	
	
}
