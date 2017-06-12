package com.zcrmyy.equ.controller;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zcrmyy.equ.entity.Supplier;
import com.zcrmyy.equ.repository.SupplierRepository;

@RestController
@RequestMapping("/api")
public class SupplierController {
	
	private Supplier JSON2Supplier(JSONObject json) {
		Supplier supplier = new Supplier();
		
		return supplier;
	}
	
    @Autowired
    private SupplierRepository supplierRepository;
    
    @Autowired
    @Qualifier("entityManager")
    EntityManager em;

    @RequestMapping(value="/data/findById/{id}" ,method= RequestMethod.GET)
    public Supplier findById(@PathVariable Long id) {
    	Supplier data = this.supplierRepository.findOne(id);
        if(data == null) {
            data = new Supplier();
            Long mid = Long.valueOf(-1);
            data.setId(mid);
        }
        return data;
    }
    
    @RequestMapping(value="/data/findAllData" ,method= RequestMethod.GET)
    public List<Supplier> findAllData() {
        List<Supplier> datas = this.supplierRepository.findAll();
        return datas;
    }
    
    @RequestMapping(value="/data/adSearch" ,method= RequestMethod.POST)
    public String adSearch(@RequestBody String conditionStr) {
//    	Field[] field = data.getClass().getDeclaredFields();
//    	for(int j=0 ; j<field.length ; j++){     //遍历所有属性
//            String name = field[j].getName();    //获取属性的名字
//            name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
//            String type = field[j].getGenericType().toString();    //获取属性的类型
//    	}
//    	
//    	Supplier udata = this.supplierRepository.findOne(data.getId());
//        if(udata == null) {
//        	udata = this.supplierRepository.saveAndFlush(data);
//        } else {
//        	
//        }
//        return udata;
    	JSONObject condition = new JSONObject(conditionStr);
    	StringBuffer sql = new StringBuffer();
    	boolean flag = false;
    	int q = 1;
    	List values = new ArrayList();
    	if(condition.optString("supplier") != null && condition.optString("supplier").trim().length()>0){
    		sql.append(" where s.supplierName like  ?" + (q++));
//    		sql.append(condition.optString("supplier"));
    		values.add("%" + condition.optString("supplier") + "%");
    		flag = true;
    	}
    	
    	if(condition.optString("productName") != null && condition.optString("productName").trim().length()>0){
    		if(flag){
    			sql.append(" and ");
    		} else {
    			sql.append(" where ");
    		}
    		sql.append(" s.productName like ?" + (q++));
    		//sql.append(condition.optString("productName"));
    		values.add("%" + condition.optString("productName") + "%");
    		flag = true;
    	}
    	
    	String condition3_key = condition.optString("condition3Key");
    	String condition3Date = condition.optString("condition3Date");
    	if(condition3_key != null && condition3_key.trim().length()>0){
    		if(flag){
    			sql.append(" and ");
    		} else {
    			sql.append(" where ");
    		}
    		
    		if(condition3Date != null && condition3Date.trim().length() > 0) {
    			if(condition3Date.trim().equalsIgnoreCase("NULL")){
    				sql.append("s." + condition3_key);
    				sql.append(" IS NULL ");
    			} else {
    				sql.append("YEAR(s." + condition3_key + ")");
    				sql.append(" < ?" + (q++));
    				values.add(Integer.valueOf(condition3Date));
    			}
    		} else {
    			sql.append("s." + condition3_key);
    			sql.append(" like ?" + (q++));
        		values.add("%" + condition.optString("condition3Value") + "%");
    		}
    		
    		flag = true;
    	}
    	
    	String condition4_key = condition.optString("condition4Key");
    	String condition4Date = condition.optString("condition4Date");
    	if(condition4_key != null && condition4_key.trim().length()>0){
    		if(flag){
    			sql.append(" and ");
    		} else {
    			sql.append(" where ");
    		}
    		
    		if(condition4Date != null && condition4Date.trim().length() > 0) {
    			if(condition4Date.trim().equalsIgnoreCase("NULL")){
    				sql.append("s." + condition4_key);
    				sql.append(" IS NULL ");
    			} else {
    				sql.append("YEAR(s." + condition4_key + ")");
    				sql.append(" < ?" + (q++));
    				values.add(Integer.valueOf(condition4Date));
    			}
    		} else {
    			sql.append("s." + condition4_key);
    			sql.append(" like ?" + (q++));
        		values.add("%" + condition.optString("condition4Value") + "%");
    		}
    		
    		flag = true;
    	}
    	
    	int page = 1;
    	int size = 20;
    	
    	if(condition.optInt("page") > 0){
    		page = condition.optInt("page");
    	}
    	
    	if(condition.optInt("size") > 0){
    		size = condition.optInt("size");
    	}
    	
    	Query query = em.createQuery("SELECT s FROM Supplier s " + sql.toString());
    	for(int i=0; i<values.size(); i++) {
    		query.setParameter(i+1, values.get(i));
    	}
    	
    	//page
    	query.setMaxResults(size);
    	query.setFirstResult((page-1)*size);
    	
    	Query query2 = em.createQuery("SELECT count(s) FROM Supplier s " + sql.toString());
    	for(int i=0; i<values.size(); i++) {
    		query2.setParameter(i+1, values.get(i));
    	}
    	Long totalCount = (Long) query2.getSingleResult();
    	
    	List<Supplier> result = query.getResultList();
    	
    	JSONObject resutlVo = new JSONObject();
    	resutlVo.put("data", result);
    	resutlVo.put("total", totalCount);
    	resutlVo.put("page" , page);
    	resutlVo.put("size", size);
    	
    	return resutlVo.toString();
    }
    
