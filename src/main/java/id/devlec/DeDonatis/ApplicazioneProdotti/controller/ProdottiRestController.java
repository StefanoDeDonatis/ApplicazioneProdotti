package id.devlec.DeDonatis.ApplicazioneProdotti.controller;

import id.devlec.DeDonatis.ApplicazioneProdotti.avviso.ProdottoNonTrovato;
import id.devlec.DeDonatis.ApplicazioneProdotti.model.Prodotto;
import id.devlec.DeDonatis.ApplicazioneProdotti.persistence.ProdottiRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
public class ProdottiRestController {
    private ProdottiRepository repository;

    ProdottiRestController(ProdottiRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/prodotti")
    private List<Prodotto> tuttiProdotti() {
        return repository.findAll();
    }

    @GetMapping("/prodotto/{id}")
    public Prodotto trovaProdottoConID(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ProdottoNonTrovato(id));
    }

    @GetMapping("/prodotto/nome")
    public List<Prodotto> trovaProdottoConNome(@RequestParam String nome) {
        return repository.findAllBynome(nome);
    }

    @GetMapping("/prodotto/quantita")
    public List<Prodotto> trovaProdottoConQuantita(@RequestParam float quantita) {
        return repository.findAllByquantita(quantita);
    }

    @PostMapping("/prodotto")
    public Prodotto inserisciNuovoProdotto(@RequestBody Prodotto nuovoProdotto) {
        return repository.save(nuovoProdotto);
    }

    @PutMapping("/prodotto/{id}")
    public Prodotto aggiornaProdotto(@PathVariable Long id, @RequestBody Prodotto prodotto) {
        return repository.save(prodotto);
    }

    @DeleteMapping("/prodotto/{id}")
    void eliminaProdotto(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/prodotti/ricercaprezzo")
    public List<Prodotto> ricercaPerPrezzo(@RequestParam(name = "min") float min,
                                           @RequestParam(name = "max") float max) {
        return repository.findByprezzoBetween(min, max);
    }

    @GetMapping("/prodotti/ricercadataacquisto")
    public List<Prodotto> ricercaPerDataDiAcquisto(@RequestParam(name = "datada") @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                           Date datada,
                                                   @RequestParam(name = "dataa") @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                           Date dataa) {
        return repository.findBydataacquistoBetween(datada, dataa);
    }

    @GetMapping("/prodotti/ricercadata")
    public List<Prodotto> ricercaPerDataDiAcquisto(@RequestParam(name = "data") @DateTimeFormat(pattern = "dd-MM-yyyy") Date data) {
        return repository.findBydataacquisto(data);
    }

    @GetMapping("/prodotti/quantitainferiore")
    public List<Prodotto> ricercaPerQuantitaInferirore(@RequestParam(name = "max") float max) {
        return repository.findByquantitaLessThan(max);
    }

    @GetMapping("/ordinemento")
    public List<Prodotto> ricercaOrdinata() {
        return repository.findByOrderByQuantita();
    }

    @PostMapping("/caricafile")
    public String caricaFile(@RequestParam("file") MultipartFile file){

        String infoFile = file.getOriginalFilename()+" "+file.getContentType();
        String conFormat =String.format("%s-%s",file.getOriginalFilename(),file.getContentType());

        return conFormat;
    }

}
