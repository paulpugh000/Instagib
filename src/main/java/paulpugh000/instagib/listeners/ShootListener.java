package paulpugh000.instagib.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class ShootListener implements Listener {
    @EventHandler
    public void onFireRifle(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Material heldItem = p.getInventory().getItemInMainHand().getType();
        if (!(heldItem == Material.ECHO_SHARD)) {
            return;
        };

        Location loc = p.getLocation();
        
        int maxDistance = 50;
        float particleGap = .5f; // Gap between particles 

        Bukkit.broadcastMessage("Fire!");
        e.setCancelled(true);
        
        
        Location particleLocation = loc.add( 0, 1.4, 0); // Starting offset to shoot from the eyes

        RayTraceResult blockRayTrace = p.rayTraceBlocks(maxDistance);
        RayTraceResult entityRayTrace = p.rayTraceEntities(maxDistance);
        
        // if raytrace is null, hit position is max value to player location
        double blockDist = blockRayTrace != null ? loc.distance(blockRayTrace.getHitPosition().toLocation(p.getWorld())): maxDistance;
        double entityDist = entityRayTrace != null ? loc.distance(entityRayTrace.getHitPosition().toLocation(p.getWorld())): maxDistance;
        
        double closestRayDist = Math.min(entityDist, blockDist);
        
        double totalParticles = Math.ceil(closestRayDist / particleGap);

        // TODO: get player hit by the ray and respawnPlayer()
        
        for (int i = 0; i <= totalParticles; i++) {
            p.getWorld().spawnParticle(Particle.WAX_OFF, particleLocation, 1, .01d, .01d, .01d);
            Vector direction = loc.getDirection().multiply(particleGap);
            particleLocation.add(direction);
        }
    }
}
