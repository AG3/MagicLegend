/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLegend;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import java.io.File;
import java.util.logging.Logger;
import org.anjocaido.groupmanager.GroupManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import ru.tehkode.permissions.PermissionManager;

public class MagicLegend extends JavaPlugin {
    private MagicLegend plugin;
    private SortAttaque sortatt;
    private SortDefence sortdef;
    private SortZone sortzon;
    private Sortileges sortilege;
    private ConsoleCommand concommand;
    private PlayerCommand placommand;
    public PermissionManager pManager;
    public GroupManager gmManager;
    Logger log;

    public MagicLegend() 
    {
	plugin = this;
    }

    public void onEnable() 
    {
        BukkitScheduler checker = Bukkit.getServer().getScheduler();
        checker.runTaskTimer(this,new CheckEffects(this),20,40);
	System.out.println("[MagicLegend] Plugin connected");
	PluginManager pm = getServer().getPluginManager();

	concommand = new ConsoleCommand(this);
	pm.registerEvents(concommand, this);
	placommand = new PlayerCommand(this);
	pm.registerEvents(placommand, this);
	sortatt = new SortAttaque(this);
	pm.registerEvents(sortatt, this);
	sortdef = new SortDefence(this);
	pm.registerEvents(sortdef, this);
	sortzon = new SortZone(this);
	pm.registerEvents(sortzon, this);
	sortilege = new Sortileges(this);
	pm.registerEvents(sortilege, this);
	plugin.saveConfig();
        
	try {
	    File f = new File("plugins/MagicLegend/classes.yml");
	    if (!f.exists()) {
		f.createNewFile();
		FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
		ConfigurationSection Weapons = modif.createSection("Weapons");
		Weapons.set("One", "SWORD");
		Weapons.set("Two", "BOW");
		Weapons.set("Three", "RED_ROSE");
		Weapons.set("Four", "PAPER");
		Weapons.set("Five", "HOE");
		Weapons.set("Six", "FLINT");
		Weapons.set("Seven", "YELLOW_FLOWER");
		Weapons.set("Eight", "AXE");
		Weapons.set("Nine", "SWORD");
		Weapons.set("Select", "SLIME_BALL");
		ConfigurationSection Classes = modif.createSection("Class");
		Classes.set("One", "战士");
		Classes.set("Two", "弓箭手");
		Classes.set("Three", "牧师");
		Classes.set("Four", "巫师");
		Classes.set("Deleted", "清除职业");
		Classes.set(
			"List",
			modif.getString("Class.One") + " | " + modif.getString("Class.Two") + " | " + modif.getString("Class.Three") + " | "
				+ modif.getString("Class.Four") + " | "  + modif.getString("Class.Deleted"));
		ConfigurationSection Frances = modif.createSection("French");
		Frances.set("change", " est devenu ");
		Frances.set("be", "Maintenant vous êtes un ");
		Frances.set("nopermittobe", "Vous n'avez pas la permission de devenir un ");
		Frances.set("nopermittostuff", "Vous n'avez pas la permission d'avoir votre équipement.");
		Frances.set("nohaveclass", "Vous n'avez pas de classe.");
		Frances.set("alreadyclass", "Vous avez déjà une classe !");
		Frances.set("recharge", "Chargé !");
		Frances.set("warning", "Vous ne pourrez plus changer de classe après.");
		Frances.set("worldtrue", "Dans le monde dans lequel vous êtes, le plugin NirvaEx est actif.");
		Frances.set("worldfalse", "Dans le monde dans lequel vous êtes, le plugin NirvaEx est inactif.");
		Frances.set("locationtrue", "Là où vous êtes situé, le plugin NirvaEx est actif.");
		Frances.set("locationfalse", "Là où vous êtes situé, le plugin NirvaEx est inactif.");
                
		ConfigurationSection Anglais = modif.createSection("English");
		Anglais.set("change", " 成为了 ");
		Anglais.set("be", "现在你是一个 ");
		Anglais.set("nopermittobe", "你没有足够的权限来成为 ");
		Anglais.set("nopermittostuff", "你没有权限来取得魔法石");
		Anglais.set("nohaveclass", "你还没有职业");
		Anglais.set("alreadyclass", "你已经拥有职业");
		Anglais.set("recharge", "技能就绪！");
		Anglais.set("warning", "你以后就不能更改职业了");
		Anglais.set("worldtrue", "在这个世界里, 本插件已经启用");
		Anglais.set("worldfalse", "在这个世界里, 本插件已经禁用");
		Anglais.set("locationtrue", "在你所处的位置上, 本插件已经启用");
		Anglais.set("locationfalse", "在你所处的位置上, 本插件已经禁用");
		modif.save(f);
	    }
	} catch (Exception e) {
	}
	try {
	    File f = new File("plugins/MagicLegend/skills.yml");
	    if (!f.exists()) {
		f.createNewFile();
		FileConfiguration modif = YamlConfiguration.loadConfiguration(f);
		ConfigurationSection Modes = modif.createSection("Types");
		Modes.set("Atk", "[攻击] ");
		Modes.set("Def", "[状态] ");
		Modes.set("Zon", "[范围] ");
		Modes.set("Cooldown", 20000);
		ConfigurationSection Frances = modif.createSection("French");
		Frances.set("use", "Vous utilisez ");
		ConfigurationSection Attaques = Frances.createSection("Atk");
		Attaques.set("One", "Coup Puissant");
		Attaques.set("Two", "Tranché Explosif");
		Attaques.set("Three", "Renvoi");
		Attaques.set("Four", "Faucheuse Obscure");
		Attaques.set("Five", "Baiser Vampirique");
		Attaques.set("Six", "Jugement Dernier");
		Attaques.set("Seven", "Flèche Confuse");
		Attaques.set("Eight", "Flèche de Foudre");
		Attaques.set("Nine", "Flèche Dégénérative");
		Attaques.set("Ten", "Flèche Explosive");
		Attaques.set("Eleven", "Coup Etourdissant");
		Attaques.set("Twelve", "Vol de Vie");
		Attaques.set("Thirteen", "Surprise");
		Attaques.set("Fourteen", "Traitrise");
		ConfigurationSection Defences = Frances.createSection("Def");
		Defences.set("One", "Communion Elémentaire");
		Defences.set("Two", "Saut Titanesque");
		Defences.set("Three", "Vol du Jaguar");
		Defences.set("Four", "Berseker");
		Defences.set("Five", "Lancement");
		Defences.set("Six", "Saut Eclair");
		Defences.set("Seven", "ChauveSouris");
		Defences.set("Eight", "Rage");
		Defences.set("Nine", "Dopage");
		Defences.set("Ten", "Poussière");
		Defences.set("Eleven", "Traque");
		ConfigurationSection Passives = Frances.createSection("Pass");
		Passives.set("Nothing", "Aucune capacité");
		ConfigurationSection Zones = Frances.createSection("Zon");
		Zones.set("One", "Etres Nocturnes");
		Zones.set("Two", "Prison Temporelle");
		Zones.set("Three", "Cauchemars");
		Zones.set("Four", "Embrasement");
		Zones.set("Five", "Dissipation Supreme");
		Zones.set("Six", "Aspiration");
		Zones.set("Seven", "Transfert d'Energie");
		Zones.set("Eight", "Maladie");
		Zones.set("Nine", "Implosion");
		Zones.set("Ten", "Cercle de Soin");
		Zones.set("Eleven", "Prière");
                
		ConfigurationSection Anglais = modif.createSection("English");
		Anglais.set("use", "你选择了 ");
		Attaques = Anglais.createSection("Atk");
		Attaques.set("One", "昏昏倒地");
		Attaques.set("Two", "爆破打击");
		Attaques.set("Three", "抗拒光环");
		Attaques.set("Four", "黑暗收割");
		Attaques.set("Five", "吸血鬼之吻");
		Attaques.set("Six", "最终审判");
		Attaques.set("Seven", "饥饿难当");
		Attaques.set("Eight", "雷神之怒");
		Attaques.set("Nine", "萎靡不振");
		Attaques.set("Ten", "爆破箭");
		Attaques.set("Eleven", "全力一击");
		Attaques.set("Twelve", "生命吸收");
		Attaques.set("Thirteen", "惊喜时刻");
		Attaques.set("Fourteen", "背袭");
		Defences = Anglais.createSection("Def");
		Defences.set("One", "盛宴");
		Defences.set("Two", "泰坦尼克之跃");
		Defences.set("Three", "飞行雄虎");
		Defences.set("Four", "血之狂暴");
		Defences.set("Five", "浮空术");
		Defences.set("Six", "闪现");
		Defences.set("Seven", "蝠化");
		Defences.set("Eight", "怒火");
		Defences.set("Nine", "不死之身");
		Defences.set("Ten", "雾化");
		Defences.set("Eleven", "追捕");
		Passives = Anglais.createSection("Pass");
		Passives.set("Nothing", "不使用技能");
		Zones = Anglais.createSection("Zon");
		Zones.set("One", "噩梦降临");
		Zones.set("Two", "监狱束缚");
		Zones.set("Three", "梦靥");
		Zones.set("Four", "烈火熊熊");
		Zones.set("Five", "驱散术");
		Zones.set("Six", "能量汲取");
		Zones.set("Seven", "能量交换");
		Zones.set("Eight", "瘟疫");
		Zones.set("Nine", "自我牺牲");
		Zones.set("Ten", "再生轮盘");
		Zones.set("Eleven", "祈祷");
		modif.save(f);
	    }
	} catch (Exception e) {
	}
    }

    public void onDisable() {
	System.out.println("[MagicLegend] Plugin disconnected");
    }

    public boolean hasPermission(Player p, String perm) {
	if (!p.isOp() && !(pManager != null && pManager.has(p, perm))) {
	    return false;
	} else {
	    return true;
	}
    }

    public boolean hasgmPermission(Player p, String perm) {
	if (!p.isOp() && !(gmManager != null && gmManager.getWorldsHolder().getWorldPermissions(p).getAllPlayersPermissions(p.getName()).contains(perm))) {
	    return false;
	} else {
	    return true;
	}
    }

    public WorldGuardPlugin getWorldGuard() {
	Plugin plug = plugin.getServer().getPluginManager().getPlugin("WorldGuard");
	if ((plug == null) || (!(plug instanceof WorldGuardPlugin))) {
	    return null;
	}
	return (WorldGuardPlugin) plug;
    }
}
