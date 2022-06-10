package com.simplilearn.phase1project;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;




public class VirtualKeyRepository {
	
	public static void optionsSelection()
	{
		while(true)
		{
			System.out.println("Enter your choice for the main menu: ");
			Scanner sc=new Scanner(System.in);
			int ch=sc.nextInt();
			switch(ch)
			{
			case 1:
				System.out.println("Enter the directory location which you want to access");
				String str=sc.next();
				File directory=new File(str);
				File[] listOfFiles= directory .listFiles();
				System.out.println("List of files in the specified directory: ");
				readFiles(listOfFiles,0);
				break;
			case 2:
				String[] arr = {"1. Add a file to the existing directory list ",
		                "2. Delete a file from the existing directory list",
		                "3. Search a specified file from the main directory",
		                "4. Go Back to the main menu"
		                
		        };
				for(int i=0;i<arr.length;i++)
				{
					System.out.println(arr[i]);
				}
				System.out.println("Enter the directory location: ");
				String str1=sc.next();
				businessOperations(str1);
				break;
			case 3:
				System.out.println("Application terminated..");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please enter correct option");
				break;
			}
		}
	}
	public static void readFiles(File[] listOfFiles,int level)
	{
		
		Arrays.sort(listOfFiles);	
		
		for(int i=0;i<listOfFiles.length;i++)
		{
			for(int j=0;j<level;j++)
			{
				System.out.print("\t");
			}
		
			if(listOfFiles[i].isFile())
			{
			  System.out.println(listOfFiles[i].getName());
			}
			else if(listOfFiles[i].isDirectory())
			{
				System.out.println("["+listOfFiles[i].getName()+"]");
				readFiles(listOfFiles[i].listFiles(),level +1);
			}
		}
	}
	public static void businessOperations(String str1)
	{
		while(true)
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter your choice for the business Operations menu:");
			int ch1=sc.nextInt();
			switch(ch1)
			{
			case 1:
				createFile(str1);
				break;
			case 2:
				deleteFile(str1);
			    break;
			case 3:
				searchFile(str1);
				break;
			case 4:
				System.out.println("Return to the main menu..");
				optionsSelection();
				break;
			default:
				System.out.println("Invalid choice. Please enter correct option");
				break;
				
			}
		}
	}
	public static void createFile(String str1)
	{
		String pathName="";
		System.out.println("Enter the name of the file to be created: ");
		Scanner sc=new Scanner(System.in);
		String fileName=sc.next();
		pathName=str1+fileName;
		File file=new File(pathName);
		try {
			if(file.createNewFile())
				System.out.println("File created succesfully.");
			else
				System.out.println("File already exists.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void deleteFile(String str1)
	{
		String pathName="";
		System.out.println("Enter the name of the file to be deleted: ");
		Scanner sc=new Scanner(System.in);
		String fileName=sc.next();
		pathName=str1+fileName;
		
		try {
			if(Files.deleteIfExists(Paths.get(pathName)))
				System.out.println("File deleted succesfully");
			else
				System.out.println("File not found");
		}
		catch(NoSuchFileException e) {
			System.out.println("No such file exists "+e.getMessage());
		}
		catch(DirectoryNotEmptyException e) {
			System.out.println("Directory is not empty"+e.getMessage());
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void searchFile(String str1)
	{
		
		System.out.println("Enter the name of the file to be searched: ");
		Scanner sc=new Scanner(System.in);
		String fileName=sc.next();
		File directory=new File(str1);
		String flist[]=directory.list();
		int flag=0;
		if(flist==null) {
			System.out.println("Empty directory");
		}
		else {
			for(int i=0;i<flist.length;i++)
			{
				if(flist[i].equals(fileName))
				{
					System.out.println(fileName + " found.");
					flag=1;
					break;
				}
			}
			if(flag==0)
				System.out.println("File not Found");
		
		}	
		
	}

	public static void main(String[] args) {
		
		System.out.println("Welcome to the Virtual Keys Repository");
		System.out.println("\n**************************************\n");
		System.out.println("Developer Name: Ramsha Kamal");
		System.out.println("\n**************************************\n");
		System.out.println("User Interactions: ");
		String[] arr = {"1. Retrieving the file names in ascending order ",
                "2. Business level operations",
                "3. Close the Application",
                
        };
		for(int i=0;i<arr.length;i++)
		{
			System.out.println(arr[i]);
		}

		optionsSelection();

	}

}
