/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.sistemaCajeroAutomatico.utils;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;

/**
 *
 * @author bruns
 */
public class EmailUtil {
    public static void enviarCorreo(String email, String nombre) throws IOException {
        
        String apiKey = System.getenv("SENDGRID_API_KEY");
        
        if (apiKey == null || apiKey.isEmpty()) {
            System.out.println("ERROR: No se encontró la variable de entorno SENDGRID_API_KEY");
            return;
        }
        
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        
        // Configura los detalles del correo
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody("{\n" +
                            "  \"personalizations\": [\n" +
                            "    {\n" +
                            "      \"to\": [\n" +
                            "        {\"email\": \"" + email + "\"}\n" +
                            "      ],\n" +
                            "      \"subject\": \"Consulta de Saldo exitoso\"\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"from\": {\n" +
                            "    \"email\": \"theraider0@gmail.com\"\n" +
                            "  },\n" +
                            "  \"content\": [\n" +
                            "    {\n" +
                            "      \"type\": \"text/plain\",\n" +
                            "      \"value\": \"Hola " + nombre + ",\\n\\nGracias por usar nuestro cajero automático. Tu operación fue exitosa.\\n\\n¡Saludos!\"\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}");
            Response response = sg.api(request);
            System.out.println("Correo enviado: " + response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println("Error al enviar el correo: " + ex.getMessage());
        }
    }
}
