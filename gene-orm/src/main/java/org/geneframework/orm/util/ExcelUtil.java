package org.geneframework.orm.util;

import org.geneframework.orm.matrix.Table;
/**
 * Utils to r&w tables in excel file 
 * @author kong
 *
 */
public class ExcelUtil implements TableReader{

	public ExcelUtil(){
		
	}
	
	@Override
	public Table readTable(String filePath,boolean Ctaintopic) {
		return null;
	}

	@Override
	public void writeTable(Table target, String filePath) {
		
	}

	@Override
	public String[] getSuffixs() {
		String[] suffixs = {"xls","xlsx","xlsm"};
		return suffixs;
	}

}
