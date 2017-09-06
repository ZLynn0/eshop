$(function(){
	//添加订单；
	$('#btnsave').on('click',function(){
		var isValid=$('form').form('validate');
		if(isValid){
			$.ajax({
				type:'post',
				data:$('#f3').serialize(),
				url:'/web_project/ordercontroller.do?type=add',
				success:function(data){
					if(data=="1"){
						$.messager.alert('添加提示','添加成功','info');
						//清空input；
						$('#f3').form('clear');
						//关闭dialog；
						$('#dialog').dialog('close');
						//刷新datagrid；
						$('#tb').datagrid('reload');
					}
				}			
			})
		}	
	})
	$('#btncancel').on('click',function(){
		//清空input；
		$('#f3').form('clear');
		//关闭dialog；
		$('#dialog').dialog('close');
	
	})
	
	
	//加载t_order表数据，在easyui的datagrid控件中显示；
	$('#tb').datagrid({
		url:'/web_project/ordercontroller.do',
		title:'订单信息',
		iconCls:'icon-ok',
		idField:'orderid',
		collapsible:true,
		maximizable:true,
		singleSelect:true,
		rownumbers:true,
		resizable:true,
		queryParams:{type:'list'},
		loadMsg:'正在加载，请稍后......',
		toolbar:[{
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				//处理添加；
				//alert("ok")
				$('#dialog').dialog('open');
			}
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				var row=$('#tb').datagrid('getSelected');
				if(row==null){
					$.messager.alert('删除提示','请选择要删除的行','info');
				}
				if(row!=null){
					$.messager.confirm('确认删除', "您确认要删除:"+row.orderid+"订单数据吗", function(r){
						if (r){//实现删除；
						   $.ajax({
							   type:'post',
							   url:'/web_project/ordercontroller.do',
							   data:{type:'remove',orderid:row.orderid},
							   success:function(data){
								   if(data=="1"){
									   $.messager.alert('删除提示','删除成功','info');
									   //删除成功后重新加载；
									   $('#tb').datagrid('reload');
									   
								   }
							   }
						   })
						}
					});


				}
			}
		},'-',{
			text:'编辑',
			iconCls:'icon-edit',
			handler:function(){
				var row=$('#tb').datagrid('getSelected');
				
				if(row==null){
					$.messager.alert('提示','请选择要修改的行','info');
				}else{
					//设置默认值；
					$('#orderid').textbox('setValue',row.orderid);					
					$('#gid').textbox('setValue',row.gid);
					$('#orderid').textbox({readonly:true});
					$('#userid').textbox('setValue',row.userid);
					$('#totalprice').textbox('setValue',row.totalprice);
					$('#orderDate').textbox('setValue',row.orderDate);
					//弹出修改对话框；
					$('#dialog').dialog({
						closed:false,
						title:'修改用户',
						iconCls:'icon-edit',
						buttons:[{
							text:'保存修改',iconCls:'icon-save',handler:function(){
								var isValid=$('#f3').form('validate');	
								if(isValid){
									$.ajax({
										type:'post',
										data:$('#f3').serialize(),
										url:'/web_project/ordercontroller.do?type=edit&orderid='+row.orderid,
								        success:function(data){
								        
													if(data=="1"){
														$.messager.alert('修改提示','修改成功','info');
														//清空input；
														$('#f3').form('clear');
														//关闭dialog；
														$('#dialog').dialog('close');
														//刷新datagrid；
														$('#tb').datagrid('reload');
													}
								        }
									})

								}
							
							}
						}]
					})
				}
			}
		}],
		columns:[[
		          {field:'chk',checkbox:true },
		          {field:'orderid',title:'订单编号'},
		          {field:'gid',title:'商品编号',hidden:true},
		          {field:'userid',title:'用户编号'},
		          {field:'totalprice',title:'总价格'},
		          {field:'orderDate',title:'订单日期',formatter:function(orderdate){
		        	  var date=new Date(orderdate);
		        	  return date.toLocaleDateString();
		          }}
		          ]],
		          view: detailview, 
		  		  detailFormatter:function(index,row){    
		  	        return '<table id="table-' + index + '" style="height: auto"></table>';    
		  	    },    
		  	    onExpandRow: function(index,row){    
		  	        $('#table-'+index).datagrid({    
		  	            url:'/web_project/orderdetailcontroller.do?type=orderdetail',
		  	    		queryParams:{orderid:row.orderid},
		  	    		title:'商品详情',
		  	    		iconCls:'icon-ok',
		  	    		collapsible:true,
		  	    		singleSelect:true,
		  	    		rownumbers:true,
		  	    		columns:[[{
		  	    			field:'orderid',title:'订单编号',hidden:true
		  	    		},{
		  	    			field:'orderdetailid',title:'订单详情编号'
		  	    		},{
		  	    			field:'gtitle',title:'商品名称'
		  	    		},{
		  	    			field:'gnumber',title:'商品数量'
		  	    		},{
		  	    			field:'gsaleprice',title:'商品价格'
		  	    		}]],
		  	            onLoad:function(){    
		  	                $('#tb').datagrid('fixDetailRowHeight',index);    
		  	            }    
		  	        });    
		  	    }
	})
	
})

