package com.it.just.solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils {
	public static Properties loadXmlProperties(String path) {
		Properties settings = new Properties();
		try (InputStream in = Files.newInputStream(Paths.get(path), StandardOpenOption.READ)) {
			settings.loadFromXML(in);
			return settings;
		} catch (IOException e) {
			Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
	}
	
	public static String readAll(String path) {
		try (BufferedReader in = Files.newBufferedReader(Paths.get(path))) {
			StringBuilder sb = new StringBuilder();
			in.lines().forEach((String line) -> {
				sb.append(line);
			});
			return sb.toString();
		} catch (IOException e) {
			Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 
	 * @param path
	 * @param buf
	 */
	public static void write(String path, String buf, Charset cs, OpenOption... options) {
		
		try (BufferedWriter out = Files.newBufferedWriter(Paths.get(path), cs, options)) {
			out.write(buf);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	public static void write(String path, String buf, Charset cs) {
		write(path, buf, 
				cs, 
				StandardOpenOption.CREATE, 
				StandardOpenOption.WRITE,
				StandardOpenOption.TRUNCATE_EXISTING);
	}
	
	public static void write(String path, String buf) {
		write(path, buf, 
				Charset.forName("UTF-8"), 
				StandardOpenOption.CREATE, 
				StandardOpenOption.WRITE,
				StandardOpenOption.TRUNCATE_EXISTING);
	}
}
