package GDGoC.project.user_api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/* 예외가 발생하면 스트링 부트는 설정된 HTTP 상태 코드(HttpStatus.NOT_FOUND)와 이유("entity not found")를 포함한 응답을 생성하여 클라이언트에게 반환하게 된다.*/
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  public DataNotFoundException(String message) {
    super(message);
  }
}


