package com.cjhetzle;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        ObjectMapper objectMapper = new ObjectMapper();
        List<FlashCard> flashCards = null;
        try {
            URL resource = Main.class.getClassLoader().getResource("java-flashcards.json");
            if (resource == null) {
                throw new IllegalArgumentException("file not found!");
            } else {
                System.out.println(resource);
            }
            flashCards = objectMapper.readValue(new File(resource.toURI()), new TypeReference<List<FlashCard>>() {
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Random rand = new Random();
        FlashCard flashCard = flashCards.get(rand.nextInt(flashCards.size()));
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println(String.format("%d. %s", flashCard.getNumber(), flashCard.getQuestion()));
            System.out.println("...");
            scan.nextLine();
            System.out.println(flashCard.getAnswer());
            System.out.println("Press enter to continue...");
            flashCard = flashCards.get(rand.nextInt(flashCards.size()));
            scan.nextLine();
        }
    }
}