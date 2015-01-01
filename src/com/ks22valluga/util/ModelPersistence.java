package com.ks22valluga.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class ModelPersistence {

	public boolean write(String fileName, String inputText) {
		boolean success = true;
		FileOutputStream fop = null;
		File file;
		file = new File(fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fop = new FileOutputStream(file);
			byte[] contentInBytes = inputText.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		} catch (FileNotFoundException e) {
			success = false;
			e.printStackTrace();
		} catch (IOException e) {
			success = false;
			e.printStackTrace();
		}
		return success;
	}

	public String read(String fileName) {
		File loadFile = new File(fileName);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(loadFile));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		StringBuilder content = new StringBuilder();
		boolean check = true;
		while (check) {
			try {
				int val = br.read();
				if (val != -1) {
					content.append((char) val);
				} else {
					check = false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}
}
