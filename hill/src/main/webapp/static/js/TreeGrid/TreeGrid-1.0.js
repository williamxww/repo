(function($){

	//工具方法
	function request(url,restricts){
		if(restricts==""){
			restricts = {};
		}
		if(typeof(restricts)!="object"){
			console.error("requestParam should be Ojbect");
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
		
		//创建容器
		createContainer:function(config){
			var $context = this;
			$context.css({width:config.width,height:config.height});
			//先清除工作(可能之前有残留)
			$context.find('.TreeGrid-inner').remove();
			$context.removeClass('TreeGrid');
			//正式构造
			$context.addClass('TreeGrid');
			//创建遮罩容器
			$context.append('<div class="TreeGrid-inner"></div>');
			var $inner = $context.find('.TreeGrid-inner');
			var id = config.id || "TreeGrid"+$.TreeGrid.COUNT++;
			$inner.append("<table id='"+id+"' />");
			var $table = $("#"+id);
			$table.attr('cellspacing',0);
			$table.attr('cellpadding',0);
			
			
			//对每一个符合条件的jquery对象(this即选择的div),对其执行以下函数
			return $context.each(function(){
				
			});
		},
		
		//画表头
		drawHeader:function(config){
			var id = config.id+'H';
			$(this).find("table").append("<tr id='"+id+"' />");
			var $th = $('#'+id);
			$th.addClass('header');
			$th.attr('height',config.headerHeight);
			$th.attr('level',0);
			
			if(config.showCheckbox ){
				//第一列要用来显示checkbox
				$th.append("<td><input type='checkbox' /></td>");
			}

			var cols = config.columns;
			for(i=0;i<cols.length;i++){
				var col = cols[i];
				$th.append("<td />");
				var $td = $th.find('td:last');
				$td.attr('align',(col.headerAlign || config.headerAlign) );
				$td.css('width',(col.width || "") );
				$td.append(col.headerText || "");
			}
			return this.each(function(){
				if(config.columnWidthResizable){
					$(this).TreeGrid('resizeHeaderWidth',config,$th);
				}
				
			});
		},
		
		//改变列宽
		resizeHeaderWidth:function(config,$th){
			var $context = this;
			var resizable = false;//当前位置是可以开始改变宽度的
			var resizing = false;//表明正在拖动改变大小
			var begin = 0;
			var $resizeTarget = null;

			//当鼠标滑动到边界，指针发生改变
			$th.mousemove(function(e){
				var $target = $(e.target);
				var x = e.pageX;//鼠标位置的左边距
				var offset = $target.offset();
				var left = offset.left;//元素整体偏移量
				var allWidth = left + $target.outerWidth();//td右侧边线的左边距
				if(x>allWidth-config.cursorRange){
					resizable = true;
					$target.css('cursor','e-resize');
				}else{
					resizable = false;
					$target.css("cursor", "default");
				}

			});
			
			//触发
			$th.mousedown(function(e){
				
				if(resizable){
					$resizeTarget = $(e.target);
					var $inner = $context.find('.TreeGrid-inner');
					$inner.append("<div class='table-resize-proxy'></div>");
					var $proxy = $context.find('.table-resize-proxy');
					$proxy.css({left : e.pageX-$context.offset().left+2,display : 'block'});
					begin = e.pageX;
					resizing = true;
				}	
			});
			
			//辅助线移动
			$context.mousemove(function(e){
				if(resizing){
					var $proxy = $context.find('.table-resize-proxy');
					$proxy.css({left : e.pageX-$context.offset().left,display : 'block'});
				}
			});

			$context.mouseup(function(e){
				
				if(resizing){
					//设置新宽度
					var changedWith = e.pageX-begin;
					var newWidth = $resizeTarget.width()+changedWith;
					$resizeTarget.width(newWidth);
					//清理工作
					$context.find('.table-resize-proxy').remove();
					resizing = false;
					$resizeTarget = null;
					begin = 0;
					
				}
			});	
		},

		//画数据
		drawData : function(config){
			var $context = this;
			var rows = [];
			//本地有数据用本地的，没有则远程获取
			if(config.data){
				rows = config.data;
			}else if(config.requestUrl!=''){
				rows = request(config.requestUrl,config.requestParam);
			}else{
				console.error('pls config the data source');
			}
			
			//表头即为整体的根
			var rootId = config.id+'H';
			//表头中存放了所有数据，为了保持规律：在所有的父节点中都能找到子节点的数据
			$context.find('#'+rootId).data('data',{children:rows});
			return $context.each(function(){
				$(this).TreeGrid('drawDataRecursive', config,rootId,rows,config.displayLevel);
			});
		},
		
		//递归将rows画在parentId下  displayLevel级别之前的都要显示
		drawDataRecursive:function(config,parentId,rows,displayLevel){
			var $context = this;

			var prevTrId = parentId;
			
			var count = rows.length;
			if(config.pagination){
				//只画前 pageNum 行
				count = count<config.pageNum ? count:config.pageNum;
			}
			for(var i=0; i<count; i++){
				var id = parentId + "_" + i;
				var row = rows[i];
				
				prevTrId = $context.TreeGrid('drawTableTr',config,id,row,prevTrId,'dataTr',displayLevel );
				//递归画子树
				if(row.children && row.children.length>0 ){
					prevTrId = $context.TreeGrid('drawDataRecursive', config,id, row.children,displayLevel);
				}
			}
			//分页器
			if(config.pagination){
				var id = parentId + "_" + count;
				prevTrId = $context.TreeGrid('drawTableTr',config,id,row,prevTrId,'paginationTr',displayLevel );
			}
			return prevTrId;
		},
		
		

		//画tr  prevTrId:该行的前一行id;  displayLevel:该级之前的节点都要显示
		drawTableTr:function(config,id,row,prevTrId,trCls,displayLevel){
			var $context = this;
			$context.find('#'+prevTrId).after("<tr id="+id+" />");
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
			if(currentLevel<displayLevel) openStatus = "Y";//级别<displayLevel的节点,都为展开状态
			if(currentLevel<=displayLevel) display = ""; //级别<=displayLevel的节点,都要显示
			
			$tr.attr('openStatus',openStatus);
			$tr.css('display',display);
			
			if(trCls=='dataTr'){
				$context.TreeGrid('drawDataTd',config,$tr);
			}else if(trCls=='paginationTr'){
				$context.TreeGrid('drawPaginationTd',config,$tr);
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

			if(config.showCheckbox ){
				//第一列要用来显示checkbox
				$tr.append("<td><input type='checkbox' /></td>");
			}

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
					//如果是延迟加载则只要求有children属性即可，否则要求children.length>0
					if((config.delayLoad&&row.children) || (row.children&&row.children.length>0) ){
						$img.addClass('folder');
						var nodeClass = (openStatus=="Y")? "nodeOpen" : "nodeClose";
						$img.addClass(nodeClass);
					}else{
						$img.addClass('image_nohand');
						$img.addClass('nodeLeaf');
					}
				}
				
				var displayData = row[col.dataField] || "";
				//该字段有转换器
				if(col.converter){
					displayData = col.converter.call(this,displayData);
				}
				$td.append(displayData);

			}
		},

		//画分页器
		drawPaginationTd:function(config,$tr){
			var level = parseInt( $tr.attr('level') );
			
			
			$tr.append("<td />");
			var $td = $tr.find('td:last');
			$td.attr('align','right');
			$td.css('padding-right',(level-1)*20);
			//$td.css('border','none');

			$td.addClass('pagination');
			var colspan = config.columns.length;
			if(config.showCheckbox){
				colspan += 1;
			}
			$td.attr('colspan',colspan);
			$td.append("<span class='first'></span>");
			$td.append("<span class='prev'></span>");
			$td.append("<span class='page'><input /></span>");
			$td.append("/<span class='pageCount'></span>");
			$td.append("<span class='next'></span>");
			$td.append("<span class='last'></span>");
			this.TreeGrid('bindPaginationEvent',config,$tr);
		},

		//用rows重画parentId下面的数据
		reloadData:function(config,parentId,rows){
			var $context = this;
			var parentLevel = $context.find('#'+parentId).attr('level');
			var currentLevel = parseInt(parentLevel)+1;
			//删除所有子项
			$context.TreeGrid('deleteDataRecursive',config,parentId);
			//重画所有子项
			$context.TreeGrid('drawDataRecursive', config,parentId,rows,currentLevel);
		},
		
		//递归删除parentId的所有子项
		deleteDataRecursive:function(config,parentId){
			var $context = this;
			$context.TreeGrid('markDeleteTr',config,parentId);
			$context.find('.deleteTr').remove();
		},
		
		//将所有需要删除的行标记出
		markDeleteTr:function(config,parentId){
			var $context = this;
			var $trs = $context.find("tr[pid="+ parentId +"]");
			
			for(var i=0; i<$trs.length;i++){
				var $tr = $($trs[i]);
				$tr.addClass('deleteTr');
				$context.TreeGrid('markDeleteTr',config,$tr.attr('id'));
			}
		},
		
		//子节点分页事件
		bindPaginationEvent:function(config,$tr){
			var $context = this;
			var pid = $tr.attr('pid');
			var $parent = $context.find('#'+pid)

			var $first = $tr.find('span.first');
			var $prev = $tr.find('span.prev');
			var $page = $tr.find('span.page input');
			var $pageCount = $tr.find('span.pageCount');
			var $next = $tr.find('span.next');
			var $last = $tr.find('span.last');
			
			//从父节点上获取子节点的当前页
			var currentPage = $parent.data('currentPage');
			if(currentPage == undefined ){
				currentPage = 1;
			}

			//从父节点上获取所有子节点
			var allDatas = $parent.data('data').children;
			var pageNum = config.pageNum;
			var totalCount = allDatas.length;
			var pageCount = Math.ceil( totalCount/pageNum );
			$page.val(currentPage);
			$pageCount.text(pageCount);
			
			//给分页器加禁止的样式
			if(currentPage<=1){
				$first.addClass('disabled');
				$prev.addClass('disabled');
			}
			if(currentPage>=pageCount){
				$next.addClass('disabled');
				$last.addClass('disabled');
			}

			$prev.click(function(){
				if(currentPage<=1){
					return;
				}
				//将子页面 页码存放于父节点
				$parent.data('currentPage',currentPage-1);
				$context.TreeGrid('reloadData',config,pid,getPageContent(currentPage-1) );
				
			});

			$next.click(function(){
				if(currentPage>=pageCount){
					return;
				}
				$parent.data('currentPage',currentPage+1);
				$context.TreeGrid('reloadData',config,pid,getPageContent(currentPage+1) );
			});

			$first.click(function(){
				if(currentPage<=1){
					return;
				}
				$parent.data('currentPage',1);
				$context.TreeGrid('reloadData',config,pid,getPageContent(1) );			
			});

			$last.click(function(){
				if(currentPage>=pageCount){
					return;
				}
				$parent.data('currentPage',pageCount);
				$context.TreeGrid('reloadData',config,pid,getPageContent(pageCount) );				
			});

			$page.change(function(){
				var index = $page.val();
				var reg = new RegExp("^[0-9]*$");
				if(!reg.test(index)){
					$page.val(currentPage);
					return;
				}
				index = parseInt(index);
				if(index>pageCount){
					index = pageCount;
				}else if(index<1){
					index = 1;
				}
				$parent.data('currentPage',index);
				$context.TreeGrid('reloadData',config,pid,getPageContent(index) );				
			});


			function getPageContent(index){
				var rows = [];
				var begin = (index-1<0?0:index-1)*pageNum;
				var end = begin+pageNum<totalCount-1 ? begin+pageNum : totalCount-1;
				for(var i=begin;i<=end;i++){
					rows.push(allDatas[i]);
				}
				return rows;
			}
		},
		

		bindEvent:function(config){
			
			var $context = this;

			//对数据行增加悬浮样式,因为数据行有可能是动态增加的,因而采用如下写法
			if(config.showHoverCss){
				$context.find("tr.dataTr").die().live({
					mouseenter:function(){
						if($(this).hasClass("header")) return;
						$(this).addClass("row_hover");
					},
					mouseleave:function(){
						$(this).removeClass("row_hover");
					}
				});
			}
			
			var timer = null;//区分单双击
			//bind click to <tr>
			$context.find("tr.dataTr").die().live("click", function(){
				var $this = $(this);
				$context.find("tr").removeClass("row_active");
				$this.addClass("row_active");
				
				var id = $this.attr('id');
				var data = $this.data("data");

				if(config.itemClick){
					config.itemClick(id,data);
				}
				
			});


			//bind click to image
			$context.find("span.folder").die().live("click", function(){
				var trid = $(this).attr("trid"); 
				var $tr = $context.find("#" + trid);
				var isOpen = $tr.attr("openStatus");
				var statusAfterClick = (isOpen == "Y") ? "N" : "Y";//当前为打开状态则关闭
				$tr.attr("openStatus", statusAfterClick);
				
				if(statusAfterClick == "N"){ //隐藏子节点
					$tr.find("span.folder").removeClass("nodeOpen").addClass("nodeClose");
					$context.find("tr[id^=" + trid + "_]").css("display", "none");
				}else{ //显示子节点
					$tr.find("span.folder").removeClass("nodeClose").addClass("nodeOpen");
					$context.TreeGrid("showNextLevelRecursive",config,trid);
				}
				//阻止事件冒泡
				return false;
			});
		},

		//递归显示数据
		showNextLevelRecursive:function(config,parentId){
			var $context = this;
			
			var $parentTr = $context.find("#" + parentId);
			var isOpen = $parentTr.attr("openStatus");
			//只有当前行处于打开状态才可以显示下一行
			if(isOpen == "Y"){
				//找出所有trid的子行
				var nextTrs = $context.find("tr[pid=" + parentId + "]");
				if(nextTrs.length>0){
					for(var i=0;i<nextTrs.length;i++){
						var next = $(nextTrs[i]);
						next.css("display", "");
						this.TreeGrid("showNextLevelRecursive",config,next.attr('id'));
					}
				}else if(config.delayLoad && nextTrs.length==0){
					//延迟加载且当前没有数据
					var rows = [];
					if(config.onDelayLoadData){
						//调用用户自定义的延迟加载获取数据
						rows = config.onDelayLoadData($parentTr.data("data"));
						if(!rows){
							console.error('onDelayLoadData get nothing from remote');
							return;
						}
						$parentTr.data('data').children = rows;
					}
					var currentLevel = parseInt($parentTr.attr('level'))+1;
					$context.TreeGrid('drawDataRecursive', config,parentId,rows,currentLevel );
					
				}
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
		itemClick: function(id,data){},

		showCheckbox:false,
		
		//remote props
		requestUrl:"",
		requestParam:"",
		
		//delay load data
		delayLoad:false,
		onDelayLoadData:function(data){},
		//pager
		pagination:true,
		pageNum:5,
		
		//resize column width
		columnWidthResizable:true,
		cursorRange:6  //在td的右侧6像素内能识别到左右滑动鼠标
	};



})(jQuery)