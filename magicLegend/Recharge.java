package magicLegend;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

class Recharge extends Thread {

    private MagicLegend plugin;
    private Player p;

    public Recharge(Player p, MagicLegend plugin) {
	this.plugin = plugin;
	this.p = p;
    }

    public void run() {
	try {
	    FileConfiguration config = plugin.getConfig();
	    File f = new File("plugins/MagicLegend/classes.yml");
	    FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
	    File fi = new File("plugins/MagicLegend/skills.yml");
	    FileConfiguration modifi = YamlConfiguration.loadConfiguration(fi);
	    String langages = "languages." + p.getName();
	    String langage = config.getString(langages);
	    String tirer = "loading." + p.getName();
	    long attente = modifi.getLong("Types.Cooldown");
	    if (attente - (p.getLevel() * 1000) >= 5000) {
		attente = attente - (p.getLevel() * 1000);
	    }
            else
            {
                attente=5000;
            }
	    //p.sendMessage((attente / 1000) + "秒后就绪！");
	    while (attente != 0) {
		if (attente % 1000 == 0) {
		    p.sendMessage((attente / 1000) + "秒后就绪！");
		}
		sleep(1000);
		attente = attente - 1000;
	    }
	    config.set(tirer, true);
	    plugin.saveConfig();
	    p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 1000);
	    p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".recharge"));
	} catch (InterruptedException e) {

	} finally {
	    this.interrupt();
	}
    }
}
