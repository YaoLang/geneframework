package org.geneframework.context.mapping;

import com.sun.tools.javac.jvm.Gen;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.geneframework.context.cfg.Configuration;
import org.geneframework.context.cfg.GeneMethod;

public class FunctionMapping implements Mapping{
	
	private Set<GeneMethod> geneMethods;
	private Map<String, GeneMethod> mapping;
	
	public FunctionMapping(Set geneMethods){
		this.geneMethods = geneMethods;
		mapping = new HashMap<String, GeneMethod>();
		for(Iterator it = geneMethods.iterator() ; it.hasNext() ; ){
			Object o = it.next();
			GeneMethod geneMethod = (GeneMethod)o;
			mapping.put(String.valueOf(geneMethod.getMark()), geneMethod);
		}
	}
	
	public Object invoke(String key,Object[] paras) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		GeneMethod geneMethod = mapping.get(key);
		return geneMethod.invoke(paras);
	}

	public GeneMethod getValue(String key){
		return mapping.get(key);
	}

	@Override
	public Set getKeys(){
		return mapping.keySet();
	}


}
