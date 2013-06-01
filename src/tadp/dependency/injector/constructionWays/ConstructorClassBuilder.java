package tadp.dependency.injector.constructionWays;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tadp.dependency.injector.ConectenloAMisVenas;
import tadp.dependency.injector.exception.noSeQueConstructorUsarException;

public class ConstructorClassBuilder<T> extends ClassBuilder<T> {

	public ConstructorClassBuilder(Class<T> clazz){
		this.underConstruction = clazz;
	}
	
	@Override
	public T build(Map<Class<?>, ClassBuilder<?>> how) {
		this.hows = how;
		
		Constructor<?>[] constructors = underConstruction.getConstructors();
		
		Object newInstance;
		Constructor<?> constructorElegido = this.elegirConstructor(constructors);
		
		if( constructorElegido != null ){
			Class<?>[] parameterTypes = constructorElegido.getParameterTypes();
			
			try {
				if(parameterTypes.length == 0){
					newInstance = constructorElegido.newInstance();
				}else{		
					List<Object> paramz = new ArrayList<Object>();
					
					for (Class<?> paramClazz : parameterTypes) {
						ClassBuilder<?> classBuilder = this.hows.get(paramClazz);
						paramz.add(classBuilder.build(this.hows));
						
					}
					newInstance = constructorElegido.newInstance(paramz.toArray( new Object[1] ));
				}

				return (T) newInstance;
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		return null;
	}
	
	private Constructor<?> elegirConstructor(Constructor<?>[] constructors) {
		Constructor<?> constructorElegido = null;
		boolean encontreMasDeuno = false;
		
		for (Constructor<?> constructor : constructors) {
			if (constructor.isAnnotationPresent(ConectenloAMisVenas.class)){
				constructorElegido = constructor;
				encontreMasDeuno = false;
				break;
			}else if (puedoUsarEsteConstructor(constructor)){
				if( constructorElegido == null){
					constructorElegido = constructor;
				}else{
					encontreMasDeuno = true;
				}
			}
		}
		
		if(encontreMasDeuno)
			throw new noSeQueConstructorUsarException();
		
		return constructorElegido;
	}

	private boolean puedoUsarEsteConstructor(Constructor<?> constructor) {
		Class<?>[] parameterTypes = constructor.getParameterTypes();
		boolean ret = true;
		
		for (Class<?> clazz : parameterTypes) {
			ret = ret && this.hows.containsKey(clazz);
		}
		return ret;
	}

}
