package tadp.dependency.injector.fixture;

import java.util.ArrayList;
import java.util.List;

public class EnsaladaErronea {

	List<Ingrediente> ingredientes;
	
	public EnsaladaErronea(){
		this.ingredientes = new ArrayList<Ingrediente>();
	}
	
	public EnsaladaErronea(Ingrediente ingrediente){
		this.ingredientes = new ArrayList<Ingrediente>();
		this.ingredientes.add(ingrediente);
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	
	
}
