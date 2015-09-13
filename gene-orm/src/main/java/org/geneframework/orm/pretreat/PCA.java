package org.geneframework.orm.pretreat;

import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;

/**
 * Created by kong on 15/7/16.
 * Principle Component Analysis
 */
public class PCA {

    public static void main(String[] args) {
        PCA pca = new PCA();
    }


    private Matrix getCovMatrix(Matrix m){
    	int row = m.getRowCount();
    	int col = m.getColumnCount();
    	Matrix covMatrix = MatrixFactory.createMatrix(col, col);
    	for(int i = 0 ; i < col ; i ++){
    		
    		for(int j = 0 ; j < col ; j ++){
    			covMatrix.set(i, j, 0);
    		}
    	}
    	
    	return null;
    }
    
    private double mean(Matrix m,int col){
    	int row = m.getRowCount();
    	double sum = 0;
    	for(int i = 0 ; i < row ; i ++){
    		sum = m.get(i, col) + sum;
    	}
    	
    	return sum/row;
    }
}
