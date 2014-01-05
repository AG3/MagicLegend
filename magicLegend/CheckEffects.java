/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLegend;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;   
import org.bukkit.scheduler.BukkitRunnable;

public class CheckEffects extends BukkitRunnable
{
    public MagicLegend plugin;
    
    public CheckEffects(MagicLegend plu)
    {
        this.plugin=plu;
    }
    
    @Override
    public void run() 
    {
        FileConfiguration config = plugin.getConfig();
        Player pl[],p;
        pl=plugin.getServer().getOnlinePlayers();
        int playerCount=pl.length,i;
            
        for(i=0;i<playerCount;i++)
        {
            p=pl[i];
            String playerName = p.getName();
            if("牧师".equals(config.getString("class."+playerName)))
            {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,178000,1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION ,178000,0));
            }
        }
    }
}
