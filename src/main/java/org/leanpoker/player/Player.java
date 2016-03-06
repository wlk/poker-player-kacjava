package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.leanpoker.player.dto.GameStateDto;

import java.util.Optional;

public class Player {

	static final String VERSION = "KacJava-" + 1;

    private static Gson gson = new Gson();

	public static int betRequest(JsonElement request) {

        Optional<GameStateDto> dtoOption = parseJson(request);
        /*dtoOption.ifPresent(
                // let's play

        );*/


        return 1000;
	}

    private static Optional<GameStateDto> parseJson(JsonElement request) {
        try {
            return Optional.of(gson.fromJson(request, GameStateDto.class));
        } catch(Exception e) {
            return Optional.empty();
        }
    }

	public static void showdown(JsonElement game) {
	}
}
