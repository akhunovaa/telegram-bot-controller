package com.botmasterzzz.controller.controller.lady;

import com.botmasterzzz.bot.api.impl.objects.replykeyboard.ReplyKeyboardMarkup;
import com.botmasterzzz.bot.api.impl.objects.replykeyboard.buttons.KeyboardRow;
import com.botmasterzzz.controller.controller.TikTokData;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BotMessageHelperUtil {

    protected static String getNewsMessageRussian() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("➖➖➖➖➖➖➖➖➖➖➖➖\n");
        stringBuilder.append("<b>22.09.2020</b>\n\n");
        stringBuilder.append("Друзья, у нас отличные новости!\nМы решили открыться в Telegram!\n\n");
        stringBuilder.append("Добро пожаловать!\n\n");
        stringBuilder.append("➖➖➖➖➖➖➖➖➖➖➖➖\n\n");
        stringBuilder.append("Для того, чтобы начать пользоваться всеми возможностями нашего магазина - просто отправьте боту команду /start \n");
        return stringBuilder.toString();
    }

    protected static ReplyKeyboardMarkup cancelKeyBoardBuild(String language) {
        String text;
        if (StringUtils.isEmpty(language)) {
            language = "";
        }
        switch (language) {
            case "en":
                text = TikTokData.ENG_CANCEL_TEXT;
                break;
            case "ru":
                text = TikTokData.RUS_CANCEL_TEXT;
                break;
            case "cn":
                text = TikTokData.CH_CANCEL_TEXT;
                break;
            default:
                text = TikTokData.RUS_CANCEL_TEXT;
        }
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRowLineOne = new KeyboardRow();
        keyboardRowLineOne.add(text);
        keyboardRows.add(keyboardRowLineOne);
        keyboard.setKeyboard(keyboardRows);
        return keyboard;
    }

    protected static String getEmojiFromInt(int identifier) {
        switch (identifier) {
            case 0:
                return "1️⃣";
            case 1:
                return "2️⃣";
            case 2:
                return "3️⃣";
            case 3:
                return "4️⃣";
            case 4:
                return "5️⃣";
            case 5:
                return "6️⃣";
            case 6:
                return "7️⃣";
            case 7:
                return "8️⃣";
            case 8:
                return "9️⃣";
            case 9:
                return "\uD83D\uDD1F";
            default:
                return "\uD83D\uDD1F";
        }
    }

    protected static ReplyKeyboardMarkup buildMainReplyKeyBoard() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRowLineTop = new KeyboardRow();
        KeyboardRow keyboardRowLineOne = new KeyboardRow();
        KeyboardRow keyboardRowLineTwo = new KeyboardRow();
        KeyboardRow keyboardRowLineThree = new KeyboardRow();
        keyboardRowLineTop.add("\uD83D\uDCE8Главное Меню");
        keyboardRowLineOne.add("\uD83C\uDF81Новинки");
        keyboardRowLineTwo.add("\uD83D\uDC83Каталог");
        keyboardRowLineThree.add("☎️Контакты");
        keyboardRows.add(keyboardRowLineTop);
        keyboardRows.add(keyboardRowLineOne);
        keyboardRows.add(keyboardRowLineTwo);
        keyboardRows.add(keyboardRowLineThree);
        keyboard.setKeyboard(keyboardRows);
        keyboard.setOneTimeKeyboard(Boolean.TRUE);
        return keyboard;
    }


    protected static String mainTextBuild() {
        return StyleLadyData.RUS_MAIN_TEXT;
    }


}
