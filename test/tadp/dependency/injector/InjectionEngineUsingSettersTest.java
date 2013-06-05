package tadp.dependency.injector;

import junit.framework.Assert;

import org.junit.Test;

import tadp.dependency.injector.fixture.velador.LamparitasOsram;
import tadp.dependency.injector.fixture.velador.SetterVeladorConOsramStructure;
import tadp.dependency.injector.fixture.velador.Velador;
import tadp.dependency.injector.structure.SetterInjectionStructure;

public class InjectionEngineUsingSettersTest {

	@Test
	public void inyectarUnNombreYUnaMarcaAlVeladorConLamapritaOsram() {
		SetterInjectionStructure structure = new SetterVeladorConOsramStructure();
		
		InjectionEngine ebrion = InjectionEngine.createEngine(structure);
		Velador velador = ebrion.sparkOfLife(Velador.class);
		
		Assert.assertTrue(velador.getLuz() instanceof LamparitasOsram);
			
	}

}
