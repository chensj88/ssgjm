/**
 * 卫宁健康--实施管理工具
 * @Author jinxl
 * @Date 2018/3/1
 * 
 */
const IMS = {
    //初始化
    init: function () {
    	service.init();
    	this.tabSwitch();
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
    },
	menuTab:function () {
        $('.wap-tab>div').click(function () {
            $(this).addClass('active')
                .siblings('div')
                .removeClass('active');
            $('.wap-tab-cnt>div').eq($(this).index())
                .removeClass('hide')
                .siblings('div')
                .addClass('hide');
        });
        $('.wrap-foot>div').click(function () {
            $(this).addClass('active')
                .siblings('div')
                .removeClass('active');
            $('.wrap-cnt>div').eq($(this).index())
                .removeClass('hide')
                .siblings('div')
                .addClass('hide');
        });
    },
    /*
	 * dropdown
	 * */
	dropDown:function(){
		$(".select").on("click","a",function(){
			$(this).find("i").toggleClass("reverse");
			$(this).next("ul").slideToggle();
		});
		$(".select").on("click","ul>li",function(){
			var _this=$(this),_dropd=_this.parent("ul"), _val=_this.data("val"),_txt=_this.text();
			_dropd.slideToggle();
			_dropd.siblings("[type='hidden']").val(_val);
			_dropd.siblings("a").find("span").text(_txt);
			_dropd.siblings("a").find("i").toggleClass("reverse");
		});
	},
	tabSwitch:function(){
		$(".tab-nav>div").click(function(){
			$(this).addClass('active')
				.siblings('div')
				.removeClass('active');
			$('.tab .tab-cnt>div').eq($(this).index())
				.addClass('active')
				.siblings('div')
				.removeClass('active');
			return false;
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
/*企业号*/
const enterprise = {
	init:function(){
		this.delUpladImg();
		this.largeImg();
	},
	//删除上传图片
	delUpladImg:function(){
		$('.datum-upload').on('click','.icon-close',function(){
			$(this).parent('div').remove();
		});
	},
	largeImg:function(){
		$('.datum').on('click','.datum-upload>div',function(){
			let _img = $(this).find('img');
			if(_img.length>0){
				if(_img.attr('src')){
					$('.large-img>img').attr('src',_img.attr('src'));
					$('.large-img').fadeIn();
				}
			}else{
				$('.large-img').fadeOut();
			}
		});
		
		$('.large-img>span').click(function(){
			enterprise.closeLargeImg();
		})
	},
	closeLargeImg:function(){
		$('.large-img').fadeOut();
	},
	selectUser:function(){
		let _this = null;
		$('.pro-down').click(function(){
			$('.select-user').fadeIn();
			_this = $(this);
			return false;
		})
		$('.icon-close').click(function(){
			$('.select-user').fadeOut();
		})
		$('.select-user-cnt').on('click','p',function(){
			let _key = $(this).data('key');
			$(this).addClass('active')
				.siblings('p')
				.removeClass('active');
			_this.text($(this).find('span').text());
			_this.next('input').val(_key);
			//触发事件
			allots(_this.prev('input').val(),_key);
			$('.icon-close').trigger('click');
		});
	},
	//站点安装删除
	siteDelIetm:function(){
		$('.register-one').click(function(){
			let _this= $(this);
			_this.find('i').toggleClass('up');
			$(this).next('.register-cnt').slideToggle();
		});
		//删除当前条目
		$('.del-item').click(function(){
			$(this).parent('.site-register').remove();
			$('.site-register').removeClass('delete');
		});
		//显示删除
		$('.register-button .icon-reduce').click(function(){
			$('.site-register').addClass('delete');
		});
	}
};
