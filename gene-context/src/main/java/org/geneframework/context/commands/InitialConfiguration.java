package org.geneframework.context.commands;

import org.geneframework.context.cfg.Configuration;
import org.geneframework.context.cfg.Setting;
import org.geneframework.context.util.PropertiesHelper;
import org.geneframework.context.util.XMLHelper;

/**
 * Created by kong on 15/9/1.
 */
public class InitialConfiguration implements ProgramCommand {


    @Override
    public void excute() {
        Setting setting = new Setting();
        PropertiesHelper prop = new PropertiesHelper(setting, "gene.properties");
        XMLHelper helper = new XMLHelper("gene-function.xml");
        Configuration.getInstance().setXmlHelper(helper);
        Configuration.getInstance().setSetting(setting);
        Configuration.getInstance().setPropertiesHelper(prop);
        Configuration.getInstance().setFunction(helper.getGeneMethods());


    }
}
