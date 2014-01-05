package magicLegend;

import com.sk89q.worldguard.protection.flags.DefaultFlag;
import java.io.File;
import org.anjocaido.groupmanager.GroupManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import ru.tehkode.permissions.bukkit.PermissionsEx;


public class PlayerCommand implements Listener {

    private MagicLegend plugin;

    public PlayerCommand(MagicLegend plugin) {
	this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR) public void PlayerConnected(PlayerLoginEvent e) {
	Player p = e.getPlayer();
	FileConfiguration config = plugin.getConfig();
	String tirer = "loading." + p.getName();
	config.set(tirer, true);
	String langages = "languages." + p.getName();
	String langage = config.getString(langages);
	String ldefaut = "languages." + "Default";
	String defaut = config.getString(ldefaut);
	if (langage == null) {
	    if (defaut == null) {
		config.set(ldefaut, "English");
		plugin.saveConfig();
		defaut = config.getString(ldefaut);
	    }
	    config.set(langages, defaut);
	}
	plugin.saveConfig();
    }

    @EventHandler(priority = EventPriority.MONITOR) public void onPluginEnable(PluginEnableEvent e) {

	if (plugin.pManager == null && Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
	    plugin.pManager = PermissionsEx.getPermissionManager();
	}
	Plugin p = plugin.getServer().getPluginManager().getPlugin("GroupManager");
	if (p != null) {
	    if (!plugin.getServer().getPluginManager().isPluginEnabled(p)) {
		plugin.getServer().getPluginManager().enablePlugin(p);
	    }
	}
	plugin.gmManager = (GroupManager) p;
    }

