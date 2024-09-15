package com.example.demo.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Customers;
import com.example.demo.models.ProductReceipt;
import com.example.demo.models.Users;
import com.example.demo.services.CustomersService;
import com.example.demo.services.ProductReceiptService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductReceiptController {

	@Autowired
	private ProductReceiptService productServ;
	
	@Autowired
	private CustomersService customerServ;
	
	@GetMapping("/productReceipts")
	public String productReceipts(Model model) {
		List<ProductReceipt> productReceipts = productServ.allProductReceipts();
		model.addAttribute("productReceipts",productReceipts);  
		return "productReceipts.jsp";
	}
	
	@GetMapping("/createReceipt")
	public String createReceipt(Model model,@ModelAttribute("newReceipt") ProductReceipt newReceipt) {
		List<Customers> customers = customerServ.allCostumers();
		model.addAttribute("customers",customers);
		return "createReceipt.jsp";
	}
	
	@PostMapping("/createReceipt")
	public String newReceipt(Model model,@ModelAttribute("newReceipt") ProductReceipt newReceipt, HttpSession session,BindingResult result) {
		Users userTemp = (Users) session.getAttribute("userInSession"); //Obj User o null
        if(userTemp == null) {
            return "redirect:/login";
        }
        if(result.hasErrors()) {
        	List <ProductReceipt> productReceipts = productServ.allProductReceipts();
            model.addAttribute("productReceipts", productReceipts);
            return "productReceipts.jsp";
        }
        else {
        	try {
                // Verifica y guarda cada imagen
                if (!newReceipt.getProductImage1().isEmpty()) {
                    String ruta1 = guardarArchivo(newReceipt.getProductImage1());
                    newReceipt.setRutaProductImage1(ruta1);  // Guarda la ruta relativa
                }
                if (!newReceipt.getProductImage2().isEmpty()) {
                    String ruta2 = guardarArchivo(newReceipt.getProductImage2());
                    newReceipt.setRutaProductImage2(ruta2);
                }
                if (!newReceipt.getProductImage3().isEmpty()) {
                    String ruta3 = guardarArchivo(newReceipt.getProductImage3());
                    newReceipt.setRutaProductImage3(ruta3);
                }
                if (!newReceipt.getProductImage4().isEmpty()) {
                    String ruta4 = guardarArchivo(newReceipt.getProductImage4());
                    newReceipt.setRutaProductImage4(ruta4);
                }

                // Guardar el producto en la base de datos (lógica según tu implementación)
                // productoService.save(producto); 

            } catch (IOException e) {
                e.printStackTrace();
                return "error";  // Manejar el error en caso de fallo
            }
        	productServ.saveProductReceipt(newReceipt);
        	return "redirect:/";
        }
	}
	
	
	private String guardarArchivo(MultipartFile archivo) throws IOException {
	    // Obtén el nombre del archivo
	    String nombreArchivo = archivo.getOriginalFilename();

	    // Obtén la ruta de la carpeta "productImages" en el directorio "static"
	    String rutaDirectorio = new File("src/main/resources/static/productImages").getAbsolutePath();

	    // Construye la ruta completa donde se guardará el archivo
	    Path rutaCompleta = Paths.get(rutaDirectorio + File.separator + nombreArchivo);

	    // Guarda el archivo en la carpeta "productImages"
	    Files.copy(archivo.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

	    // Retorna la ruta relativa para acceder a la imagen desde el frontend
	    return "/productImages/" + nombreArchivo;
		}
	
	
	
	@GetMapping("/productReceipt/{id}")
	public String productReceipt(@PathVariable("id") long id,Model model,@ModelAttribute("newReceipt") ProductReceipt newReceipt) {
		ProductReceipt productReceipt = productServ.productReceipt(id);
		List<Customers> customers = customerServ.allCostumers();
		model.addAttribute("productReceipt",productReceipt);
		model.addAttribute("customers",customers);
		return "productReceipt.jsp";
	}
	
	@DeleteMapping("/deleteProductReceipt/{id}")
	public String deleteProductReceipt(@RequestParam("id") Long id, HttpSession session) {
        Users userTemp = (Users) session.getAttribute("userInSession"); //Obj User o null
        if(userTemp == null) {
            return "redirect:/";
        }
        
        productServ.deleteProductReceipt(id);
        return "redirect:/";
	}
	
	@GetMapping("/productReceiptsByNumber")
	public String productReceiptsByNumber(Model model,@RequestParam("filter") String filter) {
		List<ProductReceipt> productReceipts = productServ.allProductReceiptByNumber(filter);
		model.addAttribute("productReceipts",productReceipts);  
		return "productReceipts.jsp";
	}
	
	@GetMapping("/productReceiptsByProduct")
	public String productReceiptsByProduct(Model model,@RequestParam("filter") String filter) {
		List<ProductReceipt> productReceipts = productServ.allProductReceiptByProduct(filter);
		model.addAttribute("productReceipts",productReceipts);  
		return "productReceipts.jsp";
	}
	
	@GetMapping("/productReceiptsByReceivedBy")
	public String productReceiptsByReceivedBy(Model model,@RequestParam("filter") String filter) {
		List<ProductReceipt> productReceipts = productServ.allProductReceiptByReceivedBy(filter);
		model.addAttribute("productReceipts",productReceipts);  
		return "productReceipts.jsp";
	}
	
	@GetMapping("/productReceiptsByDate")
	public String productReceiptsByDate(Model model,@RequestParam("filter") Date filter) {
		List<ProductReceipt> productReceipts = productServ.allProductReceiptByDate(filter);
		model.addAttribute("productReceipts",productReceipts);  
		return "productReceipts.jsp";
	}
	
	@GetMapping("/productReceiptsByDateAsc")
	public String productReceiptsByDateAsc(Model model) {
		List<ProductReceipt> productReceipts = productServ.allProductReceiptByDateAsc();
		model.addAttribute("productReceipts",productReceipts);  
		return "productReceipts.jsp";
	}
	
	@GetMapping("/productReceiptsByDateDesc")
	public String productReceiptsByDateDesc(Model model) {
		List<ProductReceipt> productReceipts = productServ.allProductReceiptByDateDesc();
		model.addAttribute("productReceipts",productReceipts);  
		return "productReceipts.jsp";
	}

}