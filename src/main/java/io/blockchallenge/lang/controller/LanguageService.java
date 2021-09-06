package io.blockchallenge.lang.controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class LanguageService {

    public static final String DEFAULT_LANGUAGE = "en";
    private final Type LANG_FILE_TYPE = new TypeToken<Map<String, Map<String, String>>>() {}.getType();

    private final Plugin plugin;

    // LANG, Map<Tag, Text>
    private final Map<String, Map<String, String>> languageStore = new HashMap<>();

    public LanguageService(Plugin plugin) {
        this.plugin = plugin;
    }

    public void loadLanguageFiles(File moduleFolder) {
        File[] files = moduleFolder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".json");
            }
        });
        if(files == null) {
            return;
        }
        for(File file : files) {
            loadLanguageFile(file);
        }
    }

    public void loadLanguageFile(File file) {
        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            Map<String, Map<String, String>> newLang = gson.fromJson(reader, LANG_FILE_TYPE);
            if(newLang == null) {
                return;
            }
            for(Map.Entry<String, Map<String, String>> entry : newLang.entrySet()) {
                if(!languageStore.containsKey(entry.getKey())) {
                    languageStore.put(entry.getKey(), new HashMap<>());
                }
                languageStore.get(entry.getKey()).putAll(entry.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getTranslation(String lang, String tag) {
        String sLang = lang;
        if(!languageStore.containsKey(lang)) {
            if(!languageStore.containsKey(DEFAULT_LANGUAGE)) {
                return "invalid language";
            }
            sLang = DEFAULT_LANGUAGE;
        }
        if(languageStore.get(sLang).containsKey(tag)) {
            return languageStore.get(sLang).get(tag);
        }
        if(languageStore.get(DEFAULT_LANGUAGE).containsKey(tag)) {
            return languageStore.get(DEFAULT_LANGUAGE).get(tag);
        }
        return "invalide language tag";
    }

}
