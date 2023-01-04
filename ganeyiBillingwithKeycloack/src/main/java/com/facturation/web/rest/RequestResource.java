package com.facturation.web.rest;

import com.facturation.repository.RequestRepository;
import com.facturation.repository.FactureRepository;
import com.facturation.service.FactureService;
import com.facturation.repository.ClientRepository;
import com.facturation.service.RequestService;
import com.facturation.service.dto.RequestDTO;
import com.facturation.service.dto.FactureDTO;
import com.facturation.domain.Request;
import com.facturation.domain.Client;
import com.facturation.domain.Facture;
import com.facturation.domain.Api;
import com.facturation.domain.Product;
import java.time.LocalDate;
import com.facturation.domain.enumeration.TypeFacturation;
import com.facturation.domain.enumeration.FactureStatus;
import com.facturation.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import org.springframework.scheduling.annotation.Scheduled;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.MessagingException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.springframework.core.io.FileSystemResource;
import java.io.File;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import com.itextpdf.html2pdf.HtmlConverter;
import java.io.ByteArrayOutputStream;
import com.itextpdf.html2pdf.ConverterProperties;
import java.io.IOException;

/**
 * REST controller for managing {@link com.facturation.domain.Request}.
 */
@RestController
@RequestMapping("/api")
public class RequestResource {

    private final Logger log = LoggerFactory.getLogger(RequestResource.class);

    private static final String ENTITY_NAME = "request";

    int month1 = LocalDate.now().getMonthValue();
    int year1 = LocalDate.now().getYear();
    int month2 = month1;
    int year2 = year1;
    int numero = 0;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequestService requestService;

    private final RequestRepository requestRepository;

    private final FactureRepository factureRepository;

    private final ClientRepository clientRepository;

    private final FactureService factureService;

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;


    public RequestResource(RequestService requestService, FactureService factureService, RequestRepository requestRepository, FactureRepository factureRepository, ClientRepository clientRepository, JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.requestService = requestService;
        this.requestRepository = requestRepository;
        this.factureRepository = factureRepository;
        this.clientRepository = clientRepository;
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.factureService = factureService;
    }

