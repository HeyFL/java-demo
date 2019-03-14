package com.example.test.demo.design.patterns.behavior.strategy;

/**
 * 策略工厂 屏蔽实现
 *
 * @author caizq
 * @date 2019/3/14
 * @since v1.0.0
 */
public class StrategyFactory {
    private static StrategyFactory strategyFactoryInstance;

    private StrategyFactory() {
    }

    /**
     * double check 双重校验获取单例
     *
     * @return
     */
    public static StrategyFactory getInstance() {
        if (strategyFactoryInstance == null) {
            synchronized (StrategyFactory.class) {
                if (strategyFactoryInstance == null) {
                    strategyFactoryInstance = new StrategyFactory();
                }
            }
        }
        return strategyFactoryInstance;
    }

    public Strategy getStrategy(EnumStrategy enumStrategy) throws Exception {
        switch (enumStrategy) {
            case ADD:
                return new OperationAdd();
            case MULTIPLY:
                return new OperationMultiply();
            default:
                throw new Exception();
        }
    }
}