package com.starmc.fileapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyAPI {

	public static void copy(File origem, File destino, boolean overwrite) throws IOException {
		if (destino.exists() && !overwrite) {
			return;
		}
		FileInputStream source = new FileInputStream(origem);
		FileOutputStream destination = new FileOutputStream(destino);
		FileChannel sourceFileChannel = source.getChannel();
		FileChannel destinationFileChannel = destination.getChannel();
		long size = sourceFileChannel.size();
		sourceFileChannel.transferTo(0, size, destinationFileChannel);
	}

	public static void copyAll(File origem, File destino, boolean overwrite)
			throws IOException, UnsupportedOperationException {
		if (!destino.exists()) {
			destino.mkdir();
		}
		if (!origem.isDirectory()) {
			throw new UnsupportedOperationException("Origem deve ser um diretório");
		}
		if (!destino.isDirectory()) {
			throw new UnsupportedOperationException("Destino deve ser um diretório");
		}
		File[] files = origem.listFiles();
		for (int i = 0; i < files.length; ++i) {
			if (files[i].isDirectory()) {
				copyAll(files[i], new File(destino + "/" + files[i].getName()), overwrite);
			} else {
				copy(files[i], new File(destino + "/" + files[i].getName()), overwrite);
			}
		}		
	}

}
