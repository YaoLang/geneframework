package org.geneframework.core.model;

import java.util.Vector;

/**
 * Created by kong on 15/6/21.
 * 种群
 */
public class Generation {
    private Vector<Gene> populations = new Vector<>();
    private int popsize;
    private int gen;
    private Gene bestever;
    private double maxfiness;
    private double avgfiness;
    private double minfiness = Double.MAX_VALUE;
    private double sumfiness;

    public Vector<Gene> getPopulations() {
        return populations;
    }

    public void setPopulations(Vector<Gene> populations) {
        this.populations = populations;
    }

    public int getPopsize() {
        return popsize;
    }

    public void setPopsize(int popsize) {
        this.popsize = popsize;
    }

    public int getGen() {
        return gen;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }

    public Gene getBestever() {
        if(bestever!=null)
            return bestever;
        double best = 0;
        for (int i = 0; i < populations.size(); i++) {
            if(best<populations.get(i).getFieness()){
                best = populations.get(i).getFieness();
                bestever = populations.get(i);
            }
        }
        return bestever;
    }

    public void setBestever(Gene bestever) {
        this.bestever = bestever;
    }

    public double getMaxfiness() {
        if(maxfiness!=0)
            return maxfiness;
        for (int i = 0; i < populations.size(); i++) {
            if(maxfiness<populations.get(i).getFieness())
                maxfiness=populations.get(i).getFieness();
        }
        return maxfiness;
    }

    public void setMaxfiness(double maxfiness) {
        this.maxfiness = maxfiness;
    }

    public double getAvgfiness() {
        if(avgfiness!=0)
            return avgfiness;
        double sum = getSumfiness();
        return sum/popsize;
    }

    public void setAvgfiness(double avgfiness) {
        this.avgfiness = avgfiness;
    }

    public double getMinfiness() {
        if(minfiness!=Double.MAX_VALUE)
            return minfiness;
        for (int i = 0; i < populations.size(); i++) {
            if(minfiness>populations.get(i).getFieness())
                minfiness=populations.get(i).getFieness();
        }
        return minfiness;
    }

    public void setMinfiness(double minfiness) {
        this.minfiness = minfiness;
    }

    public double getSumfiness(){
        if(sumfiness!=0)
            return sumfiness;
        for (int i = 0; i < populations.size(); i++) {
            sumfiness+=populations.get(i).getFieness();
        }
        return sumfiness;
    }

    @Override
    public String toString() {
        for (int i = 0; i < populations.size(); i++) {
            System.out.println(String.valueOf(populations.get(i).getChrom())+"     finess:"+populations.get(i).getFieness());
        }
        return "";
    }
}
