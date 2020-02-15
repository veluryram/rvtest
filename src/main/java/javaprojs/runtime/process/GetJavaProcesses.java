package javaprojs.runtime.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ram on 12/15/2016.
 */
public class GetJavaProcesses {
    public static void main(String[] args) {
        try {
            /*
            MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
            Set<Integer> vmlist = new HashSet<>(local.activeVms());
            for (Integer id : vmlist) {
                MonitoredVm vm = local.getMonitoredVm(new VmIdentifier(String.format("//%d", id)));
                String processname = MonitoredVmUtil.mainClass(vm, true);
                System.out.printf("%d: %s%n", id, processname);
            }
            */

            String line;
            String javaHome = System.getenv("JAVA_HOME");
            Process p = Runtime.getRuntime().exec(javaHome + "/bin/jps");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
