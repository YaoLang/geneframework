package org.geneframework.core.command.codecommand;

import org.geneframework.context.cfg.Configuration;
import org.geneframework.core.command.VectorAdapter;

import java.util.Vector;

/**
 * Created by kong on 15/6/24.
 */
public abstract class CodeCommand {
    private static Vector<String> function;
    protected static Vector<String> terminal;
    protected static Vector<String> heading;
    static {
        function = VectorAdapter.getVector(Configuration.getInstance().getFunction());
        terminal = VectorAdapter.getVector(Configuration.getInstance().getTerminal());
        heading = new Vector<>();
        for (int i = 0; i < function.size(); i++) {
            heading.add(function.get(i));
        }
        for (int i = 0; i < terminal.size(); i++) {
            heading.add(terminal.get(i));
        }
    }
    protected static int hlength = Configuration.getInstance().getSetting().getHlength();
    protected static int tlength = Configuration.getInstance().getSetting().getTlength();
    protected static int slength = Configuration.getInstance().getSetting().getSlength();

    /**
     * 返回随机产生的基因编码
     * @return
     */
    public abstract char[] randomencode();



}
