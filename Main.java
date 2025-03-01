package projectPOO;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.DateTimeException;

public class Main {

	public static void main(String [] args) {
		
		
		
		Scanner scan = new Scanner(System.in);
		
		Task1 t = new Task1();
		
	
		
		
        Project myproject = Project.loadFromFile("project_data.txt");

        if (myproject == null) {
            System.out.println("No saved project found. Creating a new one.");
            System.out.println("Enter project title:");
            String projectTitle = scan.nextLine();
            System.out.println("Enter project description:");
            String projectDescription = scan.nextLine();
            myproject = new Project(projectTitle, projectDescription);
        } else {
            System.out.println("Project loaded successfully!");
            myproject.displayProject();
        }
		
        
		System.out.println("welcome to ur home page  \n \n ");
	
	
		
	
		System.out.println("please enter ur task's information:  \n ");
		
		System.out.println("enter ur task 's title ");
		String title = scan.next();
		
		scan.nextLine();
		
		System.out.println("enter ur task 's description  ");
		String  description = scan.nextLine();
		
	    System.out.println("Enter the start date (YYYY-MM-DD):");
	    String dateInput = scan.nextLine(); // Read date as a string
	    LocalDate startDate = LocalDate.parse(dateInput); 
	    
	    
	    System.out.println("Enter the end date (YYYY-MM-DD):");
	    String dateInput1 = scan.nextLine(); // Read date as a string
	    LocalDate endDate = LocalDate.parse(dateInput1);
	    
	    System.out.println("enter the person assigned to's name ");
	    String assignedTo = scan.next();
	    
	    String  status = "Not Started";
	    
	    
	    
	    t.setTitle(title);
	    t.setDescription(description);
	    t.setStartDate(startDate);
	    t.setEndDate(endDate);
	    t.setAssignedTo(assignedTo);
	    t.setStatus(status);
	    
	    
	    t.getTaskDuration();
	    myproject.addTask(t);
	    t.displayTask();
        System.out.println("\n \n Task added successfully to project!");

	   
	   
	    String statuss =status;
	    System.out.println("Do you want to update your task status? (yes/no)");
	    scan.nextLine();
	    String choice = scan.nextLine().trim(); 
	   
	    
	    if (choice.equalsIgnoreCase("yes")) {  
	    	
	        System.out.println("\nEnter: \n In Progress or Completed");
            
             statuss = scan.nextLine().trim(); 
             
             System.out.println("DEBUG: New status entered -> " + statuss);
             
             t.setStatus(statuss); 
            
	       }
	       t.displayTask();
	       
	       System.out.println("Do you want to save this project? (yes/no)");
	       String saveChoice = scan.nextLine().trim();

	       if (saveChoice.equalsIgnoreCase("yes")) {  
	           myproject.saveToFile("project_data.txt");  
	           System.out.println(" Project saved successfully!");
	       }

	       scan.close();   
	
	 } 
	} 

