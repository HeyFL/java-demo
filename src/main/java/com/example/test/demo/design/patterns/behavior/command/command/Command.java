
package com.example.test.demo.design.patterns.behavior.command.command;

import com.example.test.demo.design.patterns.behavior.command.group.LinuxSystemGroup;
import com.example.test.demo.design.patterns.behavior.command.group.WindowsSystemGroup;

/**
 * @author caizq
 * @date 2019/3/21
 * @since v1.0.0
 */
public abstract class Command {
    protected LinuxSystemGroup linuxSystemGroup = new LinuxSystemGroup();
    protected WindowsSystemGroup windowsSystemGroup = new WindowsSystemGroup();

    public abstract void execute();
}
