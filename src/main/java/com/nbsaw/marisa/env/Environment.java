package com.nbsaw.marisa.env;

import java.io.*;
import java.util.Properties;

public class Environment {

    public final static String JAVA_VERSION      =   System.getProperty("java.version");
    public final static String USER_DIR          =   System.getProperty("user.dir");
    public final static String JAVA_IO_TMPDIR    =   System.getProperty("java.io.tmpdir");
    public final static String USER_TIMEZONE     =   System.getProperty("user.timezone");
    public final static String FILE_ENCODING     =   System.getProperty("file.encoding");
    public final static String CLASSPATH         =   Thread.currentThread().getContextClassLoader().getResource("").getPath();
    public final static ClassLoader CLASS_LOADER =   Thread.currentThread().getContextClassLoader();

    public static Properties config = new Properties();

    static {
        try {
            InputStream in =  CLASS_LOADER.getResourceAsStream("application.properties");
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
