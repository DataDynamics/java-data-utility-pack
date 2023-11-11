# Data 관련 Java Utility Pack

데이터 관련 다양한 업무를 하다보면 필요한 각종 유틸리티가 필요합니다.
이 프로젝트는 그러한 개발에 편한 유틸리티를 한곳에 모아둔 자바 프로젝트입니다.

## Requirement

* JDK 1.8 이상
* Apache Maven 3.8.1 이상

## Build

```
# mvn clean package
```

## Avro

### Date & Timestamp

* Logical Type `date`
  * Avro Int입니다.
  * 1970년 1월 1일(ISO 달력) Unix 시대의 일수를 저장
* Logical Type `time-millis`
  * Avro Int입니다.
  * 특정 달력, 시간대 또는 날짜를 참조하지 않고 1밀리초의 정밀도로 시간을 나타냅니다
  * 자정 이후의 밀리초 수(00:00:00.000)를 저장합니다.
* Logical Type `time-micros`
  * Avro Long입니다.
  * 특정 달력, 시간대 또는 날짜를 참조하지 않고 1마이크로초의 정밀도로 시간을 나타냅니다.
  * 자정 이후의 마이크로초 수(00:00:00.000000)를 저장합니다.
* Logical Type `timestamp-millis`
  * Avro Long이며 String을 지원하지 않습니다.
  * 특정 시간대나 달력과 관계없이 전역 타임라인의 순간을 1밀리초의 정밀도로 나타냅니다. 이 과정에서 시간대 정보가 손실됩니다.
  * 따라서, 값을 다시 읽으면 순간만 재구성할 수 있고 원래 표현은 재구성할 수 없습니다
  * 1970년 1월 1일 00:00:00.000 UTC인 Unix 시대로부터의 밀리초 수를 저장합니다.
* Logical Type `timestamp-micros`
  * Avro Long이며 String을 지원하지 않습니다.
  * 특정 시간대나 달력과 관계없이 전역 타임라인의 순간을 1마이크로초의 정밀도로 나타냅니다. 이 과정에서 시간대 정보가 손실됩니다.
  * 따라서, 값을 다시 읽으면 순간만 재구성할 수 있고 원래 표현은 재구성할 수 없습니다
  * 1970년 1월 1일 00:00:00.000000 UTC의 Unix 시대로부터 마이크로초 수를 저장합니다.
* Logical Type `local-timestamp-millis`
  * Avro Long이며 String을 지원하지 않습니다.
  * 어떤 특정 시간대가 현지로 간주되는지에 관계없이 현지 시간대의 타임스탬프를 1밀리초의 정밀도로 나타냅니다.
  * 1970년 1월 1일 00:00:00.000부터 밀리초 수를 저장합니다.
* Logical Type `local-timestamp-micros`
  * Avro Long이며 String을 지원하지 않습니다.
  * 어떤 특정 시간대가 현지로 간주되는지에 관계없이 현지 시간대의 타임스탬프를 1마이크로초의 정밀도로 나타냅니다.
  * 1970년 1월 1일 00:00:00.000000부터 마이크로초 수를 저장합니다.

### Schema 생성

```java
Schema book = SchemaBuilder.record("Book")
        .namespace("com.example.kafka")
        .fields()
        .requiredLong("id")
        .requiredLong("price")
        .requiredInt("quantity")
        .endRecord();

Schema gift = SchemaBuilder.nullable().record("Gift")
        .namespace("com.example.kafka")
        .fields()
        .requiredLong("id")
        .requiredInt("quantity")
        .endRecord();

Schema bookOrder = SchemaBuilder.record("BookOrder")
        .namespace("com.example.kafka")
        .fields()
        .requiredLong("orderId")
        .requiredLong("memberNo")
        .requiredString("orderDate")
        .optionalLong("couponId")
        .name("books")
        .type()
        .array()
        .items()
        .type(book)
        .noDefault()
        .name("gifts")
        .type()
        .array()
        .items()
        .type(gift)
        .noDefault()
        .endRecord();
```

### Avro Schema JSON

```json
{
  "namespace": "io.datadynamics.template.avro.model.types2",
  "type": "record",
  "name": "DataTypes2",
  "fields": [
    {
      "name": "TypeBoolean",
      "type": ["null", "boolean"]
    },
    {
      "name": "TypeInt",
      "type": ["null", "int"]
    },
    {
      "name": "TypeLong",
      "type": ["null", "long"]
    },
    {
      "name": "TypeFloat",
      "type": ["null", "float"]
    },
    {
      "name": "TypeDouble",
      "type": ["null", "double"]
    },
    {
      "name": "TypeString",
      "type": ["null", "string"]
    },
    {
      "name": "TypeBytesDecimal",
      "type": ["null", {"type": "bytes", "logicalType": "decimal", "precision": 6, "scale": 2}]
    },
    {
      "name": "TypeDate",
      "type": ["null", {"type": "int", "logicalType": "date"}]
    },
    {
      "name": "TypeTimeInMillis",
      "type": ["null", {"type": "int", "logicalType": "time-millis"}]
    },
    {
      "name": "TypeTimeInMicros",
      "type": ["null", {"type": "long", "logicalType": "time-micros"}]
    },
    {
      "name": "TypeTimestampInMillis",
      "type": ["null", {"type": "long", "logicalType": "timestamp-millis"}]
    },
    {
      "name": "TypeStringTimestampInMillis",
      "type": ["null", {"type": "string", "logicalType": "timestamp-millis"}]
    }
  ]
}
```

## Dependencies

* JISQL https://github.com/stdunbar/jisql
  * JOPT Simple (https://github.com/jopt-simple/jopt-simple)
  * SQL Formatter (https://github.com/vertical-blank/sql-formatter)

## Miscellaneous

### Generate Object From JSON

```xml
<dependency>
    <groupId>org.jsonschema2pojo</groupId>
    <artifactId>jsonschema2pojo-core</artifactId>
    <version>1.1.1</version>
</dependency>
```

```java
public static void convertJsonToJavaClass(URL inputJsonUrl, File outputJavaClassDirectory, String packageName, String javaClassName) throws IOException {
    JCodeModel jcodeModel = new JCodeModel();

    GenerationConfig config = new DefaultGenerationConfig() {
        @Override
        public boolean isGenerateBuilders() {
            return true;
        }

        @Override
        public SourceType getSourceType() {
            return SourceType.JSON;
        }
    };

    SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
    mapper.generate(jcodeModel, javaClassName, packageName, inputJsonUrl);

    jcodeModel.build(outputJavaClassDirectory);
}
```
