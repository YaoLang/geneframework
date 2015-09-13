package org.geneframework.context.cfg;

import org.geneframework.context.util.PropertiesHelper;
import org.geneframework.context.util.XMLHelper;
import org.geneframework.orm.matrix.GeneMatrix;

import java.io.Serializable;
import java.util.Set;


public class Configuration implements Serializable{
	
	private Set function;							//函数集
	private Set	terminal;							//终点集
	private Set DWterminal;							//dw域终点
	private Set DTterminal;							//gt域终点
	private Setting setting;						//运行配置
	private GeneMatrix geneMatrix;

	protected transient XMLHelper xmlHelper;
	protected transient PropertiesHelper propertiesHelper;

	public Set getFunction() {
		return function;
	}

	public void setFunction(Set function) {
		this.function = function;
	}

	public Set getTerminal() {
		return terminal;
	}

	public void setTerminal(Set terminal) {
		this.terminal = terminal;
	}

	public Set getDWterminal() {
		return DWterminal;
	}

	public void setDWterminal(Set DWterminal) {
		this.DWterminal = DWterminal;
	}

	public Set getDTterminal() {
		return DTterminal;
	}

	public void setDTterminal(Set DTterminal) {
		this.DTterminal = DTterminal;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public XMLHelper getXmlHelper() {
		return xmlHelper;
	}

	public void setXmlHelper(XMLHelper xmlHelper) {
		this.xmlHelper = xmlHelper;
	}

	public PropertiesHelper getPropertiesHelper() {
		return propertiesHelper;
	}

	public void setPropertiesHelper(PropertiesHelper propertiesHelper) {
		this.propertiesHelper = propertiesHelper;
	}

	private Configuration(){}

	private static Configuration ourInstance = new Configuration();

	public static Configuration getInstance() {
		return ourInstance;
	}

}
