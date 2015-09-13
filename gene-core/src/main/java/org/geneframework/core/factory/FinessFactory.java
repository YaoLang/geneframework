package org.geneframework.core.factory;

import com.fisher.gene.config.Constance;
import com.fisher.gene.model.GeneTable;
import com.fisher.gene.operator.finess.FinessImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by kong on 15/6/24.
 */
public class FinessFactory {
    public static final String RELATIVEERROR = "RelativeError";

    private static FinessFactory ourInstance = new FinessFactory();

    private GeneTable geneTable;
    
    public static FinessFactory getInstance() {
        return ourInstance;
    }

    private FinessFactory() {
    }

    public FinessImpl createFiness(String name){
        try {
            FinessImpl finess = (FinessImpl) Class.forName(name).newInstance();
            finess.setMethods(Constance.methods);
            finess.setEnding(Constance.ending);
            finess.setContract(Constance.contract);
            finess.setMethodmarks(Constance.methodmarks);
            finess.setH(Constance.hLength);
            finess.setS(Constance.sLength);
            finess.setT(Constance.tLength);
            finess.setGeneTable(geneTable);
            return finess;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FinessImpl createDefaultFiness(){
        return createFiness(RELATIVEERROR);
    }

	public GeneTable getGeneTable() {
		return geneTable;
	}

	public void setGeneTable(GeneTable geneTable) {
		this.geneTable = geneTable;
	}

    
    
}
