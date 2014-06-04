package com.roborave.edm.tweaker;

import com.roborave.edm.tweaker.recipe.RecipeManager;
import com.roborave.edm.tweaker.register.RegistryManager;

public class Tweaker {
	
	 public static void load() throws Exception 
	 {
		RecipeManager.load();
		RegistryManager.init();
	 }

}
