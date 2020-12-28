package divine.init;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class MKeyBindings {
	public static ArrayList<KeyBinding> keyBindings;
    /**
     * Register key bindings.
     */
    public static void registerKeyBindings()
    {
        // declare an array of key bindings
        keyBindings = new ArrayList<KeyBinding>();

        // instantiate the key bindings
        keyBindings.add(new KeyBinding("key.test", Keyboard.KEY_P, "category.divine"));

        // register all the key bindings
        for (int i = 0; i < keyBindings.size(); ++i)
        {
            ClientRegistry.registerKeyBinding(keyBindings.get(i));
        }
    }
}
