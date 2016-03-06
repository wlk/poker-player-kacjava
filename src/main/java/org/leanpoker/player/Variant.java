package org.leanpoker.player;

import org.leanpoker.player.dto.HoldCard;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Variant {
//    ROYAL,
//    STRAIGHT_FLUSH,
//    FOUR,
//    FULL,
//    COLOR,
//    STAIGHT,
//    THREE,
//    TWO,
    PAIR {
        @Override
        boolean match(List<HoldCard> cards) {
            return cards.stream()
                    .map(HoldCard::getRank)
                    .collect(Collectors.groupingBy(String::toString))
                    .values().stream()
                    .filter(list -> list.size()>1)
                    .findFirst()
                    .isPresent();
        }
    },
    NONE{
        @Override
        boolean match(List<HoldCard> cards) {
            return true;
        }
    };

    abstract boolean match(List<HoldCard> cards);

    public static Variant regognize(List<HoldCard> cards){
        return Arrays.stream(values())
                .filter(e -> e.match(cards))
                .findFirst()
                .orElse(NONE);
    }
}
