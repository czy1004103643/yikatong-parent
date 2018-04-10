/**
 * 表单回显
 * @author xiangyu.zhang
 */
layui.define(function(exports) {
	var $ = layui.$;
	
	$.fn.echoform = function(data){
	    return this.each(function(){
	        var formElem, name;
	        if(data == null){this.reset(); return; }
	        for(var i = 0; i < this.length; i++){  
	            formElem = this.elements[i];
	            //checkbox的name可能是name[]数组形式
	            name = (formElem.type == "checkbox")? formElem.name.replace(/(.+)\[\]$/, "$1") : formElem.name;
	            if(data[name] == undefined) continue;
	            switch(formElem.type) {
	                case "checkbox":
	                    if(data[name] == ""){
	                        formElem.checked = false;
	                    }else{
	                        //数组查找元素
	                        if(data[name] == true){
	                            formElem.checked = true;
	                        }else{
	                            formElem.checked = false;
	                        }
	                    }
	                break;
	                case "radio":
	                    if(data[name] == 0){
	                        formElem.checked = true;
	                    }else if(formElem.value == 1){
	                        formElem.checked = true;
	                    }
	                break;
	                case "date":
	                	formElem.value = TimeObjectUtil.longMsTimeConvertToDate(data[name]); 
	                	break;
	                /*case "text":
	                	if(isDate(TimeObjectUtil.longMsTimeConvertToDate(data[name])) && data[name].length == 10) {
	                    	formElem.value = TimeObjectUtil.longMsTimeConvertToDateTime(data[name]); 
	                    } else {
	                    	console.log(data[name].toString().length +'    ' + data[name])
	                    	formElem.value = data[name];
	                    }
	                	if(typeof(data[name].length) == 'undefined' && data[name].toString().length >= 13) {
	                    	formElem.value = TimeObjectUtil.longMsTimeConvertToDateTime(data[name]); 
	                    } else {
	                    	formElem.value = data[name];
	                    }
	                	break;*/
	                case "button": break;
	                default: formElem.value = data[name];
	            }
	            
	        }
	    });
	};
	exports('echoform', this);
});
