package com.botmasterzzz.controller.controller.lady;

import com.botmasterzzz.bot.api.impl.methods.send.SendMessage;
import com.botmasterzzz.bot.api.impl.objects.Update;
import com.botmasterzzz.bot.api.impl.objects.replykeyboard.ReplyKeyboardMarkup;
import com.botmasterzzz.controller.annotations.BotController;
import com.botmasterzzz.controller.annotations.BotRequestMapping;
import com.botmasterzzz.controller.service.TelegramUserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BotController
public class MainStyleLadyMenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainStyleLadyMenuController.class);

    private final TelegramUserService telegramUserService;

    private final Gson gson;

    public MainStyleLadyMenuController(TelegramUserService telegramUserService, Gson gson) {
        this.telegramUserService = telegramUserService;
        this.gson = gson;
    }

    @BotRequestMapping(value = {"lady-/start", "lady-\uD83D\uDCE8Главное Меню", "lady-↪️Вернуться", "lady-↪️Return", "lady-↪️返回"})
    public SendMessage start(Update update) {
        ReplyKeyboardMarkup mainKeyboard = BotMessageHelperUtil.buildMainReplyKeyBoard();
        String messageText = BotMessageHelperUtil.mainTextBuild();
        String news = BotMessageHelperUtil.getNewsMessageRussian();


        return new SendMessage()
                .setChatId(update.getMessage().getChatId()).enableHtml(true)
                .setText("<b> " + messageText + " </b>\n" +
                        "\n" +
                        news +
                        "\n" +
                        "❗️/start для перехода на главное меню\n" +
                        "\n")
                .setReplyMarkup(mainKeyboard);
    }

    @BotRequestMapping(value = "lady-☎️Контакты")
    public SendMessage contacts(Update update) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>Адрес:</b>\n");
        stringBuilder.append("г. Набережные Челны\n");
        stringBuilder.append("\n");
        stringBuilder.append("<b>Номера телефонов:</b>\n");
        stringBuilder.append("+7(987)231-89-97\n");
        stringBuilder.append("+7(917)877-78-78\n");
        stringBuilder.append("\n");
        stringBuilder.append("<b>Телеграмм:</b>\n");
        stringBuilder.append("@leon4uk6\n");
        stringBuilder.append("\n");
        stringBuilder.append("<b>Разработан при помощи:</b>\n");
        stringBuilder.append("https://botmasterzzz.com");
        return new SendMessage()
                .setChatId(update.getMessage().getChatId()).enableHtml(true)
                .setText(stringBuilder.toString());
    }


}
