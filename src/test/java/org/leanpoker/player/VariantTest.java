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
        List<HoldCard> cards = cards("1", "0", "1");
        Variant variant = Variant.regognize(cards);
        assertThat(variant).isEqualTo(PAIR);
    }

    @Test
    public void shouldRecognizeNone() throws Exception {
        List<HoldCard> cards = cards("1", "2", "3");
        Variant variant = Variant.regognize(cards);
        assertThat(variant).isEqualTo(NONE);
    }

    @Test
    public void shouldRecognizeThree() throws Exception {
        List<HoldCard> cards = cards("1", "1", "1", "2");
        Variant variant = Variant.regognize(cards);
        assertThat(variant).isEqualTo(THREE);
    }

    @Test
    public void shouldRecognizeTwo() throws Exception {
        List<HoldCard> cards = cards("1", "1", "2", "2");
        Variant variant = Variant.regognize(cards);
        assertThat(variant).isEqualTo(TWO);
    }

    private List<HoldCard> cards(String ... ranks) {
        return Arrays.stream(ranks)
                .map(HoldCard::new)
                .collect(Collectors.toList());
    }
}