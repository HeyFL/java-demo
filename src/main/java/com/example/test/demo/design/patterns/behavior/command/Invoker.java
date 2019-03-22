
package com.example.test.demo.design.patterns.behavior.command;

import com.example.test.demo.design.patterns.behavior.command.command.Command;
import lombok.extern.slf4j.Slf4j;

/**
 * @author caizq
 * @date 2019/3/21
 * @since v1.0.0
 */
@Slf4j
public class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;

    }

    public void action() {
        command.execute();
    }
}
