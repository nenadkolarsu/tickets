package com.cinema.tickets.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.cinema.tickets.models.Movies;
import com.cinema.tickets.models.Projections;
import com.cinema.tickets.models.Reservations;
import com.cinema.tickets.models.Theatres;
import com.cinema.tickets.repository.MoviesRepository;
import com.cinema.tickets.repository.ProjectionsRepository;
import com.cinema.tickets.repository.ReservationsRepository;
import com.cinema.tickets.repository.TheatresRepository;


@Controller
public class ReservationsController {
	
	
	@Autowired
	private ProjectionsRepository projectionsRepository;
	
	@Autowired
	private TheatresRepository theatresRepository;
	
	@Autowired
	private MoviesRepository moviesRepository;
	
    @Autowired
    private ApplicationContext appContext;
	
	@Autowired
	private ReservationsRepository reservationsRepository;

	@Value("${cinema.rows}")
	private String rows;
	@Value("${cinema.seats}")
	private String seats;
	
	@RequestMapping(value = "/reservations.html")
	public String reservationsView(HttpServletRequest request) {

		request.setAttribute("mode", "MODE_TASKS");
		request.setAttribute("title", "Actual Reservations");
		request.setAttribute("new_item", "/reservations_new.html");
		request.setAttribute("print_item", "/reservations_pdf.html");
		return "reservations";
	}	
	

	@RequestMapping(value = "/reservations_new.html", method = RequestMethod.GET)
	public ModelAndView newReservation(Model model, HttpServletRequest request) { 
		model.addAttribute("title", "New reservation");


		Reservations aa = new Reservations();
		// da bi default aktivan bio checked
	    aa.setStatus(true);
	    Date date = new Date();
	    aa.setTimestamp(date); 
	    
	    List<Projections> deptList0 = projectionsRepository.findAll(); 
	      
	    Map<Long, String> dept0 = new HashMap<>();
		HttpSession sess = request.getSession();
		
		for (Projections d : deptList0) {
	          dept0.put(d.getId(), "Projection number: " + d.getId() + " Date: " + d.getProjection_date() + " Time: " + d.getProjection_time() + " Movie: " + d.getMovies().getName());	      }
		
	    sess.setAttribute("eProjections", dept0);
	    
	    List<Theatres> deptList = theatresRepository.findAll(); 
	      
	    Map<Long, String> dept = new HashMap<>();
//		HttpSession sess = request.getSession();
		
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
	    
		return new ModelAndView("reservationsForm", "reservations", aa);

	}	
	
	@RequestMapping(value = "/projection_reservation.html", method = RequestMethod.GET)
	public ModelAndView newReservation1(Model model, HttpServletRequest request, @RequestParam Long id) {
		
		model.addAttribute("title", "New reservation");

		Reservations aa = new Reservations();

	    aa.setStatus(true);
	    Date date = new Date();
	    aa.setTimestamp(date); 

	    Projections dd = projectionsRepository.getOne(id);
	      
	    Map<Long, String> dept0 = new HashMap<>();
		HttpSession sess = request.getSession();
		
//		for (Projections d : deptList0) {
	          dept0.put(dd.getId(), "Projection number: " + dd.getId() + 
	        		  " Date: " + dd.getProjection_date() 
	        		  + " Time: " + dd.getProjection_time() + " Movie: " + dd.getMovies().getName());
//	          }
		
	    sess.setAttribute("eProjections", dept0);
	    
	    List<Theatres> deptList = theatresRepository.findAll(); 
	      
	    Map<Long, String> dept = new HashMap<>();
//		HttpSession sess = request.getSession();
		
		for (Theatres d : deptList) {
	          dept.put(d.getId(), d.getName());
	      }
		
	    sess.setAttribute("eTheatres", dept);	 
	    
	    List<Movies> moviesList = moviesRepository.findAll(); 
	      
	    Map<Long, String> dept1 = new HashMap<>();
		
		for (Movies d : moviesList) {
	          dept1.put(d.getId(), d.getName());
	      }
		
	    sess.setAttribute("eMovies", dept1);

		Integer maxrowsintheatre = theatresRepository.findNumberOfRowsForTheatre(dd.getTheatres().getId());
		Integer maxseatsintheatre = theatresRepository.findNumberOfSeatsForTheatre(dd.getTheatres().getId());

		model.addAttribute("maxrowsintheatre", maxrowsintheatre);
		model.addAttribute("maxseatsintheatre", maxseatsintheatre);

		return new ModelAndView("reservationsForm", "reservations", aa);

	}	
	

