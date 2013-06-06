package tadp.dependency.injector.structure;

import java.util.HashMap;
import java.util.Map;

import tadp.dependency.injector.InjectionParameter;
import tadp.dependency.injector.constructionWays.ClassBuilder;
import tadp.dependency.injector.constructionWays.SimpleConstructorClassBuilder;
import tadp.dependency.injector.constructionWays.setters.PropertyClassBuilder;
import tadp.dependency.injector.constructionWays.setters.PropertyClassListBuilder;
import tadp.dependency.injector.constructionWays.setters.PropertyValueBuilder;
import tadp.dependency.injector.constructionWays.setters.SettersClassBuilder;
import tadp.dependency.injector.fixture.velador.MamparaConDibujitos;
import tadp.dependency.injector.fixture.velador.MamparaSobria;
import tadp.dependency.injector.fixture.velador.Velador;

public abstract class SetterInjectionStructure extends InjectionStructure implements ConfigStructure {

	Map<Class<?>, SettersClassBuilder<?>> propertiesHows = new HashMap<Class<?>, SettersClassBuilder<?>>();
	
	protected <T> void injectProperty(Class<T> part, String propertyName ){
		SettersClassBuilder<T> classBuilder = getSetterClassBuilder(part, part);

		classBuilder.addProperty(new PropertyClassBuilder<T>(propertyName));
	}
	
	protected <T> void injectProperty(Class<T> part, String propertyName, Object value) {
		SettersClassBuilder<T> classBuilder = getSetterClassBuilder(part, part);
		
		classBuilder.addProperty(new PropertyValueBuilder<T>(propertyName,value));
	}
	
	protected <T> void injectPropertyList(Class<T> part, String propertyName, Class<?>...classes) {
		SettersClassBuilder<T> classBuilder = getSetterClassBuilder(part, part);
		
		classBuilder.addProperty(new PropertyClassListBuilder<T>(propertyName,classes));
	}

	
	@Override
	protected <T> void define(Class<?> part, Class<T> partImpl){
		SettersClassBuilder<T> classBuilder = getSetterClassBuilder(part, partImpl);
		
		classBuilder.setUnderConstruction(partImpl);
	}

	@Override
	protected <T> void define(Class<?> part, Class<T> partImpl, InjectionParameter... parameters){
		this.define(part, partImpl);
	}

	@Override
	public void configure(Map<Class<?>, ClassBuilder<?>> hows) {
		this.defineParts();
		hows.putAll(this.propertiesHows);
	}

	private <T> SettersClassBuilder<T> getSetterClassBuilder(Class<?> part,	Class<T> partImpl) {
		SettersClassBuilder<T> classBuilder = (SettersClassBuilder<T>) this.propertiesHows.get(part);
		if(classBuilder == null){
			classBuilder = new SettersClassBuilder<T>(partImpl); 
			this.propertiesHows.put(part, classBuilder);
		}
		return classBuilder;
	}
	
	abstract public void defineParts();
}
