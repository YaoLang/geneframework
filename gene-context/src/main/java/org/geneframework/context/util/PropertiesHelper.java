package org.geneframework.context.util;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.geneframework.context.cfg.Setting;

public final class PropertiesHelper {

	Properties props;
	private Logger log = Logger.getLogger(org.geneframework.context.cfg.Setting.class);

	public PropertiesHelper(Setting setting, String filePath) {
		props = new Properties();
		
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream(filePath);
		try {
			InputStreamReader is = new InputStreamReader(in, "utf-8");
			props.load(is);
			analysis(setting);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PropertiesHelper(Setting setting,Properties props){
		this.props = props;
		try {
			analysis(setting);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void analysis(Setting setting) throws NoSuchMethodException,
			Exception {
		Field[] fields = setting.getClass().getDeclaredFields();
		Class setClass = setting.getClass();
		Set<String> keySet = props.stringPropertyNames();
		for (int i = 0; i < fields.length; i++) {
			if (props.containsKey(fields[i].getName())){
				setProterties(setting, props.get(fields[i].getName()),
						fields[i].getName());
				keySet.remove(fields[i].getName());
				
				log.info(fields[i].getName()+": "+props.getProperty(fields[i].getName()));
			}
		}
		Map<String, Double> mapping = new HashMap<String, Double>();
		for(Iterator it = keySet.iterator(); it.hasNext() ; ){
			String key = (String)it.next();
			String[] values = key.split("\\.");
			if("command".equals(values[0])&&values.length==3&&"rate".equals(values[2])){
				String comkey = values[0]+"."+values[1];
				mapping.put((String)props.getProperty(comkey), Double.valueOf((String)props.getProperty(key)));
				
				log.info((String)props.getProperty(comkey)+": "+Double.valueOf((String)props.getProperty(key)));
			}
		}
		setting.setCommands(mapping);
	}

	private static void setProterties(Object rp, Object y, String field) throws Exception {
		PropertyDescriptor pd2 = new PropertyDescriptor(field, rp.getClass());
		Class type = pd2.getPropertyType();
		String value = (String) y;
		Method method = pd2.getWriteMethod();
		method.invoke(rp, cast(value, type));
	}

	private static Object cast(String a, Class type) {
		String typename = type.getName();
		if ("int".equals(typename))
			return Integer.valueOf(a).intValue();
		else if ("double".equals(typename)) 
			return Double.parseDouble(a);
		else if ("java.lang.String".equals(typename))
			return a;
		else if ("boolean".equals(typename))
			return Boolean.parseBoolean(a);
		return null;
	}
}
