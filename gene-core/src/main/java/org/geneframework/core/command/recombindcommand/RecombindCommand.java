package org.geneframework.core.command.recombindcommand;

import org.geneframework.core.model.Gene;

/**
 * Created by kong on 15/9/3.
 */
public abstract class RecombindCommand {

    public abstract char[][] recombind(char[] father, char[] mother);

    public Gene[] recombind(Gene father, Gene mother) {
        Gene[] childs = new Gene[2];
        Gene[] parents = new Gene[2];
        parents[0] = father;
        parents[1] = mother;
        char[][] chs = recombind(father.getChrom(),mother.getChrom());
        childs[0] = new Gene();
        childs[1] = new Gene();
        childs[0].setParent(parents);
        childs[1].setParent(parents);
        childs[0].setChrom(chs[0]);
        childs[1].setChrom(chs[1]);
        return childs;
    }
}
