package com.eric.dairy.controller;

import com.eric.dairy.model.Dairy;
import com.eric.dairy.model.DairyRespo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/dairy")
public class DairyController {
    private final DairyRespo dairyRespo;
    private int threshold = 3000;

    @Autowired
    public DairyController(DairyRespo dairyRespo) {
        this.dairyRespo = dairyRespo;
    }

    //Get the calories in total group by date
    @GetMapping("/days")
    @ResponseBody
    public List<Object[]> getDays() {
        List<Object[]> days = new ArrayList<>();
        if (dairyRespo != null) {
            days = dairyRespo.findCaloriesByDate();
        }
        return days;
    }

    //Get all dairies if put id the select the id if put description then select with the description
    // format /dairy/?id=&description=
    @GetMapping("/")
    @ResponseBody
    public List<Dairy> getDairy(@RequestParam("id") String id, @RequestParam("description") String description) {
        List<Dairy> dairies = new ArrayList<Dairy>();
        if (dairyRespo != null) {
            if (id != null && !id.isEmpty() && (description == null || description.isEmpty())) {
                dairies = Collections.singletonList(dairyRespo.findById(Integer.valueOf(id)).get());
            } else if (description != null && !description.isEmpty()) {
                dairies = dairyRespo.findByDescription(description);
            } else {
                dairies = dairyRespo.findAll();
            }
        }
        return dairies;
    }


    //Create new dairy
    //date: Date, description: String, calories: int
    @PostMapping("/")
    @ResponseBody
    public String postDairy(@RequestBody Dairy dairy) {
        if (dairyRespo != null) {
            dairyRespo.save(dairy);
            dairyRespo.flush();

            int value = dairyRespo.findCaloryByDate(dairy.getDate().toString());
            if (value > threshold) {
                List<Dairy> dairies = dairyRespo.findAllByDate(dairy.getDate());
                for (Dairy d : dairies) {
                    d.setMarked(true);
                    dairyRespo.save(d);
                }
            } else {
                dairy.setMarked(false);
                dairyRespo.save(dairy);
            }
            dairyRespo.flush();
        }
        return "1";
    }

    //Delete dairy by id
    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteDairy(@PathVariable("id") String id) {
        int dairyId = Integer.valueOf(id);
        if (dairyRespo != null) {
            dairyRespo.deleteById(dairyId);
        }
        return "1";
    }


}
