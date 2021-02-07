package com.botmasterzzz.controller.config;

import com.botmasterzzz.bot.api.impl.objects.Update;
import com.botmasterzzz.controller.annotations.BotController;
import com.botmasterzzz.controller.annotations.BotRequestMapping;
import com.botmasterzzz.controller.container.BotApiMethodContainer;
import com.botmasterzzz.controller.controller.BotApiMethodController;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class TelegramUpdateHandlerBeanPostProcessor implements BeanPostProcessor, Ordered {

    private final BotApiMethodContainer container = BotApiMethodContainer.getInstance();
    private final Map<String, Object> botControllerMap = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean.getClass().isAnnotationPresent(BotController.class)) {
            botControllerMap.put(beanName, bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (!botControllerMap.containsKey(beanName)) {
            return bean;
        }

        Object original = botControllerMap.get(beanName);
        Arrays.stream(original.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(BotRequestMapping.class))
                .forEach((Method method) -> generateController(bean, method));
        return bean;
    }


    private void generateController(Object bean, Method method) {
        BotController botController = bean.getClass().getAnnotation(BotController.class);
        BotRequestMapping botRequestMapping = method.getAnnotation(BotRequestMapping.class);
        String[] paths = new String[botRequestMapping.value().length];
        System.arraycopy(botRequestMapping.value(), 0, paths, 0, botRequestMapping.value().length);

        //String type = (botController.value().length != 0 ? botController.value()[0] : "") + (botRequestMapping.value().length != 0 ? botRequestMapping.value()[0] : "");

        BotApiMethodController controller = null;

        switch (botRequestMapping.method()[0]) {
            case MSG:
                controller = createControllerUpdate2ApiMethod(bean, method);
                break;
            case EDIT:
                controller = createProcessListForController(bean, method);
                break;
            default:
                break;
        }
        for (String path : paths) {
            container.addBotController(path, controller);
        }
    }

    private BotApiMethodController createControllerUpdate2ApiMethod(Object bean, Method method) {
        return new BotApiMethodController(bean, method) {
            @Override
            public boolean successUpdatePredicate(Update update) {
                return update != null && update.hasMessage() && update.getMessage().hasText();
            }
        };
    }

    private BotApiMethodController createProcessListForController(Object bean, Method method) {
        return new BotApiMethodController(bean, method) {
            @Override
            public boolean successUpdatePredicate(Update update) {
                return update != null && update.hasCallbackQuery()
                        && update.getCallbackQuery() != null;
            }
        };
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
