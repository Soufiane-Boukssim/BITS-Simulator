package com.simulator.entities.pos;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pos_exchange_rate_param")
public class PosExchangeRateParam {

    @EmbeddedId
    private PosExchangeRateParamId id;

    @Column(name = "BUY_RATE")
    private Integer buyRate;

    @Column(name = "MID_RATE")
    private Integer midRate;

    @Column(name = "SELL_RATE")
    private Integer sellRate;

    public PosExchangeRateParamId getId() {
        return id;
    }

    public void setId(PosExchangeRateParamId id) {this.id = id;}

    public Integer getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(Integer buyRate) {
        this.buyRate = buyRate;
    }

    public Integer getMidRate() {
        return midRate;
    }

    public void setMidRate(Integer midRate) {
        this.midRate = midRate;
    }

    public Integer getSellRate() {
        return sellRate;
    }

    public void setSellRate(Integer sellRate) {
        this.sellRate = sellRate;
    }

}
