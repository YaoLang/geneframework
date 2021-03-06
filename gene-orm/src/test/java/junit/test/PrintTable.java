package junit.test;

import org.geneframework.orm.matrix.GeneTable;
import org.geneframework.orm.matrix.Table;

public class PrintTable {
	
	public static void printTable(Table table){
		System.out.println("---table records---");
		String[] topic = table.getTopic();
		String[][] records = table.getRecords();
		int row = records.length ;
		int col = topic.length  ;
		for(int i = 0 ; i < col ; i ++){
			System.out.print(topic[i]+" ");
		}
		System.out.println();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(table.get(i, j)+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printGeneTable(GeneTable table){
		printTable(table);
		System.out.println("---:model:---");
		byte[] model = table.getModel();
		byte[] type = table.getType();
		for(int i = 0 ; i < model.length ; i++){
			System.out.print(model[i]+" ");
		}
		System.out.println();
		System.out.println("---:type:---");
		for(int i = 0 ; i < type.length ; i++){
			System.out.print(type[i]+" ");
		}
		System.out.println();
	}
	
	
}