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
			//确保 $context.data('config');能取到config
			this.data('config',config);
			 
			return this.each(function() {
                var $this = $(this);
                $this.TreeGrid('createContainer')
					.TreeGrid('drawHeader')
					.TreeGrid('drawData')
					.TreeGrid('bindEvent')
					.TreeGrid('bindCheckboxEvent');
					
            });
		},
		
		getConfig:function(){
			var $context = this;
			return $context.data('config');
		},
		
		//创建容器
		createContainer:function(){
			var $context = this;
			var config = $context.data('config');
			$context.css({width:config.width,height:config.height});
			//先清除工作(可能之前有残留)
			$context.find('.TreeGrid-inner').remove();
			$context.removeClass('TreeGrid');
			//正式构造
			$context.addClass('TreeGrid');
			//创建内部容器,该容器无限宽(css中10000px)，因而用户可以无休止拖动列
			$context.append('<div class="TreeGrid-inner"></div>');
			var $inner = $context.find('.TreeGrid-inner');
			var id = config.id || "T"+$.TreeGrid.COUNT++;
			$inner.append("<table id='"+id+"' cellspacing=0 cellpadding=0 />");
			
			
			//对每一个符合条件的jquery对象(this即选择的div),对其执行以下函数
			return $context.each(function(){
				
			});
		},
		
		//画表头
		drawHeader:function(){
			var $context = this;
			var config = $context.data('config');
			var $table = $context.find("table");
			var headerId = $table.attr('id')+'H';

			$table.append("<tr id='"+headerId+"' />");
			var $tr = $table.find('#'+headerId);
			$tr.addClass('header');
			$tr.attr('height',config.headerHeight);
			$tr.attr('level',0);
			
			if(config.showCheckbox ){
				//第一列要用来显示checkbox
				$tr.append("<td><input type='checkbox' trid='"+headerId+"' /></td>");
			}

			var cols = config.columns;
			for(i=0;i<cols.length;i++){
				var col = cols[i];
				$tr.append("<td />");
				var $td = $tr.find('td:last');
				$td.attr('align',(col.headerAlign || config.headerAlign) );
				$td.css('width',(col.width || "") );
				$td.append(col.headerText || "");
			}
			return this.each(function(){
				if(config.columnWidthResizable){
					$(this).TreeGrid('resizeHeaderWidth',$tr);
				}
				
			});
		},
		
		//表头拖动改变列宽
		resizeHeaderWidth:function($tr){
			var $context = this;
			var config = $context.data('config');
			var resizable = false;//当前位置是可以开始改变宽度的
			var resizing = false;//表明正在拖动改变大小
			var begin = 0;
			var $resizeTarget = null;

			//当鼠标滑动到边界，指针发生改变
			$tr.mousemove(function(e){
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
			$tr.mousedown(function(e){
				
				if(resizable){
					$context.addClass('noSelect');//拖动过程中不让选中
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
					//拖动结束可以选中
					$context.removeClass('noSelect');
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
		drawData : function(){
			var $context = this;
			var config = $context.data('config');
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
			var headerId = $context.find('table tr.header').attr('id');
			return $context.each(function(){
				$(this).TreeGrid('drawDataRecursive', headerId,rows,config.displayLevel);
			});
		},
		
		//递归将rows画在parentId下  displayLevel级别之前的都要显示
		drawDataRecursive:function(parentId,rows,displayLevel){
			var $context = this;
			var config = $context.data('config');
			
			//画行时,会紧接着行prevTrId画新tr
			var prevTrId = parentId;
			
			var count = rows.length;
			if(config.pagination){
				//只画前 pageNum 行
				count = count<config.pageNum ? count:config.pageNum;
			}
			for(var i=0; i<count; i++){
				var id = parentId + "_" + i;
				var row = rows[i];
				
				prevTrId = $context.TreeGrid('drawTableTr',id,row,prevTrId,'dataTr',displayLevel );
				//递归画子树
				if(row.children && row.children.length>0 ){
					prevTrId = $context.TreeGrid('drawDataRecursive', id, row.children,displayLevel);
				}
			}
			//parentId节点对应的子节点还没有分页器且子节点数据>1页,才画分页器
			var $parentTr = $('#'+parentId);
			var paginationExists = $parentTr.data('paginationExists');
			if(config.pagination && !paginationExists &&rows.length>config.pageNum ){
				var id = parentId + "_" + count;
				//分页器上保存着所有子节点数据
				prevTrId = $context.TreeGrid('drawTableTr',id,rows,prevTrId,'paginationTr',displayLevel);
				$parentTr.data('paginationExists',true);//表明该节点的children有分页器了
			}
			return prevTrId;
		},
		
		

		//画tr  prevTrId:该行的前一行id;  displayLevel:该级之前的节点都要显示
		drawTableTr:function(id,row,prevTrId,trCls,displayLevel){
			var $context = this;
			var config = $context.data('config');
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
				$context.TreeGrid('drawDataTd',$tr);
			}else if(trCls=='paginationTr'){
				$context.TreeGrid('drawPaginationTd',$tr);
			}
			
			return id;
		},

		//画td
		drawDataTd:function($tr){
			var $context = this;
			var config = $context.data('config');
			var treeColumnIndex = config.treeColumnIndex;
			var columns = config.columns;
			var row = $tr.data('data');
			var trid = $tr.attr('id');
			var currentLevel = $tr.attr('level');
			var openStatus = $tr.attr('openStatus');

			if(config.showCheckbox ){
				//第一列要用来显示checkbox
				//根据父行来判断子行是否选中
				var parentChecked = $context.TreeGrid('isChecked',$tr.attr('pid'));
				$tr.append("<td><input type='checkbox' /></td>");
				$tr.find("input[type='checkbox']").attr('trid',trid).attr('checked',parentChecked);
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
				if(col.formatter){
					displayData = col.formatter.call(this,displayData);
				}
				$td.append(displayData);

			}
		},

		//画分页器
		drawPaginationTd:function($tr){
			var $context = this;
			var config = $context.data('config');
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
			$td.append("<span class='page'><input value=1 /></span>");
			$td.append("/<span class='pageCount'></span>");
			$td.append("<span class='next'></span>");
			$td.append("<span class='last'></span>");
			this.TreeGrid('bindPaginationEvent',$tr);
		},

		//用rows重画parentId下面的数据
		reloadData:function(parentId,rows){
			var $context = this;
			var config = $context.data('config');
			var parentLevel = $context.find('#'+parentId).attr('level');
			var displayLevel = parseInt(parentLevel)+1;
			//删除数据子项
			$context.TreeGrid('deleteDataRecursive',parentId);
			//重画数据子项
			$context.TreeGrid('drawDataRecursive',parentId,rows,displayLevel);
		},
		
		//递归删除parentId的所有子项
		deleteDataRecursive:function(parentId){
			var $context = this;
			var config = $context.data('config');
			//注意：parentId节点下的分页器是没有删除的
			var dataTrs = $context.find("tr.dataTr[pid="+ parentId +"]");
			for(var i=0;i<dataTrs.length;i++){
				//将数据行及其子项标记为删除
				$context.TreeGrid('markDeleteTr',$(dataTrs[i]).attr('id'));
			}
			//删除
			$context.find('.deleteTr').remove();
		},
		
		//将id对应行及其子项（包括分页器）标记为删除
		markDeleteTr:function(id){
			var $context = this;
			var config = $context.data('config');
			var $tr = $context.find("#"+id);
			$tr.addClass('deleteTr');
			var $children = $context.find("tr[pid="+ id +"]");
			
			for(var i=0; i<$children.length;i++){
				var $child = $($children[i]);
				$child.addClass('deleteTr');
				$context.TreeGrid('markDeleteTr',$child.attr('id'));
			}
		},
		
		//子节点分页事件
		bindPaginationEvent:function($tr){
			var $context = this;
			var config = $context.data('config');
			var pid = $tr.attr('pid');
			var $parent = $context.find('#'+pid)

			var $first = $tr.find('span.first');
			var $prev = $tr.find('span.prev');
			var $page = $tr.find('span.page input');
			var $pageCount = $tr.find('span.pageCount');
			var $next = $tr.find('span.next');
			var $last = $tr.find('span.last');
			
			
			var currentPage = parseInt($page.val());
			
			var allDatas = $tr.data('data');
			var pageNum = config.pageNum;
			var totalCount = allDatas.length;
			var pageCount = Math.ceil( totalCount/pageNum );
			$pageCount.text(pageCount);
			refreshPagerState(currentPage,pageCount);
			

			$prev.click(function(){
				if(currentPage<=1){
					return;
				}
				gotoPage(currentPage-1);
			});

			$next.click(function(){
				if(currentPage>=pageCount){
					return;
				}
				gotoPage(currentPage+1);
			});

			$first.click(function(){
				if(currentPage<=1){
					return;
				}
				gotoPage(1);
			});

			$last.click(function(){
				if(currentPage>=pageCount){
					return;
				}
				gotoPage(pageCount);
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
				gotoPage(index);
			});
			
			function gotoPage(index){
				currentPage = index;
				$page.val(index);
				$context.TreeGrid('reloadData',pid,getPageContent(index) );	
				refreshPagerState(index,pageCount);
			}

			function getPageContent(index){
				var rows = [];
				var begin = (index-1<0?0:index-1)*pageNum;
				var end = begin+pageNum<totalCount-1 ? begin+pageNum : totalCount-1;
				for(var i=begin;i<=end;i++){
					rows.push(allDatas[i]);
				}
				return rows;
			}

			function refreshPagerState(currentPage,pageCount){
				$first.removeClass('disabled');
				$prev.removeClass('disabled');
				$next.removeClass('disabled');
				$last.removeClass('disabled');
				//给分页器加禁止的样式
				if(currentPage<=1){
					$first.addClass('disabled');
					$prev.addClass('disabled');
				}
				if(currentPage>=pageCount){
					$next.addClass('disabled');
					$last.addClass('disabled');
				}
			}
		},

		

		bindEvent:function(){
			var $context = this;
			var config = $context.data('config');
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
			
			//bind click to <tr>
			$context.find("tr.dataTr").die().live("click", function(){
				var $this = $(this);
				$context.find("tr").removeClass("row_active");
				$this.addClass("row_active");
				
				var id = $this.attr('id');
				var data = $this.data("data");
				
				//点击行,自动check
				if(config.autoChecked){
					//全部取消
					$context.find("input[type='checkbox'][trid]").attr('checked',false);
					$context.TreeGrid('toggleCheckRecursive',id,true);
				}

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
					$context.TreeGrid("showNextLevelRecursive",trid);
				}
				//阻止事件冒泡
				return false;
			});

			return this.each(function(){
				
			});
		},
		
		//给checkbox绑定事件
		bindCheckboxEvent:function(){
			var $context = this;
			var config = $context.data('config');
			if(!config.showCheckbox){
				return;
			}
			
			$context.on('click',"input[type='checkbox'][trid]",function(event){
				//阻止事件冒泡
				event.stopPropagation();
				var $ck = $(this);
				var checked = this.checked;
				var trid = $ck.attr('trid');
				
				$context.TreeGrid('toggleCheckRecursive',trid,checked);
				$context.TreeGrid('uncheckParentRecursive',trid,checked);
			});
		},

		//勾选或不勾选父节点，所有子节点跟着变化
		toggleCheckRecursive:function(id,status){
			var $context = this;
			var config = $context.data('config');
			//改变当前行的状态
			$context.find('#'+id).find("input[type='checkbox'][trid]").attr('checked',status);
			//递归处理子行
			var $trs = $context.find("tr[pid='"+id+"']");
			for( var i=0;i<$trs.length;i++ ){
				$context.TreeGrid('toggleCheckRecursive', $($trs[i]).attr('id'),status );
			}
		},
		//取消勾选子节点,父节点取消
		uncheckParentRecursive:function(id,status){
			var $context = this;
			var config = $context.data('config');
			var $tr = $context.find('#'+id);
			var pid = $tr.attr('pid');
			if(!status && pid!=undefined ){
				var $ptr = $context.find('#'+pid);
				$ptr.find("input[type='checkbox'][trid]").attr('checked',status);
				$context.TreeGrid('uncheckParentRecursive', $ptr.attr('id'), status );
			}
		},
		
		//该行是否选中
		isChecked:function(trid){
			var $context = this;
			var $tr = $context.find('#'+trid);
			if($tr == undefined ){
				return false;
			}
			return $tr.find("input[type='checkbox'][trid]").attr('checked');
		},

		//递归显示数据
		showNextLevelRecursive:function(parentId){
			var $context = this;
			var config = $context.data('config');
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
						this.TreeGrid("showNextLevelRecursive",next.attr('id'));
					}
				}else if(config.delayLoad && nextTrs.length==0){
					//延迟加载且当前没有数据
					var rows = [];
					if(config.onDelayLoadData){
						//显示loading样式
						$context.TreeGrid('showLoading');
						//调用用户自定义的延迟加载获取数据
						rows = config.onDelayLoadData($parentTr.data("data"));
						$context.find('.loading').remove();
						if(!rows){
							console.error('onDelayLoadData get nothing from remote');
							return;
						}
						
					}
					var displayLevel = parseInt($parentTr.attr('level'))+1;
					$context.TreeGrid('drawDataRecursive',parentId,rows,displayLevel );
					
				}
			}
		},

		showLoading:function(){
			var $context = this;
			var config = $context.data('config');
			$context.append('<div class="loading">loading...</div>');
			var $loading = $context.find('.loading');
			var containerWidth = $context.outerWidth();
			var containerHeight = $context.outerHeight();
			$loading.css('left',containerWidth/2-10);
			$loading.css('top',containerHeight/2-5);
			
			return this;
		},
		
		//public 获取勾选上的节点数据
		getCheckedRows:function(){
			var $context = this;
			//注意排除表头
			var $cks = $context.find("tr.dataTr input[type='checkbox'][trid]:checked");
			var result = [];
			for(var i=0;i<$cks.length;i++){
				var trid = $($cks[i]).attr('trid');
				result.push($('#'+trid).data('data'));
			}
			return result;
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
		autoChecked:true,//自动勾选点击行
		
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
		cursorRange:5  //在td的右侧3像素内能识别到左右滑动鼠标
	};



})(jQuery)