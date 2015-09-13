package org.geneframework.core.model;


public class Gene {

    private double fieness;    //个体适应值
    private int xsite; //交叉位置
    private Gene[] parent = new Gene[2]; //双亲
    private char[] chrom; //染色体

    public double getFieness() {
        return fieness;
    }

    public void setFieness(double fieness) {
        this.fieness = fieness;
    }

    public int getXsite() {
        return xsite;
    }

    public void setXsite(int xsite) {
        this.xsite = xsite;
    }

    public Gene[] getParent() {
        return parent;
    }

    public void setParent(Gene[] parent) {
        this.parent = parent;
    }

    public char[] getChrom() {
        return chrom;
    }

    public void setChrom(char[] chrom) {
        this.chrom = chrom;
    }


}
