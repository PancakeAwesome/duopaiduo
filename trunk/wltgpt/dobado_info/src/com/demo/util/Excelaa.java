package com.demo.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.ext.render.excel.PoiRender;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;



public class Excelaa extends Controller {

	public void toExcel(){
		String[]  header={"编号","性别","姓名","班级"};
	    String[]  columns={"id","sex","name","class"};
	    //List<Student> list= Student.me.find("select * from student");
	    List<Record> list = Db.find("select * from student");
	    try{
	        PoiRender me = PoiRender.me(list).fileName("test.xls").sheetName("rank").headers(header).columns(columns).cellWidth(5000).headerRow(1);
	        render(me);
	    }catch(Exception e){
	         
	        renderNull();
	    }    

		}
}
