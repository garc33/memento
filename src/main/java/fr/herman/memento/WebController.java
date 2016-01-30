package fr.herman.memento;

import java.io.Reader;
import java.io.Writer;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("data")
public class WebController {

	@RequestMapping(method=RequestMethod.POST)
	public String insert(Reader reader){
		return "id";
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public void update(Reader reader){
	}
	
	@RequestMapping(path="{id}",method=RequestMethod.GET)
	public void get(Writer writer,@NotNull @PathVariable String id){
		
	}
	
	@RequestMapping(path="{id}",method=RequestMethod.DELETE)
	public void delete(@NotNull @PathVariable String id){
		
	}
}
