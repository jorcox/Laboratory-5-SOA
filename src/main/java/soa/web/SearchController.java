package soa.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SearchController {

	@Autowired
	  private ProducerTemplate producerTemplate;

	@RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value="/search")
    @ResponseBody
    public Object search(@RequestParam("q") String q) {    	
    	String[] elem = q.split(" ");    	
    	Map<String, Object> cabeceras = new HashMap<String, Object>();
    	cabeceras.put("CamelTwitterKeywords", elem[0]);
    	cabeceras.put("CamelTwitterCount", elem[1].split(":")[1]);
    	return producerTemplate.requestBodyAndHeaders("direct:search", "", cabeceras);
    }
}