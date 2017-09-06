$(function(){
	//添加用户；
	$('#btnsave').on('click',function(){
		var isValid=$('form').form('validate');
		if(isValid){
			$.ajax({
				type:'post',
				data:$('#f1').serialize(),
				url:'/web_project/usercontroller.do?type=add',
				success:function(data){
					if(data=="1"){
						$.messager.alert('添加提示','添加成功','info');
						//清空input；
						$('#f1').form('clear');
						//关闭dialog；
						$('#dialog').dialog('close');
						//刷新datagrid；
						$('#tab').datagrid('reload');
					}
				}			
			})
		}	
	})
	$('#btncancel').on('click',function(){
		//清空input；
		$('#f1').form('clear');
		//关闭dialog；
		$('#dialog').dialog('close');
	
	})
	
	//加载t_user表数据，在easyui的datagrid控件中显示；
	$('#tab').datagrid({
		url:'/web_project/usercontroller.do',
		title:'用户信息',
		iconCls:'icon-ok',
		idField:'userid',
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
				$('#dialog').dialog('open');
			}
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				//$.messager.alert('处理删除','即将执行删除操作','info');
				//拿到删除的数据；
				//console.log($('#tab').datagrid('getdata'));
				//console.log($('#tab').datagrid('getRows'));
				//console.log($('#tab').datagrid('getRowIndex'));
				//console.log($('#tab').datagrid('getChecked')[0].userid);
				//console.log($('#tab').datagrid('getSelected').userid);
				var row=$('#tab').datagrid('getSelected');
				if(row==null){
					$.messager.alert('删除提示','请选择要删除的行','info');
				}
				if(row!=null){
					$.messager.confirm('确认删除', "您确认要删除:"+row.uloginid+"用户数据吗", function(r){
						if (r){//实现删除；
						   $.ajax({
							   type:'post',
							   url:'/web_project/usercontroller.do',
							   data:{type:'remove',userid:row.userid},
							   success:function(data){
								   if(data=="1"){
									   $.messager.alert('删除提示','删除成功','info');
									   //删除成功后重新加载；
									   $('#tab').datagrid('reload');
									   
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
				var row=$('#tab').datagrid('getSelected');
		
				if(row==null){
					$.messager.alert('提示','请选择要修改的行','info');
				}else{
					//console.log(row.uemail);
					//设置默认值；
					$('#uemail').textbox('setValue',row.uemail);					
					$('#uname').textbox('setValue',row.uloginid);
					$('#uname').textbox({readonly:true});
					$('#upassword').textbox('setValue',row.upassword);
					$('#uaddress').textbox('setValue',row.uaddress);
					$('#utel').textbox('setValue',row.utel);
					$('#usex').textbox('setValue',row.usex);
					//弹出修改对话框；
					$('#dialog').dialog({
						closed:false,
						title:'修改用户',
						iconCls:'icon-edit',
						buttons:[{
							text:'保存修改',iconCls:'icon-save',handler:function(){
								var isValid=$('#f1').form('validate');	
								if(isValid){
									$.ajax({
										type:'post',
										data:$('#f1').serialize(),
										url:'/web_project/usercontroller.do?type=edit&userid='+row.userid,
								        success:function(data){
								        
													if(data=="1"){
														$.messager.alert('修改提示','修改成功','info');
														//清空input；
														$('#f1').form('clear');
														//关闭dialog；
														$('#dialog').dialog('close');
														//刷新datagrid；
														$('#tab').datagrid('reload');
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
		          {field:'userid',title:'用户编号'},
		          {field:'uemail',title:'邮箱'},
		          {field:'uloginid',title:'用户名'},
		          {field:'upassword',title:'密码'},
		          {field:'usex',title:'性别'},
		          {field:'uaddress',title:'地址'},
		          {field:'utel',title:'电话'},
		          {field:'ustateid',title:'状态编码'},
		          {field:'uroleid',title:'角色编码'}
		          ]]
	})
})