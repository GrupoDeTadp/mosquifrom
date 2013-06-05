package tadp.dependency.injector.exception;

public class NoPudimosCrearLaInstanciaException extends RuntimeException {

	Exception e;
	
	public NoPudimosCrearLaInstanciaException(Exception e) {
		this.e = e;
	}

}
