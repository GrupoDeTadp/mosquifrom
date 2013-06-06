package tadp.dependency.injector.constructionWays.setters;

import java.lang.reflect.Method;
import java.util.Map;

import tadp.dependency.injector.constructionWays.ClassBuilder;

public class PropertyValueBuilder<T> implements PropertyBuilder<T> {

	String propertyName;
	Object value;
	
	public PropertyValueBuilder(String propertyName, Object value) {
		this.propertyName = propertyName;
		this.value = value;
	}

	@Override
	public Object getValue(Method method, Map<Class<?>, ClassBuilder<?>> hows) {
		return this.value;
	}

	@Override
	public String getPropertyName() {
		return this.propertyName;
	}

}
