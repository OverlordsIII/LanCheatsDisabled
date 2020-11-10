package io.github.overlordsiii.lancheats.mixin;

import io.github.overlordsiii.lancheats.LanCheatsDisabled;
import io.github.overlordsiii.lancheats.gui.button.GamemodeButtonWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.OpenToLanScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(OpenToLanScreen.class)
public class OpenToLanScreenMixin extends Screen {
    @Shadow private ButtonWidget buttonAllowCommands;

    protected OpenToLanScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At(value = "TAIL"))
    private void stopLanScreen(CallbackInfo ci){
        this.buttonAllowCommands.visible = false;
    }
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/OpenToLanScreen;addButton(Lnet/minecraft/client/gui/widget/AbstractButtonWidget;)Lnet/minecraft/client/gui/widget/AbstractButtonWidget;", ordinal = 2), index = 0)
    private AbstractButtonWidget centerGameModeScreen(AbstractButtonWidget widget){
        if (widget instanceof ButtonWidget){
            ButtonWidget buttonWidget = (ButtonWidget)widget;
            return new GamemodeButtonWidget(LanCheatsDisabled.getConfig().getIntEntry("x"), LanCheatsDisabled.getConfig().getIntEntry("y"), 150, 20, LiteralText.EMPTY, (widget1) -> buttonWidget.onPress(), this);
        }
        return widget;
    }
}
