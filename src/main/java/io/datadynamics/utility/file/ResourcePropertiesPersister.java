package io.datadynamics.utility.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ResourcePropertiesPersister extends DefaultPropertiesPersister {

    /**
     * A convenient constant for a default {@code ResourcePropertiesPersister} instance,
     * as used in Spring's common resource support.
     *
     * @since 5.3
     */
    public static final ResourcePropertiesPersister INSTANCE = new ResourcePropertiesPersister();

    @Override
    public void loadFromXml(Properties props, InputStream is) throws IOException {
        super.loadFromXml(props, is);
    }

    @Override
    public void storeToXml(Properties props, OutputStream os, String header) throws IOException {
        super.storeToXml(props, os, header);
    }

    @Override
    public void storeToXml(Properties props, OutputStream os, String header, String encoding) throws IOException {
        super.storeToXml(props, os, header, encoding);
    }

}
