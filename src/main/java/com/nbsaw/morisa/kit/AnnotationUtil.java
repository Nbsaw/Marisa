package com.nbsaw.morisa.kit;

import com.nbsaw.marisa.annotation.Get;
import com.nbsaw.marisa.annotation.Path;
import com.nbsaw.marisa.server.Server;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;

@Slf4j
public class AnnotationUtil {

    private HashMap<String,String> annotationMap = new HashMap<>();
    private Class clazz;
    private ClassLoader classLoader;

    public AnnotationUtil(Class clazz){
        this.clazz       = clazz;
        this.classLoader = clazz.getClassLoader();
        this.getList();
    }

    public HashMap<String,String> getList() {
        return getList(clazz.getCanonicalName().split("\\.")[0]);
    }

    public HashMap<String,String> getList(String name){
        Enumeration<URL> resources = null;
        try {
            resources = classLoader.getResources(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (resources.hasMoreElements()) {
            File files = new File(resources.nextElement().getFile());
            loop(files,name);
        }
        return annotationMap;
    }

    public void loop(File files,String name){
        for (File file : files.listFiles()){
            if (file.isDirectory()){
                loop(file,name + "/" + file.getName());
            }else{
                try{
                    String currentName = name.replace("/", ".") + "." + file.getName().substring(0, file.getName().length() - 6);
                    Class klass = Class.forName(currentName);
                    String prefix = "";
                    if (klass.getAnnotation(Path.class)!=null){
                        Path path = (Path) klass.getAnnotation(Path.class);
                        prefix = path.value();
                    }
                    Object instance = klass.newInstance();
                    //
                    for (Method method : klass.getMethods()){
                        Annotation[] annotations =  method.getAnnotations();
                        for (Annotation annotation : annotations) {
                            Method m1 = klass.getMethod(method.getName(),method.getParameterTypes());
                            String[] s = ((Get)annotation).value();
                            for (String n : s){
                                if (!n.substring(0,1).equals("/")) n = "/" + n;
                                log.info("register get {}", prefix + n);
                                HashMap m3 = new HashMap();
                                m3.put(method,instance);
                                Server.getMap.put(prefix + n,m3);
                            }
                        }
                    }
                }catch (ReflectiveOperationException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public HashMap<String, String> getAnnotationMap() {
        return annotationMap;
    }
}
