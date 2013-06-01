package tadp.dependency.injector.fixture;

import tadp.dependency.injector.InjectionStructure;

public class EnsaladaStructure extends InjectionStructure {

	@Override
	public void defineParts() {
		this.define(Ingrediente.class, Papa.class);
		this.define(Ensalada.class, Ensalada.class);
	}

}
