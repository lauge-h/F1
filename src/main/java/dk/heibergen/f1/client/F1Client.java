package dk.heibergen.f1.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

import org.lwjgl.glfw.GLFW;

public class F1Client implements ClientModInitializer {

    private static boolean currentlyZoomed;
    private static KeyBinding keyBinding;
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static final double zoomLevel = 0.23;

    @Override
    public void onInitializeClient() {
        keyBinding = new KeyBinding("key.f1.zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "category.f1.zoom");
        currentlyZoomed = false;
        KeyBindingHelper.registerKeyBinding(keyBinding);
    }
    public static boolean isZooming() {
        return keyBinding.isPressed();
    }

    private static boolean zoomStarting() {
        return isZooming() && !currentlyZoomed;
    }

    private static boolean zoomStopping() {
        return !isZooming() && currentlyZoomed;
    }

    private static void zoomStarted() {
        currentlyZoomed = true;
    }

    private static void zoomStopped() {
        currentlyZoomed = false;
    }

}
