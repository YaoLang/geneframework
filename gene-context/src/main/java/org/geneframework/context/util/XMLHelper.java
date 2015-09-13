package org.geneframework.context.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.geneframework.context.cfg.GeneMethod;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public final class XMLHelper {
	
	DOMReader domReader;
	private Set geneMethods;
	private Logger log = Logger.getLogger(org.geneframework.context.cfg.Setting.class);
	
	public XMLHelper(String filePath){
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(filePath);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(in);
			domReader = new DOMReader();
			org.dom4j.Document doc = domReader.read(document);
			
			analysis(doc);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void analysis(org.dom4j.Document doc) throws Exception{
		StringBuilder message = new StringBuilder();
		geneMethods = new HashSet<GeneMethod>();
		Element root = doc.getRootElement();
		int num = 0;;
		for (Iterator i = root.elementIterator(); i.hasNext();) {
			Element r = (Element) i.next();
			if ("method".equals(r.getName())) {
				String type = r.attribute("type").getText();
				String mark = null;
				if(r.attribute("mark")!=null)
					mark = r.attribute("mark").getText();
				num++;
				GeneMethod geneMethod = new GeneMethod(type,mark);
				geneMethods.add(geneMethod);
				message.append(geneMethod.getMethod().getName()+" ");
			}else if("define".equals(r.getName())){
				String clazz = r.attribute("class").getText();
				String method = r.attribute("method").getText();
				String mark = r.attribute("mark").getText();
				num++;
				GeneMethod geneMethod = new GeneMethod(clazz, method, mark);
				geneMethods.add(geneMethod);
				message.append(geneMethod.getMethod().getName()+" ");
			}
		}
		String clazz = "org.geneframework.context.maths.CommonMaths";
		GeneMethod add = new GeneMethod(clazz,"addExact","+");
		GeneMethod sub = new GeneMethod(clazz,"addExact","-");
		GeneMethod mul = new GeneMethod(clazz,"addExact","*");
		GeneMethod div = new GeneMethod(clazz,"addExact","/");
		geneMethods.add(add);
		geneMethods.add(sub);
		geneMethods.add(mul);
		geneMethods.add(div);
		message.append("+ ");
		message.append("- ");
		message.append("* ");
		message.append("/ ");
		num = num + 4;


		if(num!=geneMethods.size()){
			log.error("repeated mark the define method");
			throw new Exception("XMLERROR:repeated mark the define method");
		}else
			log.info("methods: "+message.toString());
	}

	public Set<GeneMethod> getGeneMethods() {
		return geneMethods;
	}
	
}
