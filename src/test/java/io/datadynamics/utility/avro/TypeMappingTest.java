package io.datadynamics.utility.avro;

import org.junit.Assert;
import org.junit.Test;

public class TypeMappingTest {

    @Test
    public void createTypeMapping() {
        TypeMapping mapping = new TypeMapping();
        mapping.setDefaultType("string");

        mapping.getMapping().put("varchar", "string");
        mapping.getMapping().put("char", "string");
        mapping.getMapping().put("text", "string");

        mapping.getMapping().put("int", "integer");
        mapping.getMapping().put("smallint", "integer");
        mapping.getMapping().put("tinyint", "integer");

        mapping.getMapping().put("boolean", "boolean");

        mapping.getMapping().put("bool", "long");

        mapping.getMapping().put("timestamp", "timestamp");

        mapping.getMapping().put("date", "date");
        mapping.getMapping().put("decimal", "decimal");

        Assert.assertEquals("string", mapping.getDefaultType());
        Assert.assertTrue(mapping.getMapping().size() == 11);
        Assert.assertEquals("string", mapping.getType("varchar"));
    }

}