package dk.heibergen.f1.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

public class F1Client implements ClientModInitializer {

    private static KeyBinding keyBinding;
    private static final double defaultZoomLevel = 6;
    private static final double exponentialZoomValue = 0.2;
    private static double currentZoomIndex = defaultZoomLevel; // Start at index 1 (which is 3.0 by default)
    private static double currentZoomLevel = Math.exp(currentZoomIndex * exponentialZoomValue);

    // Keybinding
    @Override
    public void onInitializeClient() {
        keyBinding = new KeyBinding("key.f1.zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "category.f1.zoom");
        KeyBindingHelper.registerKeyBinding(keyBinding);
    }

    // Also used in InvMixin
    public static boolean isZooming() {
        return keyBinding.isPressed();
    }

    // Called by MouseMixin
    public void onMouseScroll(double amount) {
        if (isZooming()) zoomMethod(amount);
    }

    /*
    MAIN METHOD
    Called by onMouseScroll
    Returns zoom level. Zoom level is exponential.
     */
    private static void zoomMethod(double amount) {
        // Adjust zoom level based on scroll amount
        if (amount > 0) {
            currentZoomIndex++; // Zoom in
        } else if (amount < 0) {
            currentZoomIndex--; // Zoom out
        }
        currentZoomIndex = MathHelper.clamp(currentZoomIndex, 0.3, 20);
        currentZoomLevel = Math.exp(currentZoomIndex * exponentialZoomValue);
    }

    // Method to reset the zoom index and level
    public static void resetZoomIndex() {
        currentZoomIndex = defaultZoomLevel; // Reset to the desired default index
        currentZoomLevel = Math.exp(currentZoomIndex * exponentialZoomValue); // Update the zoom level accordingly
    }
    // Used in FovMixin
    public static double getZoomLevel() {
        return currentZoomLevel;
    }
}