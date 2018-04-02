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
import com.cinema.tickets.repository.CinemasRepository;

@Controller
public class CinemasController {
	@Autowired
	private CinemasRepository cinemasRepository;
	
    @Autowired
    private ApplicationContext appContext;
	

	@RequestMapping(value = "/cinemas.html")
	public String cinemas1(HttpServletRequest request) {

		request.setAttribute("mode", "MODE_TASKS");
		request.setAttribute("title", "Cinemas");
		request.setAttribute("new_item", "/cinemas_new.html");
		request.setAttribute("print_item", "/cinemas_pdf.html");
		return "cinemas";
	}	

	@RequestMapping(value = "/cinemas_new.html", method = RequestMethod.GET)
	public ModelAndView newCinema(Model model, HttpServletRequest request) { 
		model.addAttribute("title", "New cinema");


		Cinemas aa = new Cinemas();
		// da bi default aktivan bio checked
	    aa.setStatus(true);
	    Date date = new Date();
	    aa.setTimestamp(date); 
	    
		return new ModelAndView("cinemasForm", "cinemas", aa);

	}	

	@RequestMapping(value = "/save_cinemas.html", method = RequestMethod.POST)
	public String addTypetypeOfDocuments(@ModelAttribute("cinemas") @Valid Cinemas cinemas, 
			BindingResult result, Model model) { // , @PathVariable int aktivan


		if (result.hasErrors()) {
			model.addAttribute("error", "error");
			return "cinemasForm";
		}

		cinemasRepository.save(cinemas);

		model.addAttribute("mode", "MODE_TASKS");
		model.addAttribute("title", "Cinemas");
		model.addAttribute("new_cinemas", "/cinemas_new.html");
		model.addAttribute("print_cinemas", "/cinemas_pdf.html");

		return "redirect:cinemas.html";
	}
	
	@RequestMapping(path="/getAllCinemas", method=RequestMethod.GET)
	public @ResponseBody List<Cinemas> getJsonCinemas(){
		
		List<Cinemas> aa = cinemasRepository.findAll();
	
		 for (Iterator iterator = aa.iterator(); iterator.hasNext();) {
			 Cinemas dokument = (Cinemas) iterator.next();
			 dokument.setAkcija("<a href=\"update_cinemas.html?id=" + dokument.getId() + "\"> " + "<i class=\"fa fa-pencil-square-o edit-delete-icon\"></i> </a> "
					+ "            <a href=\"delete_cinemas.html?id=" + dokument.getId() + "\" Onclick=\"return ConfirmDelete();\"> " + "<i class=\"fa fa-trash-o edit-delete-icon\"></i> </a>");
		}
		return aa; 
	}
	
    
    @RequestMapping(value = "/update_cinemas.html")
	public String updateTypetypeOfDocuments(@RequestParam Long id, HttpServletRequest request){
		request.setAttribute("cinemas", cinemasRepository.getOne(id));
		request.setAttribute("title", "Update cinema");	
		return "cinemasForm";
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
	@RequestMapping(value = "/delete_cinemas.html")
	public String deleteTypeDocuments(@RequestParam Long id, HttpServletRequest request) {

		Cinemas aa = cinemasRepository.getOne(id);
	    if (!aa.getTheatres().isEmpty()) {	
	    	return "redirect:414.html?ops=Theatre exist, can't delete cinema!";
	    }
	    
		try {
			cinemasRepository.deleteById(id);
		}
		catch (Exception ex)
		{
		    return "redirect:414.html?ops=Can't delete record";
		}
		
		return "redirect:cinemas.html";
	}
	
//    @RequestMapping(path = "/cinemas_pdf.html", method = RequestMethod.GET)
//    public ModelAndView printTypetypeOfDocumentsReport() {
//
//        JasperReportsPdfView view = new JasperReportsPdfView();
//        view.setUrl("classpath:rpt_Items1.jrxml");
//        view.setApplicationContext(appContext);
//       
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("datasource", cinemasRepository.findAllByOrderByIdAsc());
//        params.put("title", "Cinemas");
//
//        params.put("company",  companyDetails.companyDetails1);
//        params.put("adress",  companyDetails.companyDetails2);
//        params.put("city",  companyDetails.companyDetails3);
//        return new ModelAndView(view, params);
//    }
//	
}
