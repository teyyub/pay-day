package com.payday.bank.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;



@Data
public class Notification {


	private Integer id;

	private String accountId;

	private Date completionDate;

	private Integer orderId;

	private String message;

}
