package org.leanpoker.player.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerDto {

    private Integer id;
    private String name;
    private Integer stack;
    private String status;
    private Integer bet;
    private String version;
    @SerializedName("hole_cards")
    private List<HoldCard> holeCards;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getStack() {
        return stack;
    }
    public void setStack(Integer stack) {
        this.stack = stack;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBet() {
        return bet;
    }
    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    public List<HoldCard> getHoleCards() {
        return holeCards;
    }
    public void setHoleCards(List<HoldCard> holeCards) {
        this.holeCards = holeCards;
    }
}
