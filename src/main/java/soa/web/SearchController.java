package soa.web;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    	Pattern exprReg = Pattern.compile("(.*)max:([0-9]*)(.*)");
    	Matcher m = exprReg.matcher(q);
    	m.matches(); 
    	Map<String, Object> cabeceras = new HashMap<String, Object>();
    	cabeceras.put("CamelTwitterKeywords", m.group(1)+m.group(3));
    	cabeceras.put("CamelTwitterCount", m.group(2));
    	return producerTemplate.requestBodyAndHeaders("direct:search", "", cabeceras);
    }
}