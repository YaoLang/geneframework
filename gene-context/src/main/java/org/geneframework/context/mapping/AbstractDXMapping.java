package org.geneframework.context.mapping;

import org.geneframework.context.cfg.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractDXMapping implements Mapping{
	
	private Map mapping;
	
	public AbstractDXMapping(int length,double low,double height){
		mapping = new HashMap<String, Double>();
		char a = '0';
		for(int i = 0 ; i < length ; i ++,a ++){
			Double r = Double.valueOf(random(low, height));
			mapping.put(String.valueOf(a), r);
		}

	}
	
	private final static double random(double low,double height){
		double r = (double) (Math.random()*height);
		if(r>low)
			return r;
		else
			return random(low, height);
	}
	
	public double getValue(String key){
		return (double)mapping.get(key);
	}
	
	@Override
	public Set getKeys(){
		return mapping.keySet();
	}


}
