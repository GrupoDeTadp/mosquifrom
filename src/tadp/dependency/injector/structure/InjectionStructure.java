package tadp.dependency.injector.structure;

import java.util.HashMap;
import java.util.Map;

import tadp.dependency.injector.InjectionParameter;
import tadp.dependency.injector.constructionWays.ClassBuilder;
import tadp.dependency.injector.constructionWays.ConstructorClassBuilder;
import tadp.dependency.injector.constructionWays.ConstructorWithParamsBuilder;
import tadp.dependency.injector.constructionWays.SettersClassBuilder;

public abstract class InjectionStructure implements ConfigStructure {
	Map<Class<?>, ClassBuilder<?>> hows = new HashMap<Class<?>, ClassBuilder<?>>();
	
	@Override
	public void configure(Map<Class<?>, ClassBuilder<?>> hows) {
		this.defineParts();
		hows.putAll(this.hows);
	}

	abstract public void defineParts();
	
	protected <T> void define(Class<?> part, Class<T> partImpl){
		this.hows.put(part, new ConstructorClassBuilder<T>(partImpl));
	}
	
	protected <T> void define(Class<T> part){
		this.define(part, part);
	}
	
	protected <T> void define(Class<?> part, Class<T> partImpl, InjectionParameter... parameters){
		this.hows.put(part, new ConstructorWithParamsBuilder<T>(partImpl, parameters));
	}
	
}
