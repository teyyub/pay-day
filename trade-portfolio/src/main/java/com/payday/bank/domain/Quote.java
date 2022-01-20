package com.payday.bank.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a point in time price and information for a company's stock.
 * 
 * Used to communicate with the Quote service.
 * 
 * @author David Ferreira Pinto.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

	private Long id;

	private String name;

	private String symbol;

	private BigDecimal lastPrice;

	private BigDecimal change;

	private Float changePercent;

	private Date actionDate;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	public Float getChangePercent() {
		return changePercent;
	}

	public void setChangePercent(Float changePercent) {
		this.changePercent = changePercent;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
}
