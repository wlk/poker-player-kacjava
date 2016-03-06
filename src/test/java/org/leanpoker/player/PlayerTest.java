package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.leanpoker.player.dto.GameStateDto;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;


public class PlayerTest {

    @Test
    public void testBetRequest() throws Exception {

        JsonElement jsonElement = new JsonParser().parse("{\"key1\": \"value1\", \"key2\": \"value2\"}");

        int betRequest = Player.betRequest(jsonElement);

        assertThat(betRequest).isEqualTo(300);

    }

    @Test
    public void testMinimalRaiseJson() throws Exception {
        GameStateDto game = readGameStateFromSampleJson();

        assertThat(game.getMinimumRaise()).isEqualTo(240);
    }

    @Test
    public void testParsePlayerAndPlayerCardsJson() throws Exception {
        GameStateDto game = readGameStateFromSampleJson();

        assertThat(game.getPlayer()).isPresent();
        assertThat(game.getPlayerCards()).hasSize(2);
        assertThat(game.getPlayerCards().get(0).getRank()).isEqualTo("6");
        assertThat(game.getPlayerCards().get(0).getSuit()).isEqualTo("hearts");
    }

    private GameStateDto readGameStateFromSampleJson() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("sample.json");
        InputStreamReader reader = new InputStreamReader(stream);
        JsonElement jsonElement = new JsonParser().parse(reader);
        return new Gson().fromJson(jsonElement, GameStateDto.class);
    }

}
