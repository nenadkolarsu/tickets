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
import com.cinema.tickets.models.Projections;
import com.cinema.tickets.models.Theatres;
import com.cinema.tickets.repository.CinemasRepository;
import com.cinema.tickets.repository.ProjectionsRepository;
import com.cinema.tickets.repository.TheatresRepository;

@Controller
public class TheatresController {
	@Autowired
	private TheatresRepository theatresRepository;
	
	@Autowired
	private ProjectionsRepository projectionsRepository;
	
    @Autowired
    private ApplicationContext appContext;
	
	@Autowired
	private CinemasRepository cinemasRepository;

	@RequestMapping(value = "/theatres.html")
	public String theatres1(HttpServletRequest request) {

		request.setAttribute("mode", "MODE_TASKS");
		request.setAttribute("title", "Theatres");
		request.setAttribute("new_item", "/theatres_new.html");
		request.setAttribute("print_item", "/theatres_pdf.html");
		return "theatres";
	}	

	@RequestMapping(value = "/theatres_new.html", method = RequestMethod.GET)
	public ModelAndView newCinema(Model model, HttpServletRequest request) { 
		model.addAttribute("title", "New theatre");


		Theatres aa = new Theatres();
		// da bi default aktivan bio checked
	    aa.setStatus(true);
	    Date date = new Date();
	    aa.setTimestamp(date); 
	    
//		Cinemas km = new Cinemas();
	    List<Cinemas> deptList = cinemasRepository.findAll(); 
	      
	    Map<Long, String> dept = new HashMap<>();
		HttpSession sess = request.getSession();
		
		for (Cinemas d : deptList) {
	          dept.put(d.getId(), d.getName());
	      }
		
	     sess.setAttribute("eCinemas", dept);	 
	    
		return new ModelAndView("theatresForm", "theatres", aa);

	}	

	@RequestMapping(value = "/save_theatres.html", method = RequestMethod.POST)
	public String addTypetypeOfDocuments(@ModelAttribute("theatres") @Valid Theatres theatres, 
			BindingResult result, Model model) { // , @PathVariable int aktivan


		if (result.hasErrors()) {
			model.addAttribute("error", "error");
			return "theatresForm";
		}

		theatresRepository.save(theatres);

		model.addAttribute("mode", "MODE_TASKS");
		model.addAttribute("title", "Theatres");
		model.addAttribute("new_theatres", "/theatres_new.html");
		model.addAttribute("print_theatres", "/theatres_pdf.html");

		return "redirect:theatres.html";
	}
	

	@RequestMapping(path="/getAllTheatres", method=RequestMethod.GET)
	public @ResponseBody List<Theatres> getJsonTheatres(){
		
		List<Theatres> aa = theatresRepository.findAll();
	
		 for (Iterator iterator = aa.iterator(); iterator.hasNext();) {
			 Theatres dokument = (Theatres) iterator.next();
			 dokument.setAction("<a href=\"update_theatres.html?id=" + dokument.getId() + "\"> " + "<i class=\"fa fa-pencil-square-o edit-delete-icon\"></i> </a> "
					+ "            <a href=\"delete_theatres.html?id=" + dokument.getId() + "\" Onclick=\"return ConfirmDelete();\"> " + "<i class=\"fa fa-trash-o edit-delete-icon\"></i> </a>");
		}

		return aa; 
	}
	
    
    @RequestMapping(value = "/update_theatre.html")
	public String updateTypetypeOfDocuments(@RequestParam Long id, HttpServletRequest request){
		request.setAttribute("theatres", theatresRepository.getOne(id));
		request.setAttribute("title", "Update theatres");	
	    List<Cinemas> deptList = cinemasRepository.findAll(); 
	      
	    Map<Long, String> dept = new HashMap<>();
		HttpSession sess = request.getSession();
		
		for (Cinemas d : deptList) {
	          dept.put(d.getId(), d.getName());
	      }
		
	    sess.setAttribute("eCinemas", dept);		
		
		return "theatresForm";
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
	@RequestMapping(value = "/delete_theatre.html")
	public String deleteTypeDocuments(@RequestParam Long id, HttpServletRequest request) {


		Theatres aa = theatresRepository.getOne(id);
	    if (!aa.getProjections().isEmpty()) {	
	    	return "redirect:414.html?ops=Child exist, can't delete parent!";
	    }
	    
		try {
			theatresRepository.deleteById(id);
		}
		catch (Exception ex)
		{
		    return "redirect:414.html?ops=Can't delete record";
		}
		
		return "redirect:theatres.html";
		
		
	}
//	
//    @RequestMapping(path = "/theatres_pdf.html", method = RequestMethod.GET)
//    public ModelAndView printTypetypeOfDocumentsReport() {
//
//        JasperReportsPdfView view = new JasperReportsPdfView();
//        view.setUrl("classpath:rpt_Items1.jrxml");
//        view.setApplicationContext(appContext);
//       
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("datasource", theatresRepository.findAllByOrderByIdDesc());
//        params.put("title", "Theatres");
//
//        params.put("company",  companyDetails.companyDetails1);
//        params.put("adress",  companyDetails.companyDetails2);
//        params.put("city",  companyDetails.companyDetails3);
//        return new ModelAndView(view, params);
//    }
	
}
