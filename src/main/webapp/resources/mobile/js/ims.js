/**
 * 卫宁健康--实施管理工具
 * @Author jinxl
 * @Data 2018/3/1
 * 
 */
const IMS = {
    //初始化
    init: function () {
    	service.init();
    },
    //取消空链接
    cclDefault: function () {
        $(document).on('click', 'a[href=""]', function (e) {
            e.preventDefault();
        });
    },
    /*获取批量被选中的ID
     *@selector:事件源；
     *@scrop：范围
     * */
    getDeleteChk:function(selector,scrop){
    	$(selector).on("click",function(){
    		$("input:checked",scrop).each(function(index,ele){
    			alert(index);
    		});
    	});
    },
     /*全选
     *@selector:事件源,必须是复选框；
     *@scrop：范围
     * */
    checkedAll:function(element,scrop){
    	$(element).on("click","th>input[type='checkbox']",function(){
           	$("#pageContent input[type='checkbox']").prop("checked",$(this).prop("checked"));
      	});
    }
    
};
/*服务号*/
const service = {
	init:function(){
		this.tabSwitch();
	},
	tabSwitch:function(){
		$(".course-tab li").click(function(){
			$(this).addClass('active')
				.siblings('li')
				.removeClass('active');
			$('.course-tab .btm>div').eq($(this).index())
				.addClass('current')
				.siblings('div')
				.removeClass('current');
		});
	}
};
