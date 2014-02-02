package com.sasrobotics.HUD;

import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import java.io.IOException;
import java.util.Enumeration;


public class ArduHUD {
	SerialPort serialPort;
        /** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { 
			"COM6", //for windows DriverStation
			"COM5", 
			"COM4", 
                        "/dev/ttyACM0", //for linux dev
                        "/dev/ttyACM1"
	};
        
	private OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;

	public void initialize() {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//find port defined in PORT_NAMES
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
                                        System.out.println("Port Found: "+portName);
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find port.");
			return;
		}
		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open stream
			output = serialPort.getOutputStream();
                        
                        System.out.println("Connection Established");
                        
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

        public void send(int b) {
            try {
                output.write(b);
                output.flush();
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
        
        public void left() {
            send(1);
        }
        public void right() {
            send(2);
        }
        public void both() {
            send(3);
        }
        public void off() {
            send(0);
        }
}