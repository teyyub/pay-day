package bank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Security{
		@JsonProperty("CIK")
		private String CIK=null;
		
		@JsonProperty("CUSIP")
		private String CUSIP=null;
		
		@JsonProperty("Symbol")
		private String Symbol=null;
		
		@JsonProperty("ISIN")
		private String ISIN=null;
		
		@JsonProperty("Valoren")
		private String valoren=null;
		
		@JsonProperty("Name")
		private String name=null;
		
		@JsonProperty("Market")
		private String market=null;
		
		@JsonProperty("MarketIdentificationCode")
		private String marketIdentificationCode=null;
		
		@JsonProperty("MostLiquidExchange")
		private String mostLiquidExchange=null;
		
		@JsonProperty("CategoryOrIndustry")
		private String categoryOrIndustry=null;

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Security [CIK=");
			builder.append(CIK);
			builder.append(", CUSIP=");
			builder.append(CUSIP);
			builder.append(", Symbol=");
			builder.append(Symbol);
			builder.append(", ISIN=");
			builder.append(ISIN);
			builder.append(", valoren=");
			builder.append(valoren);
			builder.append(", name=");
			builder.append(name);
			builder.append(", market=");
			builder.append(market);
			builder.append(", marketIdentificationCode=");
			builder.append(marketIdentificationCode);
			builder.append(", mostLiquidExchange=");
			builder.append(mostLiquidExchange);
			builder.append(", categoryOrIndustry=");
			builder.append(categoryOrIndustry);
			builder.append("]");
			return builder.toString();
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */

		/**
		 * @return the cIK
		 */
		public String getCIK() {
			return CIK;
		}

		/**
		 * @param cIK the cIK to set
		 */
		public void setCIK(String cIK) {
			CIK = cIK;
		}

		/**
		 * @return the cUSIP
		 */
		public String getCUSIP() {
			return CUSIP;
		}

		/**
		 * @param cUSIP the cUSIP to set
		 */
		public void setCUSIP(String cUSIP) {
			CUSIP = cUSIP;
		}

		/**
		 * @return the symbol
		 */
		public String getSymbol() {
			return Symbol;
		}

		/**
		 * @param symbol the symbol to set
		 */
		public void setSymbol(String symbol) {
			Symbol = symbol;
		}

		/**
		 * @return the iSIN
		 */
		public String getISIN() {
			return ISIN;
		}

		/**
		 * @param iSIN the iSIN to set
		 */
		public void setISIN(String iSIN) {
			ISIN = iSIN;
		}

		/**
		 * @return the valoren
		 */
		public String getValoren() {
			return valoren;
		}

		/**
		 * @param valoren the valoren to set
		 */
		public void setValoren(String valoren) {
			this.valoren = valoren;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the market
		 */
		public String getMarket() {
			return market;
		}

		/**
		 * @param market the market to set
		 */
		public void setMarket(String market) {
			this.market = market;
		}

		/**
		 * @return the marketIdentificationCode
		 */
		public String getMarketIdentificationCode() {
			return marketIdentificationCode;
		}

		/**
		 * @param marketIdentificationCode the marketIdentificationCode to set
		 */
		public void setMarketIdentificationCode(String marketIdentificationCode) {
			this.marketIdentificationCode = marketIdentificationCode;
		}

		/**
		 * @return the mostLiquidExchange
		 */
		public String getMostLiquidExchange() {
			return mostLiquidExchange;
		}

		/**
		 * @param mostLiquidExchange the mostLiquidExchange to set
		 */
		public void setMostLiquidExchange(String mostLiquidExchange) {
			this.mostLiquidExchange = mostLiquidExchange;
		}

		/**
		 * @return the categoryOrIndustry
		 */
		public String getCategoryOrIndustry() {
			return categoryOrIndustry;
		}

		/**
		 * @param categoryOrIndustry the categoryOrIndustry to set
		 */
		public void setCategoryOrIndustry(String categoryOrIndustry) {
			this.categoryOrIndustry = categoryOrIndustry;
		}
	}