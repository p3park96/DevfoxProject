package polymorphism;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements TV {
	
 // @Autowired : 변수 위에 설정하여 해당하는 타입 객체를 찾아서 자동으로 할당하는 기능
 // @Qualifier("apple") : 객체가 여러개일 경우 특정 객체의 이름을 이용하여 의존성을 주입하는 기능 
 	@Autowired			
 //	@Qualifier("apple")
 //	@Resource(name = "apple") // name 속성을 이용하여 객체를 찾아서 자동으로 할당함 
	public Speaker speaker;	// 멤버변수 추가
	
	public LgTV() {
		System.out.println("====> Lg TV 객체 생성...");
	}

	@Override
	public void powerOn() {
		System.out.println("LgTV ----- 전원 켠다.");
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV ----- 전원 끈다.");
	}

	@Override
	public void volumeUp() {
	  //System.out.println("LgTV ----- 소리 올린다.");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
      //System.out.println("LgTV ----- 소리 내린다.");
		speaker.volumeDown();
	}
}
