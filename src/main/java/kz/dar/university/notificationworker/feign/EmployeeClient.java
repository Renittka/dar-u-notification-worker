package kz.dar.university.notificationworker.feign;

import kz.dar.university.notificationworker.model.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "employee-core-api")
public interface EmployeeClient {

    @GetMapping("/employee")
    EmployeeDTO getEmployeeById(@RequestParam String employeeId);

}
