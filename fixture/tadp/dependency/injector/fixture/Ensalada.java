package tadp.dependency.injector.fixture;

import java.util.ArrayList;
import java.util.List;

import tadp.dependency.injector.ConectenloAMisVenas;

public class Ensalada {

	List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
	String nombre;
	
	@ConectenloAMisVenas
	public Ensalada(Ingrediente ingrediente){
		this.ingredientes.add(ingrediente);
	}
	
	public Ensalada(String nombre){
		this.nombre = nombre;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getNombre() {
		return nombre;
	}
	
}
