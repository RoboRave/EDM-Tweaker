package com.roborave.edm.tweaker.recipe;

import com.roborave.edm.tweaker.Main;
import com.roborave.edm.tweaker.register.RegistryManager;


public class RecipeManager 
{
	public static void load()
	{
		RegistryManager.registerAPIClass(RecipesBlocks.class);
		
		if(Main.hardMode)
		{
			RegistryManager.registerAPIClass(RecipeSword.class);
		}else{
			RegistryManager.registerAPIClass(RecipeEnchantments.class);
		}
	}
}
