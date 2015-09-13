package org.geneframework.core.command.varicommand;

import org.geneframework.context.cfg.Configuration;
import org.geneframework.core.command.VectorAdapter;
import org.geneframework.core.model.Gene;

import java.util.Vector;

/**
 * variation interface used to
 * change the chromosome including
 * IS,RIS,Variation and so on
 */
public abstract class VariCommand {
	private static Vector<String> function;
	protected static Vector<String> terminal;
	protected static Vector<String> heading;
	static {
		function = VectorAdapter.getVector(Configuration.getInstance().getFunction());
		terminal = VectorAdapter.getVector(Configuration.getInstance().getTerminal());
		heading = new Vector<>();
		for (int i = 0; i < function.size(); i++) {
			heading.add(function.get(i));
		}
		for (int i = 0; i < terminal.size(); i++) {
			heading.add(terminal.get(i));
		}
	}
	protected static int hlength = Configuration.getInstance().getSetting().getHlength();
	protected static int tlength = Configuration.getInstance().getSetting().getTlength();
	protected static int slength = Configuration.getInstance().getSetting().getSlength();


	protected double rate;
	public VariCommand(int rate){
		this.rate = rate;
	}
	
	abstract char[] vari(char[] chrom);
	
	public void vari(Gene gene){
		double r = Math.random();
		if(r>rate)
			return;
		char[] v = vari(gene.getChrom());
		gene.setChrom(v);
	}

	public int random(int a,int b){
		int r = (int) Math.random()*b;
		if(r<a)
			return random(a,b);
		return r;
	}
}
