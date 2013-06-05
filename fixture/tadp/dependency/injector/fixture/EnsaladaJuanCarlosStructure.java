package tadp.dependency.injector.fixture;

import tadp.dependency.injector.InjectionParameter;
import tadp.dependency.injector.structure.InjectionStructure;

public class EnsaladaJuanCarlosStructure extends InjectionStructure {

	@Override
	public void defineParts() {
		this.define(Ensalada.class, Ensalada.class, new InjectionParameter("Juan Carlos"));
	}

}
