package cat.iesesteveterradas.mp06.uf1.exercici;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObjectBuilder;


public class PR143GestioLlibreriaMain {
    public static void main(String[] args) throws IOException {
        File file = new File("data/pr14b/llibres_input.json");
        File llibres_output = new File("data/pr14b/llibres_output.json");

        //Lectura del fitxer
        ObjectMapper objectMapper = new ObjectMapper();
        List<Libro> libros = objectMapper.readValue(file, new TypeReference<List<Libro>>() {});

        for (Libro libro : libros) {
            System.out.println("ID: " + libro.getId());
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("Año: " + libro.getAnio());
            System.out.println();
        }

        //Modificació

        for (Libro libro : libros) {
            if (libro.getId() == 1 ){
                libro.setAnio(1995);
            }
        }
        System.out.println("Dades modificades amb èxit!" + "\n");

        
        //Afegir
        
        Libro  nuevoLibro = new Libro(4, "Històries de la ciutat", "Miquel Soler", 2022);  
        libros.add(nuevoLibro);
        System.out.println("Nou llibre afegit amb exit!" + "\n");  

        //Esborrar

        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId() == 2){
                libros.remove(i);
                System.out.println("Llibre amb id 2, eliminat!" + "\n");  
            }
        }
        
        //Guardar
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(llibres_output, libros);

        System.out.println("Dades guardades al fixer llibres_output.json");
    }    
}

    
class Libro {
    @JsonProperty("id")
    private int id;
    @JsonProperty("títol")
    private String titulo;
    @JsonProperty("autor")
    private String autor;
    @JsonProperty("any")
    private int anio;

    Libro(int id, String titulo, String autor, int anio) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }
    Libro() {}

    // Agrega getters y setters aquí
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
}