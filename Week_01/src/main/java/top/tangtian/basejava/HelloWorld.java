package top.tangtian.basejava;

/**
 * @author tangtian
 * @version 1.0
 * @className HelloWorld
 * @description
 * @date 2020/10/16 7:47 PM
 **/
public class HelloWorld {
  public static void main (String[] args) {
    int a = 1;
    int b = 2;
    int c = a * b;
    int d = c / a;
    if (a < b){
      a = b + 1;
    }
    for (int i = 0; i < a; i++){
      System.out.println(a--);
    }
  }
}
//编译成class 字节码文件
//javac src/main/java/top/tangtian/basejava/HelloWorld.java
//查看字节码助记符
//javap -c src.main.java.top.tangtian.basejava.HelloWorld
/**
 * public class top.tangtian.basejava.HelloWorld {
 *   public top.tangtian.basejava.HelloWorld();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]);
 *     Code:
 *        0: iconst_1
 *        1: istore_1
 *        2: iconst_2
 *        3: istore_2
 *        4: iload_1
 *        5: iload_2
 *        6: imul
 *        7: istore_3
 *        8: iload_3
 *        9: iload_1
 *       10: idiv
 *       11: istore        4
 *       13: iload_1
 *       14: iload_2
 *       15: if_icmpge     22
 *       18: iload_2
 *       19: iconst_1
 *       20: iadd
 *       21: istore_1
 *       22: iconst_0
 *       23: istore        5
 *       25: iload         5
 *       27: iload_1
 *       28: if_icmpge     47
 *       31: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *       34: iload_1
 *       35: iinc          1, -1
 *       38: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
 *       41: iinc          5, 1
 *       44: goto          25
 *       47: return
 * }
 */
