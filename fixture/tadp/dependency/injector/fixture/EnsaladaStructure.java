package tadp.dependency.injector.fixture;

import tadp.dependency.injector.EmbrionStructure;

public class EnsaladaStructure extends EmbrionStructure {

	@Override
	public void defineParts() {
		this.define(Ingrediente.class, Papa.class);
		this.define(Ensalada.class, Ensalada.class);
	}

}
