package com.roborave.edm.tweaker.helper;

import net.minecraft.init.Blocks;

public class ObfHelper {

	 public static boolean isObfuscatedEnv()
	    {
	        try
	        {
	            Blocks.class.getField("air");
	            return false;
	        }
	        catch (Throwable e)
	        {
	            return true;
	        }
	    }
}
