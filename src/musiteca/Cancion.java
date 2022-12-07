package musiteca;

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import org.w3c.dom.*;

public class Cancion {

    private Artista artista;
    private String titulo;
    private String duracion;
    private String año;
    private String genero;

    public Cancion(Artista artista, String titulo, String duracion, String año, String genero) {

        this.artista = artista;
        this.titulo = titulo;
        this.duracion = duracion;
        this.año = año;
        this.genero = genero;

    }

    public String getTitulo() {
        return titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getAño() {
        return año;
    }

    public String getGenero() {
        return genero;
    }

    public void Reproducir(){
        String nombreArchivo = rutaCanciones + "\\"+ this.artista.getNombre() + " - " + this.titulo +".mp3";   
        ReproductorAudio.reproducir(nombreArchivo);
    }
    //**********Atributos y métodos estático
    public static String rutaCanciones;

    public static List<Cancion> canciones;

    //Método para obtener la lista de canciones de un artista
    public static void obtener(Artista a) {

        canciones = new ArrayList<>();
        if (Artista.dXML != null) {
            //Obtener los nodos qie correspondan al tipo pedido
            NodeList n1Canciones = Artista.dXML.getElementsByTagName("Cancion");
            //Recorrer los datos
            for (int i = 0; i < n1Canciones.getLength(); i++) {
                if (n1Canciones.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    //Obtener el nodo padre

                    Element nodoPadre = (Element) n1Canciones.item(i).getParentNode();

                    if (nodoPadre != null) {
                        //Obtener el nombre del artista
                        NodeList n1 = nodoPadre.getElementsByTagName("Nombre");
                        String nombre = n1.item(0).getTextContent();
                        //Verificar que sea el artista buscado
                        if (a.getNombre().equals(nombre)) {
                            //Obtener el nodo de la cancion
                            Element nodo = (Element) n1Canciones.item(i);
                            //Obtener el nodo del titulo
                            n1 = nodo.getElementsByTagName("Titulo");
                            String titulo = n1.item(0).getTextContent();
                            //Obtener el nodo de la duracion
                            n1 = nodo.getElementsByTagName("Duracion");
                            String duracion = n1.item(0).getTextContent();
                            //Obtener el nodo del año
                            n1 = nodo.getElementsByTagName("Año");
                            String año = n1.item(0).getTextContent();

                            //Obtener el nodo del genero
                            n1 = nodo.getElementsByTagName("Genero");
                            String genero = n1.item(0).getTextContent();

                            //Agregar el artista a la lista
                            canciones.add(new Cancion(a, titulo, duracion, año, genero));
                        }
                    }
                }

            }
        }
    }//Obtener

    public static void mostrar(JTable tbl) {

        String[] encabezados = new String[]{"Titulo", "Duracion", "Año", "Género"};
        String[][] datos = new String[canciones.size()][4];
        for (int i = 0; i < canciones.size(); i++) {
            //Obtener cada instancia de la lista
            Cancion c = canciones.get(i);
            //Pasar los datos a la matrix del modelo de datos de la tabla
            datos[i][0] = c.getTitulo();
            datos[i][1] = c.getDuracion();
            datos[i][2] = c.getAño();
            datos[i][3] = c.getGenero();

        }
        //Crear el modelo de datos para la tabla
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);

        //Asignar el modelo de datos a la tabla
        tbl.setModel(dtm);

        //Quitar el escuchador de eventos
        tbl.getSelectionModel().removeListSelectionListener(Artista.evento);

    } //Mostrar
}
