package com.nbsaw.marisa.server;

import com.nbsaw.marisa.env.Environment;
import com.nbsaw.morisa.kit.AnnotationUtil;
import com.nbsaw.morisa.kit.Assert;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.util.HashMap;

@Slf4j
public class Server{
    private ServerSocket serverSocket;
    private Class bootClass;
    private HashMap<String,Method> router;
    public static HashMap<String,HashMap> getMap = new HashMap<>();

    public static void banner(){
        System.out.println("\n\n　　　　　　　　　　　　　　 　　 _,,.. --､\n" +
                "　　　　 　 　 　 　 　 　 ,..::''\"´::::::::::::::;::＼\n" +
                "　　 　 　 　 |｀'ｰ‐--＜:::::::::::::::::::_;;:: -‐ ''\"´￣｀7 \n" +
                "　　　　　　 ,'　　､_　　 ｀ヽr‐r'\"´　　_　　　　　/\n" +
                "　　　　　__/　　_,,..＞=-‐'─'─-＜..,,_　　 　 ,!＞\n" +
                "　　　　 }＞''\"´::::::::::::＿;;;;:::::;;;;;＿_::::::::::｀\"'＜./\n" +
                "　 　 ／::::::::::;;::-‐'\"´　　　　　　 　｀\"''ヽ;:::::::::｀ヽ.\n" +
                "　　 ;':::::::::/´ ,'　/　 　 i　　　! __! イ　　 i＼::::::::::::＼\n" +
                "　 　｀ヽ､:!　 i 　|　メ_ ﾊ 　 ハ_」,_ ﾊ　 　'.,　 Y:::::::::::::>\n" +
                "　 　 　　   ）ﾍﾊ　!/7´ﾊ' |／ '´i´ハ`Y!　　 ） 　',:::::／\n" +
                "　 　 　 　 　 ,ﾊﾚ7!　iｿ　　　　!___ｿ ﾉ!　／ 　　 V\n" +
                "　　　　　　 /　,/''\"　 '　 　　　　\"'' ,ﾚ'）ﾉ　　　,ﾊ\n" +
                "　　　　　 ノ　 ハ、 　 ｰ-‐-'　　u ﾉﾒﾊ 　 i　 /　i\n" +
                "　　 　　（ 　 / 　ﾚ＼　　　　　　,. ｨメﾉ　　ﾊ　　 ﾉ\n" +
                "　　　　　｀ヽ!/｀ヽ）,ノ｀'iｧ-r 　　/メﾉ＞-ｧ‐-､ﾍ（\n" +
                "　　　　　　　'　 　）へ!_,.イゝ-イ（X）/:::/ 　　　＼）､\n" +
                "　　　　　　 　 　 （／´ ./::レ'iヽ}＞く_]/　 　　 　　 Y\n" +
                "　　　　　　 　 　 /　　 7::::!_/__」,ハ::::;i　　　 　　　,ﾉ\n" +
                "　　　　　　　　 r!　　　!::::::::!/:::::レ'::::::ゝ､　　_,ゝ-へ\n\n");
    }

    private Server(int port,Class bootClass){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            // Acceptor
            ServerHandler handler = null;
            try {
                handler = new ServerHandler(serverSocket.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread thread = new Thread(handler);
            thread.start();
        }
    }

    public static void start(@NonNull String address, @NonNull String port,Class bootClass){
        // valid port
        Assert.biggerThan(Integer.valueOf(port),65535,"port can't not bigger than 65535 !");
        Assert.smallerThan(Integer.valueOf(port),0,"port can't not be an negative !");
        // print initialize information
        long initStart = System.currentTimeMillis();
        log.info("Loading Marisa Environment....");
        log.info("Environment: jdk.version\t\t\t=> {}"   , Environment.JAVA_VERSION);
        log.info("Environment: user.dir\t\t\t=> {}"      , Environment.USER_DIR);
        log.info("Environment: java.io.tmpdir\t\t=> {}"  , Environment.JAVA_IO_TMPDIR);
        log.info("Environment: user.timezone\t\t=> {}"   , Environment.USER_TIMEZONE);
        log.info("Environment: file.encoding\t\t=> {}"   , Environment.FILE_ENCODING);
        log.info("Loading Marisa Environment success.");
        Server.banner();
        // valid pass
        log.info("Marisa initialization completed in {} ms",System.currentTimeMillis() - initStart);
        log.info("Marisa is started success !!");
        log.info("u can open server in http://{}:{}", address.equals("0.0.0.0") ? "127.0.0.1" : address , port);
        new AnnotationUtil(bootClass);
        new Server(Integer.valueOf(port),bootClass);
    }
}
