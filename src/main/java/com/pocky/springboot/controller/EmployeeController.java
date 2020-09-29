package com.pocky.springboot.controller;

import com.pocky.springboot.dao.DepartmentDao;
import com.pocky.springboot.dao.EmployeeDao;
import com.pocky.springboot.entities.Department;
import com.pocky.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Signature;
import java.util.Collection;

/**
 * @author pocky
 * @date 2020/09/22/0022
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表页面
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();

        model.addAttribute("emps",employees);
        //thymeleaf默认拼串
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        //先查出所有的部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);

        //来到添加页面
        return "emp/add";
    }

    //SpringMvc 自动将请求参数和入参对象的一一绑定：要求请求参数的名字，和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee){

        System.out.println("保存的信息："+employee);
        //保存员工
        employeeDao.save(employee);
        //返回员工列表页面
        //redirect 表示重定向到一个地址
        //forward 表示转发到一个地址
        return "redirect:/emps";
    }

    //来到修改页面，查出员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //页面显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);

        //回到修改页面;
        return "emp/add";
    }

    //员工修改:需要提交员工id；
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("修改的员工数据："+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
