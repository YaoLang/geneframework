package junit.test;

import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;
import hu.kazocsaba.math.matrix.SingularValueDecomposition;
import hu.kazocsaba.math.matrix.SingularityException;
import hu.kazocsaba.math.matrix.util.MatrixPrinter;

import org.geneframework.orm.matrix.GeneMatrix;
import org.geneframework.orm.matrix.GeneTable;
import org.geneframework.orm.matrix.Table;
import org.geneframework.orm.pretreat.SVD;
import org.geneframework.orm.util.CsvUtil;
import org.junit.Test;

public class TestMatrix {
	
	@Test
	public void base() throws SingularityException{
		double[][] data = {{1,2,3},
						{4,5,6},
						{7,8,9}};
		Matrix m = MatrixFactory.createMatrix(data);
		printMatrix(m);
		printMatrix(m.transpose());
		Matrix m1 = MatrixFactory.random(5, 5);
		
		printMatrix(m1);
		printMatrix(m1.inverse());
		System.out.println("------------");
		printMatrix(m1.svd().getS());
		printMatrix(m1.svd().getU());
		printMatrix(m1.svd().getV());
		
	}
	
	private void printMatrix(Matrix m){
		int col = m.getColumnCount();
		int row = m.getRowCount();
		for(int i = 0 ; i < row ; i ++){
			for(int j = 0 ; j < col ; j ++){
				System.out.print(m.get(i, j)+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	@Test
	public void testSubMatrix(){
		double[][] data = {{1,2,3},
				{4,5,6},
				{7,8,9}};
		Matrix m = MatrixFactory.createMatrix(data);
		SingularValueDecomposition single = m.svd();
		Matrix s = single.getS();
		Matrix u = single.getU();
		Matrix v = single.getV();
		printMatrix(s);
		System.out.println(s.getSub().row(0));
		MatrixPrinter.print(m);
		MatrixPrinter.print(m.getSubmatrix(0, 1, 0, 1));
	}
	
	@Test
	public void testSVD(){
		double[][] data = {{1,2,3},
				{4,5,6},
				{7,8,9}};
		Matrix m = MatrixFactory.createMatrix(data);
		SVD svd = new SVD(m, 2);
	}
	
	@Test
	public void testGeneMatrix(){
		CsvUtil csv = new CsvUtil();
		Table table = csv.readTable("src/main/resources/data/Student.csv", true);
		GeneTable geneTable = new GeneTable(table);
		geneTable.setLabel(4);
		GeneMatrix m = geneTable.getMatrix();
		MatrixPrinter.print(m);
		System.out.println(m.getLabel());
	}
	
}
