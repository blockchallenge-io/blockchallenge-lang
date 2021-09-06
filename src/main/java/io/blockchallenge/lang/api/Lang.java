package io.blockchallenge.lang.api;

import io.blockchallenge.lang.LanguageModule;
import org.bukkit.entity.Player;

public class Lang {

    public static String getLanguage(Player player) {
        return LanguageModule.getInstance().getPlayerLanguageService().getPlayerLanguage(player);
    }

    public static void setLanguage(Player player, String lang) {
        LanguageModule.getInstance().getPlayerLanguageService().setPlayerLanguage(player, lang);
    }

    public static String get(Player player, String tag) {
        return get(getLanguage(player), tag);
    }

    public static String get(String lang, String tag) {
        return LanguageModule.getInstance().getLanguageService().getTranslation(lang, tag);
    }

}
