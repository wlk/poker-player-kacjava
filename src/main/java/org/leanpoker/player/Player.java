package org.leanpoker.player;

import java.util.Optional;

import org.leanpoker.player.dto.GameStateDto;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class Player {

	static final String VERSION = "KacJava-s" + 3;

    private static Gson gson = new Gson();

	public static int betRequest(JsonElement request) {

        Strategy strategy = new ExceptionCatcherStrategy(new Strategy3());

        Optional<GameStateDto> dtoOption = parseJson(request);
        return dtoOption
//                .filter(game -> game.getMinimumRaise()>0)
                .map(strategy::run)
                .orElse(3);
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
