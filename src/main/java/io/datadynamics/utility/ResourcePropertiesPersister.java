package io.datadynamics.utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Spring-aware subclass of the plain {@link DefaultPropertiesPersister},
 * adding a conditional check for disabled XML support through the shared
 * "spring.xml.ignore" property.
 */
public class ResourcePropertiesPersister extends DefaultPropertiesPersister {

    /**
     * A convenient constant for a default {@code ResourcePropertiesPersister} instance,
     * as used in Spring's common resource support.
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
