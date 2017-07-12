package com.nbsaw.marisa.env;

public class Environment {
    public final static String JAVA_VERSION = System.getProperty("java.version");
    public final static String USER_DIR = System.getProperty("user.dir");
    public final static String JAVA_IO_TMPDIR = System.getProperty("java.io.tmpdir");
    public final static String USER_TIMEZONE = System.getProperty("user.timezone");
    public final static String FILE_ENCODING = System.getProperty("file.encoding");
    public final static String CLASSPATH =Thread.currentThread().getContextClassLoader().getResource("").getPath();
}
