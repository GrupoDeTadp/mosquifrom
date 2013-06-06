package tadp.dependency.injector.constructionWays;

import java.util.Map;

public abstract class ClassBuilder<T> {
	
	protected Class<T> underConstruction;
	protected Map<Class<?>, ClassBuilder<?>> hows;
	protected boolean enableAnnotation = true;
	
	public abstract T build(Map<Class<?>, ClassBuilder<?>> how);

	public Class<T> getUnderConstruction() {
		return underConstruction;
	}

	public void setUnderConstruction(Class<T> partImpl) {
		this.underConstruction = partImpl;
	}
	
}
