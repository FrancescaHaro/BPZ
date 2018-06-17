package com.bpz.app.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bpz.app.models.entity.Factura;
import com.bpz.app.service.IFacturaService;
import com.bpz.app.service.IProveedorService;
import com.bpz.app.viewmodel.FacturaViewModel;
@Controller
@SessionAttributes("factura")
@RequestMapping(value="/factura")
public class FacturaController {
	@Autowired
	private IProveedorService pService;
	
	@Autowired
	private IFacturaService fService;
	
	@GetMapping(value = "/listar")
	public String listar(Model model){
		model.addAttribute("titulo", "Listado de Facturas");
		model.addAttribute("facturas", fService.findAll());
		return "listarFacturas";	
	}
	
	@GetMapping(value = "/crear")
	public String crear(Model model) {
		FacturaViewModel facturaViewModel = new FacturaViewModel();
		/*Patrón viewmodel*/
		model.addAttribute("facturaViewModel", facturaViewModel);
		model.addAttribute("proveedores", pService.findAll());
		model.addAttribute("titulo", "Crear Factura");
		
		return "crearFactura";
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping(value="/crear")
	public String guardar(@Valid FacturaViewModel facturaViewModel, BindingResult result,Model model, RedirectAttributes flash,
			SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear Factura");
			return "crearFactura";
		}
		
		System.out.print("\n\n\n\n"+ facturaViewModel.getFactura().getDescripcion() + "\n\n\n\n");
		
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			facturaViewModel.getFactura().setFechaEmision(formatter.parse(facturaViewModel.getFechaEmision()));
			facturaViewModel.getFactura().setFechaVencimiento(formatter.parse(facturaViewModel.getFechaVencimiento()));
			facturaViewModel.getFactura().setPeriodoDetraccion(formatter.parse(facturaViewModel.getFechaPeriodo()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		facturaViewModel.getFactura().setProveedor(facturaViewModel.getProveedor());
		
		fService.save(facturaViewModel.getFactura());
		
		status.setComplete();
		
		return "redirect:/factura/listar";
	}
	
	
	@RequestMapping(value = "/crear/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		Factura factura = null;

		if (id > 0) {
			factura = fService.findbyId(id);
			if (factura == null) {
				flash.addFlashAttribute("error", "El ID de la factura no existe en la BBDD!");
				return "redirect:/factura/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID de la factura no puede ser cero!");
			return "redirect:/factura/listar";
		}
		model.addAttribute("factura", factura);
		model.addAttribute("titulo", "Editar Factura");
		return "crearFactura";
	}
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       java.sql.Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));   
	}

}
