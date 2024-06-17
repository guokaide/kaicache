package io.github.guokaide.kaicache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 在 Spring 的生命周期中，初始化、启动、关闭 Plugin
 */
@Component
public class KaiApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    List<KaiPlugin> plugins;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // Spring 启动之后，初始化、启动 Plugin
        if (event instanceof ApplicationReadyEvent) {
            for (KaiPlugin plugin : plugins) {
                plugin.init();
                plugin.startup();
            }
        // Spring 关闭之前，优雅关闭 Plugin
        } else if (event instanceof ContextClosedEvent) {
            for (KaiPlugin plugin : plugins) {
                plugin.shutdown();
            }
        }
    }
}
