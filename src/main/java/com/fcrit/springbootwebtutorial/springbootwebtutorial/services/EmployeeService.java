package com.fcrit.springbootwebtutorial.springbootwebtutorial.services;


import com.fcrit.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.fcrit.springbootwebtutorial.springbootwebtutorial.entities.Employee;
import com.fcrit.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long id) {
            return  employeeRepository.findById(id).map(employee -> modelMapper.map(employee,EmployeeDTO.class));
    }


    public List<EmployeeDTO> getAllEmployee() {
        List<Employee>employeesList = employeeRepository.findAll();
        return employeesList
                .stream()
                .map(employee -> modelMapper.map(employee,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public  EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        Employee toSaveEmployee = modelMapper.map(inputEmployee,Employee.class);
        Employee savedEmployee = employeeRepository.save(toSaveEmployee);
        return modelMapper.map(savedEmployee,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeId(Long employeeId, EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO,Employee.class);
        employee.setId(employeeId);
        Employee savedEmploee = employeeRepository.save(employee);
        return modelMapper.map(savedEmploee,EmployeeDTO.class);
    }

    public boolean deleteEmploeeID(Long emploeeId) {
        boolean  exists = employeeRepository.existsById(emploeeId);
        if(!exists) return false;
        employeeRepository.deleteById(emploeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists) return null;
        Employee employee = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value)->{
            Field fieldToUpdate = ReflectionUtils.findRequiredField(Employee.class,field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate,employee,value);
        });
        return  modelMapper.map(employeeRepository.save(employee),EmployeeDTO.class);
    }
}
