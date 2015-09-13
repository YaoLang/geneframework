package org.geneframework.context.cfg;

import java.util.Map;
import java.util.Observable;

public final class Setting{
	
	private int number_of_runs		=		100			;					//运行迭代次数
	private int maxgen				= 		50			;					//最大世代
	private int popsize				=		100			;					//种群大小
	private String codeschema		=		""			;					//编码方案
	private String selection		=		""			;					//选择方案
	private double select_rate		=		0.7			;					//重组概率
	private Map	   commands;												//变异操作
	private String finess			=		""			;					//适应度评价方案
	private int hlength				=		7			;					//头部长度
	private int tlength				=		8			;					//尾部长度
	private int slength				=		3			;					//基因个数
	private boolean Dw				=		false		;					//是否开启dw域
	private double Dw_low			=		0.1			;					//dw定义域低
	private double Dw_high			=		1.0			;					//dw定义域高
	private boolean Dt				=		false		;					//是否开启dt域
	private double Dt_low			=		0.1			;					//dt定义域低
	private double Dt_high			=		1.0			;					//dt定义域高
	
	public int getNumber_of_runs() {
		return number_of_runs;
	}
	public void setNumber_of_runs(int number_of_runs) {
		this.number_of_runs = number_of_runs;
	}
	public int getMaxgen() {
		return maxgen;
	}
	public void setMaxgen(int maxgen) {
		this.maxgen = maxgen;
	}
	public int getPopsize() {
		return popsize;
	}
	public void setPopsize(int popsize) {
		this.popsize = popsize;
	}
	public String getCodeschema() {
		return codeschema;
	}
	public void setCodeschema(String codeschema) {
		this.codeschema = codeschema;
	}
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	public double getSelect_rate() {
		return select_rate;
	}
	public void setSelect_rate(double select_rate) {
		this.select_rate = select_rate;
	}
	public Map getCommands() {
		return commands;
	}
	public void setCommands(Map commands) {
		this.commands = commands;
	}
	public String getFiness() {
		return finess;
	}
	public void setFiness(String finess) {
		this.finess = finess;
	}
	public int getHlength() {
		return hlength;
	}
	public void setHlength(int hlength) {
		this.hlength = hlength;
	}
	public int getSlength() {
		return slength;
	}
	public void setSlength(int slength) {
		this.slength = slength;
	}
	public boolean isDw() {
		return Dw;
	}
	public void setDw(boolean dw) {
		Dw = dw;
	}
	public double getDw_low() {
		return Dw_low;
	}
	public void setDw_low(double dw_low) {
		Dw_low = dw_low;
	}
	public double getDw_high() {
		return Dw_high;
	}
	public void setDw_high(double dw_high) {
		Dw_high = dw_high;
	}
	public double getDt_low() {
		return Dt_low;
	}
	public void setDt_low(double dt_low) {
		Dt_low = dt_low;
	}
	public double getDt_high() {
		return Dt_high;
	}
	public void setDt_high(double dt_high) {
		Dt_high = dt_high;
	}
	public boolean isDt() {
		return Dt;
	}
	public void setDt(boolean dt) {
		Dt = dt;
	}
	public int getTlength() {
		return tlength;
	}
	public void setTlength(int tlength) {
		this.tlength = tlength;
	}
}
