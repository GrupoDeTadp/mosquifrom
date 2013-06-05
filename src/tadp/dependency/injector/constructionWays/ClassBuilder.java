package tadp.dependency.injector.constructionWays;

import java.util.Map;

public abstract class ClassBuilder<T> {
	
	Class<T> underConstruction;
	Map<Class<?>, ClassBuilder<?>> hows;
	boolean enableAnnotation = true;
	
	public abstract T build(Map<Class<?>, ClassBuilder<?>> how);

	public Class<T> getUnderConstruction() {
		return underConstruction;
	}

	public void setUnderConstruction(Class<T> partImpl) {
		this.underConstruction = partImpl;
	}
	
}
