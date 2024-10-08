package dk.heibergen.f1.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.player.PlayerInventory;
import dk.heibergen.f1.client.F1Client;

@Mixin(PlayerInventory.class)
public class InvMixin {

    @Inject(at = @At("HEAD"), method = "scrollInHotbar(D)V", cancellable = true)
    private void onScrollInHotbar(double scrollAmount, CallbackInfo ci) {
        // Cancel the hotbar scrolling if the zoom key is pressed
        if (F1Client.isZooming()) {
            ci.cancel();
        } else {
            // If the player is not zooming, and they scroll the hotbar, reset the zoom index to 2.5
            F1Client.resetZoomIndex();
        }
    }
}