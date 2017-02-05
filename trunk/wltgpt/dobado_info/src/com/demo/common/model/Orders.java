package com.demo.common.model;



import java.util.List;

import com.demo.common.model.base.BaseOrders;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Orders extends BaseOrders<Orders> {
	public static final Orders dao = new Orders();
	public Page<Orders> pageOrders(int page,int size,String orno,String orname,String usname,String usphon,String atime,String btime,int status){
		String sqla="select o.id,o.zchannel_id,o.orderNo,o.orderMoney,o.userName,o.phone,o.addTime,o.status,o.address,z.zchannel_name ";
		String sqlb =" FROM t_orders o LEFT JOIN t_zchannel z ON o.zchannel_id = z.zchannel_id where 1=1";
		if(!orno.equals("")){
			sqlb +=" AND o.orderNo LIKE '%"+orno+"%'";
		}
		if(!orname.equals("")){
			sqlb +=" AND z.zchannel_name LIKE '%"+orname+"%'";
		}
		if(!usname.equals("")){
			sqlb +=" AND o.userName LIKE '%"+usname+"%'";
		}
		if(!usphon.equals("")){
			sqlb +=" AND o.phone LIKE '%"+usphon+"%'";
		}
		if(!atime.equals("")){
			sqlb +=" AND o.addTime > '"+atime+"'";
		}
		if(!btime.equals("")){
			sqlb +=" AND o.addTime < '"+btime+"'";
		}
		if(status !=0){
			sqlb +=" AND o.status = "+status;
		}
		sqlb +=" order by o.addTime desc";
		return paginate(page, size, sqla, sqlb);
	}
	public Page<Orders> pageTjorder(int page,int size,String atime,String btime,String goodsName,String zchName){
		String sqla="SELECT o.id,g.goods_name,g.price,p.package_number,z.zchannel_name,o.addTime";
		String sqlb="FROM t_orders o "
				+ "LEFT JOIN t_goods g ON o.goodsId = g.goods_id "
				+ "LEFT JOIN t_package p ON o.package_id = p.package_id "
				+ "LEFT JOIN t_zchannel z ON o.zchannel_id = z.zchannel_id where 1=1 ";
		if(atime !=null && !atime.equals("")){
			sqlb += " and o.addTime > '"+atime+"'";
		}
		if(!btime.equals("")){
			sqlb += " and o.addTime < '"+btime+"'";
		}
		if(!goodsName.equals("")){
			sqlb += " and g.goods_name like '%"+goodsName+"%' ";
		}
		if(!zchName.equals("")){
			sqlb += " and z.zchannel_name like '%"+zchName+"%' ";
		}
		sqlb += "ORDER BY o.addTime desc";
		return paginate(page, size, sqla, sqlb);
	}
	public Orders getOrder(String orderNo){
		return findFirst("SELECT o.*,g.goods_name,g.price FROM t_orders o LEFT JOIN t_goods g ON o.goodsId = g.goods_id WHERE o.orderNo = ?",orderNo);
	}
	public Orders setOrderByid(int id){
		return findFirst("SELECT o.*,g.goods_name,g.price FROM t_orders o LEFT JOIN t_goods g ON o.goodsId = g.goods_id WHERE o.id = ?",id);
	}
	
	public List<Record> list(String atime,String btime,String goodsName,String zchName){
		String sql="SELECT g.goods_name,g.price,p.package_number,z.zchannel_name,o.addTime FROM t_orders o "
				+ "LEFT JOIN t_goods g ON o.goodsId = g.goods_id "
				+ "LEFT JOIN t_package p ON o.package_id = p.package_id "
				+ "LEFT JOIN t_zchannel z ON o.zchannel_id = z.zchannel_id where 1=1 ";
		if(atime !=null && !atime.equals("")){
			sql += " and o.addTime > '"+atime+"'";
		}
		if(!btime.equals("")){
			sql += " and o.addTime < '"+btime+"'";
		}
		if(!goodsName.equals("")){
			sql += " and g.goods_name like '%"+goodsName+"%' ";
		}
		if(!zchName.equals("")){
			sql += " and z.zchannel_name like '%"+zchName+"%' ";
		}
		sql += "ORDER BY o.addTime desc";
		return Db.find(sql);
	}
}
