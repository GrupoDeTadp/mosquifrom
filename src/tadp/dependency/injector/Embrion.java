package tadp.dependency.injector;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tadp.dependency.injector.exception.noSeQueConstructorUsarException;

/**
 * El embrion es donde se gestan los objetos.. los mosquifrom, los que inyectan...
 * */
public class Embrion {
	
	Map<Class<?>, Class<?>> parts;
	
	private Embrion(){		
	}
	
	public static Embrion createEmbrion(EmbrionStructure structure){
		Embrion embrion = new Embrion();
		
		embrion.parts = structure.organize();		
		
		return embrion;
	}
	
	public <T> T sparkOfLife(Class<T> clazz){
		return this.get(clazz);
	}
	
	public <T> T get(Class<T> clazz){
		Class<?> underConstruction = this.parts.get(clazz);
		
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
						paramz.add(this.get(paramClazz));
					}
					newInstance = constructorElegido.newInstance(paramz.toArray( new Object[1] ));
				}

				return (T) newInstance;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				return (T) underConstruction.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
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
			ret = ret && this.parts.containsKey(clazz);
		}
		return ret;
	}
}