package com.botmasterzzz.controller.controller.taxi;

import com.botmasterzzz.bot.api.impl.methods.send.SendMessage;
import com.botmasterzzz.bot.api.impl.objects.Update;
import com.botmasterzzz.bot.api.impl.objects.replykeyboard.ReplyKeyboardMarkup;
import com.botmasterzzz.bot.api.impl.objects.replykeyboard.buttons.KeyboardRow;
import com.botmasterzzz.controller.annotations.BotController;
import com.botmasterzzz.controller.annotations.BotRequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@BotController
public class MainTaxiMenuController {

    private static final Logger logger = LoggerFactory.getLogger(MainTaxiMenuController.class);

    @BotRequestMapping(value = "taxi-/start")
    public SendMessage start(Update update) {
        String name = null != update.getMessage().getFrom().getUserName() ? update.getMessage().getFrom().getUserName() : update.getMessage().getFrom().getFirstName();
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRowLineTop = new KeyboardRow();
        KeyboardRow keyboardRowLineOne = new KeyboardRow();
        KeyboardRow keyboardRowLineTwo = new KeyboardRow();
        KeyboardRow keyboardRowLineThree = new KeyboardRow();
        KeyboardRow keyboardRowLineFourth = new KeyboardRow();
        keyboardRowLineTop.add("\uD83D\uDCF2 Главное меню");
        keyboardRowLineOne.add("В Казанский аэропорт");
        keyboardRowLineOne.add("Советская Площадь/ЖД Вокзал");
        keyboardRowLineTwo.add("ЖД Вокзал №2");
        keyboardRowLineTwo.add("До адреса в Казани");
        keyboardRowLineThree.add("\uD83D\uDC8EОтзывы");
        keyboardRowLineFourth.add("\uD83D\uDCD2Контакты");
        keyboardRows.add(keyboardRowLineTop);
        keyboardRows.add(keyboardRowLineOne);
        keyboardRows.add(keyboardRowLineTwo);
        keyboardRows.add(keyboardRowLineThree);
        keyboardRows.add(keyboardRowLineFourth);
        keyboard.setKeyboard(keyboardRows);

        return new SendMessage()
                .setChatId(update.getMessage().getChatId()).enableHtml(true)
                .setText("<b>" + name + "</b>, добро пожаловать в бота для заказа такси Набережные Челны - Казань - Набережные Челны.\n" +
                        "\n" +
                        "❗️/start \uD83D\uDE96 для перехода на главное меню\n" +
                        "\n" +
                        "<b>Такси Набережные Челны - Казань</b>\n" +
                        "Заказывайте наше такси и через 2,5 часа вы будете на месте.\n" +
                        "От подъезда до подъезда от 700 рублей.\n" +
                        "\n")
                .setReplyMarkup(keyboard);
    }

    @BotRequestMapping(value = "taxi-\uD83D\uDCF2 Главное меню")
    public SendMessage main(Update update) {
        String name = null != update.getMessage().getFrom().getUserName() ? update.getMessage().getFrom().getUserName() : update.getMessage().getFrom().getFirstName();
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRowLineTop = new KeyboardRow();
        KeyboardRow keyboardRowLineOne = new KeyboardRow();
        KeyboardRow keyboardRowLineTwo = new KeyboardRow();
        KeyboardRow keyboardRowLineThree = new KeyboardRow();
        KeyboardRow keyboardRowLineFourth = new KeyboardRow();
        keyboardRowLineTop.add("\uD83D\uDCF2 Главное меню");
        keyboardRowLineOne.add("В Казанский аэропорт");
        keyboardRowLineOne.add("Советская Площадь/ЖД Вокзал");
        keyboardRowLineTwo.add("ЖД Вокзал №2");
        keyboardRowLineTwo.add("До адреса в Казани");
        keyboardRowLineThree.add("\uD83D\uDCE8Отзывы");
        keyboardRowLineFourth.add("\uD83D\uDCD2Контакты");
        keyboardRows.add(keyboardRowLineTop);
        keyboardRows.add(keyboardRowLineOne);
        keyboardRows.add(keyboardRowLineTwo);
        keyboardRows.add(keyboardRowLineThree);
        keyboardRows.add(keyboardRowLineFourth);
        keyboard.setKeyboard(keyboardRows);

        return new SendMessage()
                .setChatId(update.getMessage().getChatId()).enableHtml(true)
                .setText("<b>" + name + "</b>, добро пожаловать в бота для заказа такси Набережные Челны - Казань - Набережные Челны.\n" +
                        "\n" +
                        "❗️/start \uD83D\uDE96 для перехода на главное меню\n" +
                        "\n" +
                        "<b>Такси Набережные Челны - Казань</b>\n" +
                        "Заказывайте наше такси и через 2,5 часа вы будете на месте.\n" +
                        "От подъезда до подъезда от 700 рублей.\n" +
                        "\n")
                .setReplyMarkup(keyboard);
    }


    @BotRequestMapping(value = "taxi-\uD83D\uDCE8Отзывы")
    public SendMessage feedback(Update update) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>Татьяна Власова</b>\n");
        stringBuilder.append("<b>Комментарий:</b> Всем здравствуйте! Я всегда обращаюсь в эту компанию, потому что никогда не подводили, в любое время приезжают быстро, довозят аккуратно. А водитель Андрей такой молодец! Спасибо, за то, что вы есть!!!\n");
        stringBuilder.append("\n");

