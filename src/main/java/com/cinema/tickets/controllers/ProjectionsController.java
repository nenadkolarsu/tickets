package com.cinema.tickets.controllers;

import java.util.Date;
import java.util.HashMap;
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
import com.cinema.tickets.models.Projections;
import com.cinema.tickets.models.Theatres;
import com.cinema.tickets.repository.MoviesRepository;
import com.cinema.tickets.repository.ProjectionsRepository;
import com.cinema.tickets.repository.TheatresRepository;


@Controller
public class ProjectionsController {
	
	@Autowired
	private TheatresRepository theatresRepository;
	
	@Autowired
	private MoviesRepository moviesRepository;
	
    @Autowired
    private ApplicationContext appContext;
	
	@Autowired
	private ProjectionsRepository projectionsRepository;

	@RequestMapping(value = "/projections.html")
	public String projections1(HttpServletRequest request) {

		request.setAttribute("mode", "MODE_TASKS");
		request.setAttribute("title", "Projections");
		request.setAttribute("new_item", "/projections_new.html");
		request.setAttribute("print_item", "/projections_pdf.html");
		
		return "projections";
	}	

	@RequestMapping(value = "/availableprojections.html")
	public String makeReservation(HttpServletRequest request) {

		request.setAttribute("mode", "MODE_TASKS");
		request.setAttribute("title", "Available Projections - Make reservation ");
		request.setAttribute("new_item", "/reservations_new.html");
		request.setAttribute("print_item", "/projections_pdf.html");
		
		return "availableprojections";
	}	
	
	@RequestMapping(value = "/projections_new.html", method = RequestMethod.GET)
	public ModelAndView newCinema(Model model, HttpServletRequest request) { 
		model.addAttribute("title", "New projection");


		Projections aa = new Projections();
		// da bi default aktivan bio checked
	    aa.setStatus(true);
	    Date date = new Date();
	    aa.setTimestamp(date); 
	    

	    List<Theatres> deptList = theatresRepository.findAll(); 
	      
	    Map<Long, String> dept = new HashMap<>();
		HttpSession sess = request.getSession();
		
		for (Theatres d : deptList) {
	          dept.put(d.getId(), d.getName());
	      }
		
	    sess.setAttribute("eTheatres", dept);	 
	    
	    List<Movies> moviesList = moviesRepository.findAll(); 
	      
	    Map<Long, String> dept1 = new HashMap<>();
		// HttpSession sess = request.getSession();
		
		for (Movies d : moviesList) {
	          dept1.put(d.getId(), d.getName());
	      }
		
	    sess.setAttribute("eMovies", dept1);	
	    
		return new ModelAndView("projectionsForm", "projections", aa);

	}	

	@RequestMapping(value = "/save_projections.html", method = RequestMethod.POST)
	public String addTypetypeOfDocuments(@ModelAttribute("projections") @Valid Projections projections, 
			BindingResult result, Model model) { // , @PathVariable int aktivan


		if (result.hasErrors()) {
			model.addAttribute("error", "error");
			return "projectionsForm";
		}

		projectionsRepository.save(projections);

		model.addAttribute("mode", "MODE_TASKS");
		model.addAttribute("title", "Projections");
		model.addAttribute("new_projections", "/projections_new.html");
		model.addAttribute("print_projections", "/projections_pdf.html");

		return "redirect:projections.html";
	}
	

	@RequestMapping(path="/getAllProjections", method=RequestMethod.GET)
	public @ResponseBody List<Projections> getJsonProjections(){
		
		List<Projections> aa = projectionsRepository.findAll();
	
		 for (Iterator iterator = aa.iterator(); iterator.hasNext();) {
			 Projections dokument = (Projections) iterator.next();
			 dokument.setAction("<a href=\"update_projections.html?id=" + dokument.getId() + "\"> " + "<i class=\"fa fa-pencil-square-o edit-delete-icon\"></i> </a> "
					+ "            <a href=\"delete_projections.html?id=" + dokument.getId() + "\" Onclick=\"return ConfirmDelete();\"> " + "<i class=\"fa fa-trash-o edit-delete-icon\"></i> </a>");
		}

		return aa; 
	}
	
    
    @RequestMapping(value = "/update_projection.html")
	public String updateTypetypeOfDocuments(@RequestParam Long id, HttpServletRequest request){
		request.setAttribute("projections", projectionsRepository.getOne(id));
		request.setAttribute("title", "Update projections");	
	    List<Projections> deptList = projectionsRepository.findAll(); 
	      
	    Map<Long, String> dept = new HashMap<>();
		HttpSession sess = request.getSession();
		
		for (Projections d : deptList) {
	          dept.put(d.getId(), d.getMovie());
	      }
		
	    sess.setAttribute("eProjections", dept);		
		
		return "projectionsForm";
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
    
	@RequestMapping(value = "/delete_projection.html")
	public String deleteProjections(@RequestParam Long id, HttpServletRequest request) {

		Projections aa = projectionsRepository.getOne(id);
	    if (!aa.getReservations().isEmpty()) {	
	    	return "redirect:414.html?ops=Reservation exist, can't delete projection!";
	    }
	    
		try {
			projectionsRepository.deleteById(id);
		}
		catch (Exception ex)
		{
		    return "redirect:414.html?ops=Can't delete record";
		}
		
		return "redirect:projections.html";

	}
	
//	
//    @RequestMapping(path = "/projections_pdf.html", method = RequestMethod.GET)
//    public ModelAndView printTypetypeOfDocumentsReport() {
//
//        JasperReportsPdfView view = new JasperReportsPdfView();
//        view.setUrl("classpath:rpt_Items1.jrxml");
//        view.setApplicationContext(appContext);
//       
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("datasource", projectionsRepository.findAllByOrderByIdDesc());
//        params.put("title", "Projections");
//
//        params.put("company",  companyDetails.companyDetails1);
//        params.put("adress",  companyDetails.companyDetails2);
//        params.put("city",  companyDetails.companyDetails3);
//        return new ModelAndView(view, params);
//    }
	
}
