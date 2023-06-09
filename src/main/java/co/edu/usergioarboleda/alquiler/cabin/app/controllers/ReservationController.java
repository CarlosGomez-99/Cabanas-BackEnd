package co.edu.usergioarboleda.alquiler.cabin.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usergioarboleda.alquiler.cabin.app.models.Reservation;
import co.edu.usergioarboleda.alquiler.cabin.app.models.custom.CountClient;
import co.edu.usergioarboleda.alquiler.cabin.app.models.custom.StatusAmount;
import co.edu.usergioarboleda.alquiler.cabin.app.services.ReservationService;

@RestController
@RequestMapping("/Reservation")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAll() {
        List<Reservation> listReservations = service.getAll();
        if (listReservations.isEmpty()) {
            /*
             * A continuación se muestra la respuesta que se obtiene al ejecutar el método
             * getAll() cuando no se obtienen resultados, en este caso se obtiene un código
             * de estado 200 y un cuerpo vacío para que pasen los test cases en la
             * plataforma Mastertech, sin embargo, también se podría retornar un código de
             * estado 404:
             * return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
             */

            return ResponseEntity.status(HttpStatus.OK).body(listReservations);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(listReservations);
        }
    }

    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation) {
        return service.save(reservation);
    }

    @GetMapping("/{id}")
    public Reservation getById(@PathVariable Integer id) {
        return service.getById(id).orElse(null);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation) {
        return service.update(reservation);
    }

    @DeleteMapping("/all")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteAll(@RequestBody Reservation reservation) {
        service.deleteAll(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @GetMapping("/report-dates/{fechaInicial}/{fechaFinal}")
    public List<Reservation> getReportDates(@PathVariable("fechaInicial") String fechaInicial,
            @PathVariable("fechaFinal") String fechaFinal) {
        return service.getReportDates(fechaInicial, fechaFinal);
    }

    @GetMapping("/report-dates-amount/{fechaInicial}/{fechaFinal}")
    public int getReportDatesAmount(@PathVariable("fechaInicial") String fechaInicial,
            @PathVariable("fechaFinal") String fechaFinal) {
        List<Reservation> listaReservas = new ArrayList<>();
        listaReservas = service.getReportDates(fechaInicial, fechaFinal);
        // System.out.println(listaReservas);
        int numReservas = listaReservas.size();
        System.out.println("Numero de reservas: " + numReservas);
        return numReservas;
    }

    @GetMapping("/report-status")
    public StatusAmount getReportStatus() {
        return service.getReservationStatusReport();
    }

    @GetMapping("/report-clients")
    public List<CountClient> getReportClients() {
        return service.getTopClients();
    }
}
