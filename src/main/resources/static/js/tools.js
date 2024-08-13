/**
 * validaion체크해서 값 리턴
 * type
 * 1 : 알파벳이나 숫자
 * 2 : 영어만
 * 3 : 숫자만
 * 4 : 한글만
 * 5 : 영문 숫자 특수문자 (한글제외)
 * 6 : 특수문자 제외
 * @param {event} event - 이벤트 객체
 * @param {string} type - 체크 타입
 * @return {boolean} - 입력 허용 여부
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
            regex = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\\ '\"\\(\=]/g;
            break;
        default:
            regex = '';
    }

    event.target.value = event.target.value.replace(regex, '');
}
