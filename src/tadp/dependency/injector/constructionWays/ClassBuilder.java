package tadp.dependency.injector.constructionWays;

import java.lang.reflect.Constructor;
import java.util.Map;

import tadp.dependency.injector.ConectenloAMisVenas;
import tadp.dependency.injector.exception.noSeQueConstructorUsarException;

public abstract class ClassBuilder<T> {
	
	Class<T> underConstruction;
	Map<Class<?>, ClassBuilder<?>> hows;
	boolean enableAnnotation = true;
	
	public abstract T build(Map<Class<?>, ClassBuilder<?>> how);

	protected Constructor<?> elegirConstructor(Constructor<?>[] constructors) {
		Constructor<?> constructorElegido = null;
		boolean encontreMasDeuno = false;
		
		for (Constructor<?> constructor : constructors) {
			if (constructor.isAnnotationPresent(ConectenloAMisVenas.class) && enableAnnotation){
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
	
	abstract boolean puedoUsarEsteConstructor(Constructor<?> constructor);
	
}
