package com.starmc.start;

import java.io.IOException;
import java.net.ServerSocket;

public class ClassAPI {

	@SuppressWarnings("finally")
	public static boolean CheckOpen(int port) throws IOException {
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
		} finally {
			if (socket != null) {
				socket.close();
				return true;
			} else {
				return false;
			}
		}

	}

}
