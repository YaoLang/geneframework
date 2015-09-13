package junit.test;

import org.geneframework.context.cfg.Configuration;
import org.geneframework.context.commands.InitialConfiguration;
import org.geneframework.context.commands.InitialMapping;
import org.geneframework.context.commands.InitialTableReader;
import org.geneframework.context.commands.PretreatCommand;
import org.geneframework.context.commands.ProgramCommand;
import org.geneframework.context.commands.RunPretreatCommand;
import org.geneframework.context.commands.RunProgramCommand;
import org.geneframework.context.commands.SetID;
import org.geneframework.context.commands.SetLabel;
import org.geneframework.context.mapping.ExpressTranslate;
import org.geneframework.orm.matrix.Table;
import org.geneframework.orm.matrix.TableFactory;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by kong on 15/9/2.
 */
public class TestTreeNode {

    static {
        ProgramCommand initConfig = new InitialConfiguration();
        ProgramCommand initMappint = new InitialMapping();
        ProgramCommand initTableRead = new InitialTableReader();
        RunProgramCommand runProgramCommand = new RunProgramCommand();
        runProgramCommand.addCommand(initConfig);
        runProgramCommand.addCommand(initMappint);
        runProgramCommand.addCommand(initTableRead);
        runProgramCommand.runCommands();


        Table table = null;
        try {
            table = TableFactory.getInstance().createTable("src/main/resources/datas/sunny.csv",true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RunPretreatCommand runPretreatCommand = new RunPretreatCommand(table);
        PretreatCommand setLabel = new SetLabel(4);
        PretreatCommand setId = new SetID(1);
        runPretreatCommand.addCommand(setLabel);
        runPretreatCommand.addCommand(setId);
        runPretreatCommand.runCommands();
    }

    @Test
    public void testTreeNode(){
        Set set = Configuration.getInstance().getFunction();
        String q = "Sgcfgg++CQdaafd";

        double[] o = {1,2,2,2,2,2,2};
        double re = ExpressTranslate.invoke(q.toCharArray(),o);
        System.out.println();
        System.out.println(re);

        System.out.println(ExpressTranslate.getRule(q.toCharArray()));


    }
}
