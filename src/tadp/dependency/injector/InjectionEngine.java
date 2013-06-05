package tadp.dependency.injector;

import java.util.HashMap;
import java.util.Map;

import tadp.dependency.injector.constructionWays.ClassBuilder;
import tadp.dependency.injector.structure.ConfigStructure;

/**
 * El embrion es donde se gestan los objetos.. los mosquifrom, los que inyectan...
 * */
public class InjectionEngine {
	
	Map<Class<?>, ClassBuilder<?>> hows = new HashMap<Class<?>, ClassBuilder<?>>();
	
	private InjectionEngine(){		
	}
	
	public static InjectionEngine createEngine(ConfigStructure structure){
		InjectionEngine engine = new InjectionEngine();
		
		structure.configure(engine.hows);		
		
		return engine;
	}
	
	public <T> T sparkOfLife(Class<T> clazz){
		
		ClassBuilder<?> classBuilder = hows.get(clazz);
		
		if (classBuilder == null){
			return null;
		} 
		
		return (T)classBuilder.build(hows);
		
	}


}