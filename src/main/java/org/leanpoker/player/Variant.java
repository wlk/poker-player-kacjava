package org.leanpoker.player;

import org.leanpoker.player.dto.CardRank;
import org.leanpoker.player.dto.HoldCard;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum Variant {
    ROYAL,
    STRAIGHT_FLUSH,
    FOUR,
    FULL,
    COLOR,
    STRAIGHT{
        @Override
        boolean match(List<HoldCard> cards) {
            List<Integer> numberedCards = cards.stream()
                    .map(HoldCard::getRank)
                    .map(CardRank::findByValue)
                    .map(CardRank::ordinal)
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList());
            if (numberedCards.isEmpty()){
                return false;
            }
            Integer first = numberedCards.iterator().next();
            return IntStream.rangeClosed(first, first+4)
                    .allMatch(numberedCards::contains);
        }
    },
    THREE {
    @Override
    boolean match(List<HoldCard> cards) {
        return cards.stream()
                .map(HoldCard::getRank)
                .collect(Collectors.groupingBy(String::toString))
                .values().stream()
                .filter(list -> list.size()>2)
                .findFirst()
                .isPresent();
    }
},
    TWO {
    @Override
    boolean match(List<HoldCard> cards) {
        return cards.stream()
                .map(HoldCard::getRank)
                .collect(Collectors.groupingBy(String::toString))
                .values().stream()
                .filter(list -> list.size()>1)
                .count() > 1;
    }
},
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

    boolean match(List<HoldCard> cards){
        return false;
    }

    public static Variant regognize(List<HoldCard> cards){
        return Arrays.stream(values())
                .filter(e -> e.match(cards))
                .findFirst()
                .orElse(NONE);
    }
}
