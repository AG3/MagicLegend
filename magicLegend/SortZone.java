package magicLegend;

import com.sk89q.worldguard.protection.flags.DefaultFlag;
import java.io.File;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;

public class SortZone implements Listener 
{
    private MagicLegend plugin;
    public SortZone(MagicLegend plugin) 
    {
	this.plugin = plugin;
    }

    @SuppressWarnings("deprecation") @EventHandler(priority = EventPriority.NORMAL) public void onPlayerInteract(PlayerInteractEvent event) throws Exception 
    {
	Player p = event.getPlayer();
	if (NirvaExActived(p)) 
        {
	    File fi = new File("plugins/MagicLegend/classes.yml");
	    FileConfiguration modifi = YamlConfiguration.loadConfiguration(fi);
	    File f = new File("plugins/MagicLegend/skills.yml");
	    FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
	    FileConfiguration config = plugin.getConfig();
	    String path = "class." + p.getName();
	    String classe = config.getString(path);
	    String langages = "languages." + p.getName();
	    String langage = config.getString(langages);
	    String tirer = "loading." + p.getName();
	    boolean peutTirer = config.getBoolean(tirer);
	    String arg = "ability." + p.getName();
	    int sort = config.getInt(arg);
	    if ((event.getAction() == Action.LEFT_CLICK_AIR) || (event.getAction() == Action.LEFT_CLICK_BLOCK)) 
            {
		if (modifi.getString("Class.Four").equalsIgnoreCase(classe) && p.getItemInHand().getType().toString().equalsIgnoreCase(modifi.getString("Weapons.Four"))) 
                {
		    if (sort == 1 && peutTirer == true) 
                    {
			List<Player> entites = p.getWorld().getPlayers();
			Location ici = p.getTargetBlock(null, 50).getLocation();
			for (Player entity : entites) 
                        {
			    double distance = entity.getLocation().distance(ici);
			    if (distance < 10 && entity != p && SameTeam(p, entity) == false) 
                            {
				entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
				entity.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0));
			    }
			}
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Zon.Three"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		    if (sort == 2 && peutTirer == true) 
                    {//传说中失效的烈火熊熊
			List<Entity> entites = p.getWorld().getEntities();
			Location ici = p.getTargetBlock(null, 50).getLocation();
                        
			for (Entity entity : entites) 
                        {
			    double distance = entity.getLocation().distance(ici);
			    if (distance < 10 && entity != p && SameTeam(p, entity) == false) 
                            {
                                entity.setFireTicks(200);
				Vector v = new Vector(0, 1, 0);
				entity.setVelocity(v);
			    }
			}
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Zon.Four"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		}
                else if (modifi.getString("Class.Three").equalsIgnoreCase(classe) && p.getItemInHand().getType().toString().equalsIgnoreCase(modifi.getString("Weapons.Three"))) 
                {
		    if (sort == 1 && peutTirer == true) 
                    {
			List<Player> entites = p.getWorld().getPlayers();
			Location ici = p.getTargetBlock(null, 50).getLocation();
			for (Player entity : entites) {
			    double distance = entity.getLocation().distance(ici);
			    if (distance < 10 && SameTeam(p, entity) == true) 
                            {
				entity.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 7, 0));
				entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 2));
			    }
			}
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Zon.Ten"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		    if (sort == 4 && peutTirer == true) 
                    {
			List<Player> entites = p.getWorld().getPlayers();
			Location ici = p.getLocation();
			for (Player entity : entites) 
                        {
			    double distance = entity.getLocation().distance(ici);
			    if (distance < 10 && entity != p && SameTeam(p, entity) == false) 
                            {
				entity.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
				entity.removePotionEffect(PotionEffectType.FAST_DIGGING);
				entity.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
				entity.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
				entity.removePotionEffect(PotionEffectType.JUMP);
				entity.removePotionEffect(PotionEffectType.REGENERATION);
				entity.removePotionEffect(PotionEffectType.SPEED);
				entity.removePotionEffect(PotionEffectType.WATER_BREATHING);
				entity.removePotionEffect(PotionEffectType.INVISIBILITY);
				entity.removePotionEffect(PotionEffectType.NIGHT_VISION);
			    } 
                            else if (distance < 10 && SameTeam(p, entity) == true) 
                            {
				entity.removePotionEffect(PotionEffectType.BLINDNESS);
				entity.removePotionEffect(PotionEffectType.CONFUSION);
				entity.removePotionEffect(PotionEffectType.HUNGER);
				entity.removePotionEffect(PotionEffectType.POISON);
				entity.removePotionEffect(PotionEffectType.SLOW);
				entity.removePotionEffect(PotionEffectType.SLOW_DIGGING);
				entity.removePotionEffect(PotionEffectType.WEAKNESS);
			    }
			}
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Zon.Five"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		    if (sort == 3 && peutTirer == true) 
                    {
			List<Player> entites = p.getWorld().getPlayers();
			Location ici = p.getLocation();
			for (Player entity : entites)
                        {
			    double distance = entity.getLocation().distance(ici);
			    if (distance < 10 && SameTeam(p, entity) == true) 
                            {
				entity.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 0));
				entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0));
				entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 0));
			    }
			}
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Zon.Eleven"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		}
	    }
	}
    }
    
    public boolean SameTeam(Player p, Entity m) 
    {
	if (m instanceof Player) 
        {
	    Player mp = (Player) m;
	    Team pt = p.getScoreboard().getPlayerTeam(p);
	    Team mpt = mp.getScoreboard().getPlayerTeam(mp);
	    if (pt == mpt && pt != null) 
            {
		return true;
	    }
	}
	return false;
    }
    public boolean NirvaExActived(Player p) 
    {
	if (plugin.getWorldGuard() == null)
        {
	    File f = new File("plugins/MagicLegend/worlds.yml");
	    FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
	    String path = p.getWorld().toString()+ ".MagicLegend actived";;
	    boolean mondeset = modif.getBoolean(path);
	    if (mondeset==false)
            {
		return false;
	    }
            else
            {
		return true;
	    }
	} 
        else 
        {
	    File f = new File("plugins/MagicLegend/worlds.yml");
	    FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
	    String path = p.getWorld().toString()+ ".MagicLegend actived";;
	    boolean mondeset = modif.getBoolean(path);
	    if (mondeset==false && !plugin.getWorldGuard().getGlobalRegionManager().allows(DefaultFlag.PVP, p.getLocation())){
		return false;
	    }else{
		return true;
	    }
	}
    }
}
