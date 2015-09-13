package org.geneframework.core.command;

import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
 * Created by kong on 15/9/3.
 */
public class VectorAdapter {

    public static Vector<String> getVector(Set set){
        Vector<String> result = new Vector<>();
        for(Iterator it = set.iterator() ; it.hasNext() ; ){
            result.add((String) it.next());
        }
        return result;
    }
}
