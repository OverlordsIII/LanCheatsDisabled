package io.github.overlordsiii.lancheats.config;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

@SuppressWarnings("ALL")
public class LanCheatsDisabledConfig {
    private Map<String, String> stringEntries = Maps.newHashMap();
    private Map<String, Integer> intEntries = Maps.newHashMap();
    private Map<String, Boolean> boolEntries = Maps.newHashMap();

    private Path configPath;

    public LanCheatsDisabledConfig(Path configFolder){
        configPath = configFolder.resolve("lancheats.properties");
    }

    public void createAndLoadProperties(){
        try {
            deserialize();
            serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialize() throws IOException {
        if (!configPath.toFile().exists()){
            return;
        }
        Properties properties = new Properties();
        properties.load(Files.newInputStream(configPath));
        properties.forEach((key, value) -> {
            if (stringEntries.containsKey(key.toString())){
                String val = value.toString();
                stringEntries.replace(key.toString(), val);
            } else if (intEntries.containsKey(key.toString())){
                Integer val = Integer.parseInt(value.toString());
                intEntries.replace(key.toString(), val);
            } else if (boolEntries.containsKey(key.toString())){
                Boolean val = Boolean.parseBoolean(value.toString());
                boolEntries.replace(key.toString(), val);
            }
        });
    }

    public void serialize() throws IOException {
        Properties properties = new Properties();
        stringEntries.keySet().forEach(string -> properties.setProperty(string, stringEntries.get(string)));
        intEntries.keySet().forEach(string -> properties.setProperty(string, intEntries.get(string).toString()));
        boolEntries.keySet().forEach(string -> properties.setProperty(string, boolEntries.get(string).toString()));
        properties.store(Files.newOutputStream(configPath), "This file is used to parse LanCheatsDisabled config option");
    }

    public LanCheatsDisabledConfig addStringEntry(String name, String defaultValue){
        stringEntries.put(name, defaultValue);
        return this;
    }

    public LanCheatsDisabledConfig addIntEntry(String name, Integer defaultValue){
        intEntries.put(name, defaultValue);
        return this;
    }

    public LanCheatsDisabledConfig addBoolEntry(String name, Boolean defaultValue){
        boolEntries.put(name, defaultValue);
        return this;
    }

    public String getStringEntry(String name){
        return stringEntries.get(name);
    }

    public int getIntEntry(String name){
        return intEntries.get(name);
    }

    public boolean getBoolEntry(String name){
        return boolEntries.get(name);
    }

    public LanCheatsDisabledConfig setStringEntry(String name, String newValue){
        this.stringEntries.replace(name, newValue);
        return this;
    }

    public LanCheatsDisabledConfig setIntEntry(String name, int newValue){
        this.intEntries.replace(name, newValue);
        return this;
    }

    public LanCheatsDisabledConfig setBoolEntry(String name, boolean newValue){
        this.boolEntries.replace(name, newValue);
        return this;
    }
}
