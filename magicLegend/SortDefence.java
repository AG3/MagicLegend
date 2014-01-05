package magicLegend;

import com.sk89q.worldguard.protection.flags.DefaultFlag;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class SortDefence implements Listener {
    private MagicLegend plugin;

    public SortDefence(MagicLegend plugin) {
	this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL) public void onPlayerInteract(PlayerInteractEvent event) throws Exception {
	Player p = event.getPlayer();
	if (NirvaExActived(p)) {
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
	    if ((event.getAction() == Action.LEFT_CLICK_AIR) || (event.getAction() == Action.LEFT_CLICK_BLOCK)) {
		/*if (modifi.getString("Class.Seven").equalsIgnoreCase(classe)
			&& p.getItemInHand().getType().toString().equalsIgnoreCase(modifi.getString("Weapons.Seven"))) {
		    if (sort == 1 && peutTirer == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 300, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 1));
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 1));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Def.One"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		    if (sort == 4 && peutTirer == true) {
			Vector dessus = p.getEyeLocation().getDirection().multiply(3);
			dessus.setY(2);
			p.setVelocity(dessus);
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 4));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Def.Two"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		}*/ if (modifi.getString("Class.One").equalsIgnoreCase(classe) && p.getItemInHand().getType().toString().contains(modifi.getString("Weapons.One"))) {
		    if (sort == 2 && peutTirer == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 300, 1));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 1));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Def.Three"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		    if (sort == 3 && peutTirer == true) {
			p.setFireTicks(200);
			p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 300, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 0));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Def.Four"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		} else if (modifi.getString("Class.Four").equalsIgnoreCase(classe)
			&& p.getItemInHand().getType().toString().equalsIgnoreCase(modifi.getString("Weapons.Four"))) {
		    if (sort == 3 && peutTirer == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 4));
			Entity araign = p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
			araign.setPassenger(p);
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Def.Five"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		    if (sort == 4 && peutTirer == true) {
			Vector v = p.getEyeLocation().getDirection().setY(0).multiply(9);
			p.setVelocity(v);
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Def.Six"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		} /*else if (modifi.getString("Class.Five").equalsIgnoreCase(classe) && p.getItemInHand().getType().toString().contains(modifi.getString("Weapons.Five"))) {
		    if (sort == 1 && peutTirer == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0));
			p.getWorld().spawnEntity(p.getLocation(), EntityType.BAT);
			p.getWorld().spawnEntity(p.getLocation(), EntityType.BAT);
			p.getWorld().spawnEntity(p.getLocation(), EntityType.BAT);
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Def.Seven"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		} else if (modifi.getString("Class.Eight").equalsIgnoreCase(classe)
			&& p.getItemInHand().getType().toString().contains(modifi.getString("Weapons.Eight"))) {
		    if (sort == 1 && peutTirer == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 3));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Def.Eight"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		    if (sort == 3 && peutTirer == true && Bukkit.getBukkitVersion().contains("1.6.2")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 500, 2));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Def.Nine"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    } else if (sort == 3 && peutTirer == true) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 500, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 500, 0));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Def.Nine"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		} else if (modifi.getString("Class.Nine").equalsIgnoreCase(classe) && p.getItemInHand().getType().toString().contains(modifi.getString("Weapons.Nine"))) {
		    if (sort == 4 && peutTirer == true) {
			FireworkEffect effectUn = FireworkEffect.builder().withColor(Color.BLACK).withFade(Color.BLACK).with(Type.BALL_LARGE).trail(true).build();
			FireworkEffect effectDeux = FireworkEffect.builder().withColor(Color.WHITE).withFade(Color.WHITE).with(Type.BALL_LARGE).trail(true).build();
			FireworkEffectPlayer fwp = new FireworkEffectPlayer();
			fwp.playFirework(p.getWorld(), p.getLocation(), effectUn, effectDeux);
			p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 500, 0));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Def.Ten"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		    else if (sort == 2 && peutTirer == true) {
			List<Player> entites = p.getWorld().getPlayers();
			Location ici = p.getLocation();
			Player ppres = null;
			double distancepres = 100;
			for (Player entity : entites) {
			    double distance = entity.getLocation().distance(ici);
			    if (distance < distancepres && entity != p) {
				distancepres = distance;
				ppres = entity;
			    }
			}
			if (ppres != null){
			    p.sendMessage(ChatColor.WHITE + ppres.getName() + " -----> " + (int)distancepres);
			}else{
			    p.sendMessage(ChatColor.WHITE + ".....");
			}
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Def.Eleven"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		}*/
	    }
	}
    }public boolean NirvaExActived(Player p) {
	if (plugin.getWorldGuard() == null){
	    File f = new File("plugins/MagicLegend/worlds.yml");
	    FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
	    String path = p.getWorld().toString() + ".MagicLegend actived";
	    boolean mondeset = modif.getBoolean(path);
	    if (mondeset==false){
		return false;
	    }else{
		return true;
	    }
	} else {
	    File f = new File("plugins/MagicLegend/worlds.yml");
	    FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
	    String path = p.getWorld().toString()+ ".MagicLegend actived";
	    boolean mondeset = modif.getBoolean(path);
	    if (mondeset==false && !plugin.getWorldGuard().getGlobalRegionManager().allows(DefaultFlag.PVP, p.getLocation())){
		return false;
	    }else{
		return true;
	    }
	}
    }
}
