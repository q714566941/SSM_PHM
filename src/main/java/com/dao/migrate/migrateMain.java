package com.dao.migrate;

public class migrateMain {
    static migrate_CPU migrate_cpu = new migrate_CPU();
    static migrate_Disk migrate_disk = new migrate_Disk();
    static migrate_Mem migrate_mem = new migrate_Mem();
    static migrate_Net migrate_net = new migrate_Net();
    public static void main(String[] args) throws Exception {
        boolean flag = true;
        while (flag) {
            migrate_cpu.migrate();
            migrate_disk.migrate();
            migrate_mem.migrate();
            migrate_net.migrate();
            Thread.sleep(10*1000);
        }
    }
}
