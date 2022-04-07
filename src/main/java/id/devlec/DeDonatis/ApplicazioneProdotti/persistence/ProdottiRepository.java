package id.devlec.DeDonatis.ApplicazioneProdotti.persistence;

import id.devlec.DeDonatis.ApplicazioneProdotti.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProdottiRepository extends JpaRepository<Prodotto, Long> {

    List<Prodotto> findAllBynome(String nome);
    List<Prodotto> findAllByquantita(float quantita);
    List<Prodotto> findByprezzoBetween(float min,float max);
    List<Prodotto> findBydataacquistoBetween(Date datada,Date dataa);
    List<Prodotto> findByquantitaLessThan(float max);
    List<Prodotto> findByOrderByQuantita();
    List<Prodotto> findBydataacquisto(Date data);
}
