package tadp.dependency.injector;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tadp.dependency.injector.constructionWays.ClassBuilder;
import tadp.dependency.injector.exception.noSeQueConstructorUsarException;

/**
 * El embrion es donde se gestan los objetos.. los mosquifrom, los que inyectan...
 * */
public class InjectionEngine {
	
	Map<Class<?>, ClassBuilder<?>> hows;
	
	private InjectionEngine(){		
	}
	
	public static InjectionEngine createEngine(InjectionStructure structure){
		InjectionEngine engine = new InjectionEngine();
		
		engine.hows = structure.organize();		
		
		return engine;
	}
	
	public <T> T sparkOfLife(Class<T> clazz){
		return this.get(clazz);
	}
	
	public <T> T get(Class<T> clazz){
		
		ClassBuilder<?> classBuilder = hows.get(clazz);
		
		if (classBuilder == null){
			return null;
		} 
		
		return (T)classBuilder.build(hows);
		
	}


}