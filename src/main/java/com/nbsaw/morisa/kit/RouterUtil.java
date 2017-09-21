package com.nbsaw.morisa.kit;

import com.nbsaw.marisa.annotation.Delete;
import com.nbsaw.marisa.annotation.Get;
import com.nbsaw.marisa.annotation.Post;
import com.nbsaw.marisa.annotation.Put;

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

    public void isPut(Annotation annotation){
        if (!(annotation instanceof Put)) return;
        // do something
    }

    public void isDelete(Annotation annotation){
        if (!(annotation instanceof Delete)) return;
        // do something
    }
}
