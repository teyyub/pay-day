package com.payday.bank.service;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payday.bank.domain.*;
import com.payday.bank.repository.OrderRepository;
import com.payday.bank.repository.SellRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Manages a portfolio of holdings of stock/shares.
 *
 * @author David Ferreira Pinto
 */
@Service
@RefreshScope
public class PortfolioService {
    private static final Logger logger = LoggerFactory.getLogger(PortfolioService.class);

    /**
     * The order repository to store Order objects.
     */
    @Autowired
    OrderRepository repository;

    @Autowired
    SellRepository sellRepository;





    /**
     * The service than handles the calls to get quotes.
     */
    @Autowired
    QuoteRemoteCallService quoteService;

    @Autowired
    public RestTemplate restTemplate;


    // @Value("${pivotal.quotesService.name}")
    // protected String quotesService;

//	@Value("${accountsService}")
//	protected String accountsService;

    /**
     * Retrieves the portfolio for the given accountId.
     *
     * @param accountId The account id to retrieve for.
     * @return The portfolio.
     */
    public Portfolio getPortfolio(String accountId) {
        /*
         * Retrieve all orders for accounts id and build portfolio. - for each
         * order create holding. - for each holding find current price.
         */

        logger.debug("Getting portfolio for accountId: " + accountId);
        List<Order> orders = repository.findByAccountId(accountId);
        System.out.println("??????????????????? "+orders);
        Portfolio folio = new Portfolio();
        orders.forEach(order -> {
            Holding holding = new Holding();
            holding.setSymbol(order.getSymbol());
            // TODO: 20.01.2022

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//            ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8890/stocks/?query="+order.getSymbol(),
//                    String.class);
//            List<Quote> quotes = new ArrayList<>();
//            Quote quote=new Quote();
//
//            try {
//                System.out.println("responseEntity.getBody()="+responseEntity.getBody());
//                quote = objectMapper.readValue(responseEntity.getBody(),  Quote.class);
//                System.out.println("---------------");
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }

            ResponseEntity<Quote> responseEntity = restTemplate.getForEntity("http://localhost:8890/stocks/?query="+order.getSymbol(),
                   Quote.class);

            Quote quote=responseEntity.getBody();
            System.out.println("quote.getLastPrice()============="+quote.getLastPrice());
            holding.setCurrentValue(quote.getLastPrice());
            holding.setPurchaseValue(order.getPrice());
            folio.getHoldings().put(order.getSymbol(),holding);
        });
        folio.setAccountId(accountId);
        return folio;
    }

    /**
     * Builds a portfolio object with the list of orders.
     *
     * @param portfolio the portfolio object to build.
     * @param orders    the list of orders.
     * @return the portfolio object
     */
    private Portfolio createPortfolio(Portfolio portfolio, List<Order> orders) {
        // TODO: change to forEach() and maybe in parallel?
        Set<String> symbols = new HashSet<>();
        Holding holding = null;
        System.out.println("1111 " +portfolio.getHoldings());
        System.out.println("orders.size()="+orders.size());
        for (Order order : orders) {
            holding = portfolio.getHolding(order.getSymbol());
            System.out.println("holding="+holding);
            if (holding == null) {
                holding = new Holding();
                holding.setSymbol(order.getSymbol());
                portfolio.addHolding(holding);
                symbols.add(order.getSymbol());
            }
            holding.addOrder(order);
        }
        System.out.println("symbols="+symbols);
        List<Quote> quotes = quoteService.getQuotes(symbols);

        for (Quote quote : quotes) {
            portfolio.getHolding(quote.getSymbol()).setCurrentValue(quote.getLastPrice());
        }
        portfolio.refreshTotalValue();
        logger.debug("Portfolio: " + portfolio);
        return portfolio;
    }

    /**
     * Add an order to the repository and modify account balance.
     *
     * @param order the order to add.
     * @return the saved order.
     */
    @Transactional
    public ResponseEntity<?> addOrder(Order order) {
        Notification entity = new Notification();
        logger.debug("Adding order: " + order);
        if (order.getOrderFee() == null) {
            order.setOrderFee(Order.DEFAULT_ORDER_FEE);
            logger.debug("Adding Fee to order: " + order);
        }
        if (order.getOrderType() == OrderType.BUY) {

            double amount = order.getQuantity()
                    * order.getPrice().doubleValue()
                    + order.getOrderFee().doubleValue();
////			int amount = 1;
//			String str ="http://localhost:8080/accounts/{userid}/decreaseBalance/{amount}";
////			String str ="http://localhost:8080/accounts/"+order.getAccountId()+"/decreaseBalance/"+amount;
//
////			String str ="http://localhost:8080/accounts/meryem/decreaseBalance/1";
//			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(str);

//			System.out.println(str);
            try {
                ResponseEntity<Double> result = restTemplate.getForEntity("http://localhost:8080/accounts/{userid}/decreaseBalance/{amount}",
                        Double.class,
                        order.getAccountId(), amount
                );

//			ResponseEntity<Double> result = restTemplate.exchange("http://localhost:8080/accounts/"+order.getAccountId()+"/decreaseBalance/"+amount,
//					HttpMethod.GET,null,
//					Double.class
//			);

//			Double result = restTemplate.getForObject("http://localhost:8080/accounts/{userid}/decreaseBalance/{amount}",Double.class,
//					order.getAccountId(), amount);

                System.out.println("result ?????????? " + result);

                if (result.getStatusCode() == HttpStatus.CREATED) {
				logger.info(String
						.format("Account funds updated successfully for account: %s and new funds are: %s",
								order.getAccountId(), result.getBody()));




                    order.setInitialQuantity(order.getQuantity());
                    order.setCompletionDate(new Date());
                    order.setOrderId(null);
                    Order db = repository.save(order);

                    notificationEntityConvert(db, entity);
                    Integer id=    restTemplate.postForObject("http://localhost:8082/notification/", entity,Integer.class);
                    System.out.println("id="+id);

                    return ResponseEntity.ok(db);
                } else {
                    // TODO: throw exception - not enough funds!
                    // SK - Whats the expected behaviour?
                    logger.warn("PortfolioService:addOrder - decresing balance HTTP not ok: ");
                    return ResponseEntity.ok("not enough");
                }
            } catch (Exception ex) {
                System.out.println(ex);
                return ResponseEntity.ok(order);
            }
        } else {
            double amount = order.getQuantity()
                    * order.getPrice().doubleValue()
                    - order.getOrderFee().doubleValue();
            ResponseEntity<Double> result = restTemplate.getForEntity("http://localhost:8080/accounts/{userid}/increaseBalance/{amount}",
                    Double.class, order.getAccountId(), amount);
            if (result.getStatusCode() == HttpStatus.OK) {
                logger.info(String
                        .format("Account funds updated successfully for account: %s and new funds are: %s",
                                order.getAccountId(), result.getBody()));


                Order currentOrder = repository.findByOrderId(order.getOrderId());

                System.out.println(currentOrder);

                int quantity =currentOrder.getQuantity() - order.getQuantity() ;
                currentOrder.setQuantity(quantity);
                repository.save(currentOrder);


                Sell sell = new Sell();
                sell.setOrderId(currentOrder.getOrderId());
                sell.setAccountId(currentOrder.getAccountId());
                sell.setQuantity(order.getQuantity());
                sell.setBuyPrice(currentOrder.getPrice());
                sell.setSymbol(order.getSymbol());
                sell.setPrice(order.getPrice());
                sell.setCompletionDate(new Date());
                sellRepository.save(sell);

                notificationEntityConvert(order, entity);
                Integer id=    restTemplate.postForObject("http://localhost:8082/notification/", entity,Integer.class);
                System.out.println("id="+id);
                return ResponseEntity.ok(currentOrder);
            } else {
                // TODO: throw exception - negative value???
                logger.warn("PortfolioService:addOrder - increasing balance HTTP not ok: ");
                return ResponseEntity.ok(order);
            }
        }
    }

    private void notificationEntityConvert(Order order, Notification entity) {
        entity.setMessage("Transaction successfull");
        entity.setCompletionDate(new Date());
        entity.setAccountId(order.getAccountId());
        entity.setOrderId(order.getOrderId());
    }


}
