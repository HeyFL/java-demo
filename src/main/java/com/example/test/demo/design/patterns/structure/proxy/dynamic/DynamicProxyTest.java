
package com.example.test.demo.design.patterns.structure.proxy.dynamic;

import com.example.test.demo.design.patterns.structure.proxy.Subject;

/**
 * 动态代理个人最佳实践测试
 * <p>
 * 与静态代理区别为:
 * 需要由创建方告诉动态代理实现类
 * <p>
 * 因为动态代理需要告知实现类,为了更好地满足开闭原则,应该屏蔽掉实现，其中以Spring为例：
 * spring知道实现类是哪个   所以可以使用工厂、通过动态代理创建各种代理类
 * 然后缓存下来   给调用方get使用，以此调用方对实现类的直接依赖
 *
 * 故:
 * 最好的实践应该是动态代理结合工厂模式进行使用
 * 避免动态代理必须要将具体的策略暴露给高层模块的缺点
 *
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        DynamicProxyFactory dynamicProxyCreate = new DynamicProxyFactory();
        Subject subject = (Subject) dynamicProxyCreate.getBean(Subject.class.getName());
        subject.saySth("动态代理");
    }
}
