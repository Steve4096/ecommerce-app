package com.example.demo.Services;

import com.example.demo.DTOs.EmployeeDTO;
import com.example.demo.DTOs.EmployeeUpdateResponseDTO;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Models.Employee;
import com.example.demo.Models.Office;
import com.example.demo.Repositories.EmployeeRepository;
import com.example.demo.Repositories.OfficeRepository;
import com.example.demo.UtilityClasses.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OfficeRepository officeRepository;


    public Employee saveEmployee(Employee employee){
        String generatedID;
        do {
            generatedID=IdGenerator.generateEmployeeID();
        }while (employeeRepository.existsById(generatedID));
        employee.setEmployeeNumber(generatedID);
        return employeeRepository.save(employee);
    }

    public List<Employee> fetchAllEmployees(){
        return employeeRepository.findAll();
    }

    /*public Optional<Employee> updateEmployeeDetails(String employeeNumber, EmployeeDTO dto){
        Optional<Employee> employee=employeeRepository.findById(employeeNumber);
        if(employee.isPresent()){
            Employee employeeToBeUpdated=employee.get();
            if(dto.getExtension()!=null)employeeToBeUpdated.setExtension(dto.getExtension());
           // if(dto.getOfficeCode()!=null)employeeToBeUpdated.setOffice(dto.setOfficeCode());
            if(dto.getOfficeCode()!=null){officeRepository.findById(dto.getOfficeCode()).ifPresent(employeeToBeUpdated::setOffice);}
           // if(dto.getReportsTo()!=null)employeeToBeUpdated.setReportsTo(dto.getReportsTo());
            if(dto.getReportsTo()!=null){employeeRepository.findById(dto.getReportsTo()).ifPresent(employeeToBeUpdated::setReportsTo);}
            if(dto.getJobTitle()!=null)employeeToBeUpdated.setJobTitle(dto.getJobTitle());
            return Optional.of(employeeRepository.save(employeeToBeUpdated));
        }
        return Optional.empty();
    }*/


    public Optional<EmployeeUpdateResponseDTO> updateEmployeeDetails(String employeeNumber, EmployeeDTO dto) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeNumber);

        if (employeeOpt.isEmpty()) {
            return Optional.empty();
        }

        Employee employee = employeeOpt.get();
        Map<String, Map<String, Object>> updatedFields = new HashMap<>();

        if (dto.getExtension() != null && !dto.getExtension().equals(employee.getExtension())) {
            updatedFields.put("extension", Map.of(
                    "from", employee.getExtension(),
                    "to", dto.getExtension()
            ));
            employee.setExtension(dto.getExtension());
            System.out.println("Updated extension from " + employee.getExtension() + " to " + dto.getExtension());
        }

        if (dto.getOfficeCode() != null &&
                (employee.getOffice() == null || !dto.getOfficeCode().equals(employee.getOffice().getOfficeCode()))) {

            String oldOfficeCode = employee.getOffice() != null ? employee.getOffice().getOfficeCode() : null;
            Office office = officeRepository.findById(dto.getOfficeCode())
                    .orElseThrow(() -> new NotFoundException("Office with code " + dto.getOfficeCode() + " not found"));

            updatedFields.put("officeCode", Map.of(
                    "from", oldOfficeCode,
                    "to", dto.getOfficeCode()
            ));
            employee.setOffice(office);
            System.out.println("Updated officeCode from " + oldOfficeCode + " to " + dto.getOfficeCode());
        }

        if (dto.getReportsTo() != null &&
                (employee.getReportsTo() == null || !dto.getReportsTo().equals(employee.getReportsTo().getEmployeeNumber()))) {

            String oldManager = employee.getReportsTo() != null ? employee.getReportsTo().getEmployeeNumber() : null;
            Employee manager = employeeRepository.findById(dto.getReportsTo())
                    .orElseThrow(() -> new NotFoundException("Manager with ID " + dto.getReportsTo() + " not found"));

            updatedFields.put("reportsTo", Map.of(
                    "from", oldManager,
                    "to", dto.getReportsTo()
            ));
            employee.setReportsTo(manager);
            System.out.println("Updated reportsTo from " + oldManager + " to " + dto.getReportsTo());
        }

        if (dto.getJobTitle() != null && !dto.getJobTitle().equals(employee.getJobTitle())) {
            updatedFields.put("jobTitle", Map.of(
                    "from", employee.getJobTitle(),
                    "to", dto.getJobTitle()
            ));
            employee.setJobTitle(dto.getJobTitle());
            System.out.println("Updated jobTitle from " + employee.getJobTitle() + " to " + dto.getJobTitle());
        }

        if (!updatedFields.isEmpty()) {
            employeeRepository.save(employee);
        }

        return Optional.of(new EmployeeUpdateResponseDTO(employee.getEmployeeNumber(), updatedFields));
    }



    public Optional<Employee> deleteEmployee(String employeeCode){
        Optional<Employee> employee=employeeRepository.findById(employeeCode);
        if(employee.isPresent()){
            employeeRepository.deleteById(employeeCode);
            return employee;
        }
        return Optional.empty();
    }
}
