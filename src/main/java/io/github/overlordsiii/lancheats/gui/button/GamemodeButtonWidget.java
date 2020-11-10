package io.github.overlordsiii.lancheats.gui.button;

import io.github.overlordsiii.lancheats.LanCheatsDisabled;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.io.IOException;

public class GamemodeButtonWidget extends ButtonWidget {

    public boolean didDrag = false;
    public Screen screen;

    public GamemodeButtonWidget(int x, int y, int width, int height, Text message, PressAction onPress, Screen screen) {
        super(x, y, width, height, message, onPress);
        this.screen = screen;
    }
    public void setPos(int x, int y) {
        LanCheatsDisabled.getConfig().setIntEntry("x", x);
        LanCheatsDisabled.getConfig().setIntEntry("y", y);
        try {
            LanCheatsDisabled.getConfig().serialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return this.isValidClickButton(button) && this.clicked(mouseX, mouseY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return didDrag ? didDrag = false
                : super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
        this.setPos(Math.min(Math.max(0, (int) mouseX - this.width / 2), this.screen.width - this.width),
                Math.min(Math.max(0, (int) mouseY - this.width / 10), this.screen.height - this.height) + 10);
        this.didDrag = true;

        super.onDrag(mouseX, mouseY, deltaX, deltaY);
    }
}
