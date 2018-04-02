package com.cinema.tickets.controllers;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
// import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.cinema.tickets.models.Cinemas;
import com.cinema.tickets.models.Movies;
import com.cinema.tickets.repository.MoviesRepository;



@Controller
public class MoviesController {
	@Autowired
	private MoviesRepository moviesRepository;
	
    @Autowired
    private ApplicationContext appContext;
	

	@RequestMapping(value = "/movies.html")
	public String movies1(HttpServletRequest request) {

		request.setAttribute("mode", "MODE_TASKS");
		request.setAttribute("title", "Movies");
		request.setAttribute("new_item", "/movies_new.html");
		request.setAttribute("print_item", "/movies_pdf.html");
		return "movies";
	}	

	@RequestMapping(value = "/movies_new.html", method = RequestMethod.GET)
	public ModelAndView newCinema(Model model, HttpServletRequest request) { 
		model.addAttribute("title", "New cinema");


		Movies aa = new Movies();
		// da bi default aktivan bio checked
	    aa.setStatus(true);
	    Date date = new Date();
	    aa.setTimestamp(date); 
	    
		return new ModelAndView("moviesForm", "movies", aa);

	}	

	@RequestMapping(value = "/save_movies.html", method = RequestMethod.POST)
	public String addTypetypeOfDocuments(@ModelAttribute("movies") @Valid Movies movies, 
			BindingResult result, Model model) { // , @PathVariable int aktivan


		if (result.hasErrors()) {
			model.addAttribute("error", "error");
			return "moviesForm";
		}

		moviesRepository.save(movies);

		model.addAttribute("mode", "MODE_TASKS");
		model.addAttribute("title", "Movies");
		model.addAttribute("new_movies", "/movies_new.html");
		model.addAttribute("print_movies", "/movies_pdf.html");

		return "redirect:movies.html";
	}
	
	@RequestMapping(path="/getAllMovies", method=RequestMethod.GET)
	public @ResponseBody List<Movies> getJsonMovies(){
		
		List<Movies> aa = moviesRepository.findAll();
	
		 for (Iterator iterator = aa.iterator(); iterator.hasNext();) {
			 Movies dokument = (Movies) iterator.next();
			 dokument.setAkcija("<a href=\"update_movies.html?id=" + dokument.getId() + "\"> " + "<i class=\"fa fa-pencil-square-o edit-delete-icon\"></i> </a> "
					+ "            <a href=\"delete_movies.html?id=" + dokument.getId() + "\" Onclick=\"return ConfirmDelete();\"> " + "<i class=\"fa fa-trash-o edit-delete-icon\"></i> </a>");
		}
		return aa; 
	}
	
    
    @RequestMapping(value = "/update_movies.html")
	public String updateTypetypeOfDocuments(@RequestParam Long id, HttpServletRequest request){
		request.setAttribute("movies", moviesRepository.getOne(id));
		request.setAttribute("title", "Update cinema");	
		return "moviesForm";
	}

    
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
//   //   logger.error("Request: " + req.getRequestURL() + " raised " + ex);
//
//      ModelAndView mav = new ModelAndView();
//      mav.addObject("exception", ex);
//      mav.addObject("url", req.getRequestURL());
//      mav.setViewName("error");
//      return mav;
//    }
//    
	@RequestMapping(value = "/delete_movies.html")
	public String deleteTypeDocuments(@RequestParam Long id, HttpServletRequest request) {

		Movies aa = moviesRepository.getOne(id);
	    if (!aa.getProjections().isEmpty()) {	
	    	return "redirect:414.html?ops=Child exist, can't delete parent!";
	    }
	    
		try {
			moviesRepository.deleteById(id);
		}
		catch (Exception ex)
		{
		    return "redirect:414.html?ops=Can't delete record";
		}		
		
		
		return "redirect:movies.html";
	}
//	
//    @RequestMapping(path = "/movies_pdf.html", method = RequestMethod.GET)
//    public ModelAndView printTypetypeOfDocumentsReport() {
//
//        JasperReportsPdfView view = new JasperReportsPdfView();
//        view.setUrl("classpath:rpt_Items1.jrxml");
//        view.setApplicationContext(appContext);
//       
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("datasource", moviesRepository.findAllByOrderByIdDesc());
//        params.put("title", "Movies");
//
//        params.put("company",  companyDetails.companyDetails1);
//        params.put("adress",  companyDetails.companyDetails2);
//        params.put("city",  companyDetails.companyDetails3);
//        return new ModelAndView(view, params);
//    }
	
}
