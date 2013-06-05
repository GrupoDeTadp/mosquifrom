package tadp.dependency.injector.constructionWays;

import java.util.Map;

public class SimpleConstructorClassBuilder<T> extends ClassBuilder<T> {

	public SimpleConstructorClassBuilder(Class<T> partImpl) {
		this.underConstruction = partImpl;
	}

	@Override
	public T build(Map<Class<?>, ClassBuilder<?>> how) {
		T o = null;
		try {
			o = this.underConstruction.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return o;
	}

}
