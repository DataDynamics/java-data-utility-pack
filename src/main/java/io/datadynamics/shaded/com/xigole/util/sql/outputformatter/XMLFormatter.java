package io.datadynamics.shaded.com.xigole.util.sql.outputformatter;

import io.datadynamics.shaded.joptsimple.OptionParser;
import io.datadynamics.shaded.joptsimple.OptionSet;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


/**
 * This is the default XML formatter for Jisql.  It outputs data in an
 * XML format.
 */
public class XMLFormatter implements JisqlFormatter {

    /**
     * Sets a the option list for this formatter.  This is a no-op in the
     * XMLFormatter.
     *
     * @param parser the OptionParser to use.
     */
    public void setSupportedOptions(OptionParser parser) {
        /* no options for the XMLFormatter */
    }

    /**
     * Consumes any options that were specified on the command line. There are
     * no options to set for the XMLFormatter so this method is a no-op.
     *
     * @param options the OptionSet that the main driver is using.
     * @throws Exception if there is a problem parsing the command line arguments.
     */
    public void consumeOptions(OptionSet options) throws Exception {
        /* no options for the XMLFormatter */
    }

    /**
     * Called to output a usage message to the command line window.  This
     * message should contain information on how to call the formatter.
     * There are no options to set for the XMLFormatter so this method is
     * a no-op.
     */
    public void usage(PrintStream out) {
        /* no options for the XMLFormatter */
    }


    /**
     * Outputs a header for a query.  For the XMLFormater this outputs the XML
     * pre-amble.  The character encoding defaults to the current character
     * encoding in use.
     *
     * @param out      a PrintStream to send any output to.
     * @param metaData the ResultSetMetaData for the output.
     */
    public void formatHeader(PrintStream out, ResultSetMetaData metaData) throws Exception {
        out.print("<?xml version=\"1.0\" encoding=\"");
        out.print(Charset.defaultCharset().displayName().toLowerCase());
        out.println("\" ?>");
    }


    /**
     * Called to output the data.  Note that for the XMLFormatter null fields are
     * just output as an empty field.
     *
     * @param out       the PrintStream to output data to.
     * @param resultSet the ResultSet for the row.
     * @param metaData  the ResultSetMetaData for the row.
     */
    public void formatData(PrintStream out, ResultSet resultSet, ResultSetMetaData metaData) throws Exception {

        while (resultSet.next()) {
            int numColumns = metaData.getColumnCount();

            for (int i = 1; i <= numColumns; i++) {
                out.print("<");
                out.print(metaData.getColumnName(i).trim());
                out.print(">");
                String result = resultSet.getString(i);
                if (!resultSet.wasNull())
                    out.print(result.trim());
                out.print("</");
                out.print(metaData.getColumnName(i).trim());
                out.print(">");
            }

            out.println();
        }
    }


    /**
     * Outputs a footer for a query. This method isn't used in the XMLFormatter.
     *
     * @param out      the PrintStream to output data to.
     * @param metaData the ResultSetMetaData for the output.
     */
    public void formatFooter(PrintStream out, ResultSetMetaData metaData) throws Exception {
    }
}