    /**
     * {@code POST  /requests} : Create a new request.
     *
     * @param requestDTO the requestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requestDTO, or with status {@code 400 (Bad Request)} if the request has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/requests")
    public ResponseEntity<RequestDTO> createRequest(@RequestBody RequestDTO requestDTO) throws URISyntaxException {
        log.debug("REST request to save Request : {}", requestDTO);
        if (requestDTO.getId() != null) {
            throw new BadRequestAlertException("A new request cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RequestDTO result = requestService.save(requestDTO);
        return ResponseEntity
            .created(new URI("/api/requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /requests/:id} : Updates an existing request.
     *
     * @param id the id of the requestDTO to save.
     * @param requestDTO the requestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestDTO,
     * or with status {@code 400 (Bad Request)} if the requestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/requests/{id}")
    public ResponseEntity<RequestDTO> updateRequest(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody RequestDTO requestDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Request : {}, {}", id, requestDTO);
        if (requestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, requestDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!requestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RequestDTO result = requestService.update(requestDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requestDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /requests/:id} : Partial updates given fields of an existing request, field will ignore if it is null
     *
     * @param id the id of the requestDTO to save.
     * @param requestDTO the requestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestDTO,
     * or with status {@code 400 (Bad Request)} if the requestDTO is not valid,
     * or with status {@code 404 (Not Found)} if the requestDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the requestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/requests/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RequestDTO> partialUpdateRequest(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody RequestDTO requestDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Request partially : {}, {}", id, requestDTO);
        if (requestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, requestDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!requestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RequestDTO> result = requestService.partialUpdate(requestDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requestDTO.getId())
        );
    }

    /**
     * {@code GET  /requests} : get all the requests.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requests in body.
     */
    @GetMapping("/requests")
    public ResponseEntity<List<RequestDTO>> getAllRequests(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Requests");
        Page<RequestDTO> page = requestService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /requests/:id} : get the "id" request.
     *
     * @param id the id of the requestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/requests/{id}")
    public ResponseEntity<RequestDTO> getRequest(@PathVariable String id) {
        log.debug("REST request to get Request : {}", id);
        Optional<RequestDTO> requestDTO = requestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requestDTO);
    }

    /**
     * {@code DELETE  /requests/:id} : delete the "id" request.
     *
     * @param id the id of the requestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/requests/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable String id) {
        log.debug("REST request to delete Request : {}", id);
        requestService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    

    @Scheduled(cron="0 0 0 1 1/1 *")
    @GetMapping("generate/invoices/bymonth")
    public ModelAndView createbillingbymonth() throws MessagingException,IOException{
        List<Client> allclients = clientRepository.findAll();
        LocalDate localDate = LocalDate.now();
        Facture facture = new Facture();
        ModelAndView mav = new ModelAndView("facture");
        List<Api> apis = new ArrayList();
        List<Product> produits = new ArrayList();
        if (month1 == 12){
            year2 = year1 + 1;
            month2 = 1;
        }
        else{
            year2 = year1;
            month2 = month1 +1;
        }
        LocalDate date1 = LocalDate.of(year1,month1,1);
        LocalDate date2 = LocalDate.of(year2,month2,1);
        /*Boucle qui va générer une facture pour chaque client */
        for(int i = 0;i<allclients.size();i++) {
            List<Request> requests  = requestService.filterRequestsByDate(date1, date2, allclients.get(i));
            TypeFacturation periodique = TypeFacturation.FACTURATION_PAR_PERIODE;
            FactureStatus status = FactureStatus.NON_PAYE;
            
            Double total = 0.0;
            Double soustotal = 0.0;
            /*Boucle qui permet d'avoir les apis correspondant à chaque requete */
            for(int j = 0;j<requests.size();j++) {
                apis.add(requests.get(j).getApi());
            }
            /*Boucle qui permet les produit liés aux apis  */
            for(int k = 0;k<apis.size();k++) {
                produits.add(apis.get(k).getProduct());
            }
            /*Boucle qui permet de calculer le sous total en ajoutant le prix de chaque produit */
            for (int y = 0;y<produits.size();y++) {
                soustotal = soustotal + produits.get(y).getPrice();
            }

            Set<Product> setofProducts  = new HashSet<Product>(produits);
            /*On calcule le total en ajoutant la tva */
            total = soustotal + (0.18*soustotal);
            /*On update les infos de la facture à envoyer */
            facture.setRabais(12.0);
            facture.setTva(18.0);
            facture.setSousTotal(soustotal);
            facture.setTotal(total);
            facture.setTypeFacturation(periodique);
            facture.setStatus(status);
            facture.setDate(localDate);
            facture.setClient(allclients.get(i));
            facture.setProducts(setofProducts);
            facture.setNumero("001 AB00" + numero);
            factureRepository.save(facture);

            /*On paramètre le template du mail à envoyer et les configurations à faire */
            Client client = allclients.get(i);
            mav.addObject("facture", facture);
            Context context = new Context();
            Context facture_context = new Context();
            context.setVariable("client",client);
            facture_context.setVariable("facture",facture);
            String process = templateEngine.process("email", context);
            String facture_process = templateEngine.process("facture", facture_context); 
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            
            /*Récupération du template facture.html */
            File html = new File("C:/Users/6430U/Desktop/ganeyiBillingwithKeycloack/src/main/resources/templates/facture.html");
            HtmlConverter.convertToPdf(html,new File("C:/Users/6430U/Desktop/facturepdf.pdf"));
            File attachment = new File("C:/Users/6430U/Desktop/facturepdf.pdf");
            FileSystemResource file = new FileSystemResource(attachment);
            helper.setSubject("Réception de facture ");
            helper.setText(process, true);
            helper.setTo(allclients.get(i).getEmail());
            helper.addAttachment("FactureBaamtu",attachment);
            mailSender.send(mimeMessage);
            System.out.println("Sent message successfully....");
           
        } 
        /*On incrémente numéro après l'exection de la boucle */
        numero +=1; 
        return mav;
        
    }
    
}
