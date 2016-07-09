/**
 * Created by huchao on 2016/7/6.
 */
//验证邮箱
jQuery.validator.addMethod( "checkEmail",function(value,element){
    var myreg = /^[_a-zA-Z0-9\-]+(\.[_a-zA-Z0-9\-]*)*@[a-zA-Z0-9\-]+([\.][a-zA-Z0-9\-]+)+$/;
    if(value !=''){if(!myreg.test(value)){return false;}};
    return true;
} , " 请输入有效的E_mail");
//验证密码 6-18位由字符数字和特殊符号组成 排除空格..
jQuery.validator.addMethod("checkPassword",function(value, element) {
    var myreg = /^[^\s]{6,18}$/;
    if (value != '') {if (!myreg.test(value)) {return false;}};
    return true;}, "请输入有效密码!");
// 联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    console.log(value);
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+d{8})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");