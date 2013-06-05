package tadp.dependency.injector.fixture.velador;

import tadp.dependency.injector.structure.InjectionStructure;

public class VeladorConOsramStructure extends InjectionStructure {

	@Override
	public void defineParts() {
		this.define(Velador.class, Velador.class);
		this.define(Alumbrable.class, LamparitasOsram.class);
		this.define(Enchufable.class, EnchufeTresPatas.class);
	}

}
