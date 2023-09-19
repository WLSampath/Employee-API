package com.example.CRUD.App.repo;

import com.example.CRUD.App.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    @Query(value = "SELECT * FROM employee WHERE empid = :empid", nativeQuery = true)
    Employee getEmployeeByID(@Param("empid") String empid);

    @Modifying
    @Query(value = "INSERT INTO employee (empid, emp_address, empmnumber, emp_name) VALUES (:empid, :address, :number, :name)", nativeQuery = true)
    void insertEmployee(@Param("empid") int empid, @Param("address") String address, @Param("number") String number, @Param("name") String name);

    @Modifying
    @Query(value = "UPDATE employee SET emp_address = :address, empmnumber = :number, emp_name = :name WHERE empid = :id", nativeQuery = true)
    void updateEmployeeByID( @Param("name") String name, @Param("address") String address, @Param("number") String number, @Param("id") int id);

    @Modifying
    @Query(value = "DELETE FROM employee WHERE empid = :id", nativeQuery = true)
    void deleteEmployeeByID(@Param("id") int id);

}
