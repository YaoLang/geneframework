package junit.test;

import org.geneframework.orm.matrix.GeneTable;
import org.geneframework.orm.matrix.Table;
import org.geneframework.orm.util.CsvUtil;
import org.geneframework.orm.util.TimeAlignUtil;
import org.junit.Test;

public class TestTable {
	
	@Test
	public void testimport(){
		CsvUtil csvUtil = new CsvUtil(",");
		Table table = csvUtil.readTable("src/main/resources/data/BreastTissue001.csv", true);
		PrintTable.printTable(table);
		GeneTable geneTable = new GeneTable(table);
		PrintTable.printGeneTable(geneTable);
		
		csvUtil.writeTable(table, "src/main/resources/output/aaa.csv");
		
		geneTable.getCounting().print();
	}
	
	@Test
	public void testimporttime() throws Exception{
		TimeAlignUtil alignUtil = new TimeAlignUtil();
		Table table = alignUtil.readTable("src"
				+ "/main/resources/data/太阳黑子数据.txt", false);
		PrintTable.printTable(table);
	}
	
	
	
}
