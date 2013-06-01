package tadp.dependency.injector.constructionWays;

public class NoPudimosCrearLaInstanciaException extends RuntimeException {

	Exception e;
	
	public NoPudimosCrearLaInstanciaException(Exception e) {
		this.e = e;
	}

}