	@RequestMapping(value = "/save_reservations.html", method = RequestMethod.POST)
	public String saveReservation(@ModelAttribute("reservations") @Valid Reservations reservations, 
			BindingResult result, Model model) { // , @PathVariable int aktivan


		if (result.hasErrors()) {
			model.addAttribute("error", "error");
			return "reservationsForm";
		}
		
		Long p0 = reservations.getProjections().getId();	
		Projections p1 = projectionsRepository.getOne(p0); // .findById(p0)
		
		Integer numberOfRowsInTheatre = theatresRepository.findNumberOfRowsForTheatre(p1.getTheatres().getId());

		if (reservations.getRow()>numberOfRowsInTheatre) {
			return "redirect:414.html?ops=Cant reserve row " + reservations.getRow() + ". Maximal number of rows is " + numberOfRowsInTheatre;
		}

		Integer numberOfSeatsInTheatre = theatresRepository.findNumberOfSeatsForTheatre(p1.getTheatres().getId());
		
		if (reservations.getSeat()>numberOfSeatsInTheatre) {
			return "redirect:414.html?ops=Can't reserve seat " + reservations.getSeat() + ". Maximal number of seat is " + numberOfSeatsInTheatre;
		}
				
//		if (reservations.getRow()>this.rows) {
//			return "redirect:414.html?ops=Maximal number of row is " + this.rows;
//		}
//		
//		if (reservations.getSeat()>this.seats) {
//			return "redirect:414.html?ops=Maximal number of seat is " + this.seats;
//		}
		
		Long m = reservationsRepository.findReservationByIdProjections(reservations.getProjections().getId(), reservations.getRow(), reservations.getSeat());
	
		if(m==null)
		reservationsRepository.save(reservations);
		else
			return "redirect:414.html?ops=Position is alredy reserved!";

		model.addAttribute("mode", "MODE_TASKS");
		model.addAttribute("title", "Reservations");
		model.addAttribute("new_reservations", "/reservations_new.html");
		model.addAttribute("print_reservations", "/reservations_pdf.html");

		return "redirect:reservations.html";
	}
	

	@RequestMapping(path="/getAllReservations", method=RequestMethod.GET)
	public @ResponseBody List<Reservations> getJsonReservations(){
		
		List<Reservations> aa = reservationsRepository.findAll();
	
		 for (Iterator iterator = aa.iterator(); iterator.hasNext();) {
			 Reservations dokument = (Reservations) iterator.next();
			 dokument.setAction("<a href=\"update_reservations.html?id=" + dokument.getId() + "\"> " + "<i class=\"fa fa-pencil-square-o edit-delete-icon\"></i> </a> "
					+ "            <a href=\"delete_reservations.html?id=" + dokument.getId() + "\" Onclick=\"return ConfirmDelete();\"> " + "<i class=\"fa fa-trash-o edit-delete-icon\"></i> </a>");
		}

		return aa; 
	}
	
    
    @RequestMapping(value = "/update_reservation.html")
	public String updateReservation(@RequestParam Long id, HttpServletRequest request){
		request.setAttribute("reservations", reservationsRepository.getOne(id));
		request.setAttribute("title", "Update reservations");	
	    List<Reservations> deptList = reservationsRepository.findAll(); 
	      
//	    Map<Long, String> dept = new HashMap<>();
//		HttpSession sess = request.getSession();
//		
//		for (Reservations d : deptList) {
//	          dept.put(d.getId(), d.getMovie());
//	      }
//		
//	    sess.setAttribute("eReservations", dept);		
		
		return "reservationsForm";
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
	@RequestMapping(value = "/delete_reservation.html")
	public String deleteReservation(@RequestParam Long id, HttpServletRequest request) {

		try {
			reservationsRepository.deleteById(id);
		}
		catch (Exception ex)
		{

		      return "redirect:414.html";
		}
		
		return "redirect:reservations.html";
	}
	
	
//	
//    @RequestMapping(path = "/reservations_pdf.html", method = RequestMethod.GET)
//    public ModelAndView printTypetypeOfDocumentsReport() {
//
//        JasperReportsPdfView view = new JasperReportsPdfView();
//        view.setUrl("classpath:rpt_Items1.jrxml");
//        view.setApplicationContext(appContext);
//       
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("datasource", reservationsRepository.findAllByOrderByIdDesc());
//        params.put("title", "Reservations");
//
//        params.put("company",  companyDetails.companyDetails1);
//        params.put("adress",  companyDetails.companyDetails2);
//        params.put("city",  companyDetails.companyDetails3);
//        return new ModelAndView(view, params);
//    }
	
}
