package tadp.dependency.injector.constructionWays.setters;

import java.lang.reflect.Method;
import java.util.Map;

import tadp.dependency.injector.constructionWays.ClassBuilder;
import tadp.dependency.injector.constructionWays.SimpleConstructorClassBuilder;

public class PropertyClassBuilder<T> implements PropertyBuilder<T> {

	Class<T> underConstruction;
	String propertyName;
	
	public PropertyClassBuilder(Class<T> clazz){
		this.underConstruction = clazz;
	}
	
	public PropertyClassBuilder(String propertyName){
		this.propertyName = propertyName;
	}

	@Override
	public Object getValue(Method method, Map<Class<?>, ClassBuilder<?>> how) {
		Class<?>[] parameterTypes = method.getParameterTypes();
		
		if(parameterTypes.length == 1){
			return how.get(parameterTypes[0]).build(how);
		}
		
		return null;
	}

	public Class<T> getUnderConstruction() {
		return underConstruction;
	}

	public void setUnderConstruction(Class<T> underConstruction) {
		this.underConstruction = underConstruction;
	}

	@Override
	public String getPropertyName() {
		return propertyName;
	}
	
	
}
