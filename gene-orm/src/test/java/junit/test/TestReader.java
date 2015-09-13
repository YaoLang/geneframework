package junit.test;

import org.geneframework.orm.matrix.Table;
import org.geneframework.orm.matrix.TableFactory;
import org.geneframework.orm.util.CsvUtil;
import org.geneframework.orm.util.ExcelUtil;
import org.geneframework.orm.util.TableReader;
import org.geneframework.orm.util.TableReaderFactory;
import org.junit.Test;

/**
 * Created by kong on 15/8/20.
 */
public class TestReader {

    @Test
    public void testReaderFactory(){
        TableReaderFactory tableReaderFactory = TableReaderFactory.getInstance();
        tableReaderFactory.registerTableReader(new CsvUtil());
        tableReaderFactory.registerTableReader(new ExcelUtil());
        TableReader tableReader = tableReaderFactory.createTableReader("xlsm");
        System.out.println(tableReader);
       
        for (int i = 0; i < tableReaderFactory.getAllSuffix().length; i++) {
			System.out.println(tableReaderFactory.getAllSuffix()[i]);
		}
        
    }
    
    @Test
    public void testTableFactory(){
    	TableFactory tableFactory = TableFactory.getInstance();
    	TableReaderFactory tableReaderFactory = TableReaderFactory.getInstance();
        tableReaderFactory.registerTableReader(new CsvUtil());
        tableReaderFactory.registerTableReader(new ExcelUtil());
        try {
			Table table = tableFactory.createTable("src/main/resources/data/BreastTissue001.csv", true);
			PrintTable.printTable(table);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
