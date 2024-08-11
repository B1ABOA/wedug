/**
 * @constructor
 * @description greenit tools
 * @author soonhyeong
 * @version 0.0.1
 * @date 23. 01. 10
 */

String.format = function () {
    let s = arguments[0];
    for (let i = 0; i < arguments.length - 1; i++) {
        let reg = new RegExp("\\{" + i + "\\}", "gm");
        s = s.replace(reg, arguments[i + 1]);
    }
    return s;
};

function replaceAll(str, searchStr, replaceStr) {
    return str.split(searchStr).join(replaceStr);
}

function mAjax(sUrl, params, method, proYn, fnSuccess, fnError) {
    if (proYn) {
        progressStart();
    }

    if (method == "GET") {
        var sParams = "";
        var i = 0;

        for (var key in params) {
            if (i == 0) {
                sParams += String.format("{0}={1}", key, params[key]);
            } else {
                sParams += String.format("&{0}={1}", key, params[key]);
            }
            i++;
        }

        console.log(sUrl + "?" + sParams);

        setTimeout(function () {
            $.ajax({
                "async": true
                , "type": "GET"
                , "url": sUrl
                , "data": encodeURI(sParams)
                , "cache": false
                , "success": function (data) {
                    progressStop();
                    fnSuccess(data);
                }
                , "error": function (jqXHR, textStatus, errorThrown) {
                    progressStop();
                    fnError(jqXHR.responseJSON);
                }
                , "dataType": "json"
                , "contentType": "application/json; charset=utf-8"
            });
        }, 300);
    } else if (method == "POST") {
        setTimeout(function () {
            $.ajax({
                "async": true
                , "type": "POST"
                , "url": sUrl
                , "data": JSON.stringify(params)
                , "cache": false
                , "dataType": "json"
                , "contentType": "application/json; charset=utf-8"
                , "success": function (data) {
                    progressStop();
                    fnSuccess(data);
                }
                , "error": function (jqXHR, textStatus, errorThrown) {
                    progressStop();
                    fnError(jqXHR.responseJSON);
                }
            });
        }, 300);
    }
}

/* 날짜 포맷
 * ex) new Date().yyyymmdd()
 *  */
function getDateFormat(sDate) {
    return new Date(sDate.substring(0, 4) + "-" + sDate.substring(4, 6) + "-" + sDate.substring(6, 8))
}

function getDateAndTimeFormat(sDate, sTime) {
    return new Date(sDate.substring(0, 4), sDate.substring(4, 6) - 1, sDate.substring(6, 8), sTime.substring(0,2), sTime.substring(2,4))
}

function addDate(sDate, value) {
    const day = Number(sDate.substring(6, 8)) + value;

    return new Date(sDate.substring(0, 4), Number(sDate.substring(4, 6)) - 1, day);
}

function addMonth(sDate, value) {
    const month = Number(sDate.substring(4, 6)) + (value - 1);

    return new Date(sDate.substring(0, 4), month, Number(sDate.substring(6, 8)));
}

Date.prototype.yyyymmddhhmm = function () {
    const mm = this.getMonth() + 1;
    const dd = this.getDate();
    const hh = this.getHours();
    const mi = this.getMinutes();

    return [this.getFullYear(), (mm > 9 ? '' : '0') + mm, (dd > 9 ? '' : '0') + dd, (hh > 9 ? '' : '0') + hh, (mi > 9 ? '' : '0') + mi].join('');
};

Date.prototype.yyyymmddhhmmss = function () {
    const mm = this.getMonth() + 1;
    const dd = this.getDate();
    const hh = this.getHours();
    const mi = this.getMinutes();
    const ss = this.getSeconds();

    return [this.getFullYear(), (mm > 9 ? '' : '0') + mm, (dd > 9 ? '' : '0') + dd, (hh > 9 ? '' : '0') + hh, (mi > 9 ? '' : '0') + mi, (ss > 9 ? '' : '0') + ss].join('');
};

Date.prototype.yyyymmdd = function () {
    const mm = this.getMonth() + 1;
    const dd = this.getDate();

    return [this.getFullYear(), (mm > 9 ? '' : '0') + mm, (dd > 9 ? '' : '0') + dd].join('');
};

Date.prototype.yyyymm = function () {
    const mm = this.getMonth() + 1;

    return [this.getFullYear(), (mm > 9 ? '' : '0') + mm].join('');
};

Date.prototype.yyyy = function () {
    return this.getFullYear();
};

Date.prototype.mm = function () {
    const mm = this.getMonth() + 1;

    return (mm > 9 ? '' : '0') + mm;
};

Date.prototype.dd = function () {
    const dd = this.getDate();

    return (dd > 9 ? '' : '0') + dd;
};

Date.prototype.hh = function () {
    const hh = this.getHours();

    return (hh > 9 ? '' : '0') + hh;
};

Date.prototype.week = function () {
    return this.getDay();
};

Date.prototype.weekName = function () {
    const week = ['일', '월', '화', '수', '목', '금', '토'];

    return week[this.getDay()];
};

function progressStart() {
    $.preloader.start({
        modal: true,
        src: "/images/sprites.png"
    });
}

function progressStop() {
    $.preloader.stop();
}

function getDateStringFormat(date, div) {
    if (date.length == 8) {
        return date.substring(0, 4) + div + date.substring(4, 6) + div + date.substring(6, 8);
    } else {
        return "";
    }
}

function getTimeStringFormat(time) {
    if (time.length == 4) {
        return time.substring(0, 2) + ":" + time.substring(2, 4);
    } else {
        return "";
    }
}

/**
 * validaion체크해서 값 리턴
 * type
 * 1 : 알파벳이나 숫자
 * 2 : 영어만
 * 3 : 숫자만
 * 4 : 한글만
 * 5 : 영문 숫자 특수문자 (한글제외)
 * @param {event, string}
 * onkeydown='return validationCheck(event, "3")' onkeyup='return validationCheck(event, "3")'
 */
function validationCheck(event, type) {
    event = event || window.event;

    let regex = '';
    switch (type) {
        case '1':
            regex = /[^a-zA-Z0-9]/g;
            break;
        case '2':
            regex = /[^a-zA-Z]/g;
            break;
        case '3':
            regex = /[^0-9]/g;
            break;
        case '4':
            regex = /[^가-힣ㄱ-ㅎㅏ-ㅣ]/g;
            break;
        case '5':
            regex = /[가-힣ㄱ-ㅎㅏ-ㅣ]/g;
            break;
        case '6' :
            regex = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\ '\"\\(\=]/g;
            break;
        default:
            regex = '';
    }

    event.target.value = event.target.value.replace(regex, '');
}

function inputNumberFormat(obj) {
    obj.value = comma(uncomma(obj.value));
}

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}