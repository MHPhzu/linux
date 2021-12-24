$("#sendcode").click(function() {
    var para={

        stuphone:$("#phonenum").val()

    }

    $.ajax({
        url:"/sendCode",//加网页的路径
        type:"Post",
        data:para,
        datatype:"html",
        success:function(data){

            eval(data);
        }
    });
});
$("#reset").click(function() {
    var para={
        stunum:$("#stunum").val(),
        stupwd:$("#stupwd").val(),
        stucode:$("#ver_code").val(),
        stuphone:$("#phonenum").val()
    }
    $.ajax({
        url:"/resetPwd",//加网页的路径
        type:"Post",
        data:para,
        datatype:"html",
        success:function(data){

            eval(data);
        }
    });
});
$(function() {
    var btn = $("#sendcode");
    $(function() {
        btn.click(settime);
    })
    var countdown = 60;//倒计时总时间，为了演示效果，设为5秒，一般都是60s
    function settime() {
        if (countdown == 0) {
            btn.attr("disabled", false);
            btn.html("获取验证码");
            btn.removeClass("disabled");
            $("#sendcode").css("background-image", "linear-gradient(to right,#32be8f,#38d39f,#32be8f)");
            countdown = 60;
            return;
        } else {
            $("#sendcode").css("background-image", "linear-gradient(to right,#bdbeb3,#bdbeb3,#bdbeb3)");

            // $("#sendcode").css("cursor", "not-allowed");
            // $("#sendcode").css("pointer-events", "none");
            btn.addClass("disabled");
            btn.attr("disabled", true);
            btn.html("重新发送(" + countdown + ")");
            countdown--;
        }
        setTimeout(settime, 1000);
    }

})