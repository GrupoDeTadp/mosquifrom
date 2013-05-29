package tadp.dependency.injector;

import java.util.HashMap;
import java.util.Map;

public abstract class EmbrionStructure {
	Map<Class<?>,Class<?>> parts = new HashMap<Class<?>,Class<?>>();
	
	public Map<Class<?>, Class<?>> organize(){
		this.defineParts();
		return parts;
	}

	abstract public void defineParts();
	
	protected void define(Class<?> part, Class<?> partImpl){
		this.parts.put(part, partImpl);
	}
	
}
