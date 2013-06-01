package tadp.dependency.injector.fixture;

import tadp.dependency.injector.InjectionStructure;

public class EnsaladaErroneaStructure extends InjectionStructure {

	@Override
	public void defineParts() {
		// TODO Auto-generated method stub
		this.define(Ingrediente.class, Papa.class);
		this.define(EnsaladaErronea.class, EnsaladaErronea.class);
	}

}
