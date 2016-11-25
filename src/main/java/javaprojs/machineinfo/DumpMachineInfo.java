package javaprojs.machineinfo;

import com.verhas.licensor.HardwareBinder;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by Ram Velury on 11/24/16.
 */
public class DumpMachineInfo
{
    private InetAddress ip;

    public static void main(String[] args)
    {
        try
        {
            DumpMachineInfo machineInfo = new DumpMachineInfo();
            machineInfo.getIpAddress();
            machineInfo.printIpAddress();
            machineInfo.printOSArchitecture();
            machineInfo.printNIInfo();
            machineInfo.printMachineUUID();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void getIpAddress()
            throws UnknownHostException
    {
        ip = InetAddress.getLocalHost();
    }

    private void printIpAddress()
            throws UnknownHostException
    {
        System.out.println("Host Name          : " + ip.getHostName());
        System.out.println("Current IP address : " + ip.getHostAddress());
        System.out.println("Local Address      : " + InetAddress.getLocalHost().getHostAddress());
        System.out.println("Loopback Address   : " + InetAddress.getLoopbackAddress().getHostAddress());
        System.out.println();
    }

    void printSystemProperties()
    {
        Properties properties = System.getProperties();

        Enumeration e = properties.propertyNames();

        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            System.out.println(key + " -- " + properties.getProperty(key));
        }
        System.out.println();
    }

    private void printOSArchitecture()
    {
        System.out.println("O/S Name           : " + System.getProperty("os.name"));
        System.out.println("O/S Version        : " + System.getProperty("os.version"));
        System.out.println("O/S Architecture   : " + System.getProperty("os.arch"));
        System.out.println();
    }

    private void printNIInfo()
            throws SocketException
    {
        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
        System.out.println("Current Interface : " + network.getName());
        System.out.println();

        final Enumeration<NetworkInterface> networkInterfaces = java.net.NetworkInterface
                .getNetworkInterfaces();

        while (networkInterfaces.hasMoreElements())
        {
            final NetworkInterface networkInterface = networkInterfaces.nextElement();
            byte[] mac = networkInterface.getHardwareAddress();

            System.out.print("MAC address (" + networkInterface.getName() + ") : ");

            StringBuilder sb = new StringBuilder();
            if (mac != null)
            {
                for (int i = 0; i < mac.length; i++)
                {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
            }
            else
            {
                sb.append("null");
            }
            System.out.println(sb.toString());
        }
        System.out.println();
    }

    private void printMachineUUID()
            throws UnsupportedEncodingException, SocketException, UnknownHostException
    {
        HardwareBinder hb = new HardwareBinder();
        System.out.println("Machine UUID       : " + hb.getMachineIdString());
    }
}
