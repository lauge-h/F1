package dk.heibergen.f1.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.Mouse;
import dk.heibergen.f1.client.F1Client;

@Mixin(Mouse.class)
public class MouseMixin {
    @Inject(at = @At("RETURN"), method = "onMouseScroll(JDD)V")
    private void onOnMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        F1Client client = new F1Client();
        client.onMouseScroll(vertical);
    }
}