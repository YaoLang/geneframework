package org.geneframework.core.command.codecommand;

/**
 * Created by kong on 15/6/22.
 */
public class TreeCode extends CodeCommand{

    public char[] encode(){
        for (int i = 0; i < slength; i++) {

        }
        return null;
    }

    @Override
    public char[] randomencode(){
        char[] result = new char[(hlength+tlength)*slength];
        int q = 0;
        for (int i = 0; i < slength; i++) {
            for (int j = 0; j < hlength; j++) {
                int r = (int) (Math.random()*heading.size());
                result[q++] = heading.get(r).charAt(0);
            }
            for (int j = 0; j < tlength; j++) {
                int r = (int) (Math.random()*terminal.size());
                result[q++] = terminal.get(r).charAt(0);
            }
        }
        return result;
    }

    public void decode(){

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
