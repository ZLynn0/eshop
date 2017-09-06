$(function(){
	//添加新闻；
	$('#btnsave').on('click',function(){
		var isValid=$('form').form('validate');
		if(isValid){
			$.ajax({
				type:'post',
				data:$('#f3').serialize(),
				url:'/web_project/newscontroller.do?type=add',
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
	
	
	//加载t_news表数据，在easyui的datagrid控件中显示；
	$('#tb').datagrid({
		url:'/web_project/newscontroller.do',
		title:'新闻信息',
		iconCls:'icon-ok',
		idField:'tid',
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
					$.messager.confirm('确认删除', "您确认要删除:"+row.tid+"新闻数据吗", function(r){
						if (r){//实现删除；
						   $.ajax({
							   type:'post',
							   url:'/web_project/newscontroller.do',
							   data:{type:'remove',tid:row.tid},
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
					$('#tid').textbox('setValue',row.tid);					
					$('#title').textbox('setValue',row.title);
					$('#tid').textbox({readonly:true});
					$('#tcontent').textbox('setValue',row.tcontent);
					$('#tpubdate').textbox('setValue',row.tpubdate);
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
										url:'/web_project/newscontroller.do?type=edit&tid='+row.tid,
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
		          {field:'tid',title:'新闻编号'},
		          {field:'title',title:'新闻标题',width:200},
		          {field:'tcontent',title:'新闻内容',width:300},
		          {field:'tpubdate',title:'新闻日期',formatter:function(tpubdate){
		        	 console.log(tpubdate)
		        	  var date=new Date(tpubdate);
		        	  return date.toLocaleDateString();
		          }}
		          ]],
		       
	})
})

