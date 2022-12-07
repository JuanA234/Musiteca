package musiteca;

import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class Archivo {

    //Metodo para mostrar una ventana que permita elegir un archivo mediante exploracion
    public static String elegirArchivo() {
        JFileChooser fc = new JFileChooser();

        if (fc.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            return f.getAbsolutePath();
        } else {
            return "";
        }
    }

    //Metodo que devuelve la ruta de la carpeta de un archivo
    public static String obtenerCarpeta(String rutaCompleta) {
        File f = new File(rutaCompleta);

        return f.getParent();
    }

    // Método estático para abrir archivos planos
    public static BufferedReader abrirArchivo(String nombreArchivo) {
        File f = new File(nombreArchivo);
        // Existe el archivo?
        if (f.exists()) {
            /*
             * captura de error estructurada. Intenta realizar la instrucción de
             * apertura del archivo. Es susceptible de no poder realizarse
             */
            try {
                /*
                 * Apertura del archivo plano La clase BufferedReader permite
                 * manipular secuencia de caracteres. La clase File ofrece
                 * funcionalidad para operar con archivos
                 */
                FileReader fr = new FileReader(f);
                return new BufferedReader(fr);
            } catch (IOException e) {
                /*
                 * Sucedió un error que se captura mediante la clase IOException
                 * encargada de manipular errores de entrada y salida
                 */
                return null;
            }
        } else {
            return null;
        }
    }//abrirArchivo

    // Método estático para guardar archivos planos dado un conjunto de líneas
    public static boolean guardarArchivo(String nombreArchivo, String[] lineas) {
        if (lineas != null) {
            /*
             * captura de error estructurada. Intenta realizar la instrucción de
             * escritura del archivo. Es susceptible de no poder realizarse
             */
            try {
                //Abrir el archivo para escritura
                BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo));
                for (int i = 0; i < lineas.length; i++) {
                    //Guardar cada linea
                    bw.write(lineas[i]);
                    bw.newLine();
                }
                //Cerrar el archivo
                bw.close();
                return true;
            } catch (IOException e) {
                /*
                 * Sucedió un error que se captura mediante la clase IOException
                 * encargada de manipular errores de entrada y salida
                 */
                return false;
            }
        } else {
            return false;
        }
    }

    // Método estático para cargar en memoria un archivo XML
    public static Document abrirDocumentoXML(String nombreArchivo) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(nombreArchivo);
        } catch (ParserConfigurationException pce) {
            return null;
        } catch (SAXException se) {
            return null;
        } catch (IOException ioe) {
            
            System.out.println(ioe.toString());
            
            return null;
        }
    }

        public static void mostrarImagen(JPanel pnl, String nombreArchivo) {
        try {
            //Limpiar el panel
            pnl.removeAll();
            //Definir el archivo de la imagen
            File f = new File(nombreArchivo);
            //Verificar si existe el archivo
            if (f.exists()) {
                //Leer el archivo
                BufferedImage bimg = ImageIO.read(f);
                //Cargar la imagen en un objeto JLABEL
                ImageIcon icon = new ImageIcon(bimg);
                JLabel lbl = new JLabel();
                lbl.setIcon(icon);
                //Definir un panel de desplazamiento
                JScrollPane jsp = new JScrollPane(lbl);
                //Ubicarlo en el panel
                jsp.setBounds(0, 0, pnl.getWidth(), pnl.getHeight());
                //Agregarlo al panel
                pnl.add(jsp);
                pnl.revalidate();
            }
            pnl.repaint();

        } catch (Exception ex) {
        }
    }
}
