package tadp.dependency.injector.fixture.velador;

public abstract class Lamparitas implements Alumbrable {
	
	int potencia;
	
	@Override
	public abstract void encender();
	
	@Override
	public abstract void apagar();
	
	@Override
	public abstract void regularPotencia();
}
