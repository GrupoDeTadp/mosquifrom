package tadp.dependency.injector.fixture;

import tadp.dependency.injector.EmbrionStructure;

public class EnsaladaErroneaStructure extends EmbrionStructure {

	@Override
	public void defineParts() {
		// TODO Auto-generated method stub
		this.define(Ingrediente.class, Papa.class);
		this.define(EnsaladaErronea.class, EnsaladaErronea.class);
	}

}
