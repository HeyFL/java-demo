
package com.example.test.demo.design.patterns.behavior.command;

import com.example.test.demo.design.patterns.behavior.command.command.Command;
import com.example.test.demo.design.patterns.behavior.command.command.LinuxCommand;
import lombok.extern.slf4j.Slf4j;

/**
 * 对于client端来说,服务端只需要提供支持的命令(Command) 代理者(Invoker)
 * 以此方式可以让客户端与实现隔离
 * <p>
 * 每次新增命令都要新增对应的Command实现类与Group实现类
 *
 * 与策略模式的区别:
 * 命令模式有不同的目的
 * 策略模式有相同的目的，对于该目的有不同的算法
 *
 * @author caizq
 * @date 2019/3/21
 * @since v1.0.0
 */
@Slf4j
public class CommandClientTest {
    public static void main(String[] args) {
        Command command = new LinuxCommand();
        Invoker invoker = new Invoker(command);
        invoker.action();
    }
}
