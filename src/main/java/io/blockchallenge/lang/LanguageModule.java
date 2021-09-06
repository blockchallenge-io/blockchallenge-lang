package io.blockchallenge.lang;

import io.blockchallenge.base.BlockChallengeModule;
import io.blockchallenge.lang.controller.LanguageService;
import io.blockchallenge.lang.controller.PlayerLanguageService;
import org.bukkit.plugin.Plugin;

public class LanguageModule extends BlockChallengeModule {

    private static LanguageModule languageModuleInstance = null;

    private final PlayerLanguageService playerLanguageService;
    private final LanguageService languageService;

    public LanguageModule(Plugin plugin) {
        super(plugin, "lang");
        playerLanguageService = new PlayerLanguageService(plugin);
        languageService = new LanguageService(plugin);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        languageService.loadLanguageFiles(this.moduleFolder);
        languageModuleInstance = this;
    }

    @Override
    public void onUnload() {
        languageModuleInstance = null;
    }


    public PlayerLanguageService getPlayerLanguageService() {
        return playerLanguageService;
    }

    public LanguageService getLanguageService() {
        return languageService;
    }

    public static LanguageModule getInstance() {
        return languageModuleInstance;
    }


}
