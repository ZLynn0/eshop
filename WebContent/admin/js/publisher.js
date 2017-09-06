$(function(){
	//添加用户；
	$('#btnsave').on('click',function(){
		var isValid=$('form').form('validate');
		if(isValid){
			$.ajax({
				type:'post',
				data:$('#f1').serialize(),
				url:'/web_project/publishercontroller.do?type=add',
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
	
	//加载t_userrole表数据，在easyui的datagrid控件中显示；
	$('#tab').datagrid({
		url:'/web_project/publishercontroller.do',
		title:'出版社信息',
		iconCls:'icon-ok',
		idField:'pid',
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
				var row=$('#tab').datagrid('getSelected');
				if(row==null){
					$.messager.alert('删除提示','请选择要删除的行','info');
				}
				if(row!=null){
					$.messager.confirm('确认删除', "您确认要删除:"+row.pid+"出版社数据吗", function(r){
						if (r){//实现删除；
						   $.ajax({
							   type:'post',
							   url:'/web_project/publishercontroller.do',
							   data:{type:'remove',pid:row.pid},
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
					//设置默认值；
					$('#pid').textbox('setValue',row.pid);					
					$('#pname').textbox('setValue',row.pname);
					$('#pid').textbox({readonly:true});

					//弹出修改对话框；
					$('#dialog').dialog({
						closed:false,
						title:'修改角色',
						iconCls:'icon-edit',
						buttons:[{
							text:'保存修改',iconCls:'icon-save',handler:function(){
								var isValid=$('#f1').form('validate');	
								if(isValid){
									$.ajax({
										type:'post',
										data:$('#f1').serialize(),
										url:'/web_project/publishercontroller.do?type=edit&pid='+row.pid,
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
		          {field:'pid',title:'出版社编号'},
		          {field:'pname',title:'出版社名称'}
		          ]]
	})
})