/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author cristian
 */
@RestController
@Service
public class CinemaAPIController {

    @Autowired
    CinemaServices cinemaServices;

    @RequestMapping( value = "/cinemas", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetCinemas(){
        try {
            return new ResponseEntity<>(cinemaServices.getAllCinemas(), HttpStatus.ACCEPTED);
        } catch (Exception e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error consultando los cinemas", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/cinemas/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getCinema(@PathVariable String name){
        try{
            return new ResponseEntity<>(cinemaServices.getCinemaByName(name), HttpStatus.ACCEPTED);
        }catch (Exception e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error consultando cinema", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/cinemas/{name}/{date}", method = RequestMethod.GET)
    public ResponseEntity<?> getCinemaFunctionsByDate(@PathVariable String name, @PathVariable String date){
        try{
            return new ResponseEntity<>(cinemaServices.getFunctionsbyCinemaAndDate(name, date), HttpStatus.ACCEPTED);
        }catch (Exception e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error consultando funciones", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/cinemas/{name}/{date}/{moviename}", method = RequestMethod.GET)
    public ResponseEntity<?> getCinemaFunction(@PathVariable String name, @PathVariable String date, @PathVariable String moviename) {
        try {
            return new ResponseEntity<>(cinemaServices.getFunction(name, date, moviename), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error consultando función", HttpStatus.NOT_FOUND);
        }
    }

    
}