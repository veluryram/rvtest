package javaprojs.bigdata.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class Master implements Watcher
{
    ZooKeeper zk;
    String hostPort;

    Master(String hostPort) {
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zk = new ZooKeeper(hostPort, 15000, this);
    }

    public void process(WatchedEvent e) {
        System.out.println(e);
    }

    public static void main(String args[])
            throws Exception {
        Master m = new Master(args[0]);

        m.startZK();

        // wait for a bit
        Thread.sleep(60000);
    }


    /*
    // returns true if there is a master
    private boolean checkMaster()
            throws InterruptedException
    {
        while (true)
        {
            try
            {
                Stat stat = new Stat();
                byte[] data = zk.getData(MASTER_NODE, false, stat);
                isLeader = new String(data).equals(serverId);
                return true;
            }
            catch (KeeperException.NoNodeException e)
            {
                // No master node, try creating again
                return false;
            }
            catch (KeeperException e)
            {
                Thread.sleep(1000);
            }
        }
    }

    @Override
    public void runForMaster() throws InterruptedException {
        while (true) {
            try
            {
                zk.create(MASTER_NODE, serverId.getBytes(),
                        OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            }
            catch (KeeperException ignored)
            {
            }

            if (checkMaster()) break;
        }
    }

*/
}
