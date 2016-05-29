(function($){

	function buildRowHtml(config,id,parentId,row, currentLevel,displayEagly){
		var treeColumnIndex = config.treeColumnIndex;
		var columns = config.columns;

		var openStatus = "N";
		var dispaly = "none";
		if(displayEagly){
			openStatus = "N"
			dispaly = "";
		}else{
			var displayLevel = config.displayLevel;
			if(currentLevel < displayLevel) openStatus = "Y";//说明当前行已展开
			if(currentLevel<=displayLevel) dispaly = ""; //只展示级别<=displayLevel的节点
		}
		
		
		var result = "<tr id='" + id + "' pid='" + parentId
			+ "' openStatus='" + openStatus + "' data='" + JSON.stringify(row) 
			+ "' rowIndex='" + config.rownum++ 
			+ "' level="+currentLevel
			+ " style='display:" + dispaly + "'>";
		for(var j=0;j<columns.length;j++){
			var col = columns[j];
			result += "<td align='" + (col.dataAlign || config.dataAlign) + "'";

			//层次缩进
			if(j==treeColumnIndex){
				result += " style='text-indent:" + (parseInt((config.indentation))*(currentLevel-1)) + "px;'> ";
			}else{
				result += ">";
			}

			//节点图标:有isLeaf属性就根据isLeaf来判断否则根据children属性判断
			if(j==treeColumnIndex){
				if(row.isLeaf != undefined ){
					if(row.isLeaf){
						result += "<img src='" + config.iconLeaf + "' class='image_nohand'>";
					}else{
						var picUrl = (openStatus=="Y")? config.iconFolderOpen : config.iconFolderClose;
						result += "<img folder='Y' trid='" + id + "' src='" + picUrl + "' class='image_hand'>";
					}
				}else{
					if(row.children){
						var picUrl = (openStatus=="Y")? config.iconFolderOpen : config.iconFolderClose;
						result += "<img folder='Y' trid='" + id + "' src='" + picUrl + "' class='image_hand'>";
					}else{
						result += "<img src='" + config.iconLeaf + "' class='image_nohand'>";
					}
				}
			}
			
			//单元格内容
			if(col.handler){
				if((col.folderHidden || false) && row.children){
					result += "</td>";
				}else{
					result += (eval(col.handler + ".call(new Object(), row, col)") || "") + "</td>";
				}
			}else{
				result += (row[col.dataField] || "") + "</td>";
			}
		}
		return result += "</tr>";
	}





	function remoteCall(url,restricts){
		if(restricts==""){
			restricts = {};
		}
		$.ajax({
			type:'POST',
			data:restricts,
			async:false,
			url:url,
			success:function(data,success){
				if(success){
					return eval('('+data+')');
				}else{
					console.error('fail to call '+url);
				}
			}
		});
	}

	

	var methods = {

		init:function(options){
			var config = $.extend({}, this.TreeGrid.defaults, options);
			 
			return this.each(function() {
                var $this = $(this);
                $this.TreeGrid('createContainer', config)
					.TreeGrid('drawHeader', config)
					.TreeGrid('drawData', config)
					.TreeGrid('bindEvent', config);
            });
		},
		createContainer:function(config){
			var id = config.id || "TreeGrid"+$.TreeGrid.COUNT++;
			
			var result = "<table id='" + id + "' cellspacing=0 cellpadding=0 width='" 
				+ (config.width) 
				+ "' class='TreeGrid'>";
			result += "</table>";
			
			//对每一个符合条件的jquery对象对其执行以下函数(一般就一个div，因为一般是根据id获取对象的)
			return this.each(function(){
				$(this).append(result);
			});
			
		},

		drawHeader:function(config){
			var result = "<tr id='TRH' class='header' height='" + config.headerHeight + "'>";
			var cols = config.columns;
			for(i=0;i<cols.length;i++){
				var col = cols[i];
				result += "<td align='" + (col.headerAlign || config.headerAlign) + "' width='" 
					+ (col.width || "") + "'>" + (col.headerText || "") + "</td>";
			}
			result += "</tr>";
			return this.each(function(){
				$(this).find("table").append(result);
			});
			
			
		},

		drawData : function(config){
			var rows = {};
			if(config.data){
				rows = config.data;
			}else if(config.remoteUrl!=''){
				rows = remoteCall(config.remoteUrl,config.remoteRestricts);
			}else{
				console.error('pls config the data source');
			}


			var columns = config.columns;
			return this.each(function(){
				$(this).TreeGrid('drawDataRecursive', config,rows,"TRH",1);
			});
		},

		drawDataRecursive:function(config,rows,parentId,currentLevel){
			
			for(var i=0;i<rows.length;i++){
				var id = parentId + "_" + i;
				var row = rows[i];
				var result = buildRowHtml(config,id,parentId,row,currentLevel);
				this.find("table").append(result);

				if(row.children){
					this.TreeGrid('drawDataRecursive', config,row.children,id, currentLevel+1);
				}
			}
		},
		
		bindEvent:function(config){
			
			var $context = this;
			var $table = $context.find("table");

			//hover css:因为live不支持hover事件，所以采用以下方法
			if(config.showHoverCss){
				$table.find("tr").live({
					mouseenter:function(){
						if($(this).prop("class") && $(this).prop("class") == "header") return;
						$(this).addClass("row_hover");
					},
					mouseleave:function(){
						$(this).removeClass("row_hover");
					}
				});
					
				
			}

			//bind click to <tr>
			$table.find("tr").live("click", function(){
				
				$table.find("tr").removeClass("row_active");
				$(this).addClass("row_active");
				
				var id = $(this).prop("id");
				var rowIndex = $(this).attr("rowIndex");
				var data = $(this).attr("data");

				if(config.itemClick){
					config.itemClick(id,rowIndex,data);
				}
			});

			//bind click to image
			$table.find("img[folder='Y']").live("click", function(){
				var trid = $(this).attr("trid"); 
				var isOpen = $table.find("#" + trid).attr("openStatus");
				var statusAfterClick = (isOpen == "Y") ? "N" : "Y";//当前为打开状态则关闭
				$table.find("#" + trid).attr("openStatus", statusAfterClick);
				
				if(statusAfterClick == "N"){ //隐藏子节点
					$table.find("#"+trid).find("img[folder='Y']").attr("src", config.iconFolderClose);
					$table.find("tr[id^=" + trid + "_]").css("display", "none");
				}else{ //显示子节点
					$table.find("#"+trid).find("img[folder='Y']").attr("src", config.iconFolderOpen);
					$(this).TreeGrid("showNextLevelRecursive",config,$context,trid);
				}
			});

			return this.each(function(){
				//do nothing
				//'this' represent div element, deliver this feature to next call
			});
		},

		showNextLevelRecursive:function(config,$context,trid){
			var $table = $context.find("table");
			//只有当前行处于打开状态才可以显示下一行
			var isOpen = $table.find("#" + trid).attr("openStatus");
			var currentLevel = parseInt($table.find("#" + trid).attr("level"));
			
			if(isOpen == "Y"){
				var nextTrs = $table.find("tr[pid=" + trid + "]");
				
				if(config.delayLoad && nextTrs.length==0){
					//延迟加载且当前没有数据
//					var rows = remoteCall(config.remoteUrl,config.remoteRestricts);
					var rows = [{name: "节点6",code:"c6",isLeaf:false},
						{name: "节点7",code:"c7",isLeaf:true}];
					this.TreeGrid('appendData', config,$table,rows,trid,currentLevel+1);
					
				}else{//只有确定该行已经有子级(nextTrs.length>0)才可以循环展示
					nextTrs.css("display", "");
					for(var i=0;i<nextTrs.length;i++){
						this.TreeGrid("showNextLevelRecursive",config,$context,nextTrs[i].id);
					}
				}
			}
		},
		
		//将数据添加在指定的parentId后面,每次添加的数据都会紧跟parentId,这里最好倒序添加数据
		appendData:function(config,$context,rows,parentId,currentLevel){
			
			for(var i=rows.length-1;i>=0;i--){
				var id = parentId + "_" + i;
				var row = rows[i];
				var result = buildRowHtml(config,id,parentId,row,currentLevel,true);
				$context.find("#"+parentId).after(result);
			}
		}



	};


	$.fn.TreeGrid = function(method) {
        if (methods[method]) {
            return methods[ method ].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method with name ' + method + ' does not exists for jQuery.treegrid');
        }
    };

	
	$.TreeGrid = {};
	$.TreeGrid.COUNT = 1;


	$.fn.TreeGrid.defaults = {
		
		width:'100%',
		headerAlign: 'center',
		headerHeight: '25',
		dataAlign: 'center',
		indentation: '20',
		displayLevel:1,//页面默认看到所有的一级节点
		treeColumnIndex:0,//默认第0列是树
		rownum:0,
		showHoverCss:true,
		delayLoad:true,
		iconFolderOpen : 'images/folderOpen.gif',
		iconFolderClose : 'images/folderClose.gif',
		iconLeaf : 'images/defaultLeaf.gif',
		itemClick: function(id,rowIndex,data){console.log(id);},

		//remote props
		remoteUrl:"",
		remoteRestricts:""
	};



})(jQuery)