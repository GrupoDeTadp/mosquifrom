package tadp.dependency.injector.constructionWays;

import java.lang.reflect.Constructor;
import java.util.Map;

import tadp.dependency.injector.InjectionParameter;
import tadp.dependency.injector.exception.NoPudimosCrearLaInstanciaException;

public class ConstructorWithParamsBuilder<T> extends ConstructorClassBuilder<T> {

	InjectionParameter[] params;
	
	public ConstructorWithParamsBuilder(Class<T> clazz, InjectionParameter... parmas){
		super(clazz);
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
	protected boolean puedoUsarEsteConstructor(Constructor<?> constructor) {
		Class<?>[] parameterTypes = constructor.getParameterTypes();
		boolean hasSameParams = false;
		int i = 0;
		
		for (Class<?> paramClass : parameterTypes) {
			hasSameParams = hasSameParams || ( this.params[i].getClazz().equals(paramClass) );
			
			if (!hasSameParams) 
				break;
			
			i++;
		}		
		
		return hasSameParams;
	}

}
