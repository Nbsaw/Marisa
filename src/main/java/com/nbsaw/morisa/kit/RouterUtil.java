package com.nbsaw.morisa.kit;

import com.nbsaw.marisa.annotation.Get;
import com.nbsaw.marisa.annotation.Post;
import java.lang.annotation.Annotation;

public class RouterUtil {

    public void isGet(Annotation annotation){
        if (!(annotation instanceof Get)) return;
        // do something
    }

    public void isPost(Annotation annotation){
        if (!(annotation instanceof Post)) return;
        // do something
    }
}
