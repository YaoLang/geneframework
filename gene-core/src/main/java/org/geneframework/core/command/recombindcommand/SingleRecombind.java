package org.geneframework.core.command.recombindcommand;

/**
 * Created by kong on 15/9/3.
 */
public class SingleRecombind extends RecombindCommand {

    @Override
    public char[][] recombind(char[] father, char[] mother) {
        int breakpoint = breakpoint(1,father.length);
        char[][] childs = new char[2][];
        childs[0] = new char[father.length];
        childs[1] = new char[father.length];
        for(int i = 0 ; i < father.length ; i ++){
            if(i<breakpoint){
                childs[0][i]=father[i];
                childs[1][i]=mother[i];
            }else {
                childs[0][i]=mother[i];
                childs[1][i]=father[i];
            }
        }
        return childs;
    }



    public int breakpoint(int a,int b){
        int r = (int) (Math.random()*b);
        if(r>a)
            return r;
        return breakpoint(a,b);
    }
}