package com.payday.bank.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entity object representing an Order.
 * 
 * @author anar
 *
 */
@Entity
@Table(name = "ORDERS")
@Data
public class Order {
	public static BigDecimal DEFAULT_ORDER_FEE = new BigDecimal(10.50);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderid")
	private Integer orderId;

	@Column(name = "accountid")
	@NotNull
	private String accountId;

	@Column(name = "symbol", length = 10)
	@NotNull
	private String symbol;

	@Column(name = "orderfee", precision = 14, scale = 2)
	private BigDecimal orderFee;

	@Column(name = "completiondate")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "LL")
	private Date completionDate;

	@Column(name = "ordertype")
	@NotNull
	@Enumerated
	private OrderType orderType;

	@Column(name = "price", precision = 14, scale = 2)
	@NotNull
	private BigDecimal price;

	@Column(name = "quantity")
	@NotNull
	private Integer quantity;

	@Column(name = "initial_quantity")
	@NotNull
	private Integer initialQuantity;


}
