package com.wmx.jdk17app.jxls3.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工信息
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2025/6/14 15:55
 */
public class Employee {

    private Integer empNum;
    private String name;
    private Date birthDate;
    private BigDecimal payment;

    public Employee() {
    }

    public Employee(Integer empNum, String name, Date birthDate, BigDecimal payment) {
        this.empNum = empNum;
        this.name = name;
        this.birthDate = birthDate;
        this.payment = payment;
    }

    public Integer getEmpNum() {
        return empNum;
    }

    public void setEmpNum(Integer empNum) {
        this.empNum = empNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNum=" + empNum +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", payment=" + payment +
                '}';
    }
}
