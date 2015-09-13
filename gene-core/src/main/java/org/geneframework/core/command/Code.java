package org.geneframework.core.command;

import org.geneframework.core.command.codecommand.CodeCommand;

import java.util.Vector;

/**
 * Created by kong on 15/9/3.
 */
public class Code {
    private Vector<CodeCommand> codeCommands = new Vector<>();

    public void addCodeCommand(CodeCommand codeCommand){
        if(codeCommands.contains(codeCommand))
            return;
        codeCommands.add(codeCommand);
    }

    public void removeCodeCommand(CodeCommand codeCommand){
        if(codeCommands.contains(codeCommand))
            codeCommands.remove(codeCommand);
    }

    public char[] runCode(){
        for (int i = 0; i < codeCommands.size(); i++) {
            codeCommands.get(i).randomencode();
        }
        return null;
    }
}
