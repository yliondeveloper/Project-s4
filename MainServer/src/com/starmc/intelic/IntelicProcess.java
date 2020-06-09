package com.starmc.intelic;

import java.io.IOException;

import com.starmc.start.ClassAPI;

public class IntelicProcess {

	public static boolean RestartNewIP;
	public static boolean RestartLobby;
	public static boolean RestartPvP;
	public static boolean RestartPotPvP;
	public static boolean RestartGladiator = false;
	public static boolean RestartAuthServer = false;
	

	public static void ProcessServer() {
		new Thread() {
			public void run() {
				while (true) {
					try {
						RestartGladiator = false;
						Thread.sleep(400000);
						if (ClassAPI.CheckOpen(10879) && !RestartNewIP) {
							System.out.println("Servidor NEWIP iniciando...");
							Runtime.getRuntime().exec("./NewIP.sh");
						}
						if (ClassAPI.CheckOpen(10023) && !RestartLobby) {
							System.out.println("Servidor Lobby iniciando...");
							Runtime.getRuntime().exec("./Lobby.sh");
						}
						if (ClassAPI.CheckOpen(15678) && !RestartPotPvP) {
							System.out.println("Servidor PotPvP iniciando...");
							Runtime.getRuntime().exec("./PotPvP.sh");
						}
						if (ClassAPI.CheckOpen(41456) && !RestartPvP) {
							System.out.println("Servidor PvP iniciando...");
							Runtime.getRuntime().exec("./PvP.sh");
						}
						if (ClassAPI.CheckOpen(1889) && !RestartAuthServer) {
							System.out.println("Servidor AuthServer iniciando...");
							Runtime.getRuntime().exec("./AuthServer.sh");
						}
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

}
