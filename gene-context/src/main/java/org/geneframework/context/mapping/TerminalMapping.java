package org.geneframework.context.mapping;

import org.geneframework.orm.matrix.GeneMatrix;
import org.geneframework.orm.matrix.GeneMatrixListenr;

import java.util.HashSet;
import java.util.Set;

public class TerminalMapping implements Mapping,GeneMatrixListenr {

	private int label;
	private int terminalnums;
	private Set mapping;
	private GeneMatrix geneMatrix;

	@Override
	public Set getKeys() {
		return mapping;
	}

	public double getValue(String key,int row){
		char a = key.charAt(0);
		int col = a - 'a';
		return geneMatrix.get(row,col);
	}

	@Override
	public void setGeneMatrix(GeneMatrix geneMatrix) {
		this.geneMatrix = geneMatrix;
		label = geneMatrix.getLabel();
		terminalnums = geneMatrix.getColumnCount();
		mapping = new HashSet<String>();
		char a = 'a';
		for (int i = 0; i < terminalnums; i++) {
			mapping.add(String.valueOf(a++));
		}
	}

	private TerminalMapping(){}
	private static TerminalMapping outInstance = new TerminalMapping();
	public static TerminalMapping getInstance(){
		return outInstance;
	}

}
