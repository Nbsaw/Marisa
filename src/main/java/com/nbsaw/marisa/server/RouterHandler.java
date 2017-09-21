package com.nbsaw.marisa.server;

import com.nbsaw.marisa.exception.NotFoundException;
import com.nbsaw.marisa.http.Request;
import com.nbsaw.marisa.http.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@Slf4j
class RouterHandler {

    // Handler Common Request
    public static boolean handlerRouter(Request request, Response response) {
        String router = request.getRouter();
        HashMap<Method, Object> m = Server.getMap.get(router);
        if (m != null) {
            for(Method method : m.keySet()){
                try {
                    method.invoke(m.get(method), request, response);
                    return true;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    // Handler Static File
    public static boolean handlerFile(Request request, Response response) {
        try {
            response.setStatic(request.getRouter());
            return true;
        } catch (NotFoundException | IOException e) {
            response.setError();
            log.error("Can not {} {}", request.getHeaders().get("method"), request.getHeaders().get("router"));
        }
        return false;
    }

    public static boolean handleError(Request request, Response response) {
        return false;
    }

}
