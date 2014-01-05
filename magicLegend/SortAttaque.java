package magicLegend;

import com.sk89q.worldguard.protection.flags.DefaultFlag;
import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;

public class SortAttaque implements Listener {
    private MagicLegend plugin;
    public SortAttaque(MagicLegend plugin) {
	this.plugin = plugin;
    }

    @SuppressWarnings("deprecation") @EventHandler(priority = EventPriority.NORMAL) public void onPlayerDamageAtt(EntityDamageByEntityEvent event) {
	Entity e = event.getDamager();
	Entity d = event.getEntity();
	if (e instanceof Player && d instanceof LivingEntity) {
	    Player p = (Player) e;
	    if (NirvaExActived(p)) {
		LivingEntity m = (LivingEntity) d;
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
		if (modifi.getString("Class.One").equalsIgnoreCase(classe)
			&& p.getItemInHand().getType().toString().contains(modifi.getString("Weapons.One"))) {
		    if (sort == 4 && peutTirer == true && SameTeam(p, m) == false) {
			Vector v = new Vector(0, 1, 0);
			m.setVelocity(v);
			m.damage(1);
			m.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Atk.One"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		    if (sort == 1 && peutTirer == true && SameTeam(p, m) == false) {
			m.damage(5);
			m.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0));
			m.getWorld().createExplosion(m.getLocation(), 0);
			Vector v = new Vector(0, 1, 0);
			m.setVelocity(v);
			v = p.getEyeLocation().getDirection().multiply(7);
			p.setVelocity(v);
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Atk.Two"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		} else if (modifi.getString("Class.Three").equalsIgnoreCase(classe) && p.getItemInHand().getType().toString().equalsIgnoreCase(modifi.getString("Weapons.Three"))) {
		    if (sort == 2 && peutTirer == true && SameTeam(p, m) == false) {
			m.setVelocity(p.getLocation().getDirection().multiply(5));
			m.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Atk.Three"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		} /*else if (modifi.getString("Class.Five").equalsIgnoreCase(classe)
			&& p.getItemInHand().getType().toString().contains(modifi.getString("Weapons.Five"))) {
		    if (sort == 3 && peutTirer == true && SameTeam(p, m) == false) {
			m.damage(5);
			m.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0));
			m.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 250, 0));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Atk.Four"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		    if (sort == 4 && peutTirer == true && SameTeam(p, m) == false) {
			m.damage(2);
			m.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 250, 0));
			m.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 2, 0));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Atk.Five"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		} else if (modifi.getString("Class.Seven").equalsIgnoreCase(classe) && p.getItemInHand().getType().toString().equalsIgnoreCase(modifi.getString("Weapons.Seven"))) {
		    if (sort == 2 && peutTirer == true && SameTeam(p, m) == false) {
			p.getWorld().setStorm(true);
			Location targetBlock = m.getLocation();
			p.getWorld().strikeLightningEffect(targetBlock);
			m.setFireTicks(100);
			m.damage(4);
			m.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0));
			p.getWorld().setStorm(false);
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Atk.Six"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		} else if (modifi.getString("Class.Eight").equalsIgnoreCase(classe)
			&& p.getItemInHand().getType().toString().contains(modifi.getString("Weapons.Eight"))) {
		    if (sort == 2 && peutTirer == true && SameTeam(p, m) == false) {
			m.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 50, 0));
			m.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 50, 2));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Atk.Eleven"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		    if (sort == 4 && peutTirer == true && SameTeam(p, m) == false) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Atk.Twelve"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		} else if (modifi.getString("Class.Nine").equalsIgnoreCase(classe)
			&& p.getItemInHand().getType().toString().contains(modifi.getString("Weapons.Nine"))) {
		    if (sort == 1 && peutTirer == true && SameTeam(p, m) == false) {
			Vector v = new Vector(0, 0.5, 0);
			m.setVelocity(v);
			m.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 50, 0));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Atk.Thirteen"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		    if (sort == 3 && peutTirer == true && p.hasPotionEffect(PotionEffectType.INVISIBILITY) && SameTeam(p, m) == false) {
			m.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 1));
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Atk.Fourteen"));
			config.set(tirer, false);
			plugin.saveConfig();
			Recharge rt = new Recharge(p, plugin);
			rt.start();
		    }
		}*/
	    }
	}
    }

    @SuppressWarnings("deprecation") @EventHandler(priority = EventPriority.NORMAL) public void onPlayer(EntityDamageByEntityEvent event) {
	Entity e = event.getEntity();
	Entity d = event.getDamager();
	if (d instanceof Arrow) {
	    Arrow a = (Arrow) d;
	    Entity g = a.getShooter();
	    if (e instanceof LivingEntity && g instanceof Player) {
		LivingEntity m = (LivingEntity) e;
		Player p = (Player) g;
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
		    if (modifi.getString("Class.Two").equalsIgnoreCase(classe)) {
			if (sort == 1 && peutTirer == true && SameTeam(p, m) == false) {
			    m.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 2));
			    m.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 2));
			    p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Atk.Seven"));
			    config.set(tirer, false);
			    plugin.saveConfig();
			    Recharge rt = new Recharge(p, plugin);
			    rt.start();
			}
			if (sort == 2 && peutTirer == true && SameTeam(p, m) == false) {
			    p.getWorld().strikeLightningEffect(m.getLocation());
			    m.setFireTicks(100);
			    m.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 1));
			    m.damage(4);
			    p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Atk.Eight"));
			    config.set(tirer, false);
			    plugin.saveConfig();
			    Recharge rt = new Recharge(p,plugin);
			    rt.start();
			}
			if (sort == 3 && peutTirer == true && SameTeam(p, m) == false) {
			    m.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 250, 0));
			    m.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 250, 1));
			    m.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 250, 1));
			    p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Atk.Nine"));
			    config.set(tirer, false);
			    plugin.saveConfig();
			    Recharge rt = new Recharge(p, plugin);
			    rt.start();
			}
			if (sort == 4 && peutTirer == true && SameTeam(p, m) == false) {
			    m.damage(10);
			    m.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 1));
			    m.getWorld().createExplosion(m.getLocation(), 0);
			    p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".use") + ChatColor.WHITE + modif.getString(langage + ".Atk.Ten"));
			    config.set(tirer, false);
			    plugin.saveConfig();
			    Recharge rt = new Recharge(p, plugin);
			    rt.start();
			}
		    }
		}
	    }
	}
    }
    public boolean SameTeam(Player p, Entity m) {
	if (m instanceof Player) {
	    Player mp = (Player) m;
	    Team pt = p.getScoreboard().getPlayerTeam(p);
	    Team mpt = mp.getScoreboard().getPlayerTeam(mp);
	    if (pt == mpt && pt != null) {
		return true;
	    }
	}
	return false;
    }
    public boolean NirvaExActived(Player p) {
	if (plugin.getWorldGuard() == null){
	    File f = new File("plugins/MagicLegend/worlds.yml");
	    FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
	    String path = p.getWorld().toString()+ ".MagicLegend actived";;
	    boolean mondeset = modif.getBoolean(path);
	    if (mondeset==false){
		return false;
	    }else{
		return true;
	    }
	} else {
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
