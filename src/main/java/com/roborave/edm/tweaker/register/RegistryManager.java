package com.roborave.edm.tweaker.register;
import java.util.HashMap;

import com.roborave.edm.tweaker.api.RecipeClass;
import com.roborave.edm.tweaker.helper.LogHelper;

public class RegistryManager
{
		
		public static HashMap<Class<?extends RecipeClass>,String> RecipeClasses = new HashMap<Class<? extends RecipeClass>, String>();
		
		public static void init()
		{
			LogHelper.info("Registered %s classes for recipes", RecipeClasses.size());
		}
		/**
		 * 
		 * @param clazz the class that needs to initizied
		 */
		public static void registerClass(Class<?extends RecipeClass> clazz)
		{
			RecipeClasses.put(clazz,clazz.toString().toLowerCase());
			try {
				clazz.newInstance().init();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}