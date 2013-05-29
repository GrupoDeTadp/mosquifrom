package tadp.dependency.injector.fixture;

import java.util.ArrayList;
import java.util.List;

import tadp.dependency.injector.ConectenloAMisVenas;

public class Ensalada {

	List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
	
	@ConectenloAMisVenas
	public Ensalada(Ingrediente ingrediente){
		this.ingredientes.add(ingrediente);
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
}
