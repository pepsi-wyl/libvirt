// Example Bootstrap Table Events
(function() {
	$('#classList').bootstrapTable({
		url: "bootstrap_table_test.json",
		pagination: true,
		iconSize: 'outline',
		toolbar: '#classToolbar',
		clickToSelect: true,  
		icons: {
			refresh: 'glyphicon-repeat',
			toggle: 'glyphicon-list-alt',
			columns: 'glyphicon-list'
		},
        columns:[{  
           field:"state",
			checkbox:true,
			align : 'center' ,
			formatter:function(value,row,index){
			   if (row.state == true)
			       return {
			           checked : true//设置选中
			       };
			   return value;
			}
    	}]  
	});
	$('#classList').bootstrapTable('hideColumn', 'id');
	$('#classList').bootstrapTable('hideColumn', 'status');
})();


/*状态函数*/
function statusFormatter(value, row, index) {
	if(row.status == 1){
		return '<span style="color: rgb(105, 170, 71);">运行</span> ';
	}else if(row.status == 0){
		return '<span style="color: rgb(221, 90, 67);">关机</span> ';
	}else{
		return '<span>状态错误</span> ';
	}
}

window.statusEvents={
	'click .distrib': function(e, value, row, index) {
		var id = row.id;
		location.href="distribute.html?type=2&id="+id;
	}
}

/*表格行操作函数---启动 挂起 重启 关机 删除 查看*/
function actionFormatter(value, row, index) {
	return '<a class="start">启动</a>' + '&nbsp;&nbsp;<a class="up">挂起</a>' + '&nbsp;&nbsp;<a class="restart">重启</a>' + '&nbsp;&nbsp;<a class="down">关机</a>' + '&nbsp;&nbsp;<a class="delete">删除</a>' + '&nbsp;&nbsp;<a class="check">查看</a>' ;
}

//表格  - 操作 - 事件
window.actionEvents = {
	'click .start': function(e, value, row, index) {//启动
		//启动操作
		/*location.href = "class_update.html"*/
		var number = row.number;
		console.log(number);
		
		document.location.href = "/admin/class/update?number="+number;
	},
	'click .up': function(e, value, row, index) {//挂起
		//挂起操作
		/*location.href = "class_update.html"*/
		var number = row.number;
		console.log(number);
		
		document.location.href = "/admin/class/update?number="+number;
	},
	'click .restart': function(e, value, row, index) {//重启
		//重启操作
		/*location.href = "class_update.html"*/
		var number = row.number;
		console.log(number);
		
		document.location.href = "/admin/class/update?number="+number;
	},
	'click .down': function(e, value, row, index) {//关机
		//关机操作
		/*location.href = "class_update.html"*/
		var number = row.number;
		console.log(number);
		
		document.location.href = "/admin/class/update?number="+number;
	},
	'click .delete': function(e, value, row, index) {//删除
		//删除操作
		if(!confirm("确认要删除吗？")) {
			return;
		}
		var id = row.id;
		var number = row.number;
		//console.log(number);
		
		/*$.ajax({
    		type: "POST",
			url: "/admin/class/delete",
			data: {number: number,id:id},
			dataType: "JSON",
			success:function(data){
				if(data.code==1){*/
					//删除成功
                   /* $("#success").show();
                    $("#classList").bootstrapTable('remove', {
						field: 'id',
						values: [parseInt(id)]
					});
					setTimeout(function(){
						$("#success").hide();
					},2000);
				}else{
                   $("#fail").show();
					setTimeout(function(){
						$("#fail").hide();
					},2000);
				}
			}
    	});*/
	},
	'click .check': function(e, value, row, index) {//查看
		//查看操作
		/*location.href = "class_update.html"*/
		var number = row.number;
		console.log(number);
		
		document.location.href = "/admin/class/update?number="+number;
	},
	
}

