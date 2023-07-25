package com.jamessu.lumberjack;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;




public class Event implements Listener {
    @EventHandler
    public void blockBreak(BlockBreakEvent event){

        Player player=event.getPlayer(); //get player info

        World world=player.getWorld();
        Block block=event.getBlock();   //get block info
        Material type=block.getType();
        String block_str=block.getType().toString();    //turn type into string
        if (block_str.contains("LOG")) {
            if(block.breakNaturally(new ItemStack(Material.DIAMOND_AXE)) || block.breakNaturally(new ItemStack(Material.IRON_AXE))
            || block.breakNaturally(new ItemStack(Material.GOLDEN_AXE)) || block.breakNaturally(new ItemStack(Material.STONE_AXE))
            || block.breakNaturally(new ItemStack(Material.WOODEN_AXE))) {
                block.setType(type);
                dfs("LOG", block.getLocation(), player);
            }
        }
    }


    void dfs(String str,Location loc,Player player){        //use dfs to break the tree
        Block block=loc.getBlock();
        player.sendMessage(str);
        player.sendMessage(block.getType().toString());
        if(block.getType().toString().contains(str)){

            break_wooden_block(block, player.getWorld(), player);
            int x,y,z;
            x=block.getX();
            y=block.getY();
            z=block.getZ();

            for(int i=-1;i<=1;i++){
                for(int j=-1;j<=1;j++){
                    for(int k=-1;k<=1;k++){
                        if(i==0&&j==0&&k==0){
                            continue;
                        }
                        dfs(str,new Location(loc.getWorld(),x+i,y+j,z+k),player);
                    }
                }
            }
        }
    }
    void break_wooden_block(Block block,World world,Player player){
        world.dropItemNaturally(block.getLocation(),new ItemStack(block.getType()));
        block.setType(Material.AIR);
    }
}
