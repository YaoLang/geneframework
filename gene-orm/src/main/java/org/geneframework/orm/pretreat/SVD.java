package org.geneframework.orm.pretreat;

import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.SingularValueDecomposition;

/**
 * Created by kong on 15/7/17.
 * Singular Value Decomposition
 */
public class SVD {
	
	private Matrix U;
	private Matrix V;
	private Matrix S;
	
	public SVD(Matrix m,int keep){
		SingularValueDecomposition single = m.svd();
		Matrix s = single.getS();
		Matrix u = single.getU();
		Matrix v = single.getV();
		assert (s.getRowCount()>keep):"vectors which is kept out of the range of row";
		S = s.getSubmatrix(0, keep, 0, keep);
		U = u.getSubmatrix(0, u.getRowCount(), 0, keep);
		V = v.getSubmatrix(0, keep, 0, v.getColumnCount());
	}

	public Matrix getU() {
		return U;
	}

	public Matrix getV() {
		return V;
	}

	public Matrix getS() {
		return S;
	}
	
}
