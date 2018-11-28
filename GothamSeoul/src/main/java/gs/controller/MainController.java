package gs.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gs.service.RChart;
import gs.service.Weather;
import gs.vo.mainVO;


@Controller
public class MainController {
	@Autowired
	RChart rc;
	@Autowired
	Weather wt;
	
	Logger log = Logger.getLogger(this.getClass());
	
//	@RequestMapping(value="/main.do")
//	public ModelAndView openMain(mainVO vo) throws Exception {
//		ModelAndView mv = new ModelAndView("mainPage");
//		log.debug("Test");
//				
//		return mv;
//	}

	@RequestMapping(value="/main.do")
	public ModelAndView openMain(mainVO vo, HttpServletRequest req, double lat, double lng, String guName) throws Exception {
		ModelAndView mav = new ModelAndView();
		String real_path = req.getSession().getServletContext().getRealPath("/");
		System.out.println(real_path);
	    real_path = real_path.replace("\\", "/");
	    System.out.println(real_path);		
		String result = rc.returnLeaflet2(real_path+"leafletchart", lat, lng);
		mav.addObject("leafletChartName", "http://localhost:8000/GothamSeoul/leafletchart/"+result);
		mav.addObject("temp", wt.returnTemp(lat, lng));
		mav.addObject("comm", wt.returnComm(lat, lng));
		
		mav.setViewName("mainPage");
		return mav;
	}
	
}
