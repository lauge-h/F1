package dk.heibergen.f1.client.mixin;

import dk.heibergen.f1.client.F1Client;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class KeyBindingMixin {

    @Inject(method = "setPressed", at = @At("HEAD"))
    private void onSetPressed(boolean pressed, CallbackInfo ci) {
        // Check if the zoom key was just released
        if (!pressed && F1Client.isZooming()) {
            F1Client.resetZoomIndex(); // Reset the zoom level on key release
        }
    }
}