package com.contact.activity;

import com.uitest.util.UiAutomatorBase;

public class EditContactActivity extends UiAutomatorBase{
/**
 *  新建联系人页：
 * 属性：
 * 组件ID: 页面各个组件ID按功能命名
 * 页面对象：页面各个UI对象
 * 方法：
 * 选择输入框输入内容（姓名、公司、部门）
 * 添加头像（拍照、从图库中选择照片）
 * 保存并返回
 * 添加新属性
 * 删除已有属性
 * 选择标签
 * 选择分组
 * 选择来电秀
 * 创建点返回选择是|否
 */
	
	private static  String contactName = "姓名";
	private static  String company = "公司";
	private static  String department = "部门";
	private static  String fphone = "电话号码";
	
	/**
	 * 设置联系人名称
	 * @param name
	 */
	public static void setContactName(String name){
		inputTextByName(contactName, name);
	}
	/**
	 * 设置公司名称
	 * @param name
	 */
	public static void setCompany(String name){
		inputTextByName(company, name);
	}
	
	/**
	 * 设置部门名称
	 */
	public static void setDepartment(String name){
		inputTextByName(department, name);
	}
	
	/**
	 * 设置电话号码
	 * @param phone
	 */
	public static void setPhone(String phone){
		inputTextByName(fphone, phone);
	}
	
	/**
	 * 点击保存
	 */
	public static void saveContact(){
		clickById("iab_ib_action");
	}
	
	
}
