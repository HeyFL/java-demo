
package com.example.test.demo.design.patterns.behavior.strategy;

/**
 * 对doOperation:
 * 1.获取StrategyFactory
 * 2.选择Strategy
 * 的复杂逻辑进行剥离,令调用方更加简单的使用
 *
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public class StrategyFacade {
    public int doOperation(int a, int b, EnumStrategy enumStrategy) throws Exception {
        StrategyFactory strategyFactory = StrategyFactory.getInstance();
        Strategy strategy = strategyFactory.getStrategy(enumStrategy);
        return strategy.doOperation(a, b);
    }
}