        stringBuilder.append("<b>Владислав Сергеевич</b>\n");
        stringBuilder.append("<b>Комментарий:</b> Всем доброго времени суток! Недавно ездил в Казань с водителем Андреем. Приехал за мной еще даже раньше чем надо. Поинтересовался у меня все ли взял, ну а я конечно забыл повербанк для телефона. Спасибо тебе Андрей, если бы я укатил без зарядки был бы просто конец\uD83D\uDE2C Все супер, опрятный автомобиль; Очень порадовало, что водитель некурящий. Вообщем никаких нареканий. Если хотите комфортно домчаться из точки А в точку Б, определенно звоните в это такси, всем рекомендую.\uD83D\uDC4D\uD83C\uDFFB\uD83D\uDE0E Андрей, тебе отдельный привет\uD83E\uDD1D\uD83C\uDFFB \n");
        stringBuilder.append("\n");

        stringBuilder.append("<b>Айгуль Хабибуллина</b>\n");
        stringBuilder.append("<b>Комментарий:</b> Сегодня утром поехали с коллегами в Казань, нас довёз водитель Олег Николаевич! Огромное спасибо за комфортное вождение, приятную атмосферу и замечательную компанию! Сервис на отличном - уровне! Обратно мы приехали с Александром Александровичем! Спасибо за приятную компанию, добрую беседу и аккуратное вождение! Ребята- Молодцы, профессионалы своего дела! Успехов Вам и процветания! Первый раз обратились к Вам и Вы не подвели! Теперь будем пользоваться Вашими услугами и рекомендовать Вас!\n");
        stringBuilder.append("\n");
        stringBuilder.append("Выберите раздел: \uD83D\uDD3D");
        return new SendMessage()
                .setChatId(update.getMessage().getChatId()).enableHtml(true)
                .setText(stringBuilder.toString());
    }

    @BotRequestMapping(value = "taxi-\uD83D\uDCD2Контакты")
    public SendMessage contacts(Update update) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>Адрес:</b>\n");
        stringBuilder.append("г. Набережные Челны\n");
        stringBuilder.append("\n");
        stringBuilder.append("<b>Номер телефона:</b>\n");
        stringBuilder.append("+7(917)863-20-02\n");
        stringBuilder.append("\n");
        stringBuilder.append("<b>Телеграмм:</b>\n");
        stringBuilder.append("@Taxichelnykazan\n");
        stringBuilder.append("\n");
        stringBuilder.append("<b>Разработан при помощи:</b>\n");
        stringBuilder.append("https://botmasterzzz.com\n");
        stringBuilder.append("\n");
        stringBuilder.append("Выберите раздел: \uD83D\uDD3D");
        return new SendMessage()
                .setChatId(update.getMessage().getChatId()).enableHtml(true)
                .setText(stringBuilder.toString());
    }

    @BotRequestMapping(value = "taxi-В Казанский аэропорт")
    public SendMessage aero(Update update) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>В Казанский аэропорт:</b>\n");
        stringBuilder.append("От подъезда:\n");
        stringBuilder.append("-------------\n");
        stringBuilder.append("1 пассажир 1000 руб\n");
        stringBuilder.append("-------------\n");
        stringBuilder.append("4 пассажира 3000 руб\n");
        stringBuilder.append("-------------\n");
        stringBuilder.append("\n");
        stringBuilder.append("Выберите раздел: \uD83D\uDD3D");
        return new SendMessage()
                .setChatId(update.getMessage().getChatId()).enableHtml(true)
                .setText(stringBuilder.toString());
    }

    @BotRequestMapping(value = "taxi-Советская Площадь/ЖД Вокзал")
    public SendMessage sovietSquare(Update update) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>До Советской площади и ж/д вокзала №1:</b>\n");
        stringBuilder.append("От подъезда:\n");
        stringBuilder.append("-------------\n");
        stringBuilder.append("1 пассажир 700 руб\n");
        stringBuilder.append("-------------\n");
        stringBuilder.append("4 пассажира 2600 руб\n");
        stringBuilder.append("-------------\n");
        stringBuilder.append("\n");
        return new SendMessage()
                .setChatId(update.getMessage().getChatId()).enableHtml(true)
                .setText(stringBuilder.toString());
    }

    @BotRequestMapping(value = "taxi-ЖД Вокзал №2")
    public SendMessage vokzal2(Update update) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>До ул Восстания, ж/д вокзал №2:</b>\n");
        stringBuilder.append("От подъезда:\n");
        stringBuilder.append("-------------\n");
        stringBuilder.append("1 пассажир 900 руб\n");
        stringBuilder.append("-------------\n");
        stringBuilder.append("4 пассажира 2800 руб\n");
        stringBuilder.append("-------------\n");
        stringBuilder.append("\n");
        return new SendMessage()
                .setChatId(update.getMessage().getChatId()).enableHtml(true)
                .setText(stringBuilder.toString());
    }

    @BotRequestMapping(value = "taxi-До адреса в Казани")
    public SendMessage address(Update update) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>До адреса в Казани:</b>\n");
        stringBuilder.append("От подъезда:\n");
        stringBuilder.append("-------------\n");
        stringBuilder.append("1 пассажир 700 - 1000 руб \n");
        stringBuilder.append("-------------\n");
        stringBuilder.append("4 пассажира 2800 руб\n");
        stringBuilder.append("-------------\n");
        stringBuilder.append("\n");
        return new SendMessage()
                .setChatId(update.getMessage().getChatId()).enableHtml(true)
                .setText(stringBuilder.toString());
    }
}
