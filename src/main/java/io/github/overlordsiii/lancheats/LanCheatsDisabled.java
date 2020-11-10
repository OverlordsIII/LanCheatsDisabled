package io.github.overlordsiii.lancheats;

import io.github.overlordsiii.lancheats.config.LanCheatsDisabledConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

@Environment(EnvType.CLIENT)
public class LanCheatsDisabled implements ClientModInitializer {
    private static LanCheatsDisabledConfig config;
    @Override
    public void onInitializeClient() {
       config = new LanCheatsDisabledConfig(FabricLoader.getInstance().getConfigDir());
       config.addIntEntry("y", 100);
       config.addIntEntry("x", 138);
       config.createAndLoadProperties();
    }
    public static LanCheatsDisabledConfig getConfig(){
        return config;
    }

}
