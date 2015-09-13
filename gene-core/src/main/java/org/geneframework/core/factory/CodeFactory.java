package org.geneframework.core.factory;

import org.geneframework.core.command.codecommand.CodeCommand;

/**
 * Created by kong on 15/6/24.
 */
public class CodeFactory {
    public static final String TREECODE = "org.geneframework.core";
    private static CodeFactory ourInstance = new CodeFactory();

    public static CodeFactory getInstance() {
        return ourInstance;
    }

    private CodeFactory() {
    }

    public CodeCommand createDefaultCodeSchema(){
        return createCodeSchema(TREECODE);
    }

    public CodeCommand createCodeSchema(String name){
        try {
            CodeCommand code = (CodeCommand) Class.forName(name).newInstance();
            return code;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
