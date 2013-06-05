package tadp.dependency.injector.constructionWays;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tadp.dependency.injector.exception.NecesitamosUnConstructorException;
import tadp.dependency.injector.exception.NoPudimosInyectarElSetterException;

public class SettersClassBuilder<T> extends ClassBuilder<T> {

	List<String> propertiesToInject = new ArrayList<String>();
	
	public SettersClassBuilder(Class<T> part, String propertyName) {
		this.underConstruction = part;
		this.propertiesToInject.add(propertyName);
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
	
		
		Method[] methods = this.underConstruction.getMethods();
		for (Method method : methods) {
			if (isASetterForMyProperties(method)){
				Class<?>[] parameterTypes = method.getParameterTypes();
				for (Class<?> paramClass : parameterTypes) {
					try {
						method.invoke(newInstance, how.get(paramClass).build(how));
					} catch (Exception e) {
						e.printStackTrace();
						throw new NoPudimosInyectarElSetterException(e);
					}
				}
			}
		}
		
		return newInstance;
	}
	
	public void addProperty(String propertyName){
		this.propertiesToInject.add(propertyName);
	}

	private boolean isASetterForMyProperties(Method method) {
		if(method.getName().length() > 3){
			boolean startWithSet =  "set".equals(method.getName().substring(0,3));
			
			if (startWithSet){
				String propertyName = method.getName().substring(3);
				return this.propertiesToInject.contains(propertyName.toLowerCase());
			}
		}
		
		return false;
	}

}
