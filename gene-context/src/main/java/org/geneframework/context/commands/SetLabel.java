package org.geneframework.context.commands;

import org.geneframework.orm.matrix.GeneTable;
/**
 * Created by kong on 15/9/2.
 */
public class SetLabel implements PretreatCommand {

    private int label;
    public SetLabel(int label){
        this.label = label;
    }

    @Override
    public void excute(GeneTable geneTable) {
        geneTable.setLabel(label);
    }
}
