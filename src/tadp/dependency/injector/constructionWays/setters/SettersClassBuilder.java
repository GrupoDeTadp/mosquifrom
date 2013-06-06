package tadp.dependency.injector.constructionWays.setters;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tadp.dependency.injector.constructionWays.ClassBuilder;
import tadp.dependency.injector.exception.NecesitamosUnConstructorException;
import tadp.dependency.injector.exception.NoPudimosInyectarElSetterException;

public class SettersClassBuilder<T> extends ClassBuilder<T> {

	List<PropertyBuilder<?>> propertiesToInject = new ArrayList<PropertyBuilder<?>>();
	
	public SettersClassBuilder(Class<T> part, PropertyBuilder<?> propertyBuilder) {
		this.underConstruction = part;
		this.propertiesToInject.add(propertyBuilder);
	}
	
	public SettersClassBuilder(Class<T> part) {
		this.underConstruction = part;
	}

	@Override
	public T build(Map<Class<?>, ClassBuilder<?>> how) {
		T newInstance;
		try {
			newInstance = this.underConstruction.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new NecesitamosUnConstructorException(e,this.underConstruction);
		} 
	
		for (PropertyBuilder propBuilder : propertiesToInject) {
			Method method = this.lookForSetter(propBuilder,underConstruction.getMethods());
			
			if(method != null)
				try {
					method.invoke(newInstance, propBuilder.getValue(method,how));
				} catch (Exception e) {
					e.printStackTrace();
					throw new NoPudimosInyectarElSetterException(e);
				} 
		}
		
		return newInstance;
	}
	
	private Method lookForSetter(PropertyBuilder propBuilder, Method[] methods) {
		// TODO Auto-generated method stub
		for (Method method : methods) {
			if (this.isASetterForProperty(method, propBuilder.getPropertyName() ) ){
				return method;
			}					
		}
		
		return null;
	}

	public void addProperty(PropertyBuilder<?> propertyBuilder){
		this.propertiesToInject.add(propertyBuilder);
	}

	private boolean isASetterForProperty(Method method, String propertyName) {
		if(method.getName().length() > 3){
			boolean startWithSet =  "set".equals(method.getName().substring(0,3));
			
			if (startWithSet){
				String restOfName = method.getName().substring(3);
				return restOfName.toLowerCase().equals(propertyName);
			}
		}
		
		return false;
	}

}
