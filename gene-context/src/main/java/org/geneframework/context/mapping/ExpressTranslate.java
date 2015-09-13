package org.geneframework.context.mapping;

import javax.security.auth.login.Configuration;

/**
 * translate from chromosome to formula
 * @author kong
 *
 */
public class ExpressTranslate {
	
	private Configuration configuration = Configuration.getConfiguration();

	/**
	 * invoke method by chromosome
	 * @param chrom
	 * @param o
	 * @return
	 */
	public static double invoke(char[] chrom,double[] o){
		TreeNode root = TreeNode.getTreeNode(chrom);
		double result = root.invoke(chrom,o);
		return result;
	}

	/**
	 * get function by chromosome
	 * @param chrom
	 * chromosome string
	 * @return
	 */
	public static String getRule(char[] chrom){
		TreeNode.printrule(chrom);
		return TreeNode.getRule().toString();
	}
}
