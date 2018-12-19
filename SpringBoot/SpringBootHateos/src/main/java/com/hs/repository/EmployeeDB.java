package com.hs.repository;

import java.util.ArrayList;
import java.util.List;

import com.hs.model.EmployeeVO;

public class EmployeeDB {
	 
    public static List<EmployeeVO> getEmployeeList()
    {
        List<EmployeeVO> list = new ArrayList<>();
 
        EmployeeVO empOne = new EmployeeVO(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
        EmployeeVO empTwo = new EmployeeVO(2, "Amit", "Singhal", "asinghal@yahoo.com");
        EmployeeVO empThree = new EmployeeVO(3, "Kirti", "Mishra", "kmishra@gmail.com");
 
        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);
 
        return list;
    }
}
