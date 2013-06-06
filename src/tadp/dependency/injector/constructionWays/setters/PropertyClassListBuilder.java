package tadp.dependency.injector.constructionWays.setters;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tadp.dependency.injector.constructionWays.ClassBuilder;

public class PropertyClassListBuilder<T> implements PropertyBuilder<T> {

	String propertyName;
	Class<?>[] classes;
	
	public PropertyClassListBuilder(String propertyName, Class<?>[] classes) {
		this.propertyName = propertyName;
		this.classes = classes;
	}

	@Override
	public Object getValue(Method method, Map<Class<?>, ClassBuilder<?>> hows) {
		List<Object> objects = new ArrayList<Object>();
		
		for (Class<?> clazz : this.classes) {
			objects.add( hows.get(clazz).build(hows) );	
		}
		
		return objects;
	}

	@Override
	public String getPropertyName() {
		return this.propertyName;
	}

}
