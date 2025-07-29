package INF2BN_2024_0_EQUIPE02.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RequestMapping("/")
@RestController 
public class IndexController {

    @GetMapping("")
    public String Hello() {
        return "Estou na api do salsi-dogs";
    }
    
    
     
}
