package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.leanpoker.player.dto.GameStateDto;

import java.util.Optional;

public class Player {

	static final String VERSION = "KacJava-" + 6;

    private static Gson gson = new Gson();

	public static int betRequest(JsonElement request) {

        Strategy strategy = new ExceptionCatcherStrategy(new Strategy1());

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
