package com.nbsaw.marisa.env;

import java.io.*;
import java.util.Properties;

public class Environment {

    public final static String JAVA_VERSION      =   System.getProperty("java.version");
    public final static String USER_DIR          =   System.getProperty("user.dir");
    public final static String JAVA_IO_TMPDIR    =   System.getProperty("java.io.tmpdir");
    public final static String USER_TIMEZONE     =   System.getProperty("user.timezone");
    public final static String FILE_ENCODING     =   System.getProperty("file.encoding");
    public final static ClassLoader CLASS_LOADER =   Thread.currentThread().getContextClassLoader();
    public final static String INDEX             =   "index.html";
    public static Properties config = new Properties();

    static {
        try {
            InputStream in =  CLASS_LOADER.getResourceAsStream("application.properties");
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isDebug(){
        String debug = config.getProperty("debug");
        if (debug.contains("true") || debug.contains("false")){
            return Boolean.valueOf(debug);
        }
        else if (debug == null){
            return false;
        }
        else{
            throw new IllegalArgumentException(debug + "is not a valid argument");
        }
    }

}
