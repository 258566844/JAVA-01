package com.jike;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class Week01 extends ClassLoader {

    public static void main(String[] args) throws Exception {
        Week01 week01 = new Week01();
        Class<?> aClass = week01.findClass("Hello", "Hello.xlass");
        Object o = aClass.newInstance();
        Method hello = aClass.getMethod("hello");
        hello.invoke(o);
    }

    protected Class<?> findClass(String className, String path) {
        byte[] bytes = null;
        byte[] cLassBytes = null;
        try {
            InputStream in = this.getResourceAsStream(path);
            bytes = toByteArray(in);
            cLassBytes = new byte[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                cLassBytes[i] = (byte) (255 - bytes[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Class clazz = defineClass(className, cLassBytes, 0, cLassBytes.length);
        return clazz;
    }

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

}
