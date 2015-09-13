package org.geneframework.orm.util;

import org.geneframework.orm.matrix.Table;

/**
 * Table r&w Utils interface
 * @author kong
 *
 */
public interface TableReader {

	/**
	 * read table
	 * @param filePath
	 * filePath of import file
	 * @param Ctaintopic
	 * whether the table of import file contains topic or not
	 * @return
	 * table model
	 * @throws Exception
	 * read exception
	 */
	Table readTable(String filePath,boolean Ctaintopic) throws Exception;
	
	/**
	 * ouput the table model of the program
	 * @param target
	 * table model
	 * @param filePath
	 * the path of output file
	 */
	void writeTable(Table target,String filePath);
	
	/**
	 * 
	 * @return
	 * suffix names of the file
	 */
	String[] getSuffixs();
	
}