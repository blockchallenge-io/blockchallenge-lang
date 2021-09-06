package io.blockchallenge.lang.controller;

import io.blockchallenge.lang.model.LanguageMetadataValue;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class PlayerLanguageService implements Listener {

    private final String PLAYER_LANG_TAG = "language";

    private final Plugin plugin;

    public PlayerLanguageService(Plugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Set the Player language
     * @param player Player
     * @param lang Language-Tag
     */
    public void setPlayerLanguage(Player player, String lang) {
        player.setMetadata(PLAYER_LANG_TAG, new LanguageMetadataValue(plugin, lang));
    }

    /**
     * Get the Player language
     * @param player Player
     * @return Language-Tag
     */
    public String getPlayerLanguage(Player player) {
        if(!player.hasMetadata(PLAYER_LANG_TAG)) {
            return LanguageService.DEFAULT_LANGUAGE;
        }
        List<MetadataValue> metaValues = player.getMetadata(PLAYER_LANG_TAG);
        if(metaValues.size() == 0) {
            return LanguageService.DEFAULT_LANGUAGE;
        }
        for(MetadataValue meta : metaValues) {
            if(meta instanceof LanguageMetadataValue) {
                return meta.asString();
            }
        }
        return LanguageService.DEFAULT_LANGUAGE;
    }

}
