package javaUsbConection;

import java.util.Scanner;
import com.fazecast.jSerialComm.SerialPort;
import java.util.ArrayList;

public class UsbMicrobit {
    private int indice;
    private String nombrePuerto;
    private String descripcion;
    private String portDescripcion;
    private String numero;

    // Constructor vacío
    public UsbMicrobit() {
    }

    // Getters y Setters
    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getNombrePuerto() {
        return nombrePuerto;
    }

    public void setNombrePuerto(String nombrePuerto) {
        this.nombrePuerto = nombrePuerto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPortDescripcion() {
        return portDescripcion;
    }

    public void setPortDescripcion(String portDescripcion) {
        this.portDescripcion = portDescripcion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    // Método para detectar puertos disponibles
    public static SerialPort[] detectaPuertos() {
        SerialPort[] ports = SerialPort.getCommPorts();
        if (ports.length == 0) {
            
            return null;
        }
        return ports;
    }

    // Método para obtener detalles de los puertos y almacenarlos en la lista
    public ArrayList<UsbMicrobit> obtengoDatosPuertos() {
        ArrayList<UsbMicrobit> portsList = new ArrayList<UsbMicrobit>();
        SerialPort[] puertos = detectaPuertos();
        
        if (puertos == null) {
            return portsList; // Lista vacía si no se encuentran puertos
        }

        int indice = 1;
        for (SerialPort puerto : puertos) {
            UsbMicrobit nuevoPuerto = new UsbMicrobit();
            nuevoPuerto.setIndice(indice);
            nuevoPuerto.setNombrePuerto(puerto.getSystemPortName());
            nuevoPuerto.setDescripcion(puerto.getDescriptivePortName());
            nuevoPuerto.setPortDescripcion(puerto.getPortDescription());
            nuevoPuerto.setNumero(puerto.getPortLocation());

            portsList.add(nuevoPuerto);
            indice++;
        }
        return portsList;
    }

    // Método para enviar un mensaje a un puerto específico
    public boolean enviarMensaje(String numeroPuerto, String mensaje) {
        SerialPort puertoSerial = SerialPort.getCommPort(numeroPuerto);
        puertoSerial.setBaudRate(115200);

        if (puertoSerial.openPort()) {
            puertoSerial.writeBytes(mensaje.getBytes(), mensaje.length());
             puertoSerial.closePort();
            return true;
        } else {
            System.out.println("No se pudo abrir el puerto " + numeroPuerto);
            return false;
        }
    }

    
}
