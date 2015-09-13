package org.geneframework.context.cfg;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class ParasMapping {
	
	Map<String, Class> clazz;
	
	public ParasMapping(){
		clazz = new HashMap<String, Class>();
		clazz.put("double", double.class);
		clazz.put("int", int.class);
		clazz.put("short", short.class);
		clazz.put("long", long.class);
		clazz.put("float", float.class);
		clazz.put("bool", boolean.class);
	}
	
	public Class getClass(String key) throws ClassNotFoundException{
		if(clazz.containsKey(key))
			return clazz.get(key);
		else{
			addMapping(key);
			return clazz.get(key);
		}
	}
	
	public void addMapping(String classname) throws ClassNotFoundException{
		clazz.put(classname, Class.forName(classname));
	}
	
	
}
