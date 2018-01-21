package com.example.util;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 1.获取项目的所有包
 * 2.根据包名获取包下的所有类
 * @author user
 *
 */
public class PackageUtil {
	
	private static String pathname=System.getProperty("user.dir") + "\\src\\main\\java";
	private static ArrayList<String> filelist=new ArrayList<>();
		
	static{getFiles(pathname);}

	/*
	 * 测试方法pass,web环境下需要另外测试
	 */
	public static void main(String[] args) {
		String packageName="com.example.web";
		Set<String> packages=getClassName(packageName,false);
		packages.forEach(System.out::println);
				
		System.out.println("---------------------------------------");
		getPackages().forEach(pack->{
			System.out.println("*****");
			getClassName(pack,false).forEach(System.out::println);
		});
	}
	
	/*
	 * 根据路径获取文件夹及其子文件夹的名称
	 */
	public static void getFiles(String path){
		File root=new File(path);
		File[] files=root.listFiles();
		for (File file : files) {
			if(file.isDirectory()){//是目录
				getFiles(file.getAbsolutePath());
				filelist.add(file.getAbsolutePath());
			}
		}
	}
	
	/*
	 * 获取路径下的所有包名称
	 */
	public static Set<String> getPackages(){
		Set<String> packages=new HashSet<>();
		filelist.forEach(item->{
			packages.add(item.substring(pathname.length()+1, item.length()).replace("\\", "."));
		});
		return packages;
	}
	
	/**
	 * 获取某包下所有类
	 * @param packageName 包名
	 * @param isRecursion 是否遍历子包
	 * @return 类的完整名称
	 */
	public static Set<String> getClassName(String packageName, boolean isRecursion) {
		Set<String> classNames = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(".", "/");

		URL url = loader.getResource(packagePath);
		if (url != null) {
			String protocol = url.getProtocol();
			if (protocol.equals("file")) {
				classNames = getClassNameFromDir(url.getPath(), packageName, isRecursion);
			} else if (protocol.equals("jar")) {
				JarFile jarFile = null;
				try{
	                jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
				} catch(Exception e){
					e.printStackTrace();
				}
				
				if(jarFile != null){
					getClassNameFromJar(jarFile.entries(), packageName, isRecursion);
				}
			}
		} else {
			/*从所有的jar包中查找包名*/
			classNames = getClassNameFromJars(((URLClassLoader)loader).getURLs(), packageName, isRecursion);
		}
		
		return classNames;
	}

	/**
	 * 从项目文件获取某包下所有类
	 * @param filePath 文件路径
	 * @param className 类名集合
	 * @param isRecursion 是否遍历子包
	 * @return 类的完整名称
	 */
	private static Set<String> getClassNameFromDir(String filePath, String packageName, boolean isRecursion) {
		Set<String> className = new HashSet<String>();
		File file = new File(filePath);
		File[] files = file.listFiles();
		for (File childFile : files) {
			if (childFile.isDirectory()) {
				if (isRecursion) {
					className.addAll(getClassNameFromDir(childFile.getPath(), packageName+"."+childFile.getName(), isRecursion));
				}
			} else {
				String fileName = childFile.getName();
				if (fileName.endsWith(".class") && !fileName.contains("$")) {
					className.add(packageName+ "." + fileName.replace(".class", ""));
				}
			}
		}

		return className;
	}

	
	/**
	 * @param jarEntries
	 * @param packageName
	 * @param isRecursion
	 * @return
	 */
	private static Set<String> getClassNameFromJar(Enumeration<JarEntry> jarEntries, String packageName, boolean isRecursion){
		Set<String> classNames = new HashSet<String>();
		
		while (jarEntries.hasMoreElements()) {
			JarEntry jarEntry = jarEntries.nextElement();
			if(!jarEntry.isDirectory()){
				/*
	             * 这里是为了方便，先把"/" 转成 "." 再判断 ".class" 的做法可能会有bug
	             * (FIXME: 先把"/" 转成 "." 再判断 ".class" 的做法可能会有bug)
	             * TODO   等待实现的功能
	             * FIXME  需要修正的功能
	             * XXX    需要改进的功能
	             */
				String entryName = jarEntry.getName().replace("/", ".");
				if (entryName.endsWith(".class") && !entryName.contains("$") && entryName.startsWith(packageName)) {
					entryName = entryName.replace(".class", "");
					if(isRecursion){
						classNames.add(entryName);
					} else if(!entryName.replace(packageName+".", "").contains(".")){
						classNames.add(entryName);
					}
				}
			}
		}
		
		return classNames;
	}
	
	/**
	 * 从所有jar中搜索该包，并获取该包下所有类
	 * @param urls URL集合
	 * @param packageName 包路径
	 * @param isRecursion 是否遍历子包
	 * @return 类的完整名称
	 */
	private static Set<String> getClassNameFromJars(URL[] urls, String packageName, boolean isRecursion) {
		Set<String> classNames = new HashSet<String>();
		
		for (int i = 0; i < urls.length; i++) {
			String classPath = urls[i].getPath();
			
			//不必搜索classes文件夹
			if (classPath.endsWith("classes/")) {continue;}

			JarFile jarFile = null;
			try {
				jarFile = new JarFile(classPath.substring(classPath.indexOf("/")));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (jarFile != null) {
				classNames.addAll(getClassNameFromJar(jarFile.entries(), packageName, isRecursion));
			}
		}
		
		return classNames;
	}
}
