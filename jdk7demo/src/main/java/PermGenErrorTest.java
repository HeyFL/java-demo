import java.util.LinkedList;
import java.util.List;

/**
 * -Xms100m -Xmx100m
 * JDK8下以上配置无效
 * 测试参数-XX:PermSize=50M -XX:MaxPermSize=50M
 */
public class PermGenErrorTest {
    public static void main(String[] args) throws InterruptedException {
        finalPoolFullError();


    }

    /**
     * 测试常量池导致 溢出
     * JDK7 jDK8 PermS均不会溢出
     * 因为JDK7、8都把常量池移到堆里了 JDK7/JDK8为堆溢出
     *
     * 参考 http://java-performance.info/string-intern-in-java-6-7-8/
     * JDK7开始  所有字符串现在都位于堆中
     * All strings are now located in the heap, as most of other ordinary objects, which allows you to manage only the heap size while tuning your application
     */
    private static void finalPoolFullError() {
        long i = 0;
        List<String> stringList = new LinkedList<>();
        while (true) {
            stringList.add(String.valueOf(i++).intern());
        }
    }
}
