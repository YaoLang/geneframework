package org.geneframework.context.commands;

import org.geneframework.orm.matrix.GeneTable;

/**
 * Created by kong on 15/9/2.
 */
public class SetName implements PretreatCommand {
    private int name;
    public SetName(int name){
        this.name = name;
    }

    @Override
    public void excute(GeneTable geneTable) {
        geneTable.setName(name);
    }
}
