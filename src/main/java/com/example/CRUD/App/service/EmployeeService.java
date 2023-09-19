package com.example.CRUD.App.service;

import com.example.CRUD.App.dto.EmployeeDTO;
import com.example.CRUD.App.entity.Employee;
import com.example.CRUD.App.repo.EmployeeRepo;
import com.example.CRUD.App.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    //Queries Methods Functions

    //Insert an employee
    public String saveEmployee(EmployeeDTO employeeDTO) {

        if (employeeRepo.existsById(employeeDTO.getEmpID())){
            return VarList.RSP_DUPLICATED;
        }
        else {
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }

    }

    //Update an employee using EmployeeID
    public String updateEmployee(EmployeeDTO employeeDTO) {

        if(employeeRepo.existsById(employeeDTO.getEmpID())){
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }
        else {
            return VarList.RSP_NO_DATA_FOUND;
        }

    }

    //Retrieve all employees details
    public List<EmployeeDTO> getAllEmployee(){
        List<Employee> employeeList = employeeRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<ArrayList<EmployeeDTO>>(){
        }.getType());
    }

    //Retrieve an employee details using EmployeeID
    public EmployeeDTO searchEmployee(int empID){

        if(employeeRepo.existsById(empID)){
            Employee employee = employeeRepo.findById(empID).orElse(null);
            return modelMapper.map(employee, EmployeeDTO.class);
        }
        else{
            return null;
        }

    }

    //Delete an employee using EmployeeID
    public String deleteEmployee(int empID) {

        if(employeeRepo.existsById(empID)){
            employeeRepo.deleteById(empID);
            return VarList.RSP_SUCCESS;
        }
        else {
            return VarList.RSP_NO_DATA_FOUND;
        }

    }

    //Native Queries Functions

    //Retrieve an employee details using EmployeeID
    public EmployeeDTO getEmpByID(String empID){

        Employee employee = employeeRepo.getEmployeeByID(empID);

        if (employee == null) {
            return null;
        }
        else {
            return modelMapper.map(employee, EmployeeDTO.class);
        }

    }

    //Insert an employee
    public String insertEmp(EmployeeDTO employeeDTO) {

        if (employeeRepo.existsById(employeeDTO.getEmpID())){
            return VarList.RSP_DUPLICATED;
        }
        else {
            employeeRepo.insertEmployee(employeeDTO.getEmpID(), employeeDTO.getEmpAddress(), employeeDTO.getEmpMNumber(), employeeDTO.getEmpName());
            return VarList.RSP_SUCCESS;
        }

    }

    //Update an employee using EmployeeID
    public String updateEmpByID(EmployeeDTO employeeDTO) {

        if (employeeRepo.existsById(employeeDTO.getEmpID())){
            employeeRepo.updateEmployeeByID(employeeDTO.getEmpName(), employeeDTO.getEmpAddress(), employeeDTO.getEmpMNumber(), employeeDTO.getEmpID());
            return VarList.RSP_SUCCESS;
        }
        else {
            return VarList.RSP_NO_DATA_FOUND;
        }

    }

    //Delete an employee using EmployeeID
    public String deleteEmpByID(int empID) {

        if(employeeRepo.existsById(empID)){
            employeeRepo.deleteEmployeeByID(empID);
            return VarList.RSP_SUCCESS;
        }
        else {
            return VarList.RSP_NO_DATA_FOUND;
        }

    }

}
