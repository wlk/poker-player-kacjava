package org.leanpoker.player;

import org.junit.Test;
import org.leanpoker.player.dto.HoldCard;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.leanpoker.player.Variant.*;

public class VariantTest {

    @Test
    public void shouldRecognizePair() throws Exception {
        List<HoldCard> cards = cards("5", "6", "5");
        Variant variant = Variant.regognize(cards);
        assertThat(variant).isEqualTo(PAIR);
    }

    @Test
    public void shouldRecognizeNone() throws Exception {
        List<HoldCard> cards = cards("5", "2", "3");
        Variant variant = Variant.regognize(cards);
        assertThat(variant).isEqualTo(NONE);
    }

    @Test
    public void shouldRecognizeThree() throws Exception {
        List<HoldCard> cards = cards("5", "5", "5", "2");
        Variant variant = Variant.regognize(cards);
        assertThat(variant).isEqualTo(THREE);
    }

    @Test
    public void shouldRecognizeTwo() throws Exception {
        List<HoldCard> cards = cards("5", "5", "2", "2");
        Variant variant = Variant.regognize(cards);
        assertThat(variant).isEqualTo(TWO);
    }

    @Test
    public void shouldRecognizeStraight() throws Exception {
        List<HoldCard> cards = cards("8", "9", "Q", "J", "J", "10");
        Variant variant = Variant.regognize(cards);
        assertThat(variant).isEqualTo(STRAIGHT);
    }

    @Test
    public void shouldRecognizeColor() throws Exception {
        List<HoldCard> cards = cardsColors("clubs", "clubs", "other", "clubs", "clubs", "clubs");
        Variant variant = Variant.regognize(cards);
        assertThat(variant).isEqualTo(COLOR);
    }

    private List<HoldCard> cards(String ... ranks) {
        return Arrays.stream(ranks)
                .map(rank -> new HoldCard(rank, "clubs"))
                .collect(Collectors.toList());
    }

    private List<HoldCard> cardsColors(String ... colors) {
        return Arrays.stream(colors)
                .map(color -> new HoldCard("2", color))
                .collect(Collectors.toList());
    }
}