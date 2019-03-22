package com.example.test.demo.design.patterns.behavior.strategy;

/**
 * 策略模式测试
 *
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public class StrategyTest {

    public static void main(String[] args) throws Exception {
        defaultStrategy();

        strategyOptimizedVersion();

        strategyOptimizedVersionV2();
    }

    private static void defaultStrategy() {
        int a=1;
        int b=5;
        OperationAdd operationAdd = new OperationAdd();
        Context context = new Context(operationAdd);
        int result = context.doOperation(a,b);
        System.out.println(result);
    }

    /**
     * 优化版,通过工厂模式隔离实现
     * 避免策略模式必须要将具体的策略实现暴露给高层模块(调用方)的缺点
     * 极端一点,这个策略实现可能是在别的系统、工程的，如果不通过这种形式，那么需要这些被依赖的jar，提供所有的实现给到调用方
     */
    private static void strategyOptimizedVersion() throws Exception {
        StrategyFactory strategyFactory = StrategyFactory.getInstance();
        Strategy strategy = strategyFactory.getStrategy(EnumStrategy.ADD);
        int result = strategy.doOperation(1, 2);
        System.out.println(result);
    }

    /**
     * 2次优化版：
     * 在工厂模式的优化基础上，再通过门面模式（外观模式）减少调用方调用的复杂度
     */
    private static void strategyOptimizedVersionV2() throws Exception {
        StrategyFacade strategyFacade = new StrategyFacade();
        int result = strategyFacade.doOperation(2, 2, EnumStrategy.MULTIPLY);
        System.out.println(result);
    }
}
