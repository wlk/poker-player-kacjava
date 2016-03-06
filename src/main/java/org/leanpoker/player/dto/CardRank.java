package org.leanpoker.player.dto;

import java.util.Arrays;

public enum CardRank {
    CARD_2("2"),
    CARD_3("3"),
    CARD_4("4"),
    CARD_5("5"),
    CARD_6("6"),
    CARD_7("7"),
    CARD_8("8"),
    CARD_9("9"),
    CARD_10("10"),
    CARD_J("J"),
    CARD_Q("Q"),
    CARD_K("K"),
    CARD_A("A")
    ;

    private final String value;

    private CardRank(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CardRank findByValue(String value) {
        return Arrays.stream(CardRank.values())
                .filter(en -> en.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
