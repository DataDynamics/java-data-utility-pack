package io.datadynamics.utility.avro;

import org.apache.avro.Schema;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AvroUtilsTest {

    @Test
    public void parseAvroJson() {

        /*
         * timestamp-millis는 String일 수 없고 Long이어야 합니다.
         */

        String avroSchemaJson = "{\n" +
                "  \"namespace\": \"io.datadynamics.template.avro.model.types2\",\n" +
                "  \"type\": \"record\",\n" +
                "  \"name\": \"DataTypes\",\n" +
                "  \"fields\": [\n" +
                "    {\n" +
                "      \"name\": \"TypeBoolean\",\n" +
                "      \"type\": [\"null\", \"boolean\"]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"TypeInt\",\n" +
                "      \"type\": [\"null\", \"int\"]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"TypeLong\",\n" +
                "      \"type\": [\"null\", \"long\"]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"TypeFloat\",\n" +
                "      \"type\": [\"null\", \"float\"]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"TypeDouble\",\n" +
                "      \"type\": [\"null\", \"double\"]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"TypeString\",\n" +
                "      \"type\": [\"null\", \"string\"]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"TypeBytesDecimal\",\n" +
                "      \"type\": [\"null\", {\"type\": \"bytes\", \"logicalType\": \"decimal\", \"precision\": 6, \"scale\": 2}]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"TypeDate\",\n" +
                "      \"type\": [\"null\", {\"type\": \"int\", \"logicalType\": \"date\"}]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"TypeTimeInMillis\",\n" +
                "      \"type\": [\"null\", {\"type\": \"int\", \"logicalType\": \"time-millis\"}]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"TypeTimeInMicros\",\n" +
                "      \"type\": [\"null\", {\"type\": \"long\", \"logicalType\": \"time-micros\"}]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"TypeTimestampInMillis\",\n" +
                "      \"type\": [\"null\", {\"type\": \"long\", \"logicalType\": \"timestamp-millis\"}]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"TypeStringTimestampInMillis\",\n" +
                "      \"type\": [\"null\", {\"type\": \"string\", \"logicalType\": \"timestamp-millis\"}]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Schema schema = AvroUtils.parseAvroJson(avroSchemaJson);

        Assert.assertNotNull(schema);
        Assert.assertTrue(12 == schema.getFields().size());

        Schema.Field field = schema.getField("TypeStringTimestampInMillis");
        Assert.assertEquals("string", field.schema().getTypes().get(1).getType().getName());
    }

    @Test
    public void createAvroSchema() {
        TypeMapping typeMapping = getTypeMapping();

        Map<String, String> columns = new HashMap();
        columns.put("col_1", "varchar");
        columns.put("col_2", "timestamp");
        columns.put("col_3", "date");

        Schema schema = AvroUtils.createAvroSchema("nifi", "table", columns, typeMapping);
        System.out.println(schema.toString(true));

        Assert.assertNotNull(schema);
        Assert.assertTrue(3 == schema.getFields().size());

        Schema.Field field = schema.getField("col_2");
//        Assert.assertEquals("string", field.schema().getTypes().get(1).getType().getName());
    }

    private TypeMapping getTypeMapping() {
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

        return mapping;
    }
}