package com.roborave.edm.tweaker;

import java.util.Arrays;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Configuration;

import com.roborave.edm.tweaker.config.MCConfiguration;
import com.roborave.edm.tweaker.helper.LogHelper;
import com.roborave.edm.tweaker.helper.ObfHelper;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, version = Main.VERSION,name=Main.NAME)
public class Main
{
	@Mod.Instance(value=Main.MODID)
	public Main instance;
	
	@Mod.Metadata(Main.MODID)
	public ModMetadata meta;

	public static boolean hardMode;

	private String descriptionDEV="this hard mod for get unenchanted swords WIP";

	private boolean test=false;
    
	public static final String MODID = "Tweaker";
    public static final String VERSION = "0.1.5";
    public static final String NAME="edmtweaker";
    
    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event)
    {
    	meta = event.getModMetadata();
		meta.autogenerated = false;
		meta.credits = "(C) Roborave, 2013-2014";
		meta.authorList = Arrays.asList("Roborave");
		meta.modId=MODID;
		meta.name=NAME;
		meta.version=VERSION;
		//meta.description="EDM";
		
		MCConfiguration.CreateConfig(event, Main.MODID, Main.NAME.toUpperCase());
		Main.hardMode = MCConfiguration.config.getBoolean(Configuration.CATEGORY_GENERAL, "HardMode", this.test, this.descriptionDEV);
		MCConfiguration.config.save();
		
		if(ObfHelper.isObfuscatedEnv()==false){
			LogHelper.info("Found Obfuscated Envorment", new Object[0]);
			try {
				LogHelper.info("%s", Blocks.class.getField("air").toString());
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
    }
    
    @EventHandler
    public void Postinit(FMLPostInitializationEvent event)
    {
    	if(Loader.isModLoaded("Extra_Diamonds_Mod"))
    	{
				try 
				{
					Tweaker.load();
				} 
					catch (Exception e) 
				{
					e.printStackTrace();
				}
    	}
    }
}
