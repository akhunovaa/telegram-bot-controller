package com.botmasterzzz.controller.controller.rune;

import com.botmasterzzz.bot.api.impl.objects.replykeyboard.buttons.InlineKeyboardButton;
import com.botmasterzzz.controller.dto.CallBackData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BotMessageHelperUtil {

    protected static String getNewsMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("➖➖➖➖➖➖➖➖➖➖➖➖\n");
        stringBuilder.append("<b>14.08.2020</b>\n");
        stringBuilder.append(" - Дата создания бота\n");
        stringBuilder.append("➖➖➖➖➖➖➖➖➖➖➖➖\n");
        return stringBuilder.toString();
    }

    protected static List<InlineKeyboardButton> arrowButtonsBuild(String path, int limitLeft, long telegramUserId, long fileId) {
        String pathToCallback;
        switch (path) {
            case "newForToday":
                pathToCallback = "art";
                break;
            case "newForYesterday":
                pathToCallback = "ary";
                break;
            case "photo":
                pathToCallback = "arf";
                break;
            case "video":
                pathToCallback = "arv";
                break;
            default:
                pathToCallback = "art";
        }
        List<InlineKeyboardButton> inlineKeyboardButtonsArrowsRow = new ArrayList<>();
        Gson gson = new Gson();
        InlineKeyboardButton leftArrowButton = new InlineKeyboardButton();
        leftArrowButton.setText("️➡️ Далее");
        CallBackData leftButtonData = new CallBackData(pathToCallback, telegramUserId, fileId);
        leftButtonData.setO(limitLeft);
        leftButtonData.setL(limitLeft);
        leftArrowButton.setCallbackData(gson.toJson(leftButtonData));
        inlineKeyboardButtonsArrowsRow.add(leftArrowButton);
        return inlineKeyboardButtonsArrowsRow;
    }
}
