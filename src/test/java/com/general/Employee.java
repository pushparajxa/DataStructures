
package com.general;

public class Employee {
    
    public String name;
    public int id;
    
    
    public Employee(String nane, int id){
        this.name = name;
        this.id = id;
    }
    
    
    public String getEmployeeName(){
        return "name" + id;
    }
    
    public static void main(String[] args) {
        
        Employee employee = new Employee("Rajesh", 2);
        System.out.println(employee.getEmployeeName());
        
    }
    
}
