package utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Conversao {
    public static Date stringParaData(String data){

        Date dataFormatada = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataFormatada =  new java.sql.Date (sdf.parse(data).getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataFormatada;
    }

    public static String convInterDatas(Date data) { // converte EUA -> BR
        String dataFormatada = null;

        SimpleDateFormat padraoAmericano = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat padraoBR = new SimpleDateFormat("dd/MM/yyyy");

        try {
            dataFormatada = padraoBR.format(padraoAmericano.parse(padraoAmericano.format(data)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataFormatada;
    }


}
