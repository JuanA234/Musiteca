package musiteca;

import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.*;

public class Artista {

    private String nombre;
    private String tipo;
    private String pais;

    public Artista(String nombre, String tipo, String pais) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.pais = pais;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    public void mostarFoto(JPanel pnl){
        
        String nombreArchivo = rutaFotos + "\\"+ this.nombre +".jpg";   
        Archivo.mostrarImagen(pnl, nombreArchivo);
    }

    //**********Atributos y métodos estático
    public static String rutaFotos;

    public static Document dXML;

    public static List<Artista> artistas;
    
   public static EscuchadorEventos evento;

    public static void obtener() {

        artistas = new ArrayList<>();
        if (dXML != null) {
            //Obtener los nodos qie correspondan al tipo pedido
            NodeList n1Artistas = dXML.getElementsByTagName("Artista");
            //Recorrer los datos
            for (int i = 0; i < n1Artistas.getLength(); i++) {
                if (n1Artistas.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    //Obtener el nodo del artista
                    Element nodo = (Element) n1Artistas.item(i);
                    //Obtener el nodo del nombre
                    NodeList n1 = nodo.getElementsByTagName("Nombre");
                    String nombre = n1.item(0).getTextContent();
                    //Obtener el nodo del tipo
                    n1 = nodo.getElementsByTagName("Tipo");
                    String tipo = n1.item(0).getTextContent();
                    //Obtener el nodo del pais
                    n1 = nodo.getElementsByTagName("Pais");
                    String pais = n1.item(0).getTextContent();
                    
                    //Agregar el artista a la lista
                    artistas.add(new Artista(nombre, tipo, pais));

                }

            }
        }
    } //Obtener
    
    public static void Mostrar (JTable tbl, JPanel pnl) {
        
        String[] encabezados = new String[]{"Nombre", "Tipo", "Pais"};
        String[][] datos = new String[artistas.size()][3];
        for(int i = 0; i <artistas.size(); i++){
            //Obtener cada instancia de la lista
            Artista a = artistas.get(i);
            //Pasar los datos a la matrix del modelo de datos de la tabla
            datos [i][0] = a.getNombre();
            datos [i][1] = a.getTipo();
            datos [i][2] = a.getPais();

        }
        //Crear el modelo de datos para la tabla
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
          
        //Asignar el modelo de datos a la tabla
        tbl.setModel(dtm);
        
        //Asignar el escuchador de eventos
        evento = new EscuchadorEventos(pnl, tbl);
        tbl.getSelectionModel().addListSelectionListener(evento);
    } //Mostrar
}
