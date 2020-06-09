package com.starmc.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Scanner;

import com.starmc.fileapi.CopyAPI;

public class Main {

	private static boolean Backup;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		System.out.println("Verificando servidores online...");
		System.out.println("Servidor Lobby iniciando...");
		Runtime.getRuntime().exec("./Lobby.sh");

		System.out.println("Servidor Gladiator iniciando...");
		Runtime.getRuntime().exec("./Gladiator.sh");

		System.out.println("Servidor PvP iniciando...");
		Runtime.getRuntime().exec("./PvP.sh");

		System.out.println("Servidor PotPvP iniciando...");
		Runtime.getRuntime().exec("./PotPvP.sh");

		System.out.println("Servidor AuthServer iniciando...");
		Runtime.getRuntime().exec("./AuthServer.sh");

		System.out.println("Servidor ScreenSahre iniciando...");
		Runtime.getRuntime().exec("./ScreenShare.sh");

		System.out.println("Servidor BungeeCord iniciando...");
		Runtime.getRuntime().exec("./BungeeCord.sh");
		
		System.out.println("Verificando log backup...");
		BufferedReader buffer = new BufferedReader(new FileReader("/root/MainServer/backuplog.vcs"));
		while (buffer.ready()) {
			String code = buffer.readLine();
			int date = new Date().getDate();
			System.out.println("Data da maquina: " + new Date());
			int datereduct = date - 4;
			System.out.println("Code backup: " + code);
			if (code.equalsIgnoreCase(datereduct + "")) {
				Backup = true;
				System.out.println("Iniciando backup...");
				OutputStreamWriter bufferOut = new OutputStreamWriter(
						new FileOutputStream("/root/MainServer/backuplog.vcs"), "UTF-8");
				bufferOut.write(date + "");
				bufferOut.flush();
				bufferOut.close();
			}
		}
		if (Backup) {
			new Thread() {
				public void run() {
					System.out.println("Deletando backups antigos...");
					if (new File("/root/NewIP/").delete()) {
						System.out.println("Backup (NewIP) deletado com sucesso!");
					}
					if (new File("/root/PvP/").delete()) {
						System.out.println("Backup (PvP) deletado com sucesso!");
					}
					if (new File("/root/PotPvP/").delete()) {
						System.out.println("Backup (PotPvP) deletado com sucesso!");
					}
					if (new File("/root/Lobby/").delete()) {
						System.out.println("Backup (Lobby) deletado com sucesso!");
					}
					System.out.println("Iniciando backup do servidor (NewIP)...");
					try {
						CopyAPI.copyAll(new File("/root/NewIP/"), new File("/Backups/NewIP/"), true);
					} catch (UnsupportedOperationException | IOException e) {
					}
					System.out.println("Backup do servidor (NewIP) foi concluido!");
					System.out.println("Iniciando backup do servidor (PvP)...");
					try {
						CopyAPI.copyAll(new File("/root/PvP/"), new File("/Backups/PvP/"), true);
					} catch (UnsupportedOperationException | IOException e) {
					}
					System.out.println("Backup do servidor (PvP) foi concluido!");
					System.out.println("Iniciando backup do servidor (PotPvP)...");
					try {
						CopyAPI.copyAll(new File("/root/PotPvP/"), new File("/Backups/PotPvP"), true);
					} catch (UnsupportedOperationException | IOException e) {
					}
					System.out.println("Backup do servidor (PotPvP) foi concluido!");
					System.out.println("Iniciando backup do servidor (Lobby)...");
					try {
						CopyAPI.copyAll(new File("/root/Lobby"), new File("/Backups/Lobby/"), true);
					} catch (UnsupportedOperationException | IOException e) {
					}
					System.out.println("Backup do servidor (Lobby) foi concluido!");
				}
			}.start();
		}
		System.out.println("SyncAPI iniciado com sucesso!");
		while (true) {
			Scanner Scanner = new Scanner(System.in);
			String command = Scanner.nextLine();
			if (command.equalsIgnoreCase("help")) {
				System.out.println("Comandos do MainServer");
				System.out.println("");
				System.out.println("- Endserver (Servidor)");
				System.out.println("- Endconnection (Servidor)");
				System.out.println("- Comunicate (Servidor) (Comando)");
				System.out.println("- forcestopall (Apenas emergência)");
				System.out.println("- forcestop (Apenas emergência)");
			}
		}
	}

}
