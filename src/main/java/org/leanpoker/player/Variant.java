package org.leanpoker.player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.codepoetics.protonpack.StreamUtils;
import org.leanpoker.player.dto.CardRank;
import org.leanpoker.player.dto.HoldCard;

public enum Variant {
    ROYAL,
    STRAIGHT_FLUSH,
    FOUR{
        @Override
        boolean match(List<HoldCard> cards) {
            return cards.stream()
                    .map(HoldCard::getRank)
                    .collect(Collectors.groupingBy(String::toString))
                    .values().stream()
                    .filter(list -> list.size()>=4)
                    .findFirst()
                    .isPresent();
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
    COLOR{
        @Override
        boolean match(List<HoldCard> cards) {
            return cards.stream()
                    .map(HoldCard::getSuit)
                    .collect(Collectors.groupingBy(String::toString))
                    .values().stream()
                    .filter(list -> list.size()>=5)
                    .findFirst()
                    .isPresent();
        }
    },
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
            Stream<List<Integer>> windowed = StreamUtils.windowed(numberedCards.stream(), 5);
            return windowed.anyMatch(this::isStreight);

        }

        private boolean isStreight(List<Integer> numberedCards) {
            Integer first = numberedCards.iterator().next();
            return IntStream.range(first, first+5)
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
                .filter(list -> list.size()>=3)
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
                .filter(list -> list.size()>=2)
                .count() >= 2;
    }
},
    PAIR {
        @Override
        boolean match(List<HoldCard> cards) {
            return cards.stream()
                    .map(HoldCard::getRank)
                    .collect(Collectors.groupingBy(String::toString))
                    .values().stream()
                    .filter(list -> list.size()>=2)
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
