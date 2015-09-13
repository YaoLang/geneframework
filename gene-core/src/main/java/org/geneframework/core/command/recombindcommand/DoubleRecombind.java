package org.geneframework.core.command.recombindcommand;

/**
 * Created by kong on 15/9/3.
 */
public class DoubleRecombind extends RecombindCommand {

    @Override
    public char[][] recombind(char[] father, char[] mother) {
        char[][] ret = new char[2][father.length];
        int a = breakpoint(1,father.length);
        int b = breakpoint(1,father.length);
        int t;
        if(a>b){
            t = a;
            a = b;
            b = t;
        }
        for(int i = 0 ; i < father.length ; i ++){
            if(i>=a&&i<b){
                ret[0][i] = father[i];
                ret[1][i] = mother[i];
            }else{
                ret[0][i] = mother[i];
                ret[1][i] = father[i];
            }
        }
        return ret;
    }


    public int breakpoint(int a,int b){
        int r = (int) (Math.random()*b);
        if(r>a)
            return r;
        return breakpoint(a,b);
    }
}