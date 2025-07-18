package com.jedmo.etymology_web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class WebController {

    private final EtymologyGraph graph;

    public WebController() {
        this.graph = new EtymologyGraph();
        try {
            this.graph.loadFromResource("etymology.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public String home() {
        return "index";  // This will serve src/main/resources/templates/index.html
    }

    @PostMapping("/find-path")
    public String findPath(@RequestParam String start, @RequestParam String end, Model model) {
        List<String> path = graph.findShortestPath(start, end);
        model.addAttribute("path", path);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        return "index";
    }
}
