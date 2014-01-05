package magicLegend;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Sortileges implements Listener {

    private MagicLegend plugin;

    public Sortileges(MagicLegend plugin) {
	this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL) public void onPlayerInteract(PlayerInteractEvent event) {
	Player p = event.getPlayer();
	File f = new File("plugins/MagicLegend/skills.yml");
	FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
	File fi = new File("plugins/MagicLegend/classes.yml");
	FileConfiguration modifi = YamlConfiguration.loadConfiguration(fi);
	if (p.getItemInHand().getType().toString().equalsIgnoreCase(modifi.getString("Weapons.Select"))) {
	    if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
		FileConfiguration config = plugin.getConfig();
		String path = "class." + p.getName();
		String classe = config.getString(path);
		String langages = "languages." + p.getName();
		String langage = config.getString(langages);
		String arg = "ability." + p.getName();
		int sort = config.getInt(arg);
		if (modifi.getString("Class.Two").equalsIgnoreCase(classe)) {
		    if (sort == 0 || sort >= 5) {
			config.set(arg, 1);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Atk") + ChatColor.WHITE + modif.getString(langage+".Atk.Seven"));
		    } else if (sort == 1) {
			config.set(arg, 2);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Atk") + ChatColor.WHITE + modif.getString(langage+".Atk.Eight"));
		    } else if (sort == 2) {
			config.set(arg, 3);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Atk") + ChatColor.WHITE + modif.getString(langage+".Atk.Nine"));
		    } else if (sort == 3) {
			config.set(arg, 4);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Atk") + ChatColor.WHITE + modif.getString(langage+".Atk.Ten"));
		    } else if (sort == 4) {
			config.set(arg, 5);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".Pass.Nothing"));
		    }
		}
		/*if (modifi.getString("Class.Seven").equalsIgnoreCase(classe)) {
		    if (sort == 0 || sort >= 5) {
			config.set(arg, 1);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Def") + ChatColor.WHITE + modif.getString(langage+".Def.One"));
		    } else if (sort == 1) {
			config.set(arg, 2);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Atk") + ChatColor.WHITE + modif.getString(langage+".Atk.Six"));
		    } else if (sort == 2) {
			config.set(arg, 3);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Zon") + ChatColor.WHITE + modif.getString(langage+".Zon.Two"));
		    } else if (sort == 3) {
			config.set(arg, 4);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Def") + ChatColor.WHITE + modif.getString(langage+".Def.Two"));
		    } else if (sort == 4) {
			config.set(arg, 5);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".Pass.Nothing"));
		    }
		}*/
		if (modifi.getString("Class.One").equalsIgnoreCase(classe)) {
		    if (sort == 0 || sort >= 5) {
			config.set(arg, 1);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Atk") + ChatColor.WHITE + modif.getString(langage+".Atk.Two"));
		    } else if (sort == 1) {
			config.set(arg, 2);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Def") + ChatColor.WHITE + modif.getString(langage+".Def.Three"));
		    } else if (sort == 2) {
			config.set(arg, 3);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Def") + ChatColor.WHITE + modif.getString(langage+".Def.Four"));
		    } else if (sort == 3) {
			config.set(arg, 4);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Atk") + ChatColor.WHITE + modif.getString(langage+".Atk.One"));
		    } else if (sort == 4) {
			config.set(arg, 5);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".Pass.Nothing"));
		    }
		}
		if (modifi.getString("Class.Four").equalsIgnoreCase(classe)) {
		    if (sort == 0 || sort >= 5) {
			config.set(arg, 1);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Zon") + ChatColor.WHITE + modif.getString(langage+".Zon.Three"));
		    } else if (sort == 1) {
			config.set(arg, 2);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Zon") + ChatColor.WHITE + modif.getString(langage+".Zon.Four"));
		    } else if (sort == 2) {
			config.set(arg, 3);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Def") + ChatColor.WHITE + modif.getString(langage+".Def.Five"));
		    } else if (sort == 3) {
			config.set(arg, 4);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Def") + ChatColor.WHITE + modif.getString(langage+".Def.Six"));
		    } else if (sort == 4) {
			config.set(arg, 5);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".Pass.Nothing"));
		    }
		}
		
		if (modifi.getString("Class.Three").equalsIgnoreCase(classe)) {
		    if (sort == 0 || sort >= 5) {
			config.set(arg, 1);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Zon") + ChatColor.WHITE + modif.getString(langage+".Zon.Ten"));
		    } else if (sort == 1) {
			config.set(arg, 2);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Atk") + ChatColor.WHITE + modif.getString(langage+".Atk.Three"));
		    } else if (sort == 2) {
			config.set(arg, 3);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Zon") + ChatColor.WHITE + modif.getString(langage+".Zon.Eleven"));
		    } else if (sort == 3) {
			config.set(arg, 4);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Zon") + ChatColor.WHITE + modif.getString(langage+".Zon.Five"));
		    } else if (sort == 4) {
			config.set(arg, 5);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".Pass.Nothing"));
		    }
		}
		/*if (modifi.getString("Class.Five").equalsIgnoreCase(classe)) {
		    if (sort == 0 || sort >= 5) {
			config.set(arg, 1);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Def") + ChatColor.WHITE + modif.getString(langage+".Def.Seven"));
		    } else if (sort == 1) {
			config.set(arg, 2);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Zon") + ChatColor.WHITE + modif.getString(langage+".Zon.One"));
		    } else if (sort == 2) {
			config.set(arg, 3);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Atk") + ChatColor.WHITE + modif.getString(langage+".Atk.Four"));
		    } else if (sort == 3) {
			config.set(arg, 4);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Atk") + ChatColor.WHITE + modif.getString(langage+".Atk.Five"));
		    } else if (sort == 4) {
			config.set(arg, 5);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".Pass.Nothing"));
		    }
		}
		if (modifi.getString("Class.Eight").equalsIgnoreCase(classe)) {
		    if (sort == 0 || sort >= 5) {
			config.set(arg, 1);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Def") + ChatColor.WHITE + modif.getString(langage+".Def.Eight"));
		    } else if (sort == 1) {
			config.set(arg, 2);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Atk") + ChatColor.WHITE + modif.getString(langage+".Atk.Eleven"));
		    } else if (sort == 2) {
			config.set(arg, 3);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Def") + ChatColor.WHITE + modif.getString(langage+".Def.Nine"));
		    } else if (sort == 3) {
			config.set(arg, 4);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Atk") + ChatColor.WHITE + modif.getString(langage+".Atk.Twelve"));
		    } else if (sort == 4) {
			config.set(arg, 5);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".Pass.Nothing"));
		    }
		}
		if (modifi.getString("Class.Nine").equalsIgnoreCase(classe)) {
		    if (sort == 0 || sort >= 5) {
			config.set(arg, 1);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Atk") + ChatColor.WHITE + modif.getString(langage+".Atk.Thirteen"));
		    } else if (sort == 1) {
			config.set(arg, 2);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Def") + ChatColor.WHITE + modif.getString(langage+".Def.Eleven"));
		    } else if (sort == 2) {
			config.set(arg, 3);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Atk") + ChatColor.WHITE + modif.getString(langage+".Atk.Fourteen"));
		    } else if (sort == 3) {
			config.set(arg, 4);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString("Types.Def") + ChatColor.WHITE + modif.getString(langage+".Def.Ten"));
		    } else if (sort == 4) {
			config.set(arg, 5);
			plugin.saveConfig();
			p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".Pass.Nothing"));
		    }
		}*/
	    }
	}
    }
}
