package dev.agentstudio.chart;
import java.util.*;
import org.springframework.boot.SpringApplication;import org.springframework.boot.autoconfigure.SpringBootApplication;import org.springframework.web.bind.annotation.*;
@SpringBootApplication public class ChartMcpApplication {public static void main(String[] args){SpringApplication.run(ChartMcpApplication.class,args);}}
@RestController @RequestMapping("/tools") class ChartTools {
 @PostMapping("/chart.generate") Map<String,Object> generate(@RequestBody Map<String,Object> input){Object rows=input.getOrDefault("data",List.of());String type=String.valueOf(input.getOrDefault("chartType","bar"));String title=String.valueOf(input.getOrDefault("title","Agent Studio chart"));Map<String,Object> spec=new LinkedHashMap<>();spec.put("$schema","https://vega.github.io/schema/vega-lite/v5.json");spec.put("title",title);spec.put("data",Map.of("values",rows));spec.put("mark",type);spec.put("encoding",input.getOrDefault("encoding",Map.of()));return Map.of("charts",List.of(Map.of("title",title,"chartType",type,"spec",spec)));}
 @PostMapping("/chart.render-png") Map<String,Object> render(@RequestBody Map<String,Object> input){return Map.of("accepted",true,"mediaType","image/png","chart",input.getOrDefault("chart",Map.of()),"note","The platform renderer converts this validated chart document to PNG.");}
}
