package io.blockchallenge.lang.model;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class LanguageMetadataValue implements MetadataValue {

    private final Plugin plugin;
    private final String language;

    public LanguageMetadataValue(Plugin plugin, String language) {
        this.plugin = plugin;
        this.language = language;
    }

    @Override
    public Object value() {
        return language;
    }

    @Override
    public int asInt() {
        return 0;
    }

    @Override
    public float asFloat() {
        return 0;
    }

    @Override
    public double asDouble() {
        return 0;
    }

    @Override
    public long asLong() {
        return 0;
    }

    @Override
    public short asShort() {
        return 0;
    }

    @Override
    public byte asByte() {
        return 0;
    }

    @Override
    public boolean asBoolean() {
        return false;
    }

    @Override
    public String asString() {
        return language;
    }

    @Override
    public Plugin getOwningPlugin() {
        return plugin;
    }

    @Override
    public void invalidate() {

    }
}
