package com.bpz.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bpz.app.models.entity.Contenedor;
import com.bpz.app.models.entity.PersonaContacto;
import com.bpz.app.models.entity.Proveedor;
import com.bpz.app.service.IPersonaContactoService;
import com.bpz.app.service.IProveedorService;



@Controller
@SessionAttributes("proveedor")
@RequestMapping(value="proveedor")
public class ProveedorController {

	@Autowired
	private IProveedorService pService;
	@Autowired
	private IPersonaContactoService pCService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model){
		model.addAttribute("titulo", "Listado de proveedores");
		model.addAttribute("proveedores", pService.findAll());
		return "listarProveedor";	
	}
	
	@RequestMapping(value = "/crear")
	public String crear(Model model) {

		Contenedor contenedor = new Contenedor();
		model.addAttribute("contenedor", contenedor);
		model.addAttribute("titulo", "Crear");
		return "formPersonaProveedor";
	}
	@RequestMapping(value="/crear",method=RequestMethod.POST)
	public String guardar(@Valid Contenedor contenedor, BindingResult result,Model model, RedirectAttributes flash,
			SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Proveedor");
			return "formPersonaProveedor";
		}
		PersonaContacto pc = contenedor.getPersonacontacto();
		
		Proveedor proveedorCompleto = new Proveedor();
		proveedorCompleto = contenedor.getProveedor();
		
		proveedorCompleto.setPersonaContacto(contenedor.getPersonacontacto());
		pc.setProveedor(proveedorCompleto);
		proveedorCompleto.setPersonaContacto(pc);
		pc.setProveedor(proveedorCompleto);
		
		
		pService.save(proveedorCompleto);
		pCService.save(contenedor.getPersonacontacto());
		status.setComplete();
		
		return "redirect:/proveedor/listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		Proveedor proveedor = pService.findbyId(id);

		if (proveedor != null) {
			pService.eliminarPorId(id);
			flash.addFlashAttribute("success", "Proveedor eliminado con éxito!");
			return "redirect:/proveedor/listar/" ;
		}
		flash.addFlashAttribute("error", "El proveedor no existe en la base de datos, no se pudo eliminar!");

		return "redirect:/proveedor/listar";
	}
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model) {

		Proveedor proveedor = new Proveedor();
		proveedor = pService.findbyId(id);
		model.addAttribute("proveedor", proveedor);
		model.addAttribute("titulo", "Editar");
		return "editarProveedor";
	}
	@PostMapping(value="/editar")
	public String guardar(@Valid Proveedor proveedor, BindingResult result,Model model, RedirectAttributes flash,
			SessionStatus status) {
		if(result.hasErrors()) {
			return "redirect:/proveedor/listar";
		}
		
		
				
		pService.save(proveedor);
		
		status.setComplete();
		
		return "redirect:/proveedor/listar";
	}
}
