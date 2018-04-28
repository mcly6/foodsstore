layui.use("layer");
var lib = {
    //信息提示框
    alert: function (msg, yes) {
        var options = {
            title: '提示',
            closeBtn: 0,
            shade: ["0.5", "#999"],
        };
        var index = layer.alert(msg, options, yes);
    },

    //信息询问框
    confirm: function (msg, yes, cancel) {
        var options = {
            title: '提示',
            closeBtn: 0,
            shade: ["0.5", "#999"],
            btn: ["确定", "取消"],
        };
        var index = layer.confirm(msg, options, yes, cancel);
    },

    //提示
    msg: function (msg, end) {
        var options = {
        };
        var index = layer.msg(msg, options, end);
    },

    //加载(调用时需再执行关闭方法)
    load: function () {
        var options = {
        };
        var index = layer.load(0, options);
    },

    //Tips
    tips: function (msg, follow) {
        var options = {
            time: 3000,
            tips:1
        };
        var index = layer.tips(msg, follow, options);
    },

    //打开页面层对话框
    openDialog: function (title, dom, w, h, t, l) {
        var index = layer.open({
            type: 1,
            title: title,
            content: $(dom),
            area: [w, h],
            offset: [t, l],
            shade: ["0.5", "#999"]
        });
    },

    //关闭对话框
    closeDialog: function () {
        layer.close(layer.index);
    },

    //打开框架层对话框
    openFrameDialog: function (title, url, w, h,end) {
        var index = layer.open({
            type: 2,
            title: title,
            content: url,
            area: [w, h],
            shade: ["0.5", "#999"],
            end:end
        });
    },

    //关闭框架层对话框
    closeFrameDialog: function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    },

    //关闭所有对话框
    closeAllDialog: function () {
        layer.closeAll();
    },

    //显示选项卡
    tab: function (objTit, objCont, objTitClassOver, index) {
        $(objTit).removeClass(objTitClassOver);
        $(objTit).eq(index).addClass(objTitClassOver);
        $(objCont).hide();
        $(objCont).eq(index).show();
    },

    //全选
    checkAll: function (objAll, objChk) {
        if ($(objAll).attr("checked")) {
            $(objChk).attr("checked", true);
        }
        else {
            $(objChk).attr("checked", false);
        }
    },

    //全选状态
    checkAllStatus: function (objAll, objChk) {
        var count = $(objChk).length;
        var countChecked = $(objChk).filter("input:checked").length;
        $(objAll).attr("checked", count == countChecked);
    },

    //输入字符限制
    inputString: function (inStr, refStr) {
        if (inStr.length == 0) return (false);
        for (count = 0; count < inStr.length; count++) {
            tempChar = inStr.substring(count, count + 1);
            if (refStr.indexOf(tempChar, 0) == -1)
                return (false);
        }
        return (true);
    },

    //显示金额
    money: function (s, n) {
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
        var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
        t = "";
        for (i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        return t.split("").reverse().join("") + "." + r;
    },

    //验证码刷新
    refreshVerifyCode: function (obj, url) {
        $(obj).attr("src", url + "rnd=" + Math.random());
    },

    //显示时间 
    showTime: function (obj) {
        var now_time = new Date();
        var years = now_time.getFullYear();
        var months = now_time.getMonth();
        var dates = now_time.getDate();
        var days = now_time.getDay();
        var today = years + "年" + (months + 1) + "月" + dates + "日";
        var weeks;
        if (days == 0)
            weeks = "星期日";
        if (days == 1)
            weeks = "星期一";
        if (days == 2)
            weeks = "星期二";
        if (days == 3)
            weeks = "星期三";
        if (days == 4)
            weeks = "星期四";
        if (days == 5)
            weeks = "星期五";
        if (days == 6)
            weeks = "星期六";
        var hours = now_time.getHours();
        var minutes = now_time.getMinutes();
        var seconds = now_time.getSeconds();
        var timer = hours;
        timer += ((minutes < 10) ? ":0" : ":") + minutes;
        timer += ((seconds < 10) ? ":0" : ":") + seconds;
        var doc = document.getElementById(obj);
        doc.innerHTML = today + " " + timer + " " + weeks;
        setTimeout("lib.showTime('" + obj + "')", 1000);
    },

    //按比例生成缩略图
    drawImage: function (ImgID, W, H) {
        TempImg = document.getElementById(ImgID);
        var image = new Image();
        image.src = TempImg.src;
        if (image.width > 0 && image.height > 0) {
            var TW = (image.width * H) / image.height;
            var TH = (image.height * W) / image.width;
            if (image.width > W) {
                if (TH > H) {
                    TempImg.width = TW;
                    TempImg.height = H;
                }
                else {
                    TempImg.width = W;
                    TempImg.height = TH;
                }
            }
            else {
                if (image.height > H) {
                    TempImg.width = TW;
                    TempImg.height = H;
                }
                else {
                    TempImg.width = image.width;
                    TempImg.height = image.height;
                }
            }
        }
    },

    //跳转页面
    gotoUrl: function (url) {
        window.location.href = url;
        return false;
    },

    //弹出消息并跳转页面
    msgGotoUrl: function (msg, url) {
        lib.alertCallback(msg, function () {
            window.location.href = url;
        });
    },

    //ajax处理表单请求
    ajaxJsonForm: function (form, url, callback0, callback1) {
        $.ajax({
            type: "POST",
            url: url + "?rnd=" + Math.random(),
            data: $(form).serialize(),
            dataType: "json",
            beforeSend: function () {
                lib.msg("系统处理中...");
            },
            success: function (json) {
                if (jQuery.isFunction(callback0)) {
                    callback0(json);
                }
                else {
                    if (json.code == 1) {
                        if (jQuery.isFunction(callback1)) {
                            callback1(json);
                        }
                        else {
                            lib.msg(json.msg);
                            setTimeout("window.location.href=document.URL;", 500);
                        }
                    }
                    else {
                        lib.alert(json.msg);
                    }
                }
            },
            error: function () {
                lib.msg("请求失败,请重试!");
            }
        });
    },

    //ajax处理参数请求
    ajaxJsonData: function (data, url, callback0, callback1) {
        $.ajax({
            type: "POST",
            url: url + "?rnd=" + Math.random(),
            data: data,
            dataType: "json",
            beforeSend: function () {
                lib.msg("系统处理中...");
            },
            success: function (json) {
                if (jQuery.isFunction(callback0)) {
                    callback0(json);
                }
                else {
                    if (json.code == 1) {
                        if (jQuery.isFunction(callback1)) {
                            callback1(json);
                        }
                        else {
                            lib.msg(json.msg);
                            setTimeout("window.location.href=document.URL;", 500);
                        }
                    }
                    else {
                        lib.alert(json.msg);
                    }
                }
            },
            error: function () {
                lib.msg("请求失败,请重试!");
            }
        });
    },

    //是否为数字
    isNumeric: function (num) {
        return num != "" && /^[0-9.]{0,}$/.test(num);
    },

    //清除html代码
    removeHtml: function (s) {
        return s = s.replace(/^<[^>]*>$/, "");
    }
}