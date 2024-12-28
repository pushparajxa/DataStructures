/*
 * Copyright 2022 DigitalRoute Route AB. All rights reserved.
 *  Proprietary and Confidential.
 */
package com.avro;

import static com.avro.AvroTest.google_domain_status.OPENED;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.data.TimeConversions.DateConversion;
import org.apache.avro.data.TimeConversions.TimestampMicrosConversion;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericData.EnumSymbol;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
//import org.joda.time.DateTimeZone;
//import org.joda.time.LocalDate;

public class AvroTest
{
   public  enum google_domain_status {
        OPENED, CLOSED
    }
    
    Schema dateType =
        LogicalTypes.date()
            .addToSchema(
                Schema.create(Schema.Type.INT)
            );
    
    Schema timestampMicrosType =
        LogicalTypes.timestampMicros()
            .addToSchema(
                Schema.create(Schema.Type.LONG)
            );
    
    Schema nullableTimeStampMicrosType =
        Schema.createUnion(List.of(Schema.create(Schema.Type.NULL), timestampMicrosType));
    
    Schema googleStatusEnumType =
        Schema.createEnum("google_domain_status",
            "Type denoting google domain status",
            "com.apple.google.domain.status.value",
            List.of("OPENED", "CLOSED"));
    
    public static void main(String[] args) throws IOException {
        AvroTest avroTest = new AvroTest();
        String fileName = "/Users/pushparaj/Desktop/code/DataStructures/src/main/resources/avro"
            + "/test";
        
        Schema schema = avroTest.getSchemaForMessageValue();
        
        GenericRecord recordToWrite = avroTest.getRecord(schema);
        
        avroTest.writeToFile(fileName, schema, recordToWrite);
    
        GenericRecord record = avroTest.readFromFile(fileName);

        System.out.println("Hello " + record);
        
        //((GenericEnumSymbol)(record.get("status"))).toString()
        //new DateConversion().fromInt((Integer) record.get("day"), avroTest.dateType, LogicalTypes.date())
            //new TimestampMicrosConversion().fromLong(null, avroTest.nullableTimeStampMicrosType, LogicalTypes.timestampMicros())
        
    }
    
    private GenericRecord getRecord(Schema schema) {
        GenericRecord genericRecord = new GenericData.Record(schema);
        genericRecord.put("status", new EnumSymbol(googleStatusEnumType, "OPENED"));
        genericRecord.put("day", new DateConversion().toInt(LocalDate.now(),
                                                               dateType,
                                                               LogicalTypes.date()));
        genericRecord.put("timestamp", null);
/*        genericRecord.put("timestamp", new TimestampMicrosConversion().toLong(Instant.now(),
                                                                                 nullableTimeStampMicrosType,
                                                                                  LogicalTypes.timestampMicros()));*/
        return genericRecord;
    }
    
    
    private void writeToFile(String file, Schema schema, GenericRecord record) {
        // writing to a file
        final DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(record.getSchema(), new File(file));
            dataFileWriter.append(record);
            System.out.println("Written customer-generic.avro");
            dataFileWriter.close();
        } catch (IOException e) {
            System.out.println("Couldn't write file");
            e.printStackTrace();
        }
    }
    
    private GenericRecord readFromFile(String fileName) throws IOException {
        // reading from a file
        final File file = new File(fileName);
        final DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();
        GenericRecord record;
        try (DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(file, datumReader)){
            record = dataFileReader.next();
            System.out.println("Successfully read avro file");
            System.out.println(record.toString());
            return record;
        }
        catch(IOException e) {
           throw e;
        }
        
    }
    
    private Schema getSchemaForMessageValue()
    {
        Schema dateType =
            LogicalTypes.date()
                .addToSchema(
                    Schema.create(Schema.Type.INT)
                );
        
        Schema timestampMicrosType =
            LogicalTypes.timestampMicros()
                .addToSchema(
                    Schema.create(Schema.Type.LONG)
                );
        
        Schema nullableTimeStampMicrosType =
            Schema.createUnion(List.of(Schema.create(Schema.Type.NULL), timestampMicrosType));
        
        Schema googleStatusEnumType =
            Schema.createEnum("google_domain_status",
                "Type denoting google domain status",
                "com.apple.google.domain.status.value",
                List.of("OPENED", "CLOSED"));
        
        
        return SchemaBuilder.builder("com.apple.google.domain.status")
            .record("google_domain_status_record")
            .fields()
                .name("day")
                    .type(dateType)
                    .noDefault()
                .name("status")
                    .type(googleStatusEnumType)
                    .noDefault()
                .name("timestamp")
                    .type(nullableTimeStampMicrosType)
                    .noDefault()
            .endRecord();
        
    }
}
