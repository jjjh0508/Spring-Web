package interceptor;

import org.springframework.stereotype.Service;

@Service
public class MenuService {
    public void Method(){
        System.out.println("메소드 호출 확인");
    }
}
