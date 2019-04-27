package com.xust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ZufangApplication {
	/**
	 *  
	 * 
	 * @author Luo Daiyang
	 * @date 2019/4/10 17:02
	 * @param [args]
	 * @return void
	 */
	public static void main(String[] args) {
		SpringApplication.run(ZufangApplication.class, args);
	}

	/**
	 *
	 *
	 * @author Luo Daiyang
	 * @date 2019/4/10 17:05
	 * @param []
	 * @return java.lang.String
	 */
	@GetMapping("/hello")
	public  String hello(){
		return  "hello";
	}


}
