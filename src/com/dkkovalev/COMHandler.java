package com.dkkovalev;

import gnu.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

/**
 * Created by DKKovalev on 21.05.2016.
 */
public class COMHandler implements SerialPortEventListener {

    private SerialPort serialPort;

    private static final String PORT_NAMES[] = {
            "/dev/tty.usbserial-A9007UX1",
            "COM3",
    };

    private BufferedReader input;
    private OutputStream output;
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;

    public void initialize() {
        CommPortIdentifier commPortIdentifier = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currentIdentifier = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currentIdentifier.getName().equals(portName)) {
                    commPortIdentifier = currentIdentifier;
                    break;
                }
            }
        }

        if (commPortIdentifier == null) {
            System.out.println("Could not find COM port");
            return;
        }

        try {
            serialPort = (SerialPort) commPortIdentifier.open(this.getClass().getName(), TIME_OUT);
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (PortInUseException e) {
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine = input.readLine();
                System.out.println(inputLine);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
