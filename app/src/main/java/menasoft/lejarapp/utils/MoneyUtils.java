package menasoft.lejarapp.utils;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by Rmena on 10/30/2015.
 */
public class MoneyUtils {

    public static String toCurrency(double d){
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-DO"));
        nf.setMaximumFractionDigits(2);
        return nf.format(d);
    }
}
