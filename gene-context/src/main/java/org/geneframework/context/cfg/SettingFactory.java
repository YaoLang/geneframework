package org.geneframework.context.cfg;

import java.io.Serializable;
import java.util.Properties;

import org.geneframework.context.util.PropertiesHelper;

public class SettingFactory implements Serializable{
	
	public Setting buildSetting(Properties props){
		Setting setting = new Setting();
		PropertiesHelper propertiesHelper = new PropertiesHelper(setting, props);
		return setting;
	}

}
