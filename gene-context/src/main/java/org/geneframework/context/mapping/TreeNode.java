package org.geneframework.context.mapping;

import org.geneframework.context.cfg.Configuration;
import org.geneframework.context.cfg.GeneMethod;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Set;

class TreeNode {

	private static FunctionMapping functionMapping = (FunctionMapping) MappingFactory.getInstance().getMapping(MappingFactory.FUNCTION);
	private static Set<String> terminal = Configuration.getInstance().getTerminal();
	private static int h = Configuration.getInstance().getSetting().getHlength();
	private static int s = Configuration.getInstance().getSetting().getSlength();
	private static int t = Configuration.getInstance().getSetting().getTlength();

	char name;
	TreeNode[] childs;
	
	public TreeNode(char name){
		this.name = name;
	}
	
	public TreeNode(char name,int childnum){
		this.name = name;
		this.childs = new TreeNode[childnum];
	}

	/**
	 * get TreeNode by genetic chromosome string
	 * @param chrom
	 * chromosome string
	 * @return
	 */
	public static TreeNode getTreeNode(char[] chrom){

		Queue<TreeNode> q = new ArrayDeque<>();
		if(terminal.contains(String.valueOf(chrom[0])))
			return new TreeNode(chrom[0]);
		GeneMethod g = functionMapping.getValue(String.valueOf(chrom[0]));
		TreeNode root = new TreeNode(chrom[0],g.getElementnums());
		q.add(root);
		int i = 0;
		while(!q.isEmpty()&&i<chrom.length){
			int n = q.size();
			for (int j = 0; j < n; j++) {
				TreeNode now = q.poll();
				for(int k = 0 ; k < now.childs.length; k ++){
					char c = chrom[++i];
					TreeNode child = null;
					if(c<'a'||c>'z') {
						GeneMethod geneMethod = functionMapping.getValue(String.valueOf(c));
						child = new TreeNode(c, geneMethod.getElementnums());
						q.add(child);
					}else
						child = new TreeNode(c);
					now.childs[k] = child;
				}
			}
		}
		return root;
	}

	private static void rule(TreeNode root){
		if(root==null)
			return;
		if(root.name>='a'&&root.name<='z'){
			rule.append(root.name);
		}
		else if(root.name=='+'||root.name=='-'||root.name=='*'||root.name=='/') {
			rule.append("(");
			rule(root.childs[0]);
			rule.append(root.name);
			rule(root.childs[1]);
			rule.append(")");
		}else{
			GeneMethod geneMethod = functionMapping.getValue(String.valueOf(root.name));
			rule.append(geneMethod.getMethod().getName());
			rule.append("(");
			for (int i = 0; i < geneMethod.getElementnums(); i++) {
				rule(root.childs[i]);
				if(i!=geneMethod.getElementnums()-1){
					rule.append(",");
				}
			}
			rule.append(")");
		}
	}

	public static void printrule(char[] chrom){

		char[] ch = new char[h+t];
		char[][] spli = new char[s][];
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < h + t; j++) {
				ch[j] = chrom[j + (h + t) * i];
			}
			spli[i] = ch.clone();
		}
		for (int i = 0; i < s; i++) {
			rule(getTreeNode(spli[i]));
			if(i!=s-1){
				rule.append("+");
			}
		}
	}

	private static StringBuilder rule = new StringBuilder();


	private static double checkrule(TreeNode root,double[] varas){
		if(root==null)
			return 0;
		if(terminal.contains(String.valueOf(root.name))){
			char s = root.name;
			return varas[s-'a'];
		}
		else if (root.name=='+')
			return checkrule(root.childs[0], varas)+checkrule(root.childs[1], varas);
		else if (root.name=='-')
			return checkrule(root.childs[0], varas)-checkrule(root.childs[1], varas);
		else if (root.name=='*')
			return checkrule(root.childs[0], varas)*checkrule(root.childs[1],varas);
		else if (root.name=='/')
			return checkrule(root.childs[0], varas)/checkrule(root.childs[1],varas);
		else {
			GeneMethod o = functionMapping.getValue(String.valueOf(root.name));
			double result = 0;
			Object[] paras = new Object[root.childs.length];
			for (int i = 0; i < root.childs.length; i++) {
				paras[i] = checkrule(root.childs[i], varas);
			}
			try {
				return (double) o.invoke(paras);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return 0;
		}
	}

	public static double invoke(char[] chrom,double[] varas){
		double result = 0;
		char[] ch = new char[h+t];
		char[][] spli = new char[s][];
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < h + t; j++) {
				ch[j] = chrom[j + (h + t) * i];
			}
			spli[i] = ch.clone();
		}
		for (int i = 0; i < s; i++) {
			double e = checkrule(getTreeNode(spli[i]),varas);
			result = result + e;
		}
		return result;
	}

	public static StringBuilder getRule() {
		return rule;
	}
}
