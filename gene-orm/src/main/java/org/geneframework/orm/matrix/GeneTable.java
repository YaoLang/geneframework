package org.geneframework.orm.matrix;

import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;

import org.geneframework.orm.pretreat.Counting;

/**
 * Created by kong on 15/6/10.
 * 表模型
 */
public class GeneTable extends Table{

	//type
    public static final byte POLYGEN = 0;
    public static final byte NUMERIC = 1;
    public static final byte INTEGER = 2;
    public static final byte TEXT    = 3;

    //model
    public static final byte ATTRIBUTE = 0;
    public static final byte LABEL     = 1;
    public static final byte ID        = 2;
    public static final byte NAME      = 3;

    private Table table;
    
    private byte[] type;                                //字段类型
    private byte[] model;                               //字段模型
    private int label;                                  //所求

    public GeneTable(Table table){
    	super(table);
    	this.table = table;
        String[] topic = table.getTopic();
        int topicSize = topic.length;
        String[][] record = table.getRecords();
        int sumRecord = record.length;
        type = new byte[topicSize];
        model = new byte[topicSize];

        for (int i = 0; i < topicSize; i++) {
            boolean isNum = true;
            boolean isNumeric = true;
            for (int j = 0; j < sumRecord; j++) {
                if(isNumeric!=false&&!isNumeric(record[j][i]))
                    isNumeric = false;
                if(isNum!=false&&!isNum(record[j][i]))
                    isNum = false;
            }
            if(isNumeric)
                type[i] = NUMERIC;
            if(isNum&&!isNumeric)
                type[i] = INTEGER;
            if(!isNum&&!isNumeric)
                type[i] = POLYGEN;
        }
    }

    public static boolean isNum(String s){
        try {
            Double.parseDouble(s);
            return true;
        }catch (Exception e){
            try {
                Integer.parseInt(s);
                return true;
            }catch (Exception e1){
                return false;
            }
        }
    }

    public boolean isNumeric(String s){
        if("0".equals(s)||"1".equals(s)||"FALSE".equalsIgnoreCase(s)||"TRUE".equalsIgnoreCase(s))
            return true;
        return false;
    }

    public byte[] getType() {
        return type;
    }

    public void setType(byte[] type) {
        this.type = type;
    }

    public byte[] getModel() {
        return model;
    }

    public void setModel(byte[] model) {
        this.model = model;
    }

    public void setLabel(int n){
        label = n;
        model[n] = GeneTable.LABEL;
    }

    public int getLabel() {
        return label;
    }

	
	public Table getSubTable(int row1, int row2, int col1, int col2) {
		
		return table.getSubTable(row1, row2, col1, col2);
	}

	
	public String get(int row, int col) {
		
		return table.get(row, col);
	}

	
	public String set(int row, int col) {
		
		return table.set(row, col);
	}

	
	public String[] getTopic() {
		
		return table.getTopic();
	}

	
	public void setTopic(String[] topic) {
		
		table.setTopic(topic);
	}

	
	public String[][] getRecords() {
		
		return table.getRecords();
	}

	
	public void setRecords(String[][] records) {
		
		table.setRecords(records);
	}

	
	public int getRow() {
		
		return table.getRow();
	}

	
	public void setRow(int row) {
		
		table.setRow(row);
	}

	
	public int getCol() {
		
		return table.getCol();
	}

	
	public void setCol(int col) {
		
		table.setCol(col);
	}

    public Counting getCounting(){
    	return new Counting(this);
    }
    
    public GeneMatrix getMatrix(){
    	int col = model.length;
    	int label = this.label;
    	for(int i = 0 ; i < model.length ; i ++){
    		if(model[i]==ID){
    			col--;
    			if(this.label>i)
    				label--;
    		}
    	}
    	Matrix m = MatrixFactory.createMatrix(table.getRow(), col);
    	int j = 0;
    	for (int i = 0; i < model.length; i++) {
    		if(model[i]==ID)
    			continue;
    		else if(type[i]==INTEGER){
    			for (int k = 0; k < table.getRow(); k++) {
					m.set(k, j, Double.valueOf(table.get(k, i)));
				}
    		}else if(type[i]==NUMERIC){
    			for (int k = 0; k < table.getRow(); k++) {
    				double d;
    				if("true".equalsIgnoreCase(table.get(k, i)))
    					d = 1;
    				else if("false".equalsIgnoreCase(table.get(k, i)))
    					d = 0;
    				else 
    					d = Double.valueOf(table.get(k, i));
					m.set(k, j, d);
				}
    		}else if(type[i]==POLYGEN){
    			//处理离散数据
    		}
    		j++;
		}
    	return new GeneMatrix(m, label);
    }

    public void setID(int id){
        model[id] = ID;
    }

    public void setName(int name){model[name] = NAME;}
}
