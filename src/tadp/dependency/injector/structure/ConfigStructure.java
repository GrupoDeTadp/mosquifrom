package tadp.dependency.injector.structure;

import java.util.Map;

import tadp.dependency.injector.constructionWays.ClassBuilder;

public interface ConfigStructure {

	public void configure(Map<Class<?>, ClassBuilder<?>> hows);
	
}
