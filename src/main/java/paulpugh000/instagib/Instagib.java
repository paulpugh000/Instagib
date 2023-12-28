package paulpugh000.instagib;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import paulpugh000.instagib.listeners.ShootListener;

public final class Instagib extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("LOADED INSTAGIB");
        Bukkit.getPluginManager().registerEvents(new ShootListener(), this);
        // TODO: Create spawn locations
        // TODO: When shot, go to spawn location

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
