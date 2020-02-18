package be.belfius.readtag.service;


import be.belfius.readtag.domain.JsonLine;
import be.belfius.readtag.repository.JsonLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JsonLineService {

    @Autowired
    JsonLineRepository repository;

    public String createJsonLine(JsonLine jsonLine){
//        Optional<JsonLine> jsonLine = repository.findById(jsonLine.getId());

        repository.save(jsonLine);
        return "line saved";
    }
//    public JsonLineEntity createOrJsonLine(JsonLineEntity entity){
//        Optional<JsonLineEntity> jline = repository.findById(entity.getId())
//    }

    public void save(JsonLine jsonLine) {
        repository.save(jsonLine);
    }
    public Optional<JsonLine> getJsonLineById(Long id){
        Optional<JsonLine> jsonLine = repository.findById(id);
        return jsonLine;
    }
}
