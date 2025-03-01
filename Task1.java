package projectPOO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.DateTimeException;

public class Task1 implements Serializable {

  static final long serialVersionUID = 1L;
  private    String title;
  private    String description;
  private    LocalDate startDate;
  private    LocalDate endDate;
  private    String status;
  private    String assignedTo;

    public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public LocalDate getStartDate() {
	return startDate;
}
public void setStartDate(LocalDate startDate) {
	this.startDate = startDate;
}
public LocalDate getEndDate() {
	return endDate;
}
public void setEndDate(LocalDate endDate) {
	this.endDate = endDate;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getAssignedTo() {
	return assignedTo;
}
public void setAssignedTo(String assignedTo) {
	this.assignedTo = assignedTo;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
	// Constructor
     public Task1() {}
    public Task1(String title, String description, LocalDate startDate, LocalDate endDate, String assignedTo) 
    {
      // manually trigger an exception

        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "Not Started"; // Default status
        this.assignedTo = assignedTo;
    

	 if (endDate.isBefore(startDate)) {
	       throw new DateTimeException("End date cannot be before start date!");
	        } 
    }
 
   
    public long getTaskDuration() {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    
    public void updateStatus(String newStatus) {
        if (newStatus.equals("Not Started") || newStatus.equals("In Progress") || newStatus.equals("Completed")) {
            this.status = newStatus;
        } else {
            throw new IllegalArgumentException("Invalid status!");
        }
    }

    // Display task details
    public void displayTask() {
    	System.out.println(" ur  task's information: ");
        System.out.println("Task: " + title);
        System.out.println("Description: " + description);
        System.out.println("Start Date: " + startDate + " | End Date: " + endDate);
        System.out.println("Duration: " + getTaskDuration() + " days");
        System.out.println("Status: " + status + " | Assigned to: " + assignedTo);
        System.out.println("--------------------------------");
    }


}

