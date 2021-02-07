package com.botmasterzzz.controller.controller.rune;

import com.botmasterzzz.bot.api.impl.methods.send.SendMessage;
import com.botmasterzzz.bot.api.impl.objects.Update;
import com.botmasterzzz.bot.api.impl.objects.replykeyboard.ReplyKeyboardMarkup;
import com.botmasterzzz.bot.api.impl.objects.replykeyboard.buttons.KeyboardRow;
import com.botmasterzzz.controller.annotations.BotController;
import com.botmasterzzz.controller.annotations.BotRequestMapping;
import com.botmasterzzz.controller.entity.TelegramBotUserEntity;
import com.botmasterzzz.controller.service.TelegramUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@BotController
public class MainRuneMenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainRuneMenuController.class);

    @Autowired
    private TelegramUserService telegramUserService;

    @BotRequestMapping(value = "rune-/start")
    public SendMessage start(Update update) {
        String name = null != update.getMessage().getFrom().getUserName() ? update.getMessage().getFrom().getUserName() : update.getMessage().getFrom().getFirstName();
        Long chatId = update.hasMessage() ? update.getMessage().getChatId() : update.getCallbackQuery().getMessage().getChatId();
        Long requestedUserId = Long.valueOf(update.getMessage().getFrom().getId());

        TelegramBotUserEntity requestedTelegramUser = telegramUserService.getTelegramUser(requestedUserId);
        boolean isRequestedUserAdmin = requestedTelegramUser.isAdmin();

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRowLineOne = new KeyboardRow();
        keyboardRowLineOne.add("Руна Дня");
        keyboardRowLineOne.add("Совет Рун");
        keyboardRows.add(keyboardRowLineOne);
        keyboard.setKeyboard(keyboardRows);
        return new SendMessage()
                .setChatId(chatId).enableHtml(true)
                .setText("<b>Добро пожаловать</b>\uD83E\uDD29, " + name + ".\n" +
                        "\n" +
                        "/start Главное меню\n" +
                        "️/rune Руна Дня\n" +
                        "️/advice Совет Рун\n" +
                        "\n")
                .setReplyMarkup(keyboard);
    }

    @BotRequestMapping(value = {"rune-ℹ️Информация"})
    public SendMessage contacts(Update update) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>Адрес:</b>\n");
        stringBuilder.append("г. Москва, Варшавское шоссе\n");
        stringBuilder.append("\n");
        stringBuilder.append("<b>Телеграмм:</b>\n");
        stringBuilder.append("@leon4uk\n");
        stringBuilder.append("\n");
        stringBuilder.append("<b>Разработан при помощи:</b>\n");
        stringBuilder.append("https://botmasterzzz.com\n");
        stringBuilder.append("\n");
        return new SendMessage()
                .setChatId(update.getMessage().getChatId()).enableHtml(true)
                .setText(stringBuilder.toString());
    }
}
