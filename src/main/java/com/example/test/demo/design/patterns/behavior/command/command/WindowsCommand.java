
package com.example.test.demo.design.patterns.behavior.command.command;

/**
 * @author caizq
 * @date 2019/3/21
 * @since v1.0.0
 */
public class WindowsCommand extends Command {

    @Override
    public void execute() {
        windowsSystemGroup.readFromNet();
        windowsSystemGroup.writeIntoDisk();
    }
}
