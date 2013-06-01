package tadp.dependency.injector.fixture.velador;

public class Velador {

	private Enchufable enchufe;
	private Alumbrable luz;

	public Velador(Alumbrable luz, Enchufable enchufe){
		this.luz = luz;
		this.enchufe = enchufe;
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
	
	
}
