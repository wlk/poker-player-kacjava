package org.leanpoker.player;

import org.leanpoker.player.dto.GameStateDto;

import java.util.Optional;

/**
 * @author Paweł Bilewicz
 */
public interface Strategy {

	int run(Optional<GameStateDto> gameStateDto);
}
