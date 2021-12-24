function chgvalueshowhide(elemid,felemid,n){
    var spwd=document.getElementById(elemid);
    if(spwd.value.length >= n){
        document.getElementById(felemid).style.display="block";
    }else{
        document.getElementById(felemid).style.display="none";
    }
}

//ajax:
$(function(){
    $("#login").click(function() {
        var para={
            stunum:$("#stunum").val(),
            // stuphone:$("#stuphone").val(),
            stupwd:$("#stupwd").val()
        }

        $.ajax({
            url:"/ayUser/check",//加网页的路径
            type:"Post",
            data:para,
            datatype:"html",
            success:function(data){
                eval(data);
            }
        });
    });



    $("input").each(function (i, domEle) {
        $(domEle).keyup(function () {
            if (i == 0) {
                if ($(this).val().length != 13) {
                    $(this).siblings(".tip").show();
                }else {
                    $(this).siblings(".tip").hide();
                }
            }
            if (i == 1) {
                if ($(this).val().length < 11) {
                    $(this).siblings(".tip").show();
                }else {
                    $(this).siblings(".tip").hide();
                }
            }
            if (i == 2) {
                if ($(this).val().length < 6) {
                    $(this).siblings(".tip").show();
                }else {
                    $(this).siblings(".tip").hide();
                }
            }
        })
    })
});