package org.geneframework.context.commands;

import org.geneframework.orm.matrix.GeneTable;
import org.geneframework.orm.matrix.Table;

/**
 * Created by kong on 15/9/2.
 */
public class SetID implements PretreatCommand {

    private int id;
    public SetID(int id){
        this.id = id;
    }

    @Override
    public void excute(GeneTable geneTable) {
        geneTable.setID(id);
    }
}
