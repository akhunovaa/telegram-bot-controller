package com.botmasterzzz.controller.service;

import com.botmasterzzz.bot.api.impl.objects.Update;
import com.botmasterzzz.controller.controller.BotApiMethodController;

public interface ControllerProcessService {

    void process(BotApiMethodController methodController, Update update, Long kafkaKey);

    void processProfilePhotosMethod(BotApiMethodController methodController, Update update, Long kafkaKey);

    void processInline(BotApiMethodController methodController, Update update, Long kafkaKey);

}
