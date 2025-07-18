package com.jedmo.etymology_web;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class EtymologyGraph {
    private Map<String, Set<String>> graph = new HashMap<>();

    // Load CSV from a filesystem path (not classpath resource)
    public void loadFromCSV(String filepath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            br.readLine(); // skip header line

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = splitCSVLine(line);
                if (parts.length < 7) continue;

                String lang = parts[1].trim();
                String term = parts[2].trim().toLowerCase();
                String relatedLang = parts[5].trim();
                String relatedTerm = parts[6].trim().toLowerCase();

                if ("English".equalsIgnoreCase(lang) && !term.isEmpty() && relatedTerm.length() > 0) {
                    if ("English".equalsIgnoreCase(relatedLang) || relatedLang.isEmpty()) {
                        addEdge(term, relatedTerm);
                    }
                }
            }
        }
    }

    // Load CSV from classpath resource (e.g. src/main/resources/etymology.csv)
   public void loadFromResource(String filename) throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
    if (inputStream == null) {
        throw new FileNotFoundException("Resource not found: " + filename);
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        String line;
        int count = 0;
        while ((line = reader.readLine()) != null) {
            count++;
            System.out.println("Line " + count + ": " + line);
            String[] parts = line.split(",");
            if (parts.length >= 2) {
                String from = parts[0].trim();
                String to = parts[1].trim();
                addEdge(from, to);
            }
        }
        System.out.println("Total lines read from resource: " + count);
        System.out.println("Total nodes in graph after loading: " + graph.size());
    }
}


    private String[] splitCSVLine(String line) {
        List<String> tokens = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == '"') inQuotes = !inQuotes;
            else if (c == ',' && !inQuotes) {
                tokens.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString());
        return tokens.toArray(new String[0]);
    }

    private void addEdge(String word1, String word2) {
        graph.computeIfAbsent(word1, k -> new HashSet<>()).add(word2);
        graph.computeIfAbsent(word2, k -> new HashSet<>()).add(word1);
    }

    public List<String> findShortestPath(String start, String end) {
        start = start.toLowerCase();
        end = end.toLowerCase();

        if (!graph.containsKey(start)) {
            System.out.println("Word not found in graph: " + start);
            return null;
        }
        if (!graph.containsKey(end)) {
            System.out.println("Word not found in graph: " + end);
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(end)) {
                List<String> path = new LinkedList<>();
                for (String at = end; at != null; at = parent.get(at)) {
                    path.add(0, at);
                }
                return path;
            }

            for (String neighbor : graph.getOrDefault(current, Collections.emptySet())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        System.out.println("No path found between " + start + " and " + end);
        return null;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java com.jedmo.etymology_web.EtymologyGraph <csv_file_path> <word1> <word2>");
            return;
        }

        String filename = args[0];
        String word1 = args[1];
        String word2 = args[2];

        EtymologyGraph graph = new EtymologyGraph();
        try {
            System.out.println("Loading data...");
            graph.loadFromCSV(filename);
            System.out.println("Data loaded.");

            List<String> path = graph.findShortestPath(word1, word2);
            if (path != null) {
                System.out.println("Shortest path from '" + word1 + "' to '" + word2 + "':");
                System.out.println(String.join(" -> ", path));
            }
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }
    }

    public int getNodeCount() {
        return graph.size();
    }
}
