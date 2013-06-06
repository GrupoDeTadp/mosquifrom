package tadp.dependency.injector.fixture.velador;

import tadp.dependency.injector.structure.SetterInjectionStructure;

public class SetterVeladorConOsramStructure extends SetterInjectionStructure {

	@Override
	public void defineParts() {
		this.define(Velador.class, Velador.class);
		this.define(Alumbrable.class, LamparitasOsram.class);
		this.define(Mampara.class, MamparaSobria.class);
		this.define(MamparaConDibujitos.class, MamparaConDibujitos.class);
		
		
		this.injectProperty(Velador.class, "luz");
		this.injectProperty(Velador.class, "marca", "Cuchuflo");
		
		this.injectPropertyList(Velador.class, "mamparas", MamparaConDibujitos.class, Mampara.class);
		
	}

	
	
}
