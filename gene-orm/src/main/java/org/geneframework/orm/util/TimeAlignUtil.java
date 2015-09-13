package org.geneframework.orm.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import org.geneframework.orm.matrix.Table;

public class TimeAlignUtil implements TableReader {

	private String split = null;
	private int timeLength;

	public TimeAlignUtil(String split) {
		this(split, 10);
	}

	public TimeAlignUtil(String split, int timeLength) {
		this.split = split;
		this.timeLength = timeLength;
	}

	public TimeAlignUtil() {
		this(" ");
	}

	@Override
	public Table readTable(String filePath, boolean Ctaintopic)
			throws Exception {
		BufferedReader br = null;
		try {
			Vector<String> data = new Vector<String>();
			Vector<String[]> records = new Vector<String[]>();
			br = new BufferedReader(new FileReader(new File(filePath)));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] sp = line.split(split);
				for (int i = 0; i < sp.length; i++) {
					data.add(sp[i]);
				}
			}
			if (data.size() < timeLength)
				throw new Exception(
						"timelength is out of range,please reset timeLength");
			int j;
			for (int i = timeLength; i <= data.size(); i++) {
				String[] s = new String[timeLength];
				j = 0;
				for (int k = i - timeLength; k < i; k++) {
					s[j++] = data.get(k);
				}
				records.add(s);
			}
			String[][] datas = new String[data.size() - timeLength + 1][timeLength];
			for (int i = 0; i < records.size(); i++) {
				String[] a = records.get(i);
				datas[i] = a;
			}

			return new Table(datas);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				br.close();
		}
		return null;
	}

	@Override
	public void writeTable(Table target, String filePath) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File(filePath)));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < target.getCol(); i++) {
				sb.append(target.get(0, i));
			}
			for (int i = 1; i < target.getRow(); i++) {
				sb.append(target.get(i, target.getCol() - 1));
			}
			bw.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	@Override
	public String[] getSuffixs() {
		String[] suffixs = { "txt" };
		return suffixs;
	}

	public String getSplit() {
		return split;
	}

	public void setSplit(String split) {
		this.split = split;
	}

	public int getTimeLength() {
		return timeLength;
	}

	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
	}

}
