package tadp.dependency.injector;

public class InjectionParameter{

	Class<?> clazz;
	Object instance;
	
	public InjectionParameter(Object object){
		this.clazz = object.getClass();
		this.instance = object;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public Object getObject() {
		return instance;
	}
	
}
