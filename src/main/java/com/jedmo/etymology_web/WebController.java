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
            System.out.println("✅ Graph loaded with " + graph.getNodeCount() + " nodes.");
            if (graph.getNodeCount() == 0) {
                System.out.println("⚠️ Warning: Graph has 0 nodes. Check your CSV file and loading logic.");
            }
        } catch (IOException e) {
            System.err.println("Failed to load graph data:");
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public String home() {
        return "index";  // This serves src/main/resources/templates/index.html
    }

    @PostMapping("/find-path")
    public String findPath(@RequestParam String start, @RequestParam String end, Model model) {
        List<String> path = graph.findShortestPath(start, end);
        model.addAttribute("path", path);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        if (path == null) {
            model.addAttribute("message", "No path found or words not in graph.");
        } else {
            model.addAttribute("message", "Shortest path found!");
        }
        return "index";
    }
}
