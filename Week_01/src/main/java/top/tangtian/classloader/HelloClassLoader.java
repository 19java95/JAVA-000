package top.tangtian.classloader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author tangtian
 * @version 1.0
 * @className Hello
 * @description 自定义一个ClassLoader
 * 加载一个Hello.xlass文件,执行hello方法,
 * 此文件内容是一个Hello.class文件所有字节(x=255-x)处理后
 * 的文件。
 * @date 2020/10/17 8:00 AM
 **/
public class HelloClassLoader extends ClassLoader {

  public static void main (String[] args) {
    try {
      Class<?> aClass = new HelloClassLoader().findClass("Hello");
      Object obj = aClass.newInstance();
      Method method = aClass.getMethod("hello");
      method.invoke(obj);
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected Class<?> findClass (String name) throws ClassNotFoundException {
    String filePath = this.getClass().getResource("/Hello.xlass").getPath();
    byte[] bFile = new byte[0];
    try {
      bFile = Files.readAllBytes(Paths.get(filePath));
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < bFile.length; i++) {
      bFile[i] = (byte)(255 - bFile[i]);
    }
    return defineClass(name,bFile,0,bFile.length);
  }
}
