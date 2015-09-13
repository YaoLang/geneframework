package org.geneframework.context.cfg;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GeneMethod {
	
	private Class clazz;
	private Method method;
	private char mark;
	private int elementnums;
	
	public GeneMethod(String methodName,String mark) throws NoSuchMethodException{
		this.clazz = java.lang.Math.class;
		Method[] methods = clazz.getMethods();
		int i;
		for(i = 0 ; i < methods.length ; i ++){
			if(methodName.equals(methods[i].getName())){
				this.method = methods[i];
				break;
			}
		}
		if(i>=methods.length)
			throw new NoSuchMethodException();
		if(mark==null)
			this.mark = methodName.toUpperCase().charAt(0);
		else
			this.mark = mark.charAt(0);
		this.elementnums = method.getParameterCount();
	}
	
	public GeneMethod(String className,String method,String mark){
		ParasMapping parasMapping = new ParasMapping();
		try {
			clazz = Class.forName(className);
			Method[] methods = clazz.getMethods();
			int i;
			for(i = 0 ; i < methods.length ; i ++){
				if(method.equals(methods[i].getName())){
					this.method = methods[i];
					break;
				}
			}
			if(i>=methods.length)
				throw new NoSuchMethodException();
			this.mark = mark.charAt(0);
			this.elementnums = this.method.getParameterCount();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
	}
	
	public Object invoke(Object[] paras) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return method.invoke(clazz, paras);
	}

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public char getMark() {
		return mark;
	}

	public void setMark(char mark) {
		this.mark = mark;
	}

	public int getElementnums() {
		return elementnums;
	}

	public void setElementnums(int elementnums) {
		this.elementnums = elementnums;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mark;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeneMethod other = (GeneMethod) obj;
		if (mark != other.mark)
			return false;
		return true;
	}
	
	
}