    @EventHandler(priority = EventPriority.LOWEST) public void demandeClasse(PlayerCommandPreprocessEvent e) {

	Player p = e.getPlayer();
	String message = e.getMessage();
	String[] params = message.split(" ");
	if (params[0].equalsIgnoreCase("/nclass")) {
	    e.setCancelled(true);
	    File f = new File("plugins/MagicLegend/classes.yml");
	    FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
	    FileConfiguration config = plugin.getConfig();
	    String langages = "languages." + p.getName();
	    String langage = config.getString(langages);
	    String path = "class." + p.getName();
	    String classe = config.getString(path);
	    if (params.length == 2) {
		p.sendMessage(ChatColor.RED + modif.getString(langage + ".warning"));
		if (classe == null || plugin.hasPermission(p, "nirva.change.class") || plugin.hasgmPermission(p, "nirva.change.class")) {
		    if (params[1].toLowerCase().equalsIgnoreCase(modif.getString("Class.One"))) {
			if (plugin.hasPermission(p, "nirva.class.one") || plugin.hasgmPermission(p, "nirva.class.one")) {
                            //p.setHealth(40);
			    config.set(path, modif.getString("Class.One"));
			    plugin.saveConfig();
			    p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".be") + modif.getString("Class.One"));
			} else {
			    p.sendMessage(ChatColor.RED + modif.getString(langage + ".nopermittobe") + modif.getString("Class.One"));
			}
		    } else if (params[1].toLowerCase().equalsIgnoreCase(modif.getString("Class.Two"))) {
			if (plugin.hasPermission(p, "nirva.class.two") || plugin.hasgmPermission(p, "nirva.class.two")) {
			    config.set(path, modif.getString("Class.Two"));
			    plugin.saveConfig();
			    p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".be") + modif.getString("Class.Two"));
			} else {
			    p.sendMessage(ChatColor.RED + modif.getString(langage + ".nopermittobe") + modif.getString("Class.Two"));
			}
		    } else if (params[1].toLowerCase().equalsIgnoreCase(modif.getString("Class.Three"))) {
			if (plugin.hasPermission(p, "nirva.class.three") || plugin.hasgmPermission(p, "nirva.class.three")) {
			    config.set(path, modif.getString("Class.Three"));
			    plugin.saveConfig();
			    p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".be") + modif.getString("Class.Three"));
			} else {
			    p.sendMessage(ChatColor.RED + modif.getString(langage + ".nopermittobe") + modif.getString("Class.Three"));
			}
		    } else if (params[1].toLowerCase().equalsIgnoreCase(modif.getString("Class.Four"))) {
			if (plugin.hasPermission(p, "nirva.class.four") || plugin.hasgmPermission(p, "nirva.class.four")) {
			    config.set(path, modif.getString("Class.Four"));
			    plugin.saveConfig();
			    p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".be") + modif.getString("Class.Four"));
			} else {
			    p.sendMessage(ChatColor.RED + modif.getString(langage + ".nopermittobe") + modif.getString("Class.Four"));
			}
		    }  else if (params[1].toLowerCase().equalsIgnoreCase(modif.getString("Class.Deleted"))) {
			if (plugin.hasPermission(p, "nirva.delete.class") || plugin.hasgmPermission(p, "nirva.delete.class")) {
			    config.set(path, "Deleted");
			    plugin.saveConfig();
			    p.sendMessage(ChatColor.AQUA + modif.getString(langage + ".be") + modif.getString("Class.Deleted"));
			} else {
			    p.sendMessage(ChatColor.RED + modif.getString(langage + ".nopermittobe") + modif.getString("Class.Deleted"));
			}
		    }
		} else {
		    p.sendMessage(ChatColor.RED + modif.getString(langage + ".alreadyclass"));
		}
	    } else {
		p.sendMessage(ChatColor.RED + "/nclass " + modif.getString("Class.List"));

	    }
	}
	if (params[0].equalsIgnoreCase("/n") || params[0].equalsIgnoreCase("/nhelp")) {
	    e.setCancelled(true);
	    FileConfiguration config = plugin.getConfig();
	    String langages = "languages." + p.getName();
	    String langage = config.getString(langages);
	    if ("French".equalsIgnoreCase(langage)) {
		p.sendMessage(ChatColor.AQUA + "---------" + ChatColor.WHITE + "Aide: NirvaEx" + ChatColor.AQUA + "--------------");
		p.sendMessage(ChatColor.AQUA + "                          ");
		p.sendMessage(ChatColor.AQUA + "/nclass: " + ChatColor.WHITE + "Choisir sa classe");
		p.sendMessage(ChatColor.AQUA + "/nlevel: " + ChatColor.WHITE + "Savoir sa classe et son niveau");
		p.sendMessage(ChatColor.AQUA + "/nlanguage: " + ChatColor.WHITE + "Choisir sa langue");
		p.sendMessage(ChatColor.AQUA + "/nworld: " + ChatColor.WHITE + "Activer ou non le plugin dans ce monde");
		p.sendMessage(ChatColor.AQUA + "/nlocation: " + ChatColor.WHITE + "Savoir si le plugin est actif");
		p.sendMessage(ChatColor.AQUA + "/nstuff: " + ChatColor.WHITE + "Avoir son équipement");
	    } else {
		p.sendMessage(ChatColor.AQUA + "---------" + ChatColor.WHITE + "Help: NirvaEx" + ChatColor.AQUA + "--------------");
		p.sendMessage(ChatColor.AQUA + "                          ");
		p.sendMessage(ChatColor.AQUA + "/nclass: " + ChatColor.WHITE + "To choice your class");
		p.sendMessage(ChatColor.AQUA + "/nlevel: " + ChatColor.WHITE + "To know your class and level");
		p.sendMessage(ChatColor.AQUA + "/nlanguage: " + ChatColor.WHITE + "To choice your language");
		p.sendMessage(ChatColor.AQUA + "/nworld: " + ChatColor.WHITE + "To set the plugin, enabled or disabled in this world");
		p.sendMessage(ChatColor.AQUA + "/nlocation: " + ChatColor.WHITE + "To know if the plugin is actived at your location");
		p.sendMessage(ChatColor.AQUA + "/nstuff: " + ChatColor.WHITE + "To get your stuff");
	    }
	}
	if (params[0].equalsIgnoreCase("/nstuff")) {
	    e.setCancelled(true);
	    File f = new File("plugins/MagicLegend/classes.yml");
	    FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
	    FileConfiguration config = plugin.getConfig();
	    String langages = "languages." + p.getName();
	    String langage = config.getString(langages);
	    if (plugin.hasPermission(p, "nirva.have.stuff") || plugin.hasgmPermission(p, "nirva.have.stuff")) 
            {
		String arg = "class." + p.getName();
		String classe = config.getString(arg);
		Player player = p;
		ItemStack arme;
		if (classe == null) 
                {
		    p.sendMessage(ChatColor.RED + modif.getString(langage + ".nohaveclass"));
		} 
                else if (classe.equalsIgnoreCase(modif.getString("Class.One"))) //战士
                {
		    arme = new ItemStack(Material.STONE_SWORD);
		    player.getInventory().addItem(arme);
		    arme = new ItemStack(Material.SLIME_BALL);
		    player.getInventory().addItem(arme);
                    player.setMaxHealth(30);
                    player.setHealth(30);
		} 
                else if (classe.equalsIgnoreCase(modif.getString("Class.Three"))) //牧师
                {
		    arme = new ItemStack(Material.RED_ROSE);
		    player.getInventory().addItem(arme);
		    arme = new ItemStack(Material.SLIME_BALL);
		    player.getInventory().addItem(arme);
		} 
                else if (classe.equalsIgnoreCase(modif.getString("Class.Two"))) //弓箭手
                {
		    arme = new ItemStack(Material.BOW);
		    player.getInventory().addItem(arme);
		    arme = new ItemStack(Material.ARROW,64);
		    player.getInventory().addItem(arme);
		    arme = new ItemStack(Material.SLIME_BALL);
		    player.getInventory().addItem(arme);
                    player.setMaxHealth(12);
                    player.setHealth(12);
		} 
                else if (classe.equalsIgnoreCase(modif.getString("Class.Four"))) //巫师
                {
		    arme = new ItemStack(Material.PAPER);
		    player.getInventory().addItem(arme);
		    arme = new ItemStack(Material.SLIME_BALL);
		    player.getInventory().addItem(arme);
                    player.setMaxHealth(16);
                    player.setHealth(16);
		}
	    } 
            else 
            {
		p.sendMessage(ChatColor.RED + modif.getString(langage + ".nopermittostuff"));
	    }
	}

	if (params[0].equalsIgnoreCase("/nlevel")) {
	    e.setCancelled(true);
	    FileConfiguration config = plugin.getConfig();
	    String path = "class." + p.getName();
	    String classe = config.getString(path);
	    String langages = "languages." + p.getName();
	    String langage = config.getString(langages);
	    if (classe != null && !classe.equalsIgnoreCase(" ")) {
		if ("French".equalsIgnoreCase(langage)) {
		    p.sendMessage(ChatColor.AQUA + "Votre classe est " + ChatColor.WHITE + classe);
		    float xp = p.getExp() * 100;
		    p.sendMessage(ChatColor.AQUA + "Vous êtes Level " + p.getLevel() + " avec " + xp + "%");
		} else {
		    p.sendMessage(ChatColor.AQUA + "你的职业是" + ChatColor.WHITE + classe);
		    float xp = p.getExp() * 100;
		    p.sendMessage(ChatColor.AQUA + "你现在等级是 " + p.getLevel() + " 级，进度 " + xp + "%");
		}
	    } else {
		if ("French".equalsIgnoreCase(langage)) {
		    p.sendMessage(ChatColor.AQUA + "Vous n'avez pas de classe.");
		    float xp = p.getExp() * 100;
		    p.sendMessage(ChatColor.AQUA + "Vous êtes Level " + p.getLevel() + " avec " + xp + "%");
		} else {
		    p.sendMessage(ChatColor.AQUA + "你还没有选择职业");
		    float xp = p.getExp() * 100;
		    p.sendMessage(ChatColor.AQUA + "你现在等级是 " + p.getLevel() + " 级，进度 " + xp + "%");
		}
	    }
	}
	if (params[0].equalsIgnoreCase("/nworld")) {
	    e.setCancelled(true);
	    if (plugin.hasPermission(p, "nirva.set.world") || plugin.hasgmPermission(p, "nirva.set.world")) {
		if (params.length == 2 && params[1].toLowerCase().startsWith("tr")) {
		    File f = new File("plugins/MagicLegend/worlds.yml");
		    try {
			if (!f.exists()) {
			    f.createNewFile();
			}
			FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
			ConfigurationSection World = modif.createSection(p.getWorld().toString());
			World.set("NirvaEx actived", true);
			modif.save(f);
		    } catch (Exception except) {
		    }
		    File fi = new File("plugins/MagicLegend/classes.yml");
		    FileConfiguration modifi = YamlConfiguration.loadConfiguration(fi);
		    FileConfiguration config = plugin.getConfig();
		    String langages = "languages." + p.getName();
		    String langage = config.getString(langages);
		    p.sendMessage(ChatColor.AQUA + modifi.getString(langage + ".worldtrue"));
		} else if (params.length == 2 && params[1].toLowerCase().startsWith("fa")) {
		    File f = new File("plugins/MagicLegend/worlds.yml");
		    try {
			if (!f.exists()) {
			    f.createNewFile();
			}
			FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
			ConfigurationSection World = modif.createSection(p.getWorld().toString());
			World.set("NirvaEx actived", false);
			modif.save(f);
		    } catch (Exception except) {
		    }
		    File fi = new File("plugins/MagicLegend/classes.yml");
		    FileConfiguration modifi = YamlConfiguration.loadConfiguration(fi);
		    FileConfiguration config = plugin.getConfig();
		    String langages = "languages." + p.getName();
		    String langage = config.getString(langages);
		    p.sendMessage(ChatColor.AQUA + modifi.getString(langage + ".worldfalse"));
		}
	    }
	}
	if (params[0].equalsIgnoreCase("/nlocation")) {
	    e.setCancelled(true);
	    if (NirvaExActived(p)) {
		File fi = new File("plugins/MagicLegend/classes.yml");
		FileConfiguration modifi = YamlConfiguration.loadConfiguration(fi);
		FileConfiguration config = plugin.getConfig();
		String langages = "languages." + p.getName();
		String langage = config.getString(langages);
		p.sendMessage(ChatColor.AQUA + modifi.getString(langage + ".locationtrue"));
	    } else {
		File fi = new File("plugins/MagicLegend/classes.yml");
		FileConfiguration modifi = YamlConfiguration.loadConfiguration(fi);
		FileConfiguration config = plugin.getConfig();
		String langages = "languages." + p.getName();
		String langage = config.getString(langages);
		p.sendMessage(ChatColor.AQUA + modifi.getString(langage + ".locationfalse"));
	    }
	}
	if (params[0].equalsIgnoreCase("/nlanguage")) {
	    e.setCancelled(true);
	    if (params.length == 2 && params[1].toLowerCase().startsWith("en")) {
		FileConfiguration config = plugin.getConfig();
		String path = "languages." + p.getName();
		config.set(path, "English");
		plugin.saveConfig();
		p.sendMessage(ChatColor.AQUA + "现在是中文状态");
	    } else if (params.length == 2 && params[1].toLowerCase().startsWith("fr")) {
		FileConfiguration config = plugin.getConfig();
		String path = "languages." + p.getName();
		config.set(path, "French");
		plugin.saveConfig();
		p.sendMessage(ChatColor.AQUA + "Nirva est en Français maintenant");
	    } else {
		p.sendMessage(ChatColor.RED + "/nlanguage <English|French>");
	    }
	}
    }

    public boolean NirvaExActived(Player p) {
	if (plugin.getWorldGuard() == null) {
	    File f = new File("plugins/MagicLegend/worlds.yml");
	    FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
	    String path = p.getWorld().toString() + ".NirvaEx actived";
	    boolean mondeset = modif.getBoolean(path);
	    if (mondeset == false) {
		return false;
	    } else {
		return true;
	    }
	} else {
	    File f = new File("plugins/MagicLegend/worlds.yml");
	    FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
	    String path = p.getWorld().toString() + ".NirvaEx actived";
	    boolean mondeset = modif.getBoolean(path);
	    if (mondeset == false && !plugin.getWorldGuard().getGlobalRegionManager().allows(DefaultFlag.PVP, p.getLocation())) {
		return false;
	    } else {
		return true;
	    }
	}
    }
}