package org.geneframework.context.mapping;

import org.geneframework.context.cfg.Configuration;
import org.geneframework.context.cfg.Setting;
import org.geneframework.context.util.PropertiesHelper;
import org.geneframework.context.util.XMLHelper;

import java.util.Set;
import java.util.Vector;

/**
 * Created by kong on 15/8/26.
 * mapping factory,used to
 * map the numbers or methods with
 * genetic character
 */
public class MappingFactory {

    private static Vector<Mapping> mappings = new Vector<>();
    private static Configuration configuration = Configuration.getInstance();

    private MappingFactory() {}
    private static MappingFactory ourInstance = new MappingFactory();

    public static MappingFactory getInstance() {
        return ourInstance;
    }


    public static int DT       = 0;
    public static int DW       = 1;
    public static int FUNCTION = 2;
    public static int TERMINAL = 3;
    public Mapping getMapping(int map){
        return mappings.get(map);
    }

    public void addMapping(Mapping mapping){
        if(!mappings.contains(mapping))
            mappings.add(mapping);
    }
}
