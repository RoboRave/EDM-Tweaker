package com.roborave.edm.tweaker.recipe;

import java.util.HashMap;

import org.apache.logging.log4j.Level;

import mods.roborave.edm.helper.LogHandler;
import net.minecraft.block.Block;

import com.roborave.edm.tweaker.Main;
import com.roborave.edm.tweaker.api.RecipeClass;
import com.roborave.edm.tweaker.helper.LogHelper;
import com.roborave.edm.tweaker.register.RegistryManager;


public class RecipeManager {

	
	public static void load()
	{
		RegistryManager.registerClass(RecipesBlocks.class);
		
		if(Main.hardMode)
		{
			RegistryManager.registerClass(RecipeSword.class);
		}
			else
		{
			RegistryManager.registerClass(RecipeEnchantments.class);
		}
		
	}
	

}
