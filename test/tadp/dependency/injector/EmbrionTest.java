package tadp.dependency.injector;

import junit.framework.Assert;

import org.junit.Test;

import tadp.dependency.injector.fixture.Ensalada;
import tadp.dependency.injector.fixture.EnsaladaStructure;
import tadp.dependency.injector.fixture.Ingrediente;
import tadp.dependency.injector.fixture.Papa;

public class EmbrionTest {

	@Test
	public void unIngredienteEsUnaPapa() {
		
		EmbrionStructure structure = new EnsaladaStructure();
		
		Embrion ebrion = Embrion.createEmbrion(structure);
		
		Ingrediente papa = ebrion.sparkOfLife(Ingrediente.class);
		
		Assert.assertNotNull(papa);		
		Assert.assertTrue(papa instanceof Papa);
	}
	
	@Test
	public void unaEnsaladaIngredienteEsUnaPapa() {
		EmbrionStructure structure = new EnsaladaStructure();
		
		Embrion ebrion = Embrion.createEmbrion(structure);
		
		Ensalada ensalada = ebrion.sparkOfLife(Ensalada.class);
		
		Assert.assertNotNull(ensalada);
		Assert.assertEquals(1, ensalada.getIngredientes().size());
		
		Assert.assertTrue(ensalada.getIngredientes().get(0) instanceof Papa);
		
	}

}
