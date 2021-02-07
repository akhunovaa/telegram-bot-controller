package com.botmasterzzz.controller.container;

import com.botmasterzzz.controller.controller.BotApiMethodController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BotApiMethodContainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BotApiMethodContainer.class);

    private final Map<String, BotApiMethodController> controllerMap;

    private BotApiMethodContainer() {
        controllerMap = new HashMap<>();
    }

    public static BotApiMethodContainer getInstance() {
        return Holder.INST;
    }

    public void addBotController(String type, BotApiMethodController controller) {
        if (controllerMap.containsKey(type)) {
            LOGGER.info("path {} already added", type);
        }
        controllerMap.put(type, controller);
    }

    public BotApiMethodController getBotApiMethodController(String type) {
        return controllerMap.get(type);
    }

    public Map<String, BotApiMethodController> getControllerMap() {
        return controllerMap;
    }

    private static class Holder {
        static final BotApiMethodContainer INST = new BotApiMethodContainer();
    }
}
