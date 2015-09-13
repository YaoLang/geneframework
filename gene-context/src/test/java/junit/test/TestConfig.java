package junit.test;

import java.lang.reflect.InvocationTargetException;

import org.geneframework.context.cfg.GeneMethod;
import org.geneframework.context.cfg.Setting;
import org.geneframework.context.util.PropertiesHelper;
import org.geneframework.context.util.XMLHelper;
import org.junit.Test;

public class TestConfig {
	
	@Test
	public void testMethod(){
		
		String classname = "java.lang.Math";
		String methodname = "abs";
		String mark = "A";
		String[] paras = {"double"};
		GeneMethod geneMethod = new GeneMethod(classname, methodname, mark);
		Object[] c = {2};
		try {
			Object o = geneMethod.invoke(c);
			System.out.println(o);
			System.out.println(geneMethod.getMark());
			System.out.println(geneMethod.getMethod());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}  catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLangMethod() throws NoSuchMethodException{
		String methodname = "log";
		GeneMethod ge = new GeneMethod(methodname,null);
		Object[] c = {4};
		try {
			Object o = ge.invoke(c);
			System.out.println(o);
			System.out.println(ge.getMark());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}  catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testProperties(){
		Setting setting = new Setting();
		PropertiesHelper prop = new PropertiesHelper(setting, "gene.properties");
		XMLHelper helper = new XMLHelper("gene-function.xml");

	}
}
