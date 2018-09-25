package com.ks22valluga.app.classloading;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarLoader {

	public JarLoader(ArrayList<ScenarioRecord> records, String jarFileLocation) {
		JarFile jarFile=null;
		try {
			jarFile = new JarFile(jarFileLocation);

			Enumeration<JarEntry> e = jarFile.entries();

			URL[] urls = { new URL("jar:file:" + jarFileLocation + "!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);

			while (e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				if (je.isDirectory() || !je.getName().endsWith(".class")) {
					continue;
				}
				// -6 because of .class
				String className = je.getName().substring(0, je.getName().length() - ".class".length());
				className = className.replace('/', '.');
				Class<?> c = cl.loadClass(className);

			}
			jarFile.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

}
