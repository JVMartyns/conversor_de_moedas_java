package conversor_de_moedas_java;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private final QuotationApi quotationApi = new QuotationApi();

    Map<String, String> currencies = new HashMap<>();

    public CurrencyConverter() {
        currencies.put("Real", "BRL");
        currencies.put("Dollar", "USD");
        currencies.put("Euro", "EUR");
        currencies.put("Libras Esterlinas", "GBP");
        currencies.put("Peso Argentino", "ARS");
        currencies.put("Peso Chileno", "CLP");
    }

    public Double convert(String from, String to, Double value) throws Exception {
        Double quotation = quotationApi.getQuotation(currencies.get(from), currencies.get(to));
        return quotation * value;
    }
}
