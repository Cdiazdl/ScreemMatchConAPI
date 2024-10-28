package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Titulo;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in); //Solicita ingresar un valor
        System.out.println("Escriba el nombre de la Pelicula"); // Pregunta
        var busqueda = lectura.nextLine(); //Palabra digitada por cliente

        String direccion = "http://www.omdbapi.com/?t="+busqueda+"&apikey=6a5538fc";  //Se personaliza la forma de busqueda junto a la API

        HttpClient client = HttpClient.newHttpClient(); //Comunicacion cliente Servidor
        HttpRequest request = HttpRequest.newBuilder() //Que se va a pedir al Servidor
                .uri(URI.create(direccion)) //Se integra URL de API
                .build();
        HttpResponse<String> response =client  //Llama a cliente q va a pedir algo
                .send(request, HttpResponse.BodyHandlers.ofString()); //Envia la consulta

        String json = response.body();;
        System.out.println(json); //Imprime el cuerpo de la API
        Gson gson = new Gson();
        Titulo miTitulo = gson.fromJson(json, Titulo.class);
        System.out.println("Titulo: "+miTitulo.getNombre());


    }
}
