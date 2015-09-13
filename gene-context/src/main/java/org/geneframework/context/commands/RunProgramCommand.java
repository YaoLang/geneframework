package org.geneframework.context.commands;

import java.util.Vector;

/**
 * Created by kong on 15/9/2.
 */
public class RunProgramCommand {
    private Vector<ProgramCommand> programCommands = new Vector<>();

    public void addCommand(ProgramCommand programCommand){
        programCommands.add(programCommand);
    }

    public void runCommands(){
        for (int i = 0; i < programCommands.size(); i++) {
            programCommands.get(i).excute();
        }
    }
}
