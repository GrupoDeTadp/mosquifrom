package tadp.dependency.injector.constructionWays;

import java.lang.reflect.Constructor;
import java.util.Map;

import tadp.dependency.injector.InjectionParameter;

public class ConstructorWithParamsBuilder<T> extends ClassBuilder<T> {

	InjectionParameter[] params;
	
	public ConstructorWithParamsBuilder(Class<T> clazz, InjectionParameter... parmas){
		this.underConstruction = clazz;
		params = parmas;
		enableAnnotation = false;
	}
	
	@Override
	public T build(Map<Class<?>, ClassBuilder<?>> how) {
		Constructor<?>[] constructors = underConstruction.getConstructors();
		
		Constructor<?> constructorElegido = this.elegirConstructor(constructors);
		
		if(constructorElegido != null){
			try {
				return (T)constructorElegido.newInstance(this.getObjects(params));
			} catch (Exception e) {
				e.printStackTrace();
				throw new NoPudimosCrearLaInstanciaException(e);
			}
		}
		
		return null;
	}

	private Object[] getObjects(InjectionParameter[] params) {
		Object[] objects = new Object[params.length];
		int i = 0;
		for (InjectionParameter injectionParameter : params) {
			objects[i] = injectionParameter.getObject();
			i++;
		}
		return objects;
	}

	@Override
	boolean puedoUsarEsteConstructor(Constructor<?> constructor) {
		Class<?>[] parameterTypes = constructor.getParameterTypes();
		boolean hasSameParams = false;
		int i = 0;
		
		for (Class<?> paramClass : parameterTypes) {
			hasSameParams = hasSameParams || ( this.params[i].getClazz().equals(paramClass) );
			
			if (!hasSameParams) 
				break;
			
			i++;
		}		
		
		System.out.println("Puedo este " + constructor + " " + hasSameParams);
		return hasSameParams;
	}

}
