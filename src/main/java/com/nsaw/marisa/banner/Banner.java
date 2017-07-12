package com.nsaw.marisa.banner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Banner {
    public static void print(){
        System.out.println("　　　　　　　　　　　　　　 　　 _,,.. --､\n" +
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
                "　　　　　　　　 r!　　　!::::::::!/:::::レ'::::::ゝ､　　_,ゝ-へ");
        log.info("☆ Marisa is Staring....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Environment: jdk.version\t\t\t=> {}", System.getProperty("java.version"));
        log.info("Environment: user.dir\t\t\t\t=> {}", System.getProperty("user.dir"));
        log.info("Environment: java.io.tmpdir\t\t=> {}", System.getProperty("java.io.tmpdir"));
        log.info("Environment: user.timezone\t\t=> {}", System.getProperty("user.timezone"));
        log.info("Environment: file.encoding\t\t=> {}", System.getProperty("file.encoding"));
        log.info("Environment: classpath\t\t\t=> {}", Thread.currentThread().getContextClassLoader().getResource("").getPath());
    }
}
