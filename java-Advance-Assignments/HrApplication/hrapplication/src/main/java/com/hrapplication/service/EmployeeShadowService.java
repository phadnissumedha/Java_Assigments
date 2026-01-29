/*package com.hrapplication.service;

import com.hrapplication.entity.EmployeeShadow;
import java.util.List;

public interface EmployeeShadowService {
    EmployeeShadow addEmployeeShadow(EmployeeShadow employeeShadow);

    List<EmployeeShadow> getAllEmployeeShadows();
}
*/

package com.hrapplication.service;

import com.hrapplication.entity.Employee;
import com.hrapplication.entity.EmployeeShadow;
import java.util.List;

public interface EmployeeShadowService {

    EmployeeShadow addEmployeeShadow(EmployeeShadow employeeShadow);

    List<EmployeeShadow> getAllEmployeeShadows();

    void copyEmployeeToShadow(Employee employee, String operationType);
}
