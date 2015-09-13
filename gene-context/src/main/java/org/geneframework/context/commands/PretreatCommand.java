package org.geneframework.context.commands;

import org.geneframework.orm.matrix.GeneTable;

/**
 * Created by kong on 15/9/2.
 * set model of genetable
 */
public interface PretreatCommand {

    void excute(GeneTable geneTable);
}
