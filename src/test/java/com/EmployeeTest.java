
package com;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.general.Employee;
import org.junit.jupiter.api.Test;



public class EmployeeTest {
    
    Employee employee = new Employee("Rajesh",2);
    
    @Test
    public void testEmpployeeName(){
        assertTrue(employee.getEmployeeName().length()!=0);
    }
    
}