    @RequestMapping(value="/data/add" ,method= RequestMethod.POST)
    public Supplier addData(@ModelAttribute("data") Supplier data) {
    	Supplier udata = this.supplierRepository.findOne(data.getId());
        if(udata == null) {
        	udata = this.supplierRepository.saveAndFlush(data);
        }
        return udata;
    }
    
//    @RequestMapping(value="/data/update" ,method= RequestMethod.POST)
//    public Supplier updateData(@ModelAttribute("data") Supplier data) {
//    	Supplier udata = this.supplierRepository.findOne(data.getId());
//        if(udata == null) {
//        	udata = this.supplierRepository.saveAndFlush(data);
//        } else {
//        	
//        }
//        return udata;
//    }
    
    @RequestMapping(value="/data/update" ,method= RequestMethod.POST)
    public Supplier updateData(@RequestBody Supplier supplier) {
//    	Supplier udata = this.supplierRepository.findOne(supplier.getId());
//        if(udata == null) {
    	supplier = this.supplierRepository.saveAndFlush(supplier);
//        } 
        
        return supplier;
    }
    
    @RequestMapping(value="/data/delete/{id}" ,method= RequestMethod.GET)
    public String deleteData(@PathVariable Long id) {
    	Supplier udata = this.supplierRepository.findOne(id);
    	JSONObject result = new JSONObject();
        if(udata == null) {
        	result.put("msg", "您要删除的数据不存在！");
        	result.put("status", false);
        } else {
        	this.supplierRepository.delete(id);
        	
        	result.put("msg", "删除id为：" + id + " 的数据成功！");
        	result.put("status", true);
        }
        return result.toString();
    }
    
