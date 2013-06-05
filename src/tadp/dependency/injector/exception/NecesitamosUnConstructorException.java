package tadp.dependency.injector.exception;

public class NecesitamosUnConstructorException extends RuntimeException {

	public NecesitamosUnConstructorException(Exception e, Class<?> underConstruction) {
		super(e);
	}

}
