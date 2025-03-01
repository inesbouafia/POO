package projectPOO;
import projectPOO.Task1;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException; // Needed to handle file errors
import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class Project  implements Serializable {
    private  String title;
    private  String description;
    private  List<Task1> tasks;

    
     
     public Project(String title, String description) {
         this.title = title;
         this.description = description;
         this.tasks = new ArrayList<>();
     }
     
     
     public String getTitle() { return title; }
     public String getDescription() { return description; }
     
     
     public void addTask(Task1 task) {
         tasks.add(task);
     }

     public List<Task1> getTasks() {
         return tasks;
     }

     public void displayProject() {
         System.out.println("Project: " + title);
         System.out.println("Description: " + description);
         for (Task1 task : tasks) {
             task.displayTask();
         }
         
     }
 
     
   
 
     public void saveToFile(String datafile) {
    	    try {
    	        File file = new File(datafile); 
    	        if (file.createNewFile()) { 
    	            System.out.println(" File created: " + file.getAbsolutePath());
    	        } else {
    	            System.out.println(" File already exists.");
    	        }

    	        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
    	            out.writeObject(this); // Save the Project object
    	            System.out.println("Project saved successfully!");
    	        }
    	    } catch (IOException e) {
    	        System.out.println(" Error saving project: " + e.getMessage());
    	    }
    	}

     
     
     
     
     public static Project loadFromFile(String datafile) {
    	    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(datafile))) {
    	        return (Project) in.readObject(); // Read & return the saved Project object
    	    } catch (IOException | ClassNotFoundException e) {
    	        System.out.println(" Error loading project: " + e.getMessage());
    	        return null; 
    	    }
    	}//Project.loadFromFile("file") loads the saved project.
     
}
