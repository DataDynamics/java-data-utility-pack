package io.datadynamics.utility.file;

import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.file.CodecFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AvroUtils {

    /**
     * Avro Schema JSON을 파싱합니다.
     *
     * @param json Avro Schema JSON
     * @return Avro Schema Object
     */
    public static Schema parse(String json) {
        Schema.Parser parser = new Schema.Parser();
        return parser.parse(json);
    }

    /**
     * Avro Schema를 생성한다.
     *
     * @param namespace   Namespace
     * @param name        Name
     * @param columns     컬럼목록
     * @param typeMapping 자료형 매핑
     * @return Schema
     */
    public static Schema createAvroSchema(String namespace, String name, Map<String, String> columns, TypeMapping typeMapping) {
        SchemaBuilder.RecordBuilder<Schema> recordBuilder = SchemaBuilder
                .record(name)
                .namespace(namespace);

        SchemaBuilder.FieldAssembler<Schema> fields = recordBuilder.fields();

        columns.keySet().forEach(entry -> {
            // 자료형을 Avro 자료형으로 변환한다.
            String type = typeMapping.getType(columns.get(entry));
            addField(fields, type, entry);
        });

        return fields.endRecord();
    }

    private static void addField(SchemaBuilder.FieldAssembler<Schema> fields, String type, String name) {
        Schema timestampMilliType = LogicalTypes.timestampMillis().addToSchema(Schema.create(Schema.Type.LONG));
        Schema dateType = LogicalTypes.date().addToSchema(Schema.create(Schema.Type.INT));

        switch (type) {
            case "long":
                fields.name(name).type().nullable().longType().noDefault();
                break;
            case "boolean":
                fields.name(name).type().nullable().booleanType().noDefault();
                break;
            case "bytes":
                fields.name(name).type().nullable().bytesType().noDefault();
                break;
            case "double":
                fields.name(name).type().nullable().doubleType().noDefault();
                break;
            case "float":
                fields.name(name).type().nullable().floatType().noDefault();
                break;
            case "integer":
                fields.name(name).type().nullable().intType().noDefault();
                break;
            case "date":
                fields.name(name).type().unionOf().nullType().and().type(dateType).endUnion().noDefault();
                break;
            case "timestamp":
                fields.name(name).type().unionOf().nullType().and().type(timestampMilliType).endUnion().noDefault();
                break;
            case "string":
            default:
                fields.name(name).type().nullable().stringType().noDefault();
        }
    }

    public static boolean isNullable(final Schema schema) {
        final Schema.Type schemaType = schema.getType();
        if (schemaType == Schema.Type.UNION) {
            for (final Schema unionSchema : schema.getTypes()) {
                if (isNullable(unionSchema)) {
                    return true;
                }
            }
        }

        return schemaType == Schema.Type.NULL;
    }

    public static List<Schema> getNonNullSubSchemas(final Schema avroSchema) {
        final List<Schema> unionFieldSchemas = avroSchema.getTypes();
        if (unionFieldSchemas == null) {
            return Collections.emptyList();
        }

        final List<Schema> nonNullTypes = new ArrayList<>(unionFieldSchemas.size());
        for (final Schema fieldSchema : unionFieldSchemas) {
            if (fieldSchema.getType() != Schema.Type.NULL) {
                nonNullTypes.add(fieldSchema);
            }
        }

        return nonNullTypes;
    }

    public static CodecFactory getCodecFactory(String property) {
        CodecType type = CodecType.valueOf(property);
        switch (type) {
            case BZIP2:
                return CodecFactory.bzip2Codec();
            case DEFLATE:
                return CodecFactory.deflateCodec(CodecFactory.DEFAULT_DEFLATE_LEVEL);
            case LZO:
                return CodecFactory.xzCodec(CodecFactory.DEFAULT_XZ_LEVEL);
            case SNAPPY:
                return CodecFactory.snappyCodec();
            case NONE:
            default:
                return CodecFactory.nullCodec();
        }
    }

    public static enum CodecType {
        BZIP2,
        DEFLATE,
        NONE,
        SNAPPY,
        LZO
    }

}