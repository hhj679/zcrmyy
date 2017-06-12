package com.zcrmyy.equ.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Supplier")
public class Supplier {
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	public String getSupplier() {
		return supplierName;
	}
	public void setSupplier(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierPhone() {
		return supplierPhone;
	}
	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}
	public String getProductLicense() {
		return productLicense;
	}
	public void setProductLicense(String productLicense) {
		this.productLicense = productLicense;
	}
	public Date getProductLicenseDate() {
		return productLicenseDate;
	}

	public void setProductLicenseDate(Date productLicenseDate) {
		this.productLicenseDate = productLicenseDate;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Date getCertificateDate() {
		return certificateDate;
	}
	public void setCertificateDate(Date certificateDate) {
		this.certificateDate = certificateDate;
	}
	public Date getLicenseDate() {
		return licenseDate;
	}
	public void setLicenseDate(Date licenseDate) {
		this.licenseDate = licenseDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPo() {
		return po;
	}
	public void setPo(String po) {
		this.po = po;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getLegal() {
		return legal;
	}
	public void setLegal(String legal) {
		this.legal = legal;
	}
	public String getLegalPhone() {
		return legalPhone;
	}
	public void setLegalPhone(String legalPhone) {
		this.legalPhone = legalPhone;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getSalesmanPhone() {
		return salesmanPhone;
	}
	public void setSalesmanPhone(String salesmanPhone) {
		this.salesmanPhone = salesmanPhone;
	}
	public String getCompanyAddr() {
		return companyAddr;
	}
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}
	public String getRegistrationAddr() {
		return registrationAddr;
	}
	public void setRegistrationAddr(String registrationAddr) {
		this.registrationAddr = registrationAddr;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Column(name="suppliername")
	String supplierName;
	
	@Column(name="supplierphone")
	String supplierPhone;
	
	@Column(name="manufacturername")
	String manufacturerName;
	
	@Column(name="productname")
	String productName;
	
	@Column(name="productspec")
	String productSpec;
	
	@Column(name="productlicense")
	String productLicense;
	
	@Column(name="productlicensedate")
	Date productLicenseDate;

	@Column(name="certificateno")
	String certificateNo;
	
	@Column(name="registrationdate")
	Date registrationDate;
	
	@Column(name="certificatedate")
	Date certificateDate;
	
	@Column(name="licensedate")
	Date licenseDate;
	
	@Column
	Double price;
	
	@Column
	String po;
	
	@Column
	String brand;
	
	@Column
	String legal;
	
	@Column(name="legalphone")
	String legalPhone;
	
	@Column
	String manager;
	
	@Column(name="managerphone")
	String managerPhone;
	
	@Column
	String salesman;
	
	@Column(name="salesmanphone")
	String salesmanPhone;
	
	@Column(name="companyaddr")
	String companyAddr;
	
	@Column(name="registrationaddr")
	String registrationAddr;
}
