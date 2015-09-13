package org.geneframework.context.commands;

import org.geneframework.context.cfg.Configuration;
import org.geneframework.context.cfg.GeneMethod;
import org.geneframework.context.cfg.Setting;
import org.geneframework.context.mapping.AbstractDXMapping;
import org.geneframework.context.mapping.DtMapping;
import org.geneframework.context.mapping.DwMapping;
import org.geneframework.context.mapping.FunctionMapping;
import org.geneframework.context.mapping.MappingFactory;
import org.geneframework.context.mapping.TerminalMapping;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by kong on 15/9/1.
 */
public class InitialMapping implements ProgramCommand{
    @Override
    public void excute() {
        Configuration configuration = Configuration.getInstance();
        Setting setting = configuration.getSetting();

        Set set = configuration.getXmlHelper().getGeneMethods();
        int t = 0;
        for(Iterator it = set.iterator() ; it.hasNext() ; ){
            GeneMethod geneMethod = (GeneMethod) it.next();
            if(t < geneMethod.getElementnums())
                t = geneMethod.getElementnums();
        }
        t = setting.getHlength()*(t - 1) +1;
        setting.setTlength(t);

        AbstractDXMapping dtMapping = new DtMapping(setting.getHlength(),setting.getDt_low(),setting.getDt_high());
        AbstractDXMapping dwMapping = new DwMapping(setting.getHlength(),setting.getDw_low(),setting.getDw_high());

        FunctionMapping functionMapping = new FunctionMapping(configuration.getXmlHelper().getGeneMethods());
        configuration.setDTterminal(dtMapping.getKeys());
        configuration.setDWterminal(dwMapping.getKeys());
        configuration.setFunction(functionMapping.getKeys());

        MappingFactory mappingFactory = MappingFactory.getInstance();
        mappingFactory.addMapping(dtMapping);
        mappingFactory.addMapping(dwMapping);
        mappingFactory.addMapping(functionMapping);
        mappingFactory.addMapping(TerminalMapping.getInstance());
    }
}
