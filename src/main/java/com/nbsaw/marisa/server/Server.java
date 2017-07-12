package com.nbsaw.marisa.server;

import com.nbsaw.marisa.env.Environment;
import com.nbsaw.morisa.kit.Assert;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {

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

    public static void start(){
        start("127.0.0.1",80);
    }

    public static void start(@NotNull String address, @NotNull int port){
        // valid port
        Assert.biggerThan(port,65535,"port can't not bigger than 65535 !");
        Assert.smallerThan(port,0,"port can't not be an negative !");
        // print initialize information
        long initStart = System.currentTimeMillis();
        log.info("Loading Marisa Environment....");
        log.info("Environment: jdk.version\t\t=> {}" , Environment.JAVA_VERSION);
        log.info("Environment: user.dir\t\t\t=> {}"  , Environment.USER_DIR);
        log.info("Environment: java.io.tmpdir\t=> {}", Environment.JAVA_IO_TMPDIR);
        log.info("Environment: user.timezone\t\t=> {}" , Environment.USER_TIMEZONE);
        log.info("Environment: file.encoding\t\t=> {}" , Environment.FILE_ENCODING);
        log.info("Environment: classpath\t\t\t=> {}"   , Environment.CLASSPATH);
        log.info("Loading Marisa Environment success.");
        Server.banner();
        // valid pass
        log.info("Marisa initialization completed in {} ms",System.currentTimeMillis() - initStart);
        log.info("Marisa is started success !!");
        log.info("u can open server in http://{}:{}",address,port);
    }
}
