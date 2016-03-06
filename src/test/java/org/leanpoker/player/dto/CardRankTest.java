package org.leanpoker.player.dto;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardRankTest {

    @Test
    public void testConvert() {

        // given
        final String value = "2";

        // when
        CardRank result = CardRank.findByValue(value);

        // then
        Assertions.assertThat(result).isEqualTo(CardRank.CARD_2);
    }

}