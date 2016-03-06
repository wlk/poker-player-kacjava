package org.leanpoker.player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.leanpoker.player.dto.HoldCard;

public enum Variant {
    ROYAL,
    STRAIGHT_FLUSH,
    FOUR{
        @Override
        boolean match(List<HoldCard> cards) {
        	 return false;
        }
    },
    FULL{
        @Override
        boolean match(List<HoldCard> cards) {
        	 List<List<String>> pairsAndThrees = cards.stream()
                     .map(HoldCard::getRank)
                     .collect(Collectors.groupingBy(String::toString))
                     .values().stream()
                     .filter(list -> list.size()>=2).collect(Collectors.toList());
        	 return pairsAndThrees.size()>=2 && pairsAndThrees.stream().filter(list -> list.size()>=3).findFirst().isPresent();
        }
    },
    COLOR,
    STRAIGHT{
        @Override
        boolean match(List<HoldCard> cards) {
        	 return false;
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
