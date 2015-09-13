package org.geneframework.context.commands;

import org.geneframework.orm.util.CsvUtil;
import org.geneframework.orm.util.ExcelUtil;
import org.geneframework.orm.util.TableReaderFactory;
import org.geneframework.orm.util.TimeAlignUtil;

/**
 * Created by kong on 15/9/1.
 */
public class InitialTableReader implements ProgramCommand {
    @Override
    public void excute() {
        TableReaderFactory tableReaderFactory = TableReaderFactory.getInstance();
        tableReaderFactory.registerTableReader(new CsvUtil());
        tableReaderFactory.registerTableReader(new ExcelUtil());
        tableReaderFactory.registerTableReader(new TimeAlignUtil());

    }
}
