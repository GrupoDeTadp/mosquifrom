package tadp.dependency.injector;

import java.util.HashMap;
import java.util.Map;

import tadp.dependency.injector.constructionWays.ClassBuilder;
import tadp.dependency.injector.constructionWays.ConstructorClassBuilder;
import tadp.dependency.injector.constructionWays.ConstructorWithParamsBuilder;

public abstract class InjectionStructure {
	Map<Class<?>, ClassBuilder<?>> hows = new HashMap<Class<?>, ClassBuilder<?>>();
	
	public Map<Class<?>, ClassBuilder<?>> organize(){
		this.defineParts();
		return hows;
	}

	abstract public void defineParts();
	
	protected <T> void define(Class<?> part, Class<T> partImpl){
		this.hows.put(part, new ConstructorClassBuilder<T>(partImpl));
	}
	
	protected <T> void define(Class<T> part){
		this.hows.put(part, new ConstructorClassBuilder<T>(part));
	}
	
	protected <T> void define(Class<?> part, Class<T> partImpl, InjectionParameter... parameters){
		this.hows.put(part, new ConstructorWithParamsBuilder<T>(partImpl, parameters));
	}
	
}
