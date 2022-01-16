package com.donata.utils.fileutils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TestData {

    private String url;
    private String product;

    public static TestData get() {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();

        URI resource;

        try {
            resource = Thread.currentThread().getContextClassLoader().getResource("test-data.csv").toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        TestData testData;

        try {
            MappingIterator<TestData> objects = mapper.readerFor(TestData.class)
                    .with(schema)
                    .readValues(new File(resource));
            testData = objects.next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return testData;
    }

    public String getUrl() {
        return url;
    }

    public String getProduct() {
        return product;
    }
}
