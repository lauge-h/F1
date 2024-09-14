package dk.heibergen.f1.client.mixin;

import dk.heibergen.f1.client.F1Client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.render.GameRenderer;

@Environment(EnvType.CLIENT)
@Mixin(GameRenderer.class)
public class F1Mixin {

    @Inject(method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D", at = @At("RETURN"), cancellable = true)
    public void getZoomLevel(CallbackInfoReturnable<Double> callbackInfo) {
        if(F1Client.isZooming()) {
            double fov = callbackInfo.getReturnValue();
            callbackInfo.setReturnValue(fov * F1Client.zoomLevel);
        }
    }
}
