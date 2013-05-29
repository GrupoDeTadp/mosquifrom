package tadp.dependency.injector;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		boolean annotated = false;
		Class<?> underConstruction = this.parts.get(clazz);
		
		Constructor<?>[] constructors = underConstruction.getConstructors();
		
		for (Constructor<?> constructor : constructors) {
			if (constructor.isAnnotationPresent(ConectenloAMisVenas.class)){
				annotated = true;
				
				Class<?>[] parameterTypes = constructor.getParameterTypes();
				
				List<Object> paramz = new ArrayList<Object>();
				
				for (Class<?> paramClazz : parameterTypes) {
					paramz.add(this.get(paramClazz));
				}
				
				try {
					Object newInstance = constructor.newInstance(paramz.toArray( new Object[1] ));
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
		}
		
		if(!annotated){
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
	
	
	
	
}
