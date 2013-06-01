package tadp.dependency.injector;

import junit.framework.Assert;

import org.junit.Test;

import tadp.dependency.injector.exception.noSeQueConstructorUsarException;
import tadp.dependency.injector.fixture.Ensalada;
import tadp.dependency.injector.fixture.EnsaladaErronea;
import tadp.dependency.injector.fixture.EnsaladaErroneaStructure;
import tadp.dependency.injector.fixture.EnsaladaStructure;
import tadp.dependency.injector.fixture.Ingrediente;
import tadp.dependency.injector.fixture.Papa;
import tadp.dependency.injector.fixture.velador.LamparitasOsram;
import tadp.dependency.injector.fixture.velador.Velador;
import tadp.dependency.injector.fixture.velador.VeladorConOsramStructure;

public class InjectionEngineTest {

	@Test
	public void unIngredienteEsUnaPapa() {
		
		InjectionStructure structure = new EnsaladaStructure();
		
		InjectionEngine ebrion = InjectionEngine.createEngine(structure);
		
		Ingrediente papa = ebrion.sparkOfLife(Ingrediente.class);
		
		Assert.assertNotNull(papa);		
		Assert.assertTrue(papa instanceof Papa);
	}
	
	@Test
	public void unaEnsaladaIngredienteEsUnaPapa() {
		InjectionStructure structure = new EnsaladaStructure();
		
		InjectionEngine ebrion = InjectionEngine.createEngine(structure);
		
		Ensalada ensalada = ebrion.sparkOfLife(Ensalada.class);
		
		Assert.assertNotNull(ensalada);
		Assert.assertEquals(1, ensalada.getIngredientes().size());
		
		Assert.assertTrue(ensalada.getIngredientes().get(0) instanceof Papa);
		
	}

	@Test
	public void unaEnsaladaQueNoSePuedeArmar() {
		InjectionStructure structure = new EnsaladaErroneaStructure();
		
		InjectionEngine ebrion = InjectionEngine.createEngine(structure);
		
		try{
			EnsaladaErronea ensalada = ebrion.sparkOfLife(EnsaladaErronea.class);
			Assert.fail();
		}catch(noSeQueConstructorUsarException no){
			
		}
		
		
	}

	@Test
	public void puedoConstruirunaVeladorConLamparitaOsram() {
		InjectionStructure structure = new VeladorConOsramStructure();
		
		InjectionEngine ebrion = InjectionEngine.createEngine(structure);
		Velador velador = ebrion.get(Velador.class);
		
		Assert.assertTrue(velador.getLuz() instanceof LamparitasOsram);
		
	}
	
}