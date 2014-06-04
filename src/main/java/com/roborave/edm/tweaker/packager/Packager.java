package com.roborave.edm.tweaker.packager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Packager
{
  String getName();
  
  String getVersion();
  
  String[] getClasses();
  
  String[] getBaseDirectories();
  
  String[] getForcedClasses() default {""};
  
  String getObfRelocationDir() default "";
}
