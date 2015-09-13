package org.geneframework.orm.pretreat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.geneframework.orm.matrix.GeneTable;

/**
 * 统计分析表格
 * @author kong
 *
 */
public class Counting {
	
	private double[] average;	//平均数
	private double[] mode;		//众数
	private double[] min;		//最小值
	private double[] max;		//最大值
	private double[] miss;		//缺失数
	
	private GeneTable geneTable;
	
	public Counting(GeneTable geneTable){
		this.geneTable = geneTable;
	}
	
	/**
	 * 获取样本均值
	 * @return
	 */
	public double[] getAverage(){
		String[][] datas = geneTable.getRecords();
		int col = datas.length;
		int row = geneTable.getTopic().length;
		average = new double[geneTable.getTopic().length];
		byte[] model = geneTable.getType();
		
		for(int i = 0 ; i < row ; i ++){
			double sum = 0;
			if(model[i]==geneTable.TEXT||model[i]==geneTable.POLYGEN){
				average[i] = 0;
				continue;
			}
			for(int j = 0 ; j < col ; j ++){
				sum+=Double.parseDouble(datas[j][i]);
			}
			average[i]=sum/col;
		}
		return average;
	}
	
	/**
	 * 获取最小值
	 * @return
	 */
	public double[] getMin(){
		String[][] datas = geneTable.getRecords();
		int col = datas.length;
		int row = geneTable.getTopic().length;
		min = new double[geneTable.getTopic().length];
		byte[] model = geneTable.getType();
		
		for(int i = 0 ; i < row ; i ++){
			double mini = Double.MAX_VALUE;
			if(model[i]==geneTable.TEXT||model[i]==geneTable.POLYGEN){
				min[i] = 0;
				continue;
			}
			for(int j = 0 ; j < col ; j ++){
				double d = Double.parseDouble(datas[j][i]);
				if(mini > d){
					mini = d;
				}
			}
			min[i]= mini;
		}
		return min;
	}
	
	/**
	 * 获取最大值
	 * @return
	 */
	public double[] getMax(){
		String[][] datas = geneTable.getRecords();
		int col = datas.length;
		int row = geneTable.getTopic().length;
		max = new double[geneTable.getTopic().length];
		byte[] model = geneTable.getType();
		
		for(int i = 0 ; i < row ; i ++){
			double maxnum = 0;
			if(model[i]==geneTable.TEXT||model[i]==geneTable.POLYGEN){
				max[i] = 0;
				continue;
			}
			for(int j = 0 ; j < col ; j ++){
				double d = Double.parseDouble(datas[j][i]);
				if(maxnum < d){
					maxnum = d;
				}
			}
			max[i]= maxnum;
		}
		return max;
	}
	
	/**
	 * 获取众数
	 * @return
	 */
	public double[] getMode(){
		String[][] datas = geneTable.getRecords();
		int col = datas.length;
		int row = geneTable.getTopic().length;
		mode = new double[geneTable.getTopic().length];
		byte[] model = geneTable.getType();
		
		for(int i = 0 ; i < row ; i ++){
			double maxnum = 0;
			if(model[i]==geneTable.TEXT||model[i]==geneTable.POLYGEN){
				mode[i] = 0;
				continue;
			}
			Map<String, Integer> modeMap = new HashMap<String, Integer>();
			
			for(int j = 0 ; j < col ; j ++){
				String d = datas[j][i];
				if(modeMap.containsKey(d)){
					int a = modeMap.get(d);
					modeMap.put(d, ++a);
				}else{
					modeMap.put(d, 1);
				}
			}
			String modes = "";
			int max = 0;
			for(Iterator it = modeMap.keySet().iterator() ; it.hasNext() ; ){
				String key = (String) it.next();
				if(max < modeMap.get(key)){
					max = modeMap.get(key);
					modes = key;
				}
			}
			if(modes!="")
				mode[i]= Double.parseDouble(modes);
		}
		return mode;
	}
	
	public void print(){
		getMax();
		getMin();
		getAverage();
		getMode();
		String[] topic = geneTable.getTopic();
		System.out.print("        ");
		for (int i = 0; i < topic.length; i++) {
			System.out.print(topic[i] + " ");
		}
		System.out.println();

		System.out.print("min     ");
		for (int i = 0; i < min.length; i++) {
			System.out.print(min[i] + " ");
		}
		System.out.println();

		System.out.print("max     ");
		for (int i = 0; i < max.length; i++) {
			System.out.print(max[i] + "   ");
		}
		System.out.println();

		System.out.print("mode    ");
		for (int i = 0; i < mode.length; i++) {
			System.out.print(mode[i]+"   ");
		}
		System.out.println();

		System.out.print("average ");
		for (int i = 0; i < average.length; i++) {
			System.out.print(average[i]+"   ");
		}
		System.out.println();
	}
}
