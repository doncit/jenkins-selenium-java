package com.donata.credentials;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;

public class Credentials {

    private String username;
    private String password;

    public static Credentials get() {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();

        Credentials credentials;

        try {
            MappingIterator<Credentials> objects = mapper.readerFor(Credentials.class)
                    .with(schema)
                    .readValues(new File("credentials.csv"));
            credentials = objects.next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return credentials;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
