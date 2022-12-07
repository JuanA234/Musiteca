
package musiteca;

import javax.swing.*;
import javax.swing.event.*;


public class EscuchadorEventos implements ListSelectionListener{
    
    //Objeto para mostrar la foto del artista
    private JPanel pnl;
    //Objeto para mostrar las canciones del artista
    private JTable tbl;
    public EscuchadorEventos(JPanel pnl, JTable tbl){
        this.pnl = pnl;
        this.tbl = tbl;
    }
    
    public void valueChanged(ListSelectionEvent lse){
        //******Instrucciones cuando se selecciona un elemento de la lista
        //Obtener la primera fila seleccionada
        int fila = lse.getFirstIndex();
        //Obtner el artista seleccionado
        Artista a = Artista.artistas.get(fila);
        //Mostar la foto del artista
        a.mostarFoto(pnl);
        //Mostar las canciones
        Cancion.obtener(a);
        Cancion.mostrar(tbl);
    }
}
