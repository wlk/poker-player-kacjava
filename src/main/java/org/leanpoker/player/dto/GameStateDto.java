package org.leanpoker.player.dto;

import com.google.gson.annotations.SerializedName;

public class GameStateDto {

    @SerializedName("minimum_raise")
    private int minimumRaise;

    public int getMinimumRaise() {
        return minimumRaise;
    }
    public void setMinimumRaise(int minimumRaise) {
        this.minimumRaise = minimumRaise;
    }
}
