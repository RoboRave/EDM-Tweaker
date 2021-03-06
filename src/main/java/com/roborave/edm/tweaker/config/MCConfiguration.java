package com.roborave.edm.tweaker.config;

import java.io.File;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

/**
 * This class offers advanced configurations capabilities, allowing to provide various categories for configuration variables.
 */
public class MCConfiguration extends Configuration
{
    private final File fileRef;
    
    /**
     * The configuration instance
     */
    public static MCConfiguration config;
    
    /**
     * Creates the config file itself
     * 
     * @param event
     *            The event used to get the directory
     * @param dir
     *            The sub-directory
     * @param modidName
     *            The mod id to name the config
     */
    public static void CreateConfig(FMLPreInitializationEvent event, String dir, String modidName)
    {
        config = new MCConfiguration(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + dir + File.separator + modidName + ".cfg"));
    }
    /**
     * @param file creates the config
     */
    public MCConfiguration(File file)
    {
        super(file);
        this.fileRef = file;
    }
    
    /**
     * Used to Get file
     */
    public File getConfigFile()
    {
        return this.fileRef;
    }
    
    /**
     * Renames a property in a given category.
     * 
     * @param category the category in which the property resides
     * @param oldPropName the existing property name
     * @param newPropName the
     * @return true if the category and property exist, false otherwise
     */
    public boolean renameProperty(String category, String oldPropName, String newPropName)
    {
        if (hasCategory(category))
        {
            if (getCategory(category).containsKey(oldPropName) && !oldPropName.equalsIgnoreCase(newPropName))
            {
                get(category, newPropName, getCategory(category).get(oldPropName).getString());
                getCategory(category).remove(oldPropName);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Moves a property from one category to another.
     * 
     * @param oldCategory the category the property currently resides in
     * @param propName the name of the property to move
     * @param newCategory the category the property should be moved to
     * @return true if the old category and property exist, false otherwise
     */
    public boolean moveProperty(String oldCategory, String propName, String newCategory)
    {
        if (hasCategory(oldCategory))
        {
            if (getCategory(oldCategory).containsKey(propName))
            {
                getCategory(newCategory).put(propName, getCategory(oldCategory).get(propName));
                return true;
            }
        }
        return false;
    }
    
    /**
     * Creates a string property.
     * 
     * @param name Name of the property.
     * @param category Category of the property.
     * @param defaultValue Default value of the property.
     * @param comment A brief description what the property does.
     * @return The value of the new string property.
     */
    public String getString(String name, String category, String defaultValue, String comment)
    {
        Property prop = this.get(category, name, defaultValue);
        prop.comment = comment + " [default: " + defaultValue + "]";
        return prop.getString();
    }
    
    /**
     * Creates a string list property.
     * 
     * @param name Name of the property.
     * @param category Category of the property.
     * @param defaultValue Default value of the property.
     * @param comment A brief description what the property does.
     * @return The value of the new string property.
     */
    public String[] getStringList(String name, String category, String[] defaultValue, String comment)
    {
        Property prop = this.get(category, name, defaultValue);
        prop.comment = comment + " [default: " + defaultValue + "]";
        return prop.getStringList();
    }
    
    /**
     * Creates a boolean property.
     * 
     * @param name Name of the property.
     * @param category Category of the property.
     * @param defaultValue Default value of the property.
     * @param comment A brief description what the property does.
     * @return The value of the new boolean property.
     */
    public boolean getBoolean(String name, String category, boolean defaultValue, String comment)
    {
        Property prop = this.get(category, name, defaultValue);
        prop.comment = comment + " [default: " + defaultValue + "]";
        return prop.getBoolean(defaultValue);
    }
    
    /**
     * Creates a integer property.
     * 
     * @param name Name of the property.
     * @param category Category of the property.
     * @param defaultValue Default value of the property.
     * @param minValue Minimum value of the property.
     * @param maxValue Maximum value of the property.
     * @param comment A brief description what the property does.
     * @return The value of the new integer property.
     */
    public int getInt(String name, String category, int defaultValue, int minValue, int maxValue, String comment)
    {
        Property prop = this.get(category, name, defaultValue);
        prop.comment = comment + " [range: " + minValue + " ~ " + maxValue + ", default: " + defaultValue + "]";
        return prop.getInt(defaultValue) < minValue ? minValue : (prop.getInt(defaultValue) > maxValue ? maxValue : prop.getInt(defaultValue));
    }
    
    /**
     * Creates a float property.
     * 
     * @param name Name of the property.
     * @param category Category of the property.
     * @param defaultValue Default value of the property.
     * @param minValue Minimum value of the property.
     * @param maxValue Maximum value of the property.
     * @param comment A brief description what the property does.
     * @return The value of the new float property.
     */
    public float getFloat(String name, String category, float defaultValue, float minValue, float maxValue, String comment)
    {
        Property prop = this.get(category, name, Float.toString(defaultValue));
        prop.comment = comment + " [range: " + minValue + " ~ " + maxValue + ", default: " + defaultValue + "]";
        try
        {
            return Float.parseFloat(prop.getString()) < minValue ? minValue : (Float.parseFloat(prop.getString()) > maxValue ? maxValue : Float.parseFloat(prop.getString()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
