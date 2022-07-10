package com.gnm.adrunner.server;
 
import com.google.gson.Gson;

public class RequestResponseInterface {


    public static final Gson gson = new Gson();
    
 
    public static String getStatusMessage(Integer statusCode){
        switch(statusCode){
            case 200:return "성공.";
            case 201:return "해당 식별자에 대한 항목이 존재하지 않습니다.";
            case 202:return "로그인 정보가 유효하지 않습니다.";
            case 203:return "토큰이 유효하지 않습니다.";
            case 204:return "토큰이 만료되었습니다.";
            case 206:return "파라미터에 클릭 키가 필수로 필요합니다.";
            case 207:return "해당 클릭키에 대해서 이미 포스트백 요청이 이루어졌습니다.";
            case 208:return "해당 광고 식별자에 대한 광고가 존재하지 않습니다.";
            case 209:return "파라미터가 유효하지 않습니다. ";
            case 211:return "일시 중지된 광고입니다. ";
            case 212:return "매체사 키가 유효하지 않습니다. ";
            case 213:return "광고가 해당 매체사에 연동되어 있지 않습니다.";
            case 214:return "클릭 키에 해당하는 클릭 로그가 존재하지 않습니다.";
            case 215:return "파라미터로 들어온 시간이 유효한 포맷이 아닙니다.";
            case 216:return "광고가 일일 한도에 도달하였습니다.";
            case 217:return "더 이상 광고를 등록할 수 없습니다.";
            case 222:return "광고 종류와 맞지 않는 포스트백 요청입니다.";
            case 223:return "이벤트 이름은 필수로 입력해야 합니다.";
            case 224:return "이벤트 값은 필수로 입력해야 합니다.";
            case 225:return "이벤트 시간은 필수로 입력해야 합니다.";
            case 226:return "종료된 광고입니다.";
            default:return "성공.";
        }
    }

}
