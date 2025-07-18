package com.jedmo.etymology_web;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EtymologyController {

    private final EtymologyGraph graph;

    public EtymologyController() {
        this.graph = new EtymologyGraph();
        try {
            this.graph.loadFromResource("etymology.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/path")
    public List<String> getPath(@RequestParam String start, @RequestParam String end) {
        return graph.findShortestPath(start, end);
    }
}
