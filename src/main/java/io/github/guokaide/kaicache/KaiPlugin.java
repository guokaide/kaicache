package io.github.guokaide.kaicache;

/**
 * kai cache plugin.
 */
public interface KaiPlugin {

    /**
     * 初始化
     */
    void init();

    /**
     * 启动
     */
    void startup();

    /**
     * 关闭
     */
    void shutdown();

}
