(function($){

	
	function remoteCall(url,restricts){
		if(restricts==""){
			restricts = {};
		}
		if(typeof(restricts)!="object"){
			console.error("remoteRestricts should be Ojbect");
		}
		var jsonData = null;
		$.ajax({
			type:'POST',
			data:restricts,
			async:false,
			url:url,
			success:function(data,success){
				if(success){
					jsonData = eval('('+data+')');
				}else{
					console.error('fail to call '+url);
				}
			}
		});
		
		return jsonData;
	}

	function getParentId(id){
		return id.substring(0,id.lastIndexOf('_'));
	}

	function getCurrentLevel(id){
		var pid = getParentId(id);
		var levelStr = $('#'+pid).attr('level');
		if( levelStr==undefined ){
			levelStr = 0;
		}
		return parseInt(levelStr)+1;
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
		
		//画表头
		drawHeader:function(config){
			var id = config.id+'H';
			$(this).find("table").append("<tr id='"+id+"' />");
			var $th = $('#'+id);
			$th.addClass('header');
			$th.attr('height',config.headerHeight);
			var cols = config.columns;
			for(i=0;i<cols.length;i++){
				var col = cols[i];
				$th.append("<td />");
				var $td = $th.find('td:last');
				$td.attr('align',(col.headerAlign || config.headerAlign) );
				$td.attr('width',(col.width || "") );
				$td.append(col.headerText || "");
			}
			return this.each(function(){
				
			});
			
			
		},

		//画数据
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
				$(this).TreeGrid('drawDataRecursive', config,config.id+'H',rows,config.pagination);
			});
		},

		drawDataRecursive:function(config,parentId,rows,appendPage){
			
			var levelStr = this.find('#'+parentId).attr('level');
			if(levelStr == undefined ){
				levelStr = 0;
			}
			var currentLevel = parseInt(levelStr)+1;
			var prevTrId = parentId;
			for(var i=0;i<rows.length;i++){
				var id = parentId + "_" + i;
				var row = rows[i];
				
				prevTrId = this.TreeGrid('drawDataTr',config,id,row,prevTrId,'dataTr' );
				//递归画子树
				if(row.children){
					prevTrId = this.TreeGrid('drawDataRecursive', config,id, row.children,appendPage);
				}
			}
			if(appendPage){
				var id = parentId + "_" + rows.length;
				prevTrId = this.TreeGrid('drawDataTr',config,id,row,prevTrId,'paginationTr' );
			}
			return prevTrId;
		},

		//画tr  prevTrId 该行的前一行id
		drawDataTr:function(config,id,row,prevTrId,trCls){

			this.find('#'+prevTrId).after("<tr id="+id+" />");
			var $tr = $('#'+id);
			var pid = getParentId(id);
			var currentLevel = getCurrentLevel(id);
			$tr.attr('level',currentLevel);
			$tr.attr('pid',pid );
			$tr.attr('rowIndex',config.rownum++);
			$tr.data('data',row);
			$tr.addClass(trCls);
			var openStatus = "N";
			var display = "none";
			var displayLevel = config.displayLevel;
			if(currentLevel<displayLevel) openStatus = "Y";//级别<displayLevel的节点,都为展开状态
			if(currentLevel<=displayLevel) display = ""; //级别<=displayLevel的节点,都要显示
			
			$tr.attr('openStatus',openStatus);
			$tr.css('display',display);
			
			if(trCls=='dataTr'){
				this.TreeGrid('drawDataTd',config,$tr);
			}else if(trCls=='paginationTr'){
				this.TreeGrid('drawPaginationTd',config,$tr);
			}
			
			return id;
		},

		//画td
		drawDataTd:function(config,$tr){
			var treeColumnIndex = config.treeColumnIndex;
			var columns = config.columns;
			var row = $tr.data('data');
			var trid = $tr.attr('id');
			var currentLevel = $tr.attr('level');
			var openStatus = $tr.attr('openStatus');
			for(var j=0;j<columns.length;j++){
				var col = columns[j];
				$tr.append("<td />");
				var $td = $tr.find('td:last');
				$td.attr('align',(col.dataAlign || config.dataAlign) );
				
				//层次缩进
				if(j==treeColumnIndex){
					$td.css('text-indent',parseInt(config.indentation)*(currentLevel-1) );
					$td.append('<span />');
					var $img = $td.find('span');
					$img.attr('trid',trid);
					if(row.children){
						$img.addClass('folder');
						var nodeClass = (openStatus=="Y")? "nodeOpen" : "nodeClose";
						$img.addClass(nodeClass);
					}else{
						$img.addClass('image_nohand');
						$img.addClass('nodeLeaf');
					}
				}

				$td.append( row[col.dataField] || "");

			}
		},

		drawPaginationTd:function(config,$tr){
			$tr.append("<td />");
			var $td = $tr.find('td:last');
			$td.addClass('pagination');
			$td.attr('colspan',3);
			$td.append("<span class='first'></span>");
			$td.append("<span class='page'><input /></span>");
			$td.append("<span class='next'></span>");
			$td.append("<span class='last'></span>");
			
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
			$table.find("tr.dataTr").live("click", function(){
				
				$table.find("tr").removeClass("row_active");
				$(this).addClass("row_active");
				
				var id = $(this).prop("id");
				var rowIndex = $(this).attr("rowIndex");
				var data = $(this).data("data");

				if(config.itemClick){
					config.itemClick(id,rowIndex,data);
				}
			});

			//bind click to image
			$table.find("span.folder").live("click", function(){
				var trid = $(this).attr("trid"); 
				var $tr = $table.find("#" + trid);
				var isOpen = $tr.attr("openStatus");
				var statusAfterClick = (isOpen == "Y") ? "N" : "Y";//当前为打开状态则关闭
				$tr.attr("openStatus", statusAfterClick);
				
				if(statusAfterClick == "N"){ //隐藏子节点
					$tr.find("span.folder").removeClass("nodeOpen").addClass("nodeClose");
					$table.find("tr[id^=" + trid + "_]").css("display", "none");
				}else{ //显示子节点
					$tr.find("span.folder").removeClass("nodeClose").addClass("nodeOpen");
					$context.TreeGrid("showNextLevelRecursive",config,trid);
				}
			});

			return this.each(function(){
				//do nothing
				//'this' represent div element, deliver this feature to next call
			});
		},

		showNextLevelRecursive:function(config,trid){
			var $table = this.find("table");
			
			var $tr = $table.find("#" + trid);
			var isOpen = $tr.attr("openStatus");
			//只有当前行处于打开状态才可以显示下一行
			if(isOpen == "Y"){
				//找出所有trid的子行
				var nextTrs = $table.find("tr[pid=" + trid + "]");
				if(nextTrs.length>0){
					for(var i=0;i<nextTrs.length;i++){
						var next = $(nextTrs[i]);
						next.css("display", "");
						this.TreeGrid("showNextLevelRecursive",config,next.attr('id'));
					}
				}else if(config.delayLoad && nextTrs.length==0){
					//延迟加载且当前没有数据
					var rows = null;
					if(config.onDelayLoadData){
						//调用用户自定义的延迟加载获取数据
						//rows = config.onDelayLoadData($tr.data("data"));
						rows = [{name:'node5',id:'n5',children:[]}]
					}
					this.TreeGrid('appendData', config,$tr.attr('id'),rows);
					
				}
			}
		},
		
		
		appendData:function(config,parentId,rows){
			if(rows==null){
				console.error("rows is null");
				return;
			}
			var prevTrId = parentId;
			for(var i=0;i<rows.length;i++){
				var id = parentId + "_" + i;
				var row = rows[i];
				prevTrId = this.TreeGrid('drawDataTr',config,id,row,prevTrId,'dataTr' );
				
				var $tr = $('#'+id);
				//点击 延迟加载的数据要立马显示
				$tr.attr('openStatus','N');
				$tr.css('display','');
			}
		}



	};


	$.fn.TreeGrid = function(method) {
        if (methods[method]) {
            return methods[ method ].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method with name ' + method + ' does not exists for jQuery.TreeGrid');
        }
    };

	
	$.TreeGrid = {};
	$.TreeGrid.COUNT = 1;


	$.fn.TreeGrid.defaults = {
		id:'T',
		width:'100%',
		headerAlign: 'center',
		headerHeight: '25',
		dataAlign: 'center',
		indentation: '20',
		displayLevel:1,//页面默认看到所有的一级节点
		treeColumnIndex:0,//默认第0列是树
		rownum:0,
		showHoverCss:true,
		itemClick: function(id,rowIndex,data){},
		
		//remote props
		delayLoad:true,
		remoteUrl:"",
		remoteRestricts:"",
		onDelayLoadData:function(data){console.log(data)},

		pagination:true
	};



})(jQuery)