    @RequestMapping("/data/upload")
    @ResponseBody
    public String fileUpload(@RequestParam("file")MultipartFile file) throws Exception{
       if(!file.isEmpty()){
    	   String filepath = file.getOriginalFilename();
        	String fileType = filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());
       		InputStream is = null;
       		Workbook wb = null;
       		try {
       			is = file.getInputStream();

       			if (fileType.equals("xls")) {
       				wb = new HSSFWorkbook(is);
       			} else if (fileType.equals("xlsx")) {
       				wb = new XSSFWorkbook(is);
       			} else {
       				throw new Exception("读取的不是excel文件");
       			}

       			Sheet sheet = wb.getSheetAt(0); //第一个sheet

       			List<Supplier> suppliers = new ArrayList<Supplier>();
       			int rowSize = sheet.getLastRowNum() + 1;
       			System.out.println("total row:" + rowSize);
       			for (int j = 1; j < rowSize; j++) {//遍历行 第一行为标题，不读
       				try{
       					Row row = sheet.getRow(j);
       					if (row == null || row.getCell(0) == null) {//略过空行
       						break;
       					}
       					Supplier supplier = new Supplier();
       					supplier.setSupplier(row.getCell(0).getStringCellValue().trim());
       					supplier.setSupplierPhone(row.getCell(1).getStringCellValue().trim());
       					supplier.setManufacturerName(row.getCell(2).getStringCellValue().trim());
       					supplier.setProductName(row.getCell(3).getStringCellValue().trim());
       					
       					int cellType = row.getCell(4).getCellType();
       					if(cellType == Cell.CELL_TYPE_NUMERIC) {
       						supplier.setProductSpec(String.valueOf(row.getCell(4).getNumericCellValue()));
       					} else if(cellType == Cell.CELL_TYPE_STRING) {
       						supplier.setProductSpec(row.getCell(4).getStringCellValue());
       					}
       					
       					supplier.setProductLicense(row.getCell(5).getStringCellValue().trim());
       					supplier.setProductLicenseDate(row.getCell(6).getDateCellValue());
       					supplier.setCertificateNo(row.getCell(7).getStringCellValue().trim());
       					supplier.setRegistrationDate(row.getCell(8).getDateCellValue());
       					supplier.setCertificateDate(row.getCell(9).getDateCellValue());
       					supplier.setLicenseDate(row.getCell(10).getDateCellValue());

       					cellType = row.getCell(11).getCellType();
       					if(cellType == Cell.CELL_TYPE_NUMERIC) {
       						supplier.setPrice(row.getCell(11).getNumericCellValue());
       					} else if(cellType == Cell.CELL_TYPE_STRING) {
       						supplier.setPrice(Double.valueOf(row.getCell(11).getStringCellValue()));
       					}

       					supplier.setPo(row.getCell(12).getStringCellValue().trim());
       					supplier.setBrand(row.getCell(13).getStringCellValue().trim());
       					supplier.setLegal(row.getCell(14).getStringCellValue().trim());

       					cellType = row.getCell(15).getCellType();
       					if(cellType == Cell.CELL_TYPE_NUMERIC) {
       						supplier.setLegalPhone(String.valueOf(row.getCell(15).getNumericCellValue()));
       					} else if(cellType == Cell.CELL_TYPE_STRING) {
       						supplier.setLegalPhone(row.getCell(15).getStringCellValue().trim());
       					}

       					supplier.setManager(row.getCell(16).getStringCellValue().trim());

       					cellType = row.getCell(17).getCellType();
       					if(cellType == Cell.CELL_TYPE_NUMERIC) {
       						supplier.setManagerPhone(String.valueOf(row.getCell(17).getNumericCellValue()));
       					} else if(cellType == Cell.CELL_TYPE_STRING) {
       						supplier.setManagerPhone(row.getCell(17).getStringCellValue().trim());
       					}

       					supplier.setSalesman(row.getCell(18).getStringCellValue().trim());

       					cellType = row.getCell(19).getCellType();
       					if(cellType == Cell.CELL_TYPE_NUMERIC) {
       						supplier.setSalesmanPhone(String.valueOf(row.getCell(19).getNumericCellValue()));
       					} else if(cellType == Cell.CELL_TYPE_STRING) {
       						supplier.setSalesmanPhone(row.getCell(19).getStringCellValue().trim());
       					}

       					supplier.setCompanyAddr(row.getCell(20).getStringCellValue().trim());
       					supplier.setRegistrationAddr(row.getCell(21).getStringCellValue().trim());

       					suppliers.add(supplier);
       				} catch(Exception e) {
       					System.out.println("导入第" + j + "行失败");
       					throw(e);
       				}
       			}
       			this.supplierRepository.save(suppliers);
       			this.supplierRepository.flush();
       			
       			return "导入成功";
       		} catch (FileNotFoundException e) {
       			e.printStackTrace();
       			return"导入失败,"+e.getMessage();
       		} finally {
       			if (is != null) {
       				is.close();
       			}
       		}
       }else{
           return"导入失败，因为文件是空的.";
       }
    }
}