package org.example.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class Environment {

    private static final Environment INSTANCE = new Environment();

    private DocumentContext jsonContext;

    private Environment() {
        try (InputStream inputStream = new FileInputStream("config.json")) {
            Reader fileReader = new InputStreamReader(inputStream);
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(fileReader);
            jsonContext = JsonPath.parse(jsonObject);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static Environment getInstance() {
        return INSTANCE;
    }

    public String getValue(final String keyJsonPath) {
        return jsonContext.read(keyJsonPath);
    }

}
