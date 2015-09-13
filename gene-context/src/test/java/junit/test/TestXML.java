package junit.test;

import org.geneframework.context.cfg.Configuration;
import org.geneframework.context.cfg.GeneMethod;
import org.geneframework.context.cfg.Setting;
import org.geneframework.context.commands.InitialConfiguration;
import org.geneframework.context.commands.InitialMapping;
import org.geneframework.context.commands.InitialTableReader;
import org.geneframework.context.commands.PretreatCommand;
import org.geneframework.context.commands.ProgramCommand;
import org.geneframework.context.commands.RunPretreatCommand;
import org.geneframework.context.commands.RunProgramCommand;
import org.geneframework.context.commands.SetID;
import org.geneframework.context.commands.SetLabel;
import org.geneframework.context.util.PropertiesHelper;
import org.geneframework.context.util.XMLHelper;
import org.geneframework.orm.matrix.Table;
import org.geneframework.orm.matrix.TableFactory;
import org.geneframework.orm.util.CsvUtil;
import org.geneframework.orm.util.ExcelUtil;
import org.geneframework.orm.util.TableReaderFactory;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Set;

public class TestXML {
	
	
	@Test
	public void testXML() throws InvocationTargetException, IllegalAccessException {
		XMLHelper helper = new XMLHelper("gene-function.xml");

		Set set = helper.getGeneMethods();
		for(Iterator it = set.iterator() ; it.hasNext() ; ){
			System.out.println(it.next());

		}

		GeneMethod g = new GeneMethod("org.geneframework.context.maths.CommonMaths","addExact","+");
		Object[] o = {1.1,2.1};
		System.out.println(g.invoke(o));
	}

	@Test
	public void testMapping() throws Exception {
		TableReaderFactory tableReaderFactory = TableReaderFactory.getInstance();
		tableReaderFactory.registerTableReader(new CsvUtil());
		tableReaderFactory.registerTableReader(new ExcelUtil());

		Setting setting = new Setting();
		PropertiesHelper prop = new PropertiesHelper(setting, "gene.properties");
		XMLHelper helper = new XMLHelper("gene-function.xml");
		TableFactory tableFactory = TableFactory.getInstance();
		Table table = tableFactory.createTable("src/main/resources/datas/sunny.csv", true);
		Configuration.getInstance().setPropertiesHelper(prop);
		Configuration.getInstance().setXmlHelper(helper);

//		MappingFactory mappingFactory = MappingFactory.getInstance();
//		Mapping map = mappingFactory.getMapping(MappingFactory.DT);
//		for (Iterator it = map.getKeys().iterator() ; it.hasNext() ;) {
//			System.out.println(it.next());
//		}
	}

	@Test
	public void testCommand() throws Exception {
		ProgramCommand initConfig = new InitialConfiguration();
		ProgramCommand initMappint = new InitialMapping();
		ProgramCommand initTableRead = new InitialTableReader();
		RunProgramCommand runProgramCommand = new RunProgramCommand();
		runProgramCommand.addCommand(initConfig);
		runProgramCommand.addCommand(initMappint);
		runProgramCommand.addCommand(initTableRead);
		runProgramCommand.runCommands();


		Table table = TableFactory.getInstance().createTable("src/main/resources/datas/sunny.csv",true);

		RunPretreatCommand runPretreatCommand = new RunPretreatCommand(table);
		PretreatCommand setLabel = new SetLabel(4);
		PretreatCommand setId = new SetID(1);
		runPretreatCommand.addCommand(setLabel);
		runPretreatCommand.addCommand(setId);
		runPretreatCommand.runCommands();
	}
}
