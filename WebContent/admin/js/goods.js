$(function(){
	$('#cate').combobox({
		url:'/web_project/catecontroller.do',
		valueField:'cid',
		textField:'cname',
		onSelect:function(data){//通过参数传过来，参数名字任意；
			//var cid=$('#cate').combobox('getValue');
			loadGoods(data.cid);
		},
		onLoadSuccess:function(){//等待数据加载完毕，才能设置默认项；
			var datas=$(this).combobox('getData');
			if(datas.length>0){//表示已绑定了数据；
				$(this).combobox('setValue',datas[0].cid);//设置默认项；
				var cid=$('#cate').combobox('getValue');//取出默认项的值
				loadGoods(cid);//加载默认类别数据；
			}
		}
	})



	//添加商品；
	$('#btnsave').on('click',function(){
		var isValid=$('form').form('validate');
		if(isValid){
			$.ajax({
				type:'post',
				data:$('#f2').serialize(),
				url:'/web_project/goodscontroller.do?type=add',
				success:function(data){
					if(data=="1"){
						$.messager.alert('添加提示','添加成功','info');
						//清空input；
						$('#f2').form('clear');
						//关闭dialog；
						$('#dialog').dialog('close');
						//刷新datagrid；
						$('#tb').datagrid('reload');
					}else{
						$.messager.alert('添加提示','添加失败','error');
					}
				}			
			})
		}	
	})
		$('#btncancel').on('click',function(){
		//清空input；
		$('#f2').form('clear');
		//关闭dialog；
		$('#dialog').dialog('close');
	
	})
function loadGoods(cid){
	$('#tb').datagrid({
		url:'/web_project/goodscontroller.do?type=list',
		queryParams:{cid:cid},
		title:'商品数据',
		iconCls:'icon-ok',
		collapsible:true,
		pagination:true,
		singleSelect:true,
		rownumbers:true,
		resizable:true,
		pageList:[10,15,20,25,30],
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
				var row=$('#tb').datagrid('getSelected');
				if(row==null){
					$.messager.alert('删除提示','请选择要删除的行','info');
				}
				if(row!=null){
					$.messager.confirm('确认删除', "您确认要删除:"+row.gid+"用户数据吗", function(r){
						if (r){//实现删除；
						   $.ajax({
							   type:'post',
							   url:'/web_project/goodscontroller.do',
							   data:{type:'remove',gid:row.gid},
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
					$('#gid').textbox('setValue',row.gid);	
					$('#gid').textbox({readonly:true});
					$('#gtitle').textbox('setValue',row.gtitle);
					$('#gauthor').textbox('setValue',row.gauthor);
					$('#gdesc').textbox('setValue',row.gdesc);
					$('#gsaleprice').textbox('setValue',row.gsaleprice);
					$('#ginprice').textbox('setValue',row.ginprice);
					$('#gimg').textbox('setValue',row.gimg);
					$('#gclicks').textbox('setValue',row.gclicks);
					$('#cid').textbox('setValue',row.cid);
					$('#pid').textbox('setValue',row.pid);
					//弹出修改对话框；
					$('#dialog').dialog({
						closed:false,
						title:'修改商品',
						iconCls:'icon-edit',
						buttons:[{
							text:'保存修改',iconCls:'icon-save',handler:function(){
								var isValid=$('#f2').form('validate');	
								if(isValid){
									$.ajax({
										type:'post',
										data:$('#f2').serialize(),
										url:'/web_project/goodscontroller.do?type=edit&gid='+row.gid,
								        success:function(data){
													if(data=="1"){
														$.messager.alert('修改提示','修改成功','info');
														//清空input；
														$('#f2').form('clear');
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
		          {field:'gid',title:'商品编码'},
		          {field:'gtitle',title:'商品名称'},
		          {field:'gauthor',title:'作者'},
		          {field:'gdesc',title:'简介',hidden:true},
		          {field:'gsaleprice',title:'售价'},
		          {field:'ginprice',title:'原价'},
		         /* {field:'gimg',title:'图片编号'},*/
		          {field:'gimg',title:'商品图片',width:'10%',align:'center',formatter:formatImg},
		          {field:'gclicks',title:'点击量'},
		          {field:'cid',title:'类别编辑'},
		          {field:'pid',title:'出版社编号'}
		          ]]

	})
	}
})

		        //格式化商品图片
		          function formatImg(val,row,index){
		          	return "<img style='height: 98px;width: 65px;' src='/web_project/images/bookcover/"+val+".jpg'/>";
		          }
