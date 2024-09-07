package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "exchange_rate_param")
public class ExchangeRateParam {
    @EmbeddedId
    private ExchangeRateParamId id;

    @Column(name = "BUY_RATE")
    private Integer buyRate;

    @Column(name = "MID_RATE")
    private Integer midRate;

    @Column(name = "SELL_RATE")
    private Integer sellRate;

    public ExchangeRateParamId getId() {
        return id;
    }

    public void setId(ExchangeRateParamId id) {this.id = id;}

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
