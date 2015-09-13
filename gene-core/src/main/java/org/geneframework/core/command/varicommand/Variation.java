package org.geneframework.core.command.varicommand;

/**
 * Created by kong on 15/9/2.
 */
public class Variation extends VariCommand {

    public Variation(int rate){
        super(rate);
    }

    @Override
    char[] vari(char[] chrom) {
        int head = heading.size();
        int end = terminal.size();
        int r = (int) (Math.random()*chrom.length);
        boolean bian = false;
        for (int i = 0; i < slength; i++) {
            if (r >= (hlength + tlength) * i && r < (hlength + tlength) * i + hlength) {
                bian = true;
            }
        }
        if(bian) {
            int q = (int) (Math.random() * head);
            chrom[r] = heading.get(q).charAt(0);
        }else {
            int q = (int) (Math.random() * end);
            chrom[r] = terminal.get(q).charAt(0);
        }
        return chrom;
    }
}
