package tadp.dependency.injector.constructionWays;

import java.util.Map;

public abstract class ClassBuilder<T> {
	
	Class<T> underConstruction;
	Map<Class<?>, ClassBuilder<?>> hows;
	
	public T build(Map<Class<?>, ClassBuilder<?>> how) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
