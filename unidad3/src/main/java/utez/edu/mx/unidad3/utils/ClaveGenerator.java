package utez.edu.mx.unidad3.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class ClaveGenerator {
    public static String generateCedeClave(Long id){
        //Generar un formato para fechas: el patron que ddMMyyyy, adicional a que le indicamos que la fecha va a estar en español-México
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy",new Locale("es-MX"));
        //Utilizamos el sdf para formatear un objeto de tipo Date
        String fecha = sdf.format(new Date());
        //Generamos un número random de 4 caracteres de longitud (%04d)
        String random = String.format("%04d", ThreadLocalRandom.current().nextInt(1,10000));

        return "C" + id + "-" + fecha + "-" + random;

    }

    public static void main(String[] args) {
        System.out.println(ClaveGenerator.generateCedeClave(1L));
    }

    public static String generateWarehouseClave(String cedeClave, Long idWarehouse){
        return null;
    }
}
