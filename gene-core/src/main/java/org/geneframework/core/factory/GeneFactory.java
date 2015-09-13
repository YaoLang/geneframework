package org.geneframework.core.factory;

import com.fisher.gene.config.GeneConfiguration;
import com.fisher.gene.model.Gene;
import com.fisher.gene.model.Generation;
import com.fisher.gene.operator.codeschema.CodeImpl;
import com.fisher.gene.operator.finess.FinessImpl;
import com.fisher.gene.operator.recombind.RecombindImpl;
import com.fisher.gene.operator.select.SelectImpl;
import com.fisher.gene.operator.transposition.TransImpl;
import com.fisher.gene.operator.variation.VariImpl;

/**
 * Created by kong on 15/6/20.
 * used to create gene
 */
public class GeneFactory {
    private GeneConfiguration geneConfiguration;
    private String codeschema;
    private String recombind;
    private String variation;
    private String transposition;
    private String selection;
    private String finess;
    private int popsize;
    private int maxgen;
    private double pmutation;
    private double pcross;
    private double diferror;

    private RecombindFactory recombindFactory = RecombindFactory.getInstance();
    private TranFactory tranFactory = TranFactory.getInstance();
    private VariationFactory variationFactory;
    private FinessFactory finessFactory;
    private SelectFactory selectFactory = SelectFactory.getInstance();
    private CodeFactory codeFactory = null;
    private RecombindImpl recombindImpl;
    private TransImpl transImpl;
    private VariImpl variImpl;
    private SelectImpl selectImpl;
    private CodeImpl codeImpl;
    private FinessImpl finessImpl;

    /**
     * 初始化种群
     * @return
     */
    public Generation init(){
        codeFactory = geneConfiguration.buildCodeFactory();
        if(variation!=null)
            variImpl = geneConfiguration.buildVariationFactory().createVari(variation);
        else
            variImpl = geneConfiguration.buildVariationFactory().createDefaultVari();
        if(transposition!=null)
            transImpl = tranFactory.createTrans(transposition);
        else
            transImpl = tranFactory.createDefaultTrans();
        if(recombind!=null)
            recombindImpl = recombindFactory.createRecombind(recombind);
        else
            recombindImpl = recombindFactory.createDefaultRecombind();
        if(selection!=null)
            selectImpl = selectFactory.createSelectImpl(selection);
        else
            selectImpl = selectFactory.createDefaultSelectImpl();
        if(codeschema!=null)
            codeImpl = codeFactory.createCodeSchema(codeschema);
        else
            codeImpl = codeFactory.createDefaultCodeSchema();
        if(finess!=null)
            finessImpl = geneConfiguration.buildFinessFactory().createFiness(finess);
        else
            finessImpl = geneConfiguration.buildFinessFactory().createDefaultFiness();

        Generation oldpop = new Generation();
        for (int i = 0; i < popsize; i++) {
            char[] chs = codeImpl.randomencode();
            Gene gene = new Gene();
            gene.setChrom(chs);
            finessImpl.evaluation(gene);
            oldpop.getPopulations().add(gene);
        }
        oldpop.setPopsize(popsize);
        return oldpop;
    }

    public Generation nextgen(Generation oldpop){
        Gene[] parents = null;
        Gene[] childs = null;
        Generation newpop = new Generation();
        newpop.setPopsize(popsize);
        newpop.setGen(oldpop.getGen()+1);
        for (int i = 0; i < popsize / 2; i++) {
            parents = selectImpl.select(oldpop,2);                        //选择
            double recom = Math.random();
            if(recom<pcross)
                childs = recombindImpl.recombind(parents[0],parents[1]);    //基因重组
            else {
                childs = parents;
            }
            double r = Math.random();
            if(r<pmutation)
                variImpl.vari(childs[0]);                                      //变异
            r = Math.random();
            if(r<pmutation)
                variImpl.vari(childs[1]);
                                                                            //转座
            transImpl.transposition(childs[0]);
            transImpl.transposition(childs[1]);

            finessImpl.evaluation(childs[0]);
            finessImpl.evaluation(childs[1]);
            newpop.getPopulations().add(childs[0]);
            newpop.getPopulations().add(childs[1]);
        }
        return newpop;
    }

    public boolean release(){
        return false;
    }

    public Generation run(){
        Generation oldpop = init();
        for(int j = 0 ; j < maxgen; j ++){
            System.out.println("----第"+j+"代----");
            Generation newpop = nextgen(oldpop);
//            System.out.println(newpop);
            oldpop = newpop;
            
            System.out.println();
            System.out.println("----end----");
        }
        System.out.println("最佳个体："+String.valueOf(oldpop.getBestever().getChrom())+"      finess:"+oldpop.getBestever().getFieness());
        System.out.print("公式:");
        finessImpl.printrule(oldpop.getBestever().getChrom());
        System.out.println();
        return oldpop;
    }

    public void setCodeschema(String codeschema) {
        this.codeschema = codeschema;
    }

    public void setRecombind(String recombind) {
        this.recombind = recombind;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public void setPopsize(int popsize) {
        this.popsize = popsize;
    }

    public void setMaxgen(int maxgen) {
        this.maxgen = maxgen;
    }

    public void setPmutation(double pmutation) {
        this.pmutation = pmutation;
    }

    public void setPcross(double pcross) {
        this.pcross = pcross;
    }

    public void setDiferror(double diferror) {
        this.diferror = diferror;
    }

    public void setGeneConfiguration(GeneConfiguration geneConfiguration) {
        this.geneConfiguration = geneConfiguration;
    }

    public void setTransposition(String transposition) {
        this.transposition = transposition;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public void setFiness(String finess) {
        this.finess = finess;
    }

    private static GeneFactory ourInstance = new GeneFactory();

    public static GeneFactory getInstance() {
        return ourInstance;
    }

    private GeneFactory() {
    }

	public FinessImpl getFinessImpl() {
		return finessImpl;
	}

	public void setFinessImpl(FinessImpl finessImpl) {
		this.finessImpl = finessImpl;
	}

    
}
