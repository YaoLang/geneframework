package org.geneframework.context.commands;

import org.geneframework.context.cfg.Configuration;
import org.geneframework.context.mapping.TerminalMapping;
import org.geneframework.orm.matrix.GeneMatrix;
import org.geneframework.orm.matrix.GeneTable;
import org.geneframework.orm.matrix.Table;

import java.util.Vector;

import hu.kazocsaba.math.matrix.util.MatrixPrinter;

/**
 * Created by kong on 15/9/2.
 */
public class RunPretreatCommand {
    private Vector<PretreatCommand> programCommands = new Vector<>();
    private GeneTable table;
    public RunPretreatCommand(Table table){
        this.table = new GeneTable(table);
    }

    public void addCommand(PretreatCommand pretreatCommand){
        programCommands.add(pretreatCommand);
    }

    public GeneMatrix runCommands(){
        for (int i = 0; i < programCommands.size(); i++) {
            programCommands.get(i).excute(table);
        }
        GeneMatrix geneMatrix = table.getMatrix();
        Vector<Integer> dele = new Vector<>();
        for (int i = 0; i < table.getModel().length; i++) {
            if(table.getModel()[i]==GeneTable.ID||table.getModel()[i]==GeneTable.NAME)
                dele.add(i);
        }
        GeneMatrix submatrix;
        for (int i = 0; i < dele.size(); i++) {

        }
        MatrixPrinter.print(geneMatrix);
        TerminalMapping terminalMapping = TerminalMapping.getInstance();
        terminalMapping.setGeneMatrix(geneMatrix);
        Configuration.getInstance().setTerminal(terminalMapping.getKeys());
        return geneMatrix;
    }
}
