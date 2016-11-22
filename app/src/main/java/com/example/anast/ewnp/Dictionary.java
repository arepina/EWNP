package com.example.anast.ewnp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anast on 19.01.2016.
 */
public class Dictionary {
    public static Map<Integer, String[]> sport() {
        Map<Integer, String[]> map = new HashMap<Integer, String[]>();
        map.put(0, new String []{"goal", "гол"});
        map.put(1, new String []{"player", "игрок"});
        map.put(2, new String []{"championship", "чемпионат"});
        map.put(3, new String []{"score", "счет"});
        map.put(4, new String []{"in a draw", "ничья"});
        map.put(5, new String []{"to score", "забить"});
        map.put(6, new String []{"reserve", "запас"});
        map.put(7, new String []{"stadium", "стадион"});
        map.put(8, new String []{"team", "команда"});
        map.put(9, new String []{"racket", "ракетка"});
        map.put(10, new String []{"to win", "выиграть"});
        map.put(11, new String []{"medal", "медаль"});
        map.put(12, new String []{"referee", "судья"});
        map.put(13, new String []{"net", "сетка"});
        map.put(14, new String []{"defender", "защитник"});
        map.put(15, new String []{"defeat", "поражение"});
        map.put(16, new String []{"spectator", "зритель"});
        map.put(17, new String []{"field", "поле"});
        map.put(18, new String []{"whistle", "свисток"});
        map.put(19, new String []{"to train", "тренироваться"});
        map.put(20, new String []{"pass", "подача"});
        return map;
    }

    public static Map<Integer, String[]>techno() {
        Map<Integer, String[]> map = new HashMap<Integer, String[]>();
        map.put(0, new String []{"letter", "письмо"});
        map.put(1, new String []{"to type", "печатать"});
        map.put(2, new String []{"to send", "отправлять"});
        map.put(3, new String []{"to get", "получать"});
        map.put(4, new String []{"message", "сообщение"});
        map.put(5, new String []{"website", "сайт"});
        map.put(6, new String []{"to search", "искать"});
        map.put(7, new String []{"to find", "находить"});
        map.put(8, new String []{"source", "источник"});
        map.put(9, new String []{"link", "ссылка"});
        map.put(10, new String []{"user", "пользователь"});
        map.put(11, new String []{"cognitive", "познавательный"});
        map.put(12, new String []{"to download", "скачать"});
        map.put(13, new String []{"editor", "редактор"});
        map.put(14, new String []{"advertising", "реклама"});
        map.put(15, new String []{"brief article", "заметка"});
        map.put(16, new String []{"title", "заголовок"});
        map.put(17, new String []{"pictures", "картинки"});
        map.put(18, new String []{"login", "регистрация"});
        map.put(19, new String []{"article", "статья"});
        map.put(20, new String []{"information", "информация"});
        return map;
    }

    public static Map<Integer, String[]> eco() {
        Map<Integer, String[]> map = new HashMap<Integer, String[]>();
        map.put(0, new String []{"world", "мир"});
        map.put(1, new String []{"revolution", "революция"});
        map.put(2, new String []{"rubbish", "мусор"});
        map.put(3, new String []{"pollution", "загрязнение"});
        map.put(4, new String []{"rainforest", "тропический лес"});
        map.put(5, new String []{"clean", "чистый"});
        map.put(6, new String []{"trees", "деревья"});
        map.put(7, new String []{"ecology", "экология"});
        map.put(8, new String []{"animal world", "животный мир"});
        map.put(9, new String []{"flora", "растительный мир"});
        map.put(10, new String []{"oxygen", "кислород"});
        map.put(11, new String []{"dump", "свалка"});
        map.put(12, new String []{"earthquake", "землетрясение"});
        map.put(13, new String []{"flooding", "наводнение"});
        map.put(14, new String []{"hurricane", "ураган"});
        map.put(15, new String []{"pond", "пруд"});
        map.put(16, new String []{"wastes", "отходы"});
        map.put(17, new String []{"air", "воздух"});
        map.put(18, new String []{"earth", "земля"});
        map.put(19, new String []{"to clean up", "убирать"});
        return map;
    }
}
