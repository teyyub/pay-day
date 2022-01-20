package com.example.notification.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entity object representing an Order.
 * 
 * @author David Ferreira Pinto
 *
 */
@Entity
@Table(name = "notification")
@Data
public class Notification {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "accountid")
	@NotNull
	private String accountId;

//	@Column(name = "symbol", length = 10)
////	@NotNull
//	private String symbol;

//	@Column(name = "buyPrice", precision = 14, scale = 2)
//	private BigDecimal buyPrice;

	@Column(name = "completiondate")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "LL")
	private Date completionDate;

//	@Column(name = "ordertype")
//	@NotNull
//	@Enumerated
//	private OrderType orderType;

	@Column(name = "orderId")
//	@NotNull
	private BigDecimal orderId;


	private String message;

}
