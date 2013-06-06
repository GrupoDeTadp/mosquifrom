package tadp.dependency.injector.constructionWays.setters;

import java.lang.reflect.Method;
import java.util.Map;

import tadp.dependency.injector.constructionWays.ClassBuilder;

public interface PropertyBuilder<T> {

	public Object getValue(Method method, Map<Class<?>, ClassBuilder<?>> hows);

	public String getPropertyName();
	
}
