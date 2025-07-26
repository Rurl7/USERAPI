package GDGoC.project.user_api.exception;

public class DataNotFoundException extends RuntimeException {
        public DataNotFoundException(String message) {
            super(message);
        }
    }
/* exception(예외클래스)가 존재하지 않았으므로 댓글/답글/게시글 등에 계속 사용이 가능하게끔
DataNotFoundException을 user_api 패키지 밑에 exception 패키지와 클래스를 추가함 